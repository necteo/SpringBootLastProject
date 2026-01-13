package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.CommonsReplyService;
import com.sist.web.vo.CommonsReplyVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commons")
// 추상화 => ChatModel => GPT / GEN / En / Oll
public class CommonsReplyRestController {
	
	private final CommonsReplyService cService;
	
	@GetMapping("/list_vue")
	public ResponseEntity<Map<String, Object>> commons_list_vue(@RequestParam("page") int page, @RequestParam("cno") int cno) {
		Map<String, Object> map = null;
		try {
			map = cService.commonsReplyListData(page, cno);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/insert_vue")
	public ResponseEntity<Map<String, Object>> commons_insert_vue(@RequestBody CommonsReplyVo vo, HttpSession session) {
		Map<String, Object> map = null;
		try {
			vo.setId((String) session.getAttribute("userid"));
			vo.setName((String) session.getAttribute("username"));
			vo.setSex((String) session.getAttribute("sex"));
			map = cService.commonsReplyInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete_vue")
	public ResponseEntity<Map<String, Object>> commons_delete_vue(@RequestParam("no") int no, 
			@RequestParam("cno") int cno, @RequestParam("page") int page) {
		Map<String, Object> map = null;
		try {
			map = cService.commonsDelete(no, page, cno);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
