package com.sist.web.service;

import java.util.Map;

import com.sist.web.vo.NoticeVO;

public interface NoticeService {
	
	public Map<String, Object> noticeListData(String page);
	
	public void noticeInsert(NoticeVO vo, String uploadDir) throws Exception;
	
	public NoticeVO noticeDetailData(int no);
	
	public void noticeDelete(int no, String path);
	
	public NoticeVO noticeUpdateData(int no);
	
	public void noticeUpdate(NoticeVO vo);

}
