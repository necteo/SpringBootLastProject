package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.BoardReplyVO;

@Mapper
public interface BoardReplyMapper {
	
	@Select("SELECT no, bno, id, name, sex, msg, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') dbday "
		  + "FROM board_reply_3 "
		  + "WHERE bno = #{bno} "
		  + "ORDER BY no")
	public List<BoardReplyVO> boardReplyListData(int bno);
	
	@Select("SELECT COUNT(*) FROM board_reply_3 "
		  + "WHERE bno = #{bno}")
	public int boardReplyCount(int bno);
	
	@Insert("INSERT INTO board_reply_3 VALUES(br3_no_seq.nextval, #{bno}, #{id}, #{name}, #{sex}, #{msg}, SYSDATE)")
	public void boardReplyInsert(BoardReplyVO vo);
	
	@Delete("DELETE FROM board_reply_3 "
		  + "WHERE no = #{no}")
	public void boardReplyDelete(int no);

}
