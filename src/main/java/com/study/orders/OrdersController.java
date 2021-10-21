package com.study.orders;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.utility.Utility;

@Controller
public class OrdersController {

	@Autowired
	@Qualifier("com.study.orders.OrdersServiceImpl")
	private OrdersService service;

	@PostMapping("/orders/updateFile")
	public String updateFile(MultipartFile filenameMF, String oldfile, int orderno, HttpServletRequest request)
			throws IOException {
		String basePath = new ClassPathResource("/static/pstorage").getFile().getAbsolutePath();

		if (oldfile != null && !oldfile.equals("default.jpg")) { // 원본파일 삭제
			Utility.deleteFile(basePath, oldfile);
		}

		// pstorage에 변경 파일 저장
		Map map = new HashMap();
		map.put("orderno", orderno);
		map.put("fname", Utility.saveFileSpring(filenameMF, basePath));

		// 디비에 파일명 변경
		int cnt = service.updateFile(map);

		if (cnt == 1) {
			return "redirect:./list";
		} else {
			return "./error";
		}
	}

	@GetMapping("/admin/updateFile/{orderno}/{oldfile}")
	public String updateFileForm(@PathVariable("orderno") int orderno, @PathVariable("oldfile") String oldfile,
			Model model) {
		model.addAttribute("orderno", orderno);
		model.addAttribute("oldfile", oldfile);

		return "/orders/updateFile";
	}

	@RequestMapping("/orders/list")
	public String list(HttpServletRequest request) {
		// 검색관련------------------------
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));

		if (col.equals("total")) {
			word = "";
		}

		// 페이지관련-----------------------
		int nowPage = 1;// 현재 보고있는 페이지
		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		int recordPerPage = 5;// 한페이지당 보여줄 레코드갯수

		// DB에서 가져올 순번-----------------
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		int total = service.total(map);

		List<OrdersDTO> list = service.list(map);

		String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

		// request에 Model사용 결과 담는다
		request.setAttribute("list", list);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);

		return "/orders/list";

	}

	@PostMapping("/orders/update")
	public String update(OrdersDTO dto) {
		int cnt = service.update(dto);

		if (cnt == 1) {
			return "redirect:./list";
		} else {
			return "error";
		}
	}

	@GetMapping("/admin/update/{orderno}")
	public String update(@PathVariable("orderno") int orderno, Model model) {

		OrdersDTO dto = service.detail(orderno);

		model.addAttribute("dto", dto);

		return "/orders/update";

	}

	@PostMapping("/orders/create")
	public String create(OrdersDTO dto, HttpServletRequest request) throws IOException {
		String upDir = new ClassPathResource("/static/pstorage").getFile().getAbsolutePath();

		String fname = Utility.saveFileSpring(dto.getFilenameMF(), upDir);
		int size = (int) dto.getFilenameMF().getSize();

		if (size > 0) {
			dto.setFilename(fname);
		} else {
			dto.setFilename("default.jpg");
		}

		if (service.create(dto) > 0) {
			return "redirect:./list";
		} else {
			return "error";
		}
	}

	@GetMapping("/orders/create")
	public String create() {
		return "/orders/create";
	}

	@GetMapping("/orders/detail/{orderno}")
	public String detail(@PathVariable("orderno") int orderno, Model model) {
		model.addAttribute("dto", service.detail(orderno));
		return "/orders/detail";
	}

	@GetMapping("/orders/delete/{orderno}")
	public String delete(@PathVariable("orderno") int orderno) {
		return "/orders/delete";
	}

	@PostMapping("/orders/delete")
	public String delete(HttpServletRequest request, int orderno, String passwd) {

		int pcnt = service.passcheck(passwd);//관리자 패스워드 검사

		int cnt = 0;
		if (pcnt == 1) {

			cnt = service.delete(orderno);
		}

		if (pcnt != 1) {
			return "./notice/passwdError";
		} else if (cnt == 1) {
			return "redirect:./list";
		} else {
			return "./notice/error";
		}
	}
}
