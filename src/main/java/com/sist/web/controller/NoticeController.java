package com.sist.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.NoticeService;
import com.sist.web.vo.NoticeVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
/*
 * 	 1. Spring-Boot
 * 		= ORM (MyBatis)
 * 				| - CRUD / 동적 쿼리
 * 				  - JOIN
 * 		= RestFul => Get / Post / Put / Delete
 * 		= FileUpload
 * 		= WebSocket : SockJS / Stomp => storm
 * 		= Kafka
 * 		= Task / JUnit
 * 	 -------------------------------------------
 * 		Batch / Spring AI : Ajax , Vue , React
 * 		=> ThymeLeaf
 * 		=> Oracle / MySQL,MariaDB
 * 					----- JPA
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	
	private final NoticeService nService;
	
	@GetMapping("/list")
	public String noticeList(@RequestParam(name = "page", required = false) String page, Model model) {
		Map<String, Object> map = nService.noticeListData(page);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("curpage", map.get("curpage"));
		model.addAttribute("totalpage", map.get("totalpage"));
		model.addAttribute("count", map.get("count"));
		
		model.addAttribute("main_jsp", "../notice/list.jsp");
		return "main/main";
	}
	
	@GetMapping("/detail")
	public String noticeDetail(@RequestParam("no") int no, Model model) {
		NoticeVO vo = nService.noticeDetailData(no);
		model.addAttribute("vo", vo);
		
		model.addAttribute("main_jsp", "../notice/detail.jsp");
		return "main/main";
	}

}
