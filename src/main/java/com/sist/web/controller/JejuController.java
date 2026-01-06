package com.sist.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.JejuService;

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
		jService.jejuListData(page, cno, model);
		model.addAttribute("main_jsp", "../jeju/list.jsp");
		return "main/main";
	}
	
	@GetMapping("find")
	public String jeju_find(Model model) {
		model.addAttribute("main_jsp", "../jeju/jeju_find.jsp");
		return "main/main";
	}

}
