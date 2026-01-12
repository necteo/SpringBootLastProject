package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.BusanService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/busan")
public class BusanController {
	
	private final BusanService bService;
	
	@GetMapping("/list")
	public String busan_list(
			@RequestParam(name = "page", required = false) String page, 
			@RequestParam("cno") int cno, Model model) {
		bService.busanListData(page, cno, model);
		model.addAttribute("main_jsp", "../busan/list.jsp");
		return "main/main";
	}
	
	@GetMapping("/find")
	public String busan_find(Model model) {
		model.addAttribute("main_jsp", "../busan/busan_find.jsp");
		return "main/main";
	}

}
