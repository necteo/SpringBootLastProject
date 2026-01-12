package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
// <mybatis-spring basepackage="com.sist.mapper">
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sist.web.vo.BoardVO;
@Mapper // 구현
/*
 * 	SpringFramework : XML + Annotation
 * 	SpringBoot : Annotation
 * 	---------- Framework (JSP / ThymeLeaf)
 * 			   ORM (MyBatis / JPA)
 * 			   => View단 (Pinia = react 비슷)
 * 	---------- Security : 일반 /  JWT => 토큰생성
 * 						   |      |
 * 						 Session Cookie
 * 	------------------------------------------ 입문
 * 	---------- WebSocket
 *  ---------- Spring AI => 
 *  ---------- Batch : Task (실시간) => 스케쥴러
 *  ---------- Kafka : 메세지
 *  
 *  Vue / Vuex / Pinia (jQuery : Ajax)
 *  ------------------
 *  React / Redux / Tanstack-Query => Next : TypeScript
 *  				|
 *  			   MSA => NodeJS / SpringBoot
 *  ----------------------------------------------------
 *   CI/CD
 *     Git Action / Docker / Docker-compose (MSA)
 *     				  |
 *     			   Docker Hub
 *     Kube => 우분투에서 처리하는 내용 (MSA)
 *     Jenkins => 모니터링 => webhook
 *     						 | Git
 *     --------------------------------------------
 *     | 자바 / 오라클 / JSP / MVC
 *     | JavaScript / HTML / CSS
 *     -------------------------- 공통 기반
 *     
 *     
 */
public interface BoardMapper {
	
	@Select("SELECT no, subject, name, hit, replycount, TO_CHAR(regdate, 'YYYY-MM-DD') dbday "
		  + "FROM board_3 "
		  + "ORDER BY no DESC "
		  + "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> boardListData(int start);
	
	@Select("SELECT COUNT(*) FROM board_3")
	public int boardTotalCount();
	
	@SelectKey(keyProperty = "no", resultType = int.class, before = true, 
			statement = "SELECT NVL(MAX(no) + 1, 1) no FROM board_3")
	@Insert("INSERT INTO board_3 VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0, 0)")
	public void boardInsert(BoardVO vo);
	
	@Update("UPDATE board_3 "
		  + "SET hit = hit + 1 "
		  + "WHERE no = #{no}")
	public void boardHitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, hit, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') dbday "
		  + "FROM board_3 "
		  + "WHERE no = #{no}")
	public BoardVO boardDetailData(int no);
	
	@Select("SELECT pwd FROM board_3 "
		  + "WHERE no = #{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE board_3 "
		  + "SET name = #{name}, subject = #{subject}, content = #{content} "
		  + "WHERE no = #{no}")
	public void boardUpdate(BoardVO vo);
	
	@Delete("DELETE FROM board_3 "
		  + "WHERE no = #{no}")
	public void boardDelete(int no);
	
	@Update("UPDATE board_3 "
		  + "SET replycount = #{replycount} "
		  + "WHERE no = #{no}")
	public void boardReplyCountUpdate(@Param("no") int no, @Param("replycount") int replycount);

}
