package com.sist.web.vo;

import lombok.Data;

/*
 *  NO          NOT NULL NUMBER         
	TITLE                VARCHAR2(1024) 
	IMAGE1               VARCHAR2(1024) 
	IMAGE2               VARCHAR2(1024) 
	X                    NUMBER(20,12)  위도  
	Y                    NUMBER(20,12)  경도
	CONTENTID            NUMBER         
	ADDRESS              VARCHAR2(300)  
	CONTENTTYPE          NUMBER(2)      
	HIT                  NUMBER
 */
@Data
public class SeoulVO {

	private int no, contentid, contenttype, hit;
	private String title, image1, image2, address;
	private double x, y;
	private AttractionVO avo = new AttractionVO();

}
