package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.JejuMapper;
import com.sist.web.vo.JejuVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JejuServiceImpl implements JejuService {
	
	private final JejuMapper mapper;

	@Override
	public List<JejuVO> jejuListData(Map<String, Integer> map) {
		return mapper.jejuListData(map);
	}

	@Override
	public int jejuTotalPage(int contenttype) {
		return mapper.jejuTotalPage(contenttype);
	}

}
