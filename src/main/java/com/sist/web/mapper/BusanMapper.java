package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.BusanVO;

@Mapper
public interface BusanMapper {
	
	/*
	 * <select id="busanListData" resultType="com.sist.web.vo.BusanVO" parameterType="hashmap">
 	 	 SELECT no, contentid, title, address, image1, hit
 	 	 FROM busantravel
 	 	 WHERE contenttype = #{contenttype}
 	 	 ORDER BY no
 	 	 OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
 	   </select>
	 */
	public List<BusanVO> busanListData(Map<String, Integer> map);
	
	@Select("SELECT CEIL(COUNT(*) / 12) FROM busantravel "
		  + "WHERE contenttype = #{contenttype}")
	public int busanTotalPage(int contenttype);

}
