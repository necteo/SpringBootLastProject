package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.sist.web.vo.JejuVO;

public interface JejuService {
	
	public void jejuListData(String page, int cno, Model model);
	public Map<String, Object> jejuFindData(int page, int selected, String fd) throws Exception;
	public List<JejuVO> jejuTop4Data();

}
