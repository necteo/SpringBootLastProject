package com.sist.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.web.mapper.MemberMapper;
import com.sist.web.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper mapper;

	@Override
	public int memberIdCheck(String userid) {
		return mapper.memberIdCheck(userid);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	// 일괄처리 => 성공 / 실패
	//				  rollback
	//			commit
	public void memberInsert(MemberVO vo) {
		mapper.memberInsert(vo);
		
	}

	@Override
	public void memberAuthorityInsert(String userid) {
		mapper.memberAuthorityInsert(userid);
	}

	@Override
	public MemberVO memberInfoData(String userid) {
		return mapper.memberInfoData(userid);
	}

}
