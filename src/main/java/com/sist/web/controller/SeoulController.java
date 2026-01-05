package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.service.SeoulService;
import com.sist.web.vo.SeoulVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
/*
 * 	 MVC : 오라클 / 컨트롤러 / JSP
 * 		   -------------- Vue / React
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/seoul/")
public class SeoulController {
	
	private final SeoulService sService;
	
	@GetMapping("list")
	public String seoul_list(
			@RequestParam(name = "page", required = false) String page, 
			@RequestParam("cno") int cno, Model model) {
		// include가 되는 파일을 올린다 => request를 공유할 수 있다
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		Map<String, Integer> map = new HashMap<>();
		map.put("start", (curpage - 1) * 12);
		map.put("contenttype", cno);
		List<SeoulVO> list = sService.seoulListData(map);
		for (SeoulVO vo : list) {
			String[] addrs = vo.getAddress().split(" ");
			vo.setAddress(addrs[0] + " " + addrs[1]);
		}
		int totalpage = sService.seoulTotalPage(cno);
		
		final int BLOCK = 10;
		int startPage = (curpage - 1) / BLOCK * BLOCK + 1;
		int endPage = (curpage - 1) / BLOCK * BLOCK + BLOCK;
		if (endPage > totalpage)
			endPage = totalpage;
		
		String name = switch (cno) {
			case 12 -> "서울 관광지";
			case 14 -> "서울 문화시설";
			case 15 -> "서울 축제 & 공연";
			case 32 -> "서울 숙박";
			case 38 -> "서울 쇼핑";
			case 39 -> "서울 음식";
			default -> throw new IllegalArgumentException("Unexpected value: " + cno);
		};
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("cno", cno);
		model.addAttribute("name", name);
		
		model.addAttribute("main_jsp", "../seoul/list.jsp");
		return "main/main";
	}
	/*
	 * 	 sendRedirect : RedirectAttributes
	 * 	 forward : Model (HttpServletRequest)
	 */
	@GetMapping("detail_before")
	public String seoul_detail_before(
			@RequestParam("contentid") int contentid, @RequestParam("contenttype") int contenttype, 
			HttpServletResponse response, RedirectAttributes ra) {
		Cookie cookie = new Cookie("seoul_" + contentid, String.valueOf(contentid));
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);
		
		ra.addAttribute("cno", contentid);
		ra.addAttribute("type", contenttype);
		return "redirect:detail";
	}
	
	@GetMapping("detail")
	public String seoul_detail(@RequestParam("cno") int cno, @RequestParam("type") int type, Model model) {
		SeoulVO vo = new SeoulVO();
		String jsp = "";
		if (type == 12) {
			vo = sService.seoulAttractionDetailData(cno);
			jsp = "../seoul/attraction.jsp";
		} else if (type == 14) {
			jsp = "../seoul/culture.jsp";
		} else if (type == 15) {
			jsp = "../seoul/festival.jsp";
		} else if (type == 32) {
			jsp = "../seoul/stay.jsp";
		} else if (type == 38) {
			jsp = "../seoul/shopping.jsp";
		} else if (type == 39) {
			jsp = "../seoul/food_store.jsp";
		}
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", jsp);
		return "main/main";
	}

}
