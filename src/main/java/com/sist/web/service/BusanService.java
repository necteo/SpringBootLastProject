package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.sist.web.vo.BusanVO;

public interface BusanService {
	
	public void busanListData(String page, int cno, Model model);
	public Map<String, Object> busanFindData(int page, String address) throws Exception;
	public List<BusanVO> busanTop4Data();

}
