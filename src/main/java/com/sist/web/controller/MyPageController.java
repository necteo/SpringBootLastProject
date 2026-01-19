package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	@GetMapping("/mypage_main")
	public String mypage_main(Model model) {
		model.addAttribute("mypage_jsp", "../mypage/mypage_home.jsp");
		model.addAttribute("main_jsp", "../mypage/mypage_main.jsp");
		return "main/main";
	}

}
