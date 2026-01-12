package com.sist.web.restcontroller;

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

import com.sist.web.service.BoardRelpyService;
import com.sist.web.vo.BoardReplyVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
public class BoardReplyRestController {
	
	private final BoardRelpyService bService;
	
	@GetMapping("/list_vue")
	public ResponseEntity<Map<String, Object>> reply_list_vue(@RequestParam("bno") int bno) {
		Map<String, Object> map = null;
		try {
			map = bService.boardReplyListData(bno);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/insert_vue")
	public ResponseEntity<Map<String, Object>> reply_insert_vue(@RequestBody BoardReplyVO vo, HttpSession session) {
		Map<String, Object> map = null;
		try {
			vo.setId((String) session.getAttribute("userid"));
			vo.setName((String) session.getAttribute("username"));
			vo.setSex((String) session.getAttribute("sex"));
			map = bService.boardReplyInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete_vue")
	public ResponseEntity<Map<String, Object>> reply_delete_vue(@RequestParam("bno") int bno, 
			@RequestParam("no") int no, HttpSession session) {
		Map<String, Object> map = null;
		try {
			map = bService.boardReplyDelete(bno, no);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
