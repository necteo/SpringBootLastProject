package com.sist.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.web.mapper.CommonsReplyMapper;
import com.sist.web.vo.CommonsReplyVo;

import lombok.RequiredArgsConstructor;
/*
 * 	 Service : DataBase , OpenAI , AI => 요청처리 => Back-End의 중심
 * 	 => Security : BI
 * 
 *	 Repository : 오라클 / MySQL만 연동
 * 	 ------------------------------
 * 	 	| DispatcherServlet : 요청 / 응답 => FrontController
 * 	 Controller : 결과값을 받아서 브라우저로 전송
 * 		| Front-End = 조립 => 결과값 추출
 * 
 * 	 Component : 기타
 * 	   | AOP / Task / Batch
 * 
 * 	 = Controller : Router역할
 * 	 = RestController : 데이터 전송
 * 
 * 	 Server  ====== Client
 * 	   |
 * 	 순수하게 서버 역할만... (화면 제어가 없다) => Front에서 자체 처리
 * 										router => Vue/React
 * 	   var / val fun 함수명:String => data class Sawon(int age)
 */
@Service
@RequiredArgsConstructor
public class CommonsReplyServiceImpl implements CommonsReplyService {
	
	private final CommonsReplyMapper mapper;
	private final String[] tbl_name = {"", "seoultravel", "busantravel", "jejutravel"};
	private final int ROW_SIZE = 10;
	private final int BLOCK = 5;
	
	// 공통 모듈
	public Map<String, Object> commonsData(int page, int cno) {
		List<CommonsReplyVo> list = mapper.commonsReplyListData(cno, (page - 1) * ROW_SIZE);
		int totalpage = mapper.commonsReplyTotalPage(cno);
		
		int startPage = (page - 1) / BLOCK * BLOCK + 1;
		int endPage = (page - 1) / BLOCK * BLOCK + BLOCK;
		if (endPage > totalpage)
			endPage = totalpage;
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("cno", cno);
		map.put("count", list.size());
		return map;
	}

	@Override
	public Map<String, Object> commonsReplyListData(int page, int cno) {
		return commonsData(page, cno);
	}

	@Override
	public Map<String, Object> commonsReplyInsert(CommonsReplyVo vo) {
		mapper.commonsReplyInsert(vo);
		return commonsData(vo.getPage(), vo.getCno());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> commonsDelete(int no, int page, int cno) {
		CommonsReplyVo vo = mapper.commonsInfoData(no);
		if (vo.getDepth() == 0) {
			mapper.commonsDelete(no);
		} else {
			CommonsReplyVo rvo = new CommonsReplyVo();
			rvo.setNo(no);
			rvo.setMsg("관리자에 의해 삭제된 댓글입니다");
			mapper.commonsMsgUpdate(rvo);
		}
		mapper.commonsDepthDecrement(vo.getRoot());
		return commonsData(page, cno);
	}

}
