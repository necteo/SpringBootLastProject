package com.sist.web.service;
import java.util.*;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RealFindDataServiceImpl implements RealFindDataService{
   private final RealFindDataMapper mapper;

   @Override
   public void realFindDataInsert(RealFindDataVO vo) {
	// TODO Auto-generated method stub
	mapper.realFindDataInsert(vo);
   }

   @Override
   public void realFindDataDelete() {
	// TODO Auto-generated method stub
	mapper.realFindDataDelete();
   }

   @Override
   public List<RealFindDataVO> realFindDataAllData() {
	// TODO Auto-generated method stub
	return mapper.realFindDataAllData();
   }
}