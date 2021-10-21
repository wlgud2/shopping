package com.study.cart;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.utility.Utility;

@Controller
public class CartController {

	@Autowired
	@Qualifier("com.study.cart.CartServiceImpl")
	private CartService service;

	@RequestMapping("/cart/list")
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

		List<CartDTO> list = service.list(map);

		String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

		// request에 Model사용 결과 담는다
		request.setAttribute("list", list);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);

		return "/cart/list";

	}

	@PostMapping("/cart/create")
	public String create(CartDTO dto, HttpServletRequest request) throws IOException {

		if (service.create(dto) > 0) {
			return "redirect:./list";
		} else {
			return "error";
		}
	}

	@GetMapping("/cart/create")
	public String create() {
		return "/cart/create";
	}

	@GetMapping("/cart/delete/{orderno}")
	public String delete(@PathVariable("orderno") int orderno) {
		return "/cart/delete";
	}

	@PostMapping("/cart/delete")
	public String delete(HttpServletRequest request, int orderno, String id, String passwd) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("passwd", passwd);
		int pcnt = service.passcheck(map);//관리자 패스워드 검사

		int cnt = 0;
		if (pcnt == 1) {

			cnt = service.delete(orderno);
		}

		if (pcnt != 1) {
			return "../notice/passwdError";
		} else if (cnt == 1) {
			return "redirect:./list";
		} else {
			return "../notice/error";
		}
	}
}
