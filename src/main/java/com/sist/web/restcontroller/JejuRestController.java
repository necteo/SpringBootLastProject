package com.sist.web.restcontroller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.JejuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jeju/")
public class JejuRestController {
	
	private final JejuService jService;
	
	@GetMapping("find_vue")
	public ResponseEntity<Map<String, Object>> jeju_find_vue(
			@RequestParam("page") int page, @RequestParam("selected") int selected, @RequestParam("fd") String fd) {
		Map<String, Object> map = null;
		try {
			// JSON으로 묶어서 => 브라우저로 전송
			map = jService.jejuFindData(page, selected, fd);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			// INTERNAL_SERVER_ERROR:500 => error를 브라우저로 전송
		}
		return new ResponseEntity<>(map, HttpStatus.OK); // OK:200 => 정상수
	}

}
