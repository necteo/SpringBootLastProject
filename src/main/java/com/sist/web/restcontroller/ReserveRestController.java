package com.sist.web.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.ReserveService;
import com.sist.web.vo.ReserveVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveRestController {
	
	private final ReserveService rService;
	
	@GetMapping("/food-list-vue")
	public ResponseEntity<Map<String, Object>> reserve_food_list(@RequestParam("page") int page, 
			@RequestParam("address") String address) {
		Map<String, Object> map = null;
		try {
			map = rService.seoulReserveData(page, address);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	/*
	 * 	Vue
	 * 	  => mounted => onMounted(()=>{})
	 * 	  => watch => 설정된 데이터 변경시에만 호출
	 * 	  => computed => 완성된 데이터
	 * 	  => getters / actions / state
	 * 	  ---------------------------------
	 */
	@GetMapping("/time-list-vue")
	public ResponseEntity<List<String>> reserve_time_vue() {
		List<String> list = null;
		try {
			list = rService.timeRand();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/insert-vue")
	public ResponseEntity<String> reserve_insert(@RequestBody ReserveVO vo, HttpSession session) {
		String res = "";
		try {
			vo.setId((String) session.getAttribute("userid"));
			res = rService.reserveInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
}
