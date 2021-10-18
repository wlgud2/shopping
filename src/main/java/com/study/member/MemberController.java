package com.study.member;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.utility.Utility;

@Controller
public class MemberController {// 브라우저에서 요청 받음->서비스로 보냄->서비스에서 mapper로 접근->mapper가 db로 접근
	@Autowired
	@Qualifier("com.study.member.MemberServiceImpl") // 어떤 객체를 memberservice에 주입할 지 선언해줘야함(qualifier)
	private MemberService service;// 컨트롤러는 서비스를 사용, 서비스는 mapper를 사용

	@GetMapping("/")
	public String home() {

		return "/home";
	}

	@GetMapping("/member/agree") // tiles에 definition만들기
	public String agree() {// signup 누르면 회원 약관 보여주는 페이지
		return "/member/agree";
	}

	@PostMapping("/member/createForm") // agreement.jsp에서 post방식으로 보내기 때문에
	public String create() {// 회원가입 창
		return "/member/create";// return되는건 다 tiles에서 찾는다
	}

	@PostMapping("member/create")
	public String create(MemberDTO dto) throws IOException {// 회원가입 정보 보내기
		String upDir = new ClassPathResource("/static/member/storage").getFile().getAbsolutePath();
		String fname = Utility.saveFileSpring(dto.getFnameMF(), upDir);// getFnameMF: 업데이트된 파일 참조, upDir: 올리기
		int size = (int) dto.getFnameMF().getSize();
		if (size > 0) {
			dto.setFname(fname);
		} else {// 파일 안올렸을 때
			dto.setFname("member.jpg");// 임의의 사진
		}

		if (service.create(dto) > 0) {// 성공, create빨간 줄 눌러서 memberservice에 create 만들기
			return "redirect:/";// 기본페이지(index)로 이동
		} else {
			return "error";
		}
	}

	@PostMapping("/member/login") // 매개변수 받아서 로그인 처리
	public String login(@RequestParam Map<String, String> map, // map으로 아이디, 비번 받을 거기 때문에 requestparam 사용
			HttpSession session, HttpServletResponse response, Model model) {
		int cnt = service.loginCheck(map);// 아이디 비번으로 로그인이 됐는지 확인//service에 메소드 생성
		if (cnt > 0) {// 회원이면
			String grade = service.getGrade(map.get("id"));// service에 메소드 생성
			session.setAttribute("id", map.get("id"));// session에 저장
			session.setAttribute("grade", grade);
			// Cookie 저장,id저장 여부 및 id
			Cookie cookie = null;
			String c_id = map.get("c_id");
			if (c_id != null) {// id 기억 check했을 때
				cookie = new Cookie("c_id", c_id); // c_id=> Y
				cookie.setMaxAge(60 * 60 * 24 * 365);// 1년
				response.addCookie(cookie);// 요청지(client:브라우저 컴) 쿠키 저장

				cookie = new Cookie("c_id_val", map.get("id"));
				cookie.setMaxAge(60 * 60 * 24 * 365);// 1년
				response.addCookie(cookie);// 요청지(client:브라우저 컴) 쿠키 저장
			} else {// check 안했을 때(해제했을 때)
				cookie = new Cookie("c_id", ""); // 체크 돼있는 동안 저장됐던 쿠키 삭제
				cookie.setMaxAge(0);
				response.addCookie(cookie);

				cookie = new Cookie("c_id_val", "");// 쿠키 삭제
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		} // if cnt>0 end
		if (cnt > 0) {
			return "redirect:/";// 기본페이지로 이동
		} else {
			model.addAttribute("msg", "아이디 또는 비밀번호를 잘못 입력 했거나 <br>회원이 아닙니다. 회원가입 하세요");
			return "/member/errorMsg";
		}
	}

	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();// invalidate: session에 저장된 거 한번에 지움(하나씩 지우는건 remove)
		return "redirect:/";
	}

	@GetMapping("/member/update") // 회원 수정페이지 보이기
	public String update(String id, HttpSession session, Model model) {
		if (id == null) {
			id = (String) session.getAttribute("id");// session에는 관리자 id 들어가있다.(?)//id가져오기
		}
		MemberDTO dto = service.read(id);
		model.addAttribute("dto", dto);

		// 목록에서 들어가면 id가 파라메터로 넘어감
		return "/member/update";// 보여질 페이지. tiles에 definition 추가
	}

	@PostMapping("/member/update")
	public String update(MemberDTO dto, Model model) {
		int cnt = service.update(dto);

		if (cnt == 1) {
			model.addAttribute("id", dto.getId());
			return "redirect:./read";
		} else {
			return "error";
		}
	}

	@GetMapping("/member/read")
	public String read(String id, HttpSession session, Model model) {
		if (id == null) {
			id = (String) session.getAttribute("id");// session에 들어가있는 id가져오기
		}
		MemberDTO dto = service.read(id);
		model.addAttribute("dto", dto);

		return "/member/read";// tiles에 추가
	}

