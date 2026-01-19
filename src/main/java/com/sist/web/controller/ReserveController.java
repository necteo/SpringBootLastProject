package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// JavaMail / Kafka ==> WebSocket
@Controller
@RequestMapping("/reserve")
public class ReserveController {
	
	@GetMapping("/reserve_main")
	public String reserve_main(Model model) {
		model.addAttribute("main_jsp", "../reserve/reserve_main.jsp");
		return "main/main";
	}

}
