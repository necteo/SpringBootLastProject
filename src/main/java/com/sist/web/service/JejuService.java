package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.JejuVO;

public interface JejuService {
	
	public List<JejuVO> jejuListData(Map<String, Integer> map);
	public int jejuTotalPage(int contenttype);

}
