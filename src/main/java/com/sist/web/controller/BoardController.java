package com.sist.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.web.service.BoardService;
import com.sist.web.vo.BoardVO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


// RouterController => Pinia에서 router
// 화면만 변경 => 실제 RestController에서 주로 처리
@Controller
@RequiredArgsConstructor // 생성자를 통해서 @Autowired
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService bService;
	// GET / POST => @RequestMapping
	@GetMapping("/list")
	// 사용자가 어떤 값을 보내는지 => 주고 받기 (Socket) C/S
	public String board_list(@RequestParam(name = "page", required = false) String page, Model model) {
		Map<String, Object> map = bService.boardListData(page);
		// 어떤 데이터 전송 => 데이터가 많은 경우 : BLOCK별 / 이전 ~ 다음
		// 요청에 대한 처리 결과
		model.addAttribute("list", map.get("list"));
		model.addAttribute("curpage", map.get("curpage"));
		model.addAttribute("totalpage", map.get("totalpage"));
		model.addAttribute("count", map.get("count"));
		model.addAttribute("today", map.get("today"));
		// ------------------------------------------------
		// 출력
		model.addAttribute("main_jsp", "../board/list.jsp");
		return "main/main";
	}
	
	@GetMapping("/insert")
	public String board_insert(Model model) {
		model.addAttribute("main_jsp", "../board/insert.jsp");
		return "main/main";
	}
	/*
	 * 	 @RequestParam => 단일값
	 * 	 @ModelAttribute => vo단위로 값을 받는 경우
	 * 	 @RequestBody => @RestController
	 * 					 JSON => 객체변환
	 * 	 ---------------------- 사용자가 전송한 값을 받는 경우
	 * 	 브라우저 전송
	 * 	 Model / HttpServletRequest
	 * 	  |				 |
	 * 	 일반			   interceptor / aop / task
	 * 	
	 * 	 JSON => @RestController : 리턴형 (Map , List , String , VO)
	 */
	@PostMapping("/insert_ok")
	public String board_insert_ok(@ModelAttribute BoardVO vo) {
		bService.boardInsert(vo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/detail")
	public String board_detail(@RequestParam("no") int no, Model model) {
		BoardVO vo = bService.boardDetailData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../board/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("/update")
	public String board_update(@RequestParam("no") int no, Model model) {
		BoardVO vo = bService.boardUpdateData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../board/update.jsp");
		return "main/main";
	}
	
	@PostMapping(value = "/update_ok", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String board_update_ok(@ModelAttribute BoardVO vo) {
		String res = "";
		String s = bService.boardUpdate(vo);
		if (s.equals("yes")) {
			res = "<script>"
				  + "location.href = 'detail?no=" + vo.getNo() + "'"
				+ "</script>";
		} else {
			res = "<script>"
				  + "alert('비밀번호가 틀립니다!!');"
				  + "history.back();"
				+ "</script>";
		}
		return res;
	}
	
	@GetMapping("/delete")
	public String board_delete(@RequestParam("no") int no, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("main_jsp", "../board/delete.jsp");
		return "main/main";
	}
	
	@PostMapping(value = "/delete_ok", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String board_delete_ok(@RequestParam("no") int no, @RequestParam("pwd") String pwd) {
		String res = "";
		boolean b= bService.boardDelete(no, pwd);
		if (b) {
			res = "<script>"
				  + "location.href = 'list';"
				+ "</script>";
		} else {
			res = "<script>"
				  + "alert('비밀번호가 틀립니다!!');"
				  + "history.back();"
				+ "</script>";
		}
		return res;
	}

}
