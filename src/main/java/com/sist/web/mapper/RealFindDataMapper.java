package com.sist.web.mapper;

import java.io.Serial;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.RealFindDataVO;
@Mapper
@Repository
public interface RealFindDataMapper {
  @Insert("INSERT INTO realFindData VALUES("
		 +"(SELECT NVL(MAX(no)+1,1) FROM realFindData),"
		 +"#{word},#{images})")
  public void realFindDataInsert(RealFindDataVO vo);
  
  @Update("TRUNCATE TABLE realFindData")
  public void realFindDataDelete();
  
  @Select("SELECT * FROM realFindData ORDER BY no ASC")
  public List<RealFindDataVO> realFindDataAllData();
}