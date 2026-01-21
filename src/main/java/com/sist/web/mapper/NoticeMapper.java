package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.web.vo.NoticeVO;

@Mapper
public interface NoticeMapper {
	
	@Select("SELECT no, type, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') dbday, hit "
		  + "FROM notice_3 "
		  + "ORDER BY no DESC "
		  + "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<NoticeVO> noticeListData(int start);
	
	@Select("SELECT COUNT(*) FROM notice_3")
	public int noticeTotalCount();
	
	@Insert("INSERT INTO notice_3(no, type, subject, content, filename, filecount) "
		  + "VALUES(no3_no_seq.nextval, #{type}, #{subject}, #{content}, #{filename}, #{filecount})")
	public void noticeInsert(NoticeVO vo);
	
	@Update("UPDATE notice_3 "
		  + "SET hit = hit + 1 "
		  + "WHERE no = #{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT * FROM notice_3 "
		  + "WHERE no = #{no}")
	public NoticeVO noticeDetailData(int no);
	
	@Select("SELECT filename, filecount "
		  + "FROM notice_3 "
		  + "WHERE no = #{no}")
	public NoticeVO noticeFileInfoData(int no);
	
	@Delete("DELETE FROM notice_3 "
		  + "WHERE no = #{no}")
	public void noticeDelete(int no);
	
	@Update("UPDATE notice_3 "
		  + "SET subject = #{subject}, content = #{content}, type = #{type} "
		  + "WHERE no = #{no}")
	public void noticeUpdate(NoticeVO vo);

}