	@GetMapping("/member/updateFile") // 사진수정
	public String updateFile() {
		return "/member/updateFile";// tiles에 이름이 updateFile인 definition 추가
	}

	@PostMapping("/member/updateFile")
	public String updateFile(MultipartFile fnameMF, String oldfile, HttpSession session, HttpServletRequest request)
			throws IOException {
		String basePath = new ClassPathResource("/static/member/storage").getFile().getAbsolutePath();

		if (oldfile != null && !oldfile.equals("member.jpg")) { // 기존파일이 member.jpg(기본)가 아닐 때
			Utility.deleteFile(basePath, oldfile);// 원본파일 삭제
		}
		// storage에 변경 파일 저장(업로드)
		Map map = new HashMap();
		map.put("id", session.getAttribute("id"));
		map.put("fname", Utility.saveFileSpring(fnameMF, basePath));

		// 디비에 파일명 변경
		int cnt = service.updateFile(map);

		if (cnt == 1) {
			return "redirect:./read";
		} else {
			return "./error";
		}
	}

	@GetMapping("/member/download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 절대경로
		String dir = new ClassPathResource("/static/member/storage").getFile().getAbsolutePath();
		String filename = request.getParameter("filename");
		byte[] files = FileUtils.readFileToByteArray(new File(dir, filename));
		response.setHeader("Content-disposition",
				"attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
		// Content-Transfer-Encoding : 전송 데이타의 body를 인코딩한 방법을 표시함.
		response.setHeader("Content-Transfer-Encoding", "binary");
		/**
		 * Content-Disposition가 attachment와 함게 설정되었다면 'Save As'로 파일을 제안하는지 여부에 따라 브라우저가
		 * 실행한다.
		 */
		response.setContentType("application/octet-stream");
		response.setContentLength(files.length);
		response.getOutputStream().write(files);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	@RequestMapping("/admin/list") // 회원 목록(관리자권한)//검색도 해야해서 get이 아니라 requestmapping
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
		int recordPerPage = 3;// 한페이지당 보여줄 레코드갯수

		// DB에서 가져올 순번-----------------
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		int total = service.total(map);

		List<MemberDTO> list = service.list(map);

		String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

		// request에 Model사용 결과 담는다
		request.setAttribute("list", list);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);

		return "/member/list";
	}

	@GetMapping("/member/login") // 로그인 페이지
	public String login(HttpServletRequest request) {
		/*----쿠키설정 내용시작(id 저장)--------------------*/
		String c_id = ""; // ID 저장 여부를 저장하는 변수, Y
		String c_id_val = ""; // ID 값 저장

		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];

				if (cookie.getName().equals("c_id")) {
					c_id = cookie.getValue(); // Y
				} else if (cookie.getName().equals("c_id_val")) {
					c_id_val = cookie.getValue(); // user1...
				}
			}
		}
		/*----쿠키설정 내용 끝----------------------------*/
		request.setAttribute("c_id", c_id);
		request.setAttribute("c_id_val", c_id_val);// 얘가 있으면 loginform의 input태그에 value값에 넣어줄거다

		return "/member/login";// tiles에 definition 생성하기
	}

	@GetMapping(value = "/member/idcheck", produces = "application/json;charset=utf-8") // 여러 개할 때는 value이용(produce로 타입
	// 선언해줘야함)
	@ResponseBody // 뷰에 대한 이름이 아니라, 그 데이터 자체를 json형식으로 return
	public Map<String, String> idcheck(String id) {// 이렇게 쓰면 json 형식으로 응답될 수 있다
		Map<String, String> map = new HashMap<String, String>();
		int cnt = service.duplicatedId(id);// duplicatedid에 빨간줄로 memberservice에 생성됨
		if (cnt > 0) {// 중복된 데이터일 때
			map.put("str", id + "는 중복되어서 사용할 수 없습니다");
		} else {
			map.put("str", id + "는 사용 가능합니다");
		}
		return map;// responsebody 때문에 이거 자체가 return 됨
	}

	@GetMapping(value = "/member/emailcheck", produces = "application/json;charset=utf-8") // 위에 idcheck 복사해서 사용
	@ResponseBody
	public Map<String, String> emailcheck(String email) {
		Map<String, String> map = new HashMap<String, String>();
		int cnt = service.duplicatedEmail(email);// create duplicated~로 service에 추가
		if (cnt > 0) {// 중복된 데이터일 때
			map.put("str", email + "는 중복되어서 사용할 수 없습니다");
		} else {
			map.put("str", email + "는 사용 가능합니다");
		}
		return map;
	}

	@GetMapping("/member/mypage")
	public String mypage(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");

		if (id == null) {
			return "redirect: ./login/";
		} else {
			MemberDTO dto = service.mypage(id);
			model.addAttribute("dto", dto);
			return "/member/mypage";
		}
	}

}