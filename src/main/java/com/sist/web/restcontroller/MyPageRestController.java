package com.sist.web.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<List<ReserveVO>> mypageReserveList(HttpSession session) {
		List<ReserveVO> list = null;
		try {
			String id = (String) session.getAttribute("userid");
			list = rService.reserveMyData(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/reserve-cancel-vue")
	public ResponseEntity<List<ReserveVO>> mypageReserveCancel(@RequestParam("no") int no, HttpSession session) {
		List<ReserveVO> list = null;
		try {
			rService.reserveCancel(no);
			String id = (String) session.getAttribute("userid");
			list = rService.reserveMyData(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/reserve-detail-vue")
	public ResponseEntity<ReserveVO> mypageReserveDetail(@RequestParam("no") int no) {
		ReserveVO vo = null;
		try {
			vo = rService.reserveDetailData(no);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
}
