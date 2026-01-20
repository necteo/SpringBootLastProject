package com.sist.web.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.ReserveService;
import com.sist.web.vo.ReserveVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageRestController {
	
	private final ReserveService rService;
	
	@GetMapping("/reserve-list-vue")
	public ResponseEntity<List<ReserveVO>> mypage_reserve_list(HttpSession session) {
		List<ReserveVO> list = null;
		try {
			String id = (String) session.getAttribute("userid");
			list = rService.reserveMyData(id);
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
