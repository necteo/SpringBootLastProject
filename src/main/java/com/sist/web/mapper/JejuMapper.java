package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.JejuVO;

@Mapper
public interface JejuMapper {
	
	/*
	 * <select id="jejuListData" resultType="com.sist.web.vo.JejuVO" parameterType="hashmap">
 	 	 SELECT no, contentid, title, address, image1, hit
 	 	 FROM jejutravel
 	 	 WHERE contenttype = #{contenttype}
 	 	 ORDER BY no
 	 	 OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
 	   </select>
	 */
	public List<JejuVO> jejuListData(Map<String, Integer> map);
	
	@Select("SELECT CEIL(COUNT(*) / 12) FROM jejutravel "
		  + "WHERE contenttype = #{contenttype}")
	public int jejuTotalPage(int contenttype);

}
