package com.sist.web.service;

import java.util.Map;

import com.sist.web.vo.BoardReplyVO;

public interface BoardRelpyService {
	
	public Map<String, Object> boardReplyListData(int bno);
	
	public Map<String, Object> boardReplyInsert(BoardReplyVO vo);
	
	public Map<String, Object> boardReplyDelete(int bno, int no);

}
