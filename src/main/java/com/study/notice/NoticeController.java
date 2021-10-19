package com.study.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.utility.Utility;

@Controller
public class NoticeController {
	@Autowired
	@Qualifier("com.study.notice.NoticeServiceImpl")
	private NoticeService service;
	
	@GetMapping("/notice/create")
	public String create() {

		return "/notice/create";
	}

	@PostMapping("/notice/create")
	public String create(NoticeDTO dto) {

		if (service.create(dto) == 1) {
//			response.setCharacterEncoding("EUC-KR");//alert에 한글 띄우기 위한 코드
//			response.getWriter().println("<script>alert ('글 등록 완료');</script>");//java에서 jsp로 alert창 띄우기
//			response.getWriter().flush();
//			response.getWriter().close();
			return "redirect:./list";
		} else {
			return "./error";
		}
	}

	@RequestMapping("/notice/list")
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
		int sno = ((nowPage - 1) * recordPerPage)+1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("cnt", recordPerPage);

		int total = service.total(map);

		List<NoticeDTO> list = service.list(map);

		String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

		// request에 notice사용 결과 담는다
		request.setAttribute("list", list);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);

		// view페이지 리턴
		return "/notice/list";
	}

	@GetMapping("/notice/read")
	public String read(int noticeno, Model model) {

		service.upViewcnt(noticeno);

		NoticeDTO dto = service.read(noticeno);

		String content = dto.getContent().replaceAll("\r\n", "<br>");

		dto.setContent(content);

		model.addAttribute("dto", dto);

		return "/notice/read";
	}

	@GetMapping("/notice/update")
	public String update(int noticeno, Model model) {

		model.addAttribute("dto", service.read(noticeno));

		return "/notice/update";
	}

	@PostMapping("/notice/update")
	public String update(NoticeDTO dto) {

		Map map = new HashMap();
		map.put("noticeno", dto.getNoticeno());
		map.put("passwd", dto.getPasswd());
		int pcnt = service.passcheck(map);

		int cnt = 0;
		if (pcnt == 1) {

			cnt = service.update(dto);
		}

		if (pcnt != 1) {
			return "./notice/passwdError";
		} else if (cnt == 1) {
			return "redirect:./list";
		} else {
			return "./notice/error";
		}

	}

	@GetMapping("/notice/delete")
	public String delete() {

		return "/notice/delete";
	}

	@PostMapping("/notice/delete")
	public String delete(HttpServletRequest request, int noticeno, String passwd) {

		Map map = new HashMap();
		map.put("noticeno", noticeno);
		map.put("passwd", passwd);
		int pcnt = service.passcheck(map);

		int cnt = 0;
		if (pcnt == 1) {

			cnt = service.delete(noticeno);
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
