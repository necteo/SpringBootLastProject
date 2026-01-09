package com.sist.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	// ID 중복 체크
	@Select("SELECT COUNT(*) FROM project_member_3 "
		  + "WHERE userid = #{userid}")
	public int memberIdCheck(String userid);
	
	@Insert("INSERT INTO project_member_3(userid, username, userpwd, sex, birthday, "
		  + "email, post, addr1, addr2, phone, content) "
		  + "VALUES(#{userid}, #{username}, #{userpwd}, #{sex}, #{birthday}, #{email}, "
		  + "#{post}, #{addr1}, #{addr2}, #{phone}, #{content})")
	public void memberInsert(MemberVO vo);
	
	@Insert("INSERT INTO authority_3 VALUES(#{userid}, 'ROLE_USER')")
	public void memberAuthorityInsert(String userid);
	
	// 비밀번호 검사 => 데이터 읽기 => session 저장
	@Select("SELECT * FROM project_member_3 "
		  + "WHERE userid = #{userid}")
	public MemberVO memberInfoData(String userid);

}
