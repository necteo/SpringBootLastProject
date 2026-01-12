package com.sist.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.BoardMapper;
import com.sist.web.mapper.BoardReplyMapper;
import com.sist.web.vo.BoardReplyVO;
import com.sist.web.vo.BoardVO;

import lombok.RequiredArgsConstructor;
/*
 * 	 VO / Entity / DTO / Record
 * 	 |		|		|	   | getter => Kotlin (data)
 * 					getter/setter
 * 		  테이블 제어 (컬럼과 일치)
 * 			=> JPA
 *  불변 (setter가 없다)
 *  *** VO / DTO
 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService, BoardRelpyService {
	
	private final BoardMapper mapper;
	private final BoardReplyMapper bMapper;
	private final int ROW_SIZE = 10;

	@Override
	public Map<String, Object> boardListData(String page) {
		int curpage = (page == null) ? 1: Integer.parseInt(page);
		int start = (curpage - 1) * ROW_SIZE;
		List<BoardVO> list = mapper.boardListData(start);
		int count = mapper.boardTotalCount();
		int totalpage = (int) (Math.ceil(1.0 * count / ROW_SIZE));
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("curpage", curpage);
		map.put("totalpage", totalpage);
		map.put("count", count - start);
		map.put("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return map;
	}

	@Override
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
		// return 생략
	}

	@Override
	public BoardVO boardDetailData(int no) {
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}

	@Override
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}

	@Override
	public String boardUpdate(BoardVO vo) {
		String res = "no";
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if (db_pwd.equals(vo.getPwd())) {
			mapper.boardUpdate(vo);
			res = "yes";
		}
		return res;
	}

	@Override
	public boolean boardDelete(int no, String pwd) {
		boolean res = false;
		String db_pwd = mapper.boardGetPassword(no);
		if (db_pwd.equals(pwd)) {
			mapper.boardDelete(no);
			res = true; // 종료 => void인 경우에는 마지막줄에 자동 추가
		}
		return res;
	}
	/////////// 댓글
	// BI
	@Override
	public Map<String, Object> boardReplyListData(int bno) {
		return commonsData(bno);
	}

	@Override
	public Map<String, Object> boardReplyInsert(BoardReplyVO vo) {
		bMapper.boardReplyInsert(vo);
		int count = bMapper.boardReplyCount(vo.getBno());
		mapper.boardReplyCountUpdate(vo.getBno(), count + 1);
		return commonsData(vo.getBno());
	}

	@Override
	public Map<String, Object> boardReplyDelete(int bno, int no) {
		bMapper.boardReplyDelete(no);
		return commonsData(bno);
	}
	
	public Map<String, Object> commonsData(int bno) {
		List<BoardReplyVO> list = bMapper.boardReplyListData(bno);
		int count = bMapper.boardReplyCount(bno);
		mapper.boardReplyCountUpdate(bno, count);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		return map;
	}

}
