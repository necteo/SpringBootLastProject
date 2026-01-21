package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.ReserveVO;

public interface ReserveService {
	
	public List<String> timeRand();
	
	/*
	 *  @Select("SELECT no, contendid, image1, titl x, y address "
			  + "FROM seoultravel "
			  + "WHERE address LIKE '%' || #{address} || '%' "
			  + " AND contenttype = 39 "
			  + "ORDER BY contentid "
			  + "OFSET #{start} ROWS FETCH NEXT 10 ROWSONLY")
		@Select("SELECT CEIL(CONUT ? *)) "
			  + "FROM seoultravel "
			  + "WHERE address LIKE '%' || #{address} || '%' "
			  + " AND contenttype = 39")
	*/
	public Map<String, Object> seoulReserveData(int page, String address);
	
	public String reserveInsert(ReserveVO vo);
	
	public List<ReserveVO> reserveMyData(String id);
	
	public List<ReserveVO> reserveAdminData();
	
	public void reserveOk(int no);
	
	public void reserveCancel(int no);
	
	public void reserveDelete(int no);
	
	public ReserveVO reserveDetailData(int no);

}
