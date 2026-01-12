package com.sist.web.service;

import java.util.Map;

import com.sist.web.vo.BoardVO;

// 1. 게시판 / 댓글
public interface BoardService {
	
	public Map<String, Object> boardListData(String page);
	
	public void boardInsert(BoardVO vo);
	
	public BoardVO boardDetailData(int no);
	
	public BoardVO boardUpdateData(int no);
	
	public String boardUpdate(BoardVO vo);
	
	public boolean boardDelete(int no, String pwd);
	
	//public void boardReplyCountUpdate(int no);

}
