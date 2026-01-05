package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.SeoulVO;
/*			 /seoul/location
 * 	  user요청 --------------- DispatcherServlet
 * 								   | 위임
 * 								 HandlerMapping
 * 								   | URI주소 찾기 => @GetMapping
 * 												  @PostMapping
 * 												  @PutMapping
 * 												  @DeleteMapping
 * 									  => 인증 => 권한 부여
 * 								   | 밑에 있는 메소드 호출
 * 										   |
 * 										Service
 * 										   |
 * 										Mapper => 수정시에 의존석을 약하게
 * 										   |
 * 										 오라클
 * 								 DispatcherServlet
 * 								   |
 * 								 ViewResolver
 * 								   |
 * 								  JSP
 */
public interface SeoulService {
	
	public List<SeoulVO> seoulListData(Map<String, Integer> map);
	public int seoulTotalPage(int contenttype);
	public SeoulVO seoulAttractionDetailData(int contentid);

}
