package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

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

}
