package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.BusanService;
import com.sist.web.vo.BusanVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/busan/")
public class BusanController {
	
	private final BusanService bService;
	
	@GetMapping("list")
	public String busan_list(
			@RequestParam(name = "page", required = false) String page, 
			@RequestParam("cno") int cno, Model model) {
		// include가 되는 파일을 올린다 => request를 공유할 수 있다
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		Map<String, Integer> map = new HashMap<>();
		map.put("start", (curpage - 1) * 12);
		map.put("contenttype", cno);
		List<BusanVO> list = bService.busanListData(map);
		for (BusanVO vo : list) {
			String[] addrs = vo.getAddress().split(" ");
			vo.setAddress(addrs[0] + " " + addrs[1]);
		}
		int totalpage = bService.busanTotalPage(cno);
		
		final int BLOCK = 10;
		int startPage = (curpage - 1) / BLOCK * BLOCK + 1;
		int endPage = (curpage - 1) / BLOCK * BLOCK + BLOCK;
		if (endPage > totalpage)
			endPage = totalpage;
		
		String name = switch (cno) {
			case 12 -> "부산 관광지";
			case 14 -> "부산 문화시설";
			case 15 -> "부산 축제 & 공연";
			case 32 -> "부산 숙박";
			case 38 -> "부산 쇼핑";
			case 39 -> "부산 음식";
			default -> throw new IllegalArgumentException("Unexpected value: " + cno);
		};
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("cno", cno);
		model.addAttribute("name", name);
		
		model.addAttribute("main_jsp", "../busan/list.jsp");
		return "main/main";
	}

}
