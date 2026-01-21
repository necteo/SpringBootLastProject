package com.sist.web.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.ReserveService;
import com.sist.web.vo.ReserveVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminRestController {
	
	private final ReserveService rService;
	private final SimpMessagingTemplate template;
	
	@GetMapping("/reserve-list-vue")
	public ResponseEntity<List<ReserveVO>> adminReserveList() {
		List<ReserveVO> list = null;
		try {
			list = rService.reserveAdminData();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/reserve-ok-vue")
	public ResponseEntity<List<ReserveVO>> adminReserveOk(@RequestParam("no") int no, @RequestParam("id") String id) {
		List<ReserveVO> list = null;
		try {
			rService.reserveOk(no);
			list = rService.reserveAdminData();
			
			template.convertAndSend("/sub/notice/" + id, "[🎉예약 승인] 예약이 완료되었습니다!!");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	} 
	
	@GetMapping("/reserve-delete-vue")
	public ResponseEntity<List<ReserveVO>> adminReserveDelete(@RequestParam("no") int no, @RequestParam("id") String id) {
		List<ReserveVO> list = null;
		try {
			rService.reserveDelete(no);
			list = rService.reserveAdminData();
			
			template.convertAndSend("/sub/notice/" + id, "[🎉예약 취소] 예약이 취소되었습니다!!");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	} 
	
}
