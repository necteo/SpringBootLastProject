package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.JejuService;
import com.sist.web.vo.JejuVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jeju/")
public class JejuController {
	
	private final JejuService jService;
	
	@GetMapping("list")
	public String jeju_list(
			@RequestParam(name = "page", required = false) String page, 
			@RequestParam("cno") int cno, Model model) {
		// include가 되는 파일을 올린다 => request를 공유할 수 있다
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		Map<String, Integer> map = new HashMap<>();
		map.put("start", (curpage - 1) * 12);
		map.put("contenttype", cno);
		List<JejuVO> list = jService.jejuListData(map);
		for (JejuVO vo : list) {
			String[] addrs = vo.getAddress().split(" ");
			vo.setAddress(addrs[0] + " " + addrs[1]);
		}
		int totalpage = jService.jejuTotalPage(cno);
		
		final int BLOCK = 10;
		int startPage = (curpage - 1) / BLOCK * BLOCK + 1;
		int endPage = (curpage - 1) / BLOCK * BLOCK + BLOCK;
		if (endPage > totalpage)
			endPage = totalpage;
		
		String name = switch (cno) {
			case 12 -> "제주 관광지";
			case 14 -> "제주 문화시설";
			case 15 -> "제주 축제 & 공연";
			case 32 -> "제주 숙박";
			case 38 -> "제주 쇼핑";
			case 39 -> "제주 음식";
			default -> throw new IllegalArgumentException("Unexpected value: " + cno);
		};
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("cno", cno);
		model.addAttribute("name", name);
		
		model.addAttribute("main_jsp", "../jeju/list.jsp");
		return "main/main";
	}

}
