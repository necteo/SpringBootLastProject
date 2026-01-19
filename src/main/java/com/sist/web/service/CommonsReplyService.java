package com.sist.web.service;

import java.util.Map;

import com.sist.web.vo.CommonsReplyVo;

public interface CommonsReplyService {
	
	public Map<String, Object> commonsReplyListData(int page, int cno);
	
	public Map<String, Object> commonsReplyInsert(CommonsReplyVo vo);
	
	public Map<String, Object> commonsDelete(int no, int page, int cno);
	
	public Map<String, Object> commonsMsgUpdate(CommonsReplyVo vo);
	
	public Map<String, Object> commonsReplyReplyInsert(CommonsReplyVo vo);

}
