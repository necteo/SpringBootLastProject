package com.sist.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.commons.AddressSplitter;
import com.sist.web.mapper.SeoulMapper;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;
// 데이터베이스 관련
@Service
@RequiredArgsConstructor
public class SeoulServiceImpl implements SeoulService {
	// @Autowired
	private final SeoulMapper mapper;
	
	/*@Autowired
	public SeoulServiceImpl(SeoulMapper mapper)
	{
		this.mapper=mapper;
	}*/

	@Override
	public List<SeoulVO> seoulListData(Map<String, Integer> map) {
		return mapper.seoulListData(map);
	}

	@Override
	public int seoulTotalPage(int contenttype) {
		return mapper.seoulTotalPage(contenttype);
	}

	@Override
	public SeoulVO seoulAttractionDetailData(int contentid) {
		mapper.seoulHitIncrement(contentid);
		return mapper.seoulAttractionDetailData(contentid);
	}

	@Override
	public Map<String, Object> seoulFindData(int page, String address) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("start", (page - 1) * 12);
		map.put("address", address);
		List<SeoulVO> list = mapper.seoulFindData(map);
		AddressSplitter.splitAddress(list);
		int totalpage = mapper.seoulFindTotalPage(address);
		
		final int BLOCK = 10;
		int startPage = (page - 1) / BLOCK * BLOCK + 1;
		int endPage = (page - 1) / BLOCK * BLOCK + BLOCK;
		if (endPage > totalpage)
			endPage = totalpage;
		
		map = new HashMap<>();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("address", address);
		
		return map;
	}

	@Override
	public List<SeoulVO> seoulTop5Data() {
		return mapper.seoulTop5Data();
	}

	@Override
	public SeoulVO seoulFestivalDetailData(int contentid) {
		return mapper.seoulFestivalDetailData(contentid);
	}

}
