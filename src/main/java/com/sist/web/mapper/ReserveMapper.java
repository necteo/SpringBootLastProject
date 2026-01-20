package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

@Mapper
public interface ReserveMapper {
	
	@Select("<script> "
		  + "SELECT no, contentid, image1, title, x, y, address "
		  + "FROM seoultravel "
		  + "WHERE contenttype = 39 "
		  + "<if test=\"address != 'all'\"> "
		  + "  AND address LIKE '%' || #{address} || '%' "
		  + "</if> "
		  + "ORDER BY contentid "
		  + "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY"
		  + "</script>")
	public List<SeoulVO> seoulReserveData(Map<String, Object> map);

	@Select("<script>"
		  + "SELECT CEIL(COUNT(*) / 10) "
		  + "FROM seoultravel "
		  + "WHERE contenttype = 39 "
		  + "<if test=\"address != 'all'\"> "
		  + "  AND address LIKE '%' || #{address} || '%' "
		  + "</if> "
		  + "</script>")
	public int seoulReserveTotalPage(String address);
	
	@Insert("INSERT INTO reserve_3(no, cno, id, rday, rtime, rinwon) "
		  + "VALUES(r3_no_seq.nextval, #{cno}, #{id}, #{rday}, #{rtime}, #{rinwon})")
	public void reserveInsert(ReserveVO vo);
	
	@Results({
		@Result(column = "title", property = "svo.title"),
		@Result(column = "image1", property = "svo.image1"),
		@Result(column = "address", property = "svo.address")
	})
	// vo.getSvo().setTitle()
	@Select("SELECT r.no, cno, rday, rtime, rinwon, TO_CHAR(regdate, 'YYYY-MM-DD') dbday, isReserve, title, image1, address "
		  + "FROM reserve_3 r, seoultravel s "
		  + "WHERE r.cno = s.contentid "
		  + "AND id = #{id} "
		  + "ORDER BY no DESC")
	public List<ReserveVO> reserveMyData(String id);
	
	@Results({
		@Result(column = "title", property = "svo.title"),
		@Result(column = "image1", property = "svo.image1"),
		@Result(column = "address", property = "svo.address")
	})
	// vo.getSvo().setTitle()
	@Select("SELECT r.no, id, cno, rday, rtime, rinwon, TO_CHAR(regdate, 'YYYY-MM-DD') dbday, isReserve, title, image1, address "
		  + "FROM reserve_3 r, seoultravel s "
		  + "WHERE r.cno = s.contentid "
		  + "ORDER BY no DESC")
	public List<ReserveVO> reserveAdminData();
	
	

}
