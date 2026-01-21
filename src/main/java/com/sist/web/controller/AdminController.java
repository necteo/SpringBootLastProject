package com.sist.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.service.NoticeService;
import com.sist.web.vo.NoticeVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final NoticeService nService;
	
	@GetMapping("/admin-main")
	public String admin_main(Model model) {
		model.addAttribute("admin_jsp", "../admin/admin_home.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	
	@GetMapping("/admin-reserve")
	public String admin_reserve(Model model) {
		model.addAttribute("admin_jsp", "../admin/admin_reserve.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	
	@GetMapping("/notice-list")
	public String adminNoticeList(@RequestParam(name = "page", required = false) String page, Model model) {
		Map<String, Object> map = nService.noticeListData(page);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("curpage", map.get("curpage"));
		model.addAttribute("totalpage", map.get("totalpage"));
		model.addAttribute("count", map.get("count"));
		
		model.addAttribute("admin_jsp", "../admin/notice_list.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	
	@GetMapping("/notice-insert")
	public String adminNoticeInsert(Model model) {
		model.addAttribute("admin_jsp", "../admin/notice_insert.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	
	@PostMapping("/notice-insert-ok")
	public String adminNoticeInsertOk(@ModelAttribute NoticeVO vo, HttpServletRequest request) throws Exception {
		String uploadDir = request.getServletContext().getRealPath("/upload");
		nService.noticeInsert(vo, uploadDir);
		return "redirect:notice-list";
	}
	
	@GetMapping("/notice-detail")
	public String adminNoticeDetail(@RequestParam("no") int no, Model model) {
		NoticeVO vo = nService.noticeDetailData(no);
		model.addAttribute("vo", vo);
		
		model.addAttribute("admin_jsp", "../admin/notice_detail.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	// => forward => Model
	// => redirect => RedirectAttributes => request가 초기화
	@GetMapping("/notice-delete")
	public String adminNoticeDelete(@RequestParam("no") int no, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/upload");
		nService.noticeDelete(no, path);
		return "redirect:notice-list";
	}
	
	@GetMapping("/notice-update")
	public String adminNoticeUpdate(@RequestParam("no") int no, Model model) {
		NoticeVO vo = nService.noticeUpdateData(no);
		model.addAttribute("vo", vo);
		
		model.addAttribute("admin_jsp", "../admin/notice_update.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	
	@PostMapping("/notice-update-ok")
	public String adminNoticeUpdateOk(@ModelAttribute NoticeVO vo, RedirectAttributes ra) {
		nService.noticeUpdate(vo);
		ra.addAttribute("no", vo.getNo());
		return "redirect:notice-detail"; // ?no=1
	}

}
