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
	
	/*
	 *   <select id="jejuFindData" resultType="com.sist.web.vo.JejuVO" parameterType="hashmap">
	 	   SELECT no, contentid, title, address, image1, hit, contenttype
	 	 	 FROM jejutravel
	 	 	 WHERE contenttype = #{selected}
	 	 	 AND title LIKE '%' || #{fd} || '%'
	 	 	 ORDER BY no
	 	 	 OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	 	 </select>
	 	 <select id="jejuFindTotalPage" resultType="int" parameterType="hashmap">
	 	   SELECT CEIL(COUNT(*) / 12)
	 	 	 FROM jejutravel
	 	 	 WHERE contenttype = #{selected}
	 	 	 AND title LIKE '%' || #{fd} || '%'
	 	 </select>
	 */
	public List<JejuVO> jejuFindData(Map<String, Object> map);
	public int jejuFindTotalPage(Map<String, Object> map);
	
	/*
	 *   <select id="jejuTop4Data" resultType="com.sist.web.vo.JejuVO">
	 	 	 SELECT no, contentid, title, address, image1, hit
	 	 	 FROM (SELECT no, contentid, title, address, image1, hit
	 	 	 			 FROM jejutravel ORDER BY hit DESC)
	 	 	 WHERE rownum &lt;= 4
	 	 </select>
	 */
	public List<JejuVO> jejuTop4Data();

}
