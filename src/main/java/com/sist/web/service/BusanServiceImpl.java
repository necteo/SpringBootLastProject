package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.BusanMapper;
import com.sist.web.vo.BusanVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusanServiceImpl implements BusanService {
	
	private final BusanMapper mapper;

	@Override
	public List<BusanVO> busanListData(Map<String, Integer> map) {
		return mapper.busanListData(map);
	}

	@Override
	public int busanTotalPage(int contenttype) {
		return mapper.busanTotalPage(contenttype);
	}

}
