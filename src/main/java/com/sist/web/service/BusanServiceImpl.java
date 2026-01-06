package com.sist.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sist.web.mapper.BusanMapper;
import com.sist.web.vo.BusanVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusanServiceImpl implements BusanService {
	
	private final BusanMapper mapper;
	private final int ROW_SIZE = 12;
	private final int BLOCK = 10;

	@Override
	public void busanListData(String page, int cno, Model model) {
		int curpage = (page == null) ? 1 : Integer.parseInt(page);
		Map<String, Integer> map = new HashMap<>();
		map.put("start", (curpage - 1) * ROW_SIZE);
		map.put("contenttype", cno);
		List<BusanVO> list = mapper.busanListData(map);
		splitAddress(list);
		int totalpage = mapper.busanTotalPage(cno);
		
		Map<String, Integer> pagination = getPagination(curpage, totalpage);
		
		String name = getContentName(cno);
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", pagination.get("startPage"));
		model.addAttribute("endPage", pagination.get("endPage"));
		model.addAttribute("cno", cno);
		model.addAttribute("name", name);
	}
	
	@Override
	public Map<String, Object> busanFindData(int page, String address) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("start", (page - 1) * ROW_SIZE);
		map.put("address", address);
		List<BusanVO> list = mapper.busanFindData(map);
		splitAddress(list);
		int totalpage = mapper.busanFindTotalPage(address);
		
		Map<String, Integer> pagination = getPagination(page, totalpage);
		
		map = new HashMap<>();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", pagination.get("startPage"));
		map.put("endPage", pagination.get("endPage"));
		map.put("address", address);
		
		return map;
	}

	@Override
	public List<BusanVO> busanTop4Data() {
		return mapper.busanTop4Data();
	}
	
	public void splitAddress(List<BusanVO> list) {
		for (BusanVO vo : list) {
			String[] addrs = vo.getAddress().split(" ");
			vo.setAddress(addrs[0] + " " + addrs[1]);
		}
	}
	
	public Map<String, Integer> getPagination(int curpage, int totalpage) {
		int startPage = (curpage - 1) / BLOCK * BLOCK + 1;
		int endPage = (curpage - 1) / BLOCK * BLOCK + BLOCK;
		if (endPage > totalpage)
			endPage = totalpage;
		
		Map<String, Integer> map = new HashMap<>();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}
	
	public String getContentName(int cno) {
		return switch (cno) {
			case 12 -> "부산 관광지";
			case 14 -> "부산 문화시설";
			case 15 -> "부산 축제 & 공연";
			case 32 -> "부산 숙박";
			case 38 -> "부산 쇼핑";
			case 39 -> "부산 음식";
			default -> throw new IllegalArgumentException("Unexpected value: " + cno);
		};
	}

}
