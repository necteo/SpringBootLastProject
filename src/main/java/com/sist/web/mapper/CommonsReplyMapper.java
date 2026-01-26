package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.web.vo.CommonsReplyVo;

@Mapper
public interface CommonsReplyMapper {
	
	@Select("SELECT no, cno, id, name, msg, sex, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') dbday, "
		  + "group_tab, group_id "
		  + "FROM commonsReply_3 "
		  + "WHERE cno = #{cno} "
		  + "ORDER BY group_id DESC, group_step "
		  + "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<CommonsReplyVo> commonsReplyListData(@Param("cno") int cno, @Param("start") int start);
	
	@Select("SELECT CEIL(COUNT(*) / 10) "
		  + "FROM commonsReply_3 "
		  + "WHERE cno = #{cno}")
	public int commonsReplyTotalPage(int cno);
	
	@Insert("INSERT INTO commonsReply_3(no, cno, id, name, sex, msg, group_id) "
		  + "VALUES(cs3_no_seq.nextval, #{cno}, #{id}, #{name}, #{sex}, #{msg}, "
		  + "(SELECT NVL(MAX(group_id) + 1, 1) FROM commonsReply_3))")
	public void commonsReplyInsert(CommonsReplyVo vo);
	
	@Select("SELECT root, depth, group_id, group_step FROM commonsReply_3 "
		  + "WHERE no = #{no}")
	public CommonsReplyVo commonsInfoData(int no);
	
	@Update("UPDATE commonsReply_3 "
		  + "SET msg = #{msg} "
		  + "WHERE no = #{no}")
	public void commonsMsgUpdate(CommonsReplyVo vo);
	
	@Delete("DELETE FROM commonsReply_3 WHERE group_id = #{group_id}")
	public void commonsAllDelete(int group_id);

	@Delete("DELETE FROM commonsReply_3 WHERE no = #{no}")
	public void commonsMyDelete(int no);
	
	@Update("UPDATE commonsReply_3 "
		  + "SET depth = depth - 1 "
		  + "WHERE no = #{no}")
	public void commonsDepthDecrement(int no);
	
	@Select("SELECT id, group_id, group_step, group_tab "
		  + "FROM commonsReply_3 "
		  + "WHERE no = #{no}")
	public CommonsReplyVo commonsReplyParentData(int no);
	
	@Update("UPDATE commonsReply_3 "
		  + "SET group_step = group_step + 1 "
		  + "WHERE group_id = #{group_id} AND group_step > #{group_step}")
	public void commonsGroupStepIncrement(CommonsReplyVo vo);
	
	@Insert("INSERT INTO commonsReply_3 "
		  + "VALUES(cs3_no_seq.nextval, #{cno}, #{id}, #{name}, #{sex}, #{msg}, "
		  + "#{group_id}, #{group_step}, #{group_tab}, #{root}, 0, SYSDATE)")
	public void commonsReplyReplyInsert(CommonsReplyVo vo);
	
	@Update("UPDATE commonsReply_3 "
		  + "SET depth = depth + 1 "
		  + "WHERE no = #{no}")
	public void commonsDepthIncrement(int no);
}
