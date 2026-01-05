package com.sist.web.vo;

import lombok.Data;

/*
 *  NO            NUMBER         
	CONTENTID     NUMBER         
	INFOCENTER    VARCHAR2(1024) 
	RESTDATE      VARCHAR2(1024) 
	USETIME       VARCHAR2(1024) 
	PARKING       VARCHAR2(1024) 
	MSG           CLOB
 */
@Data
public class AttractionVO {

	private int no, contentid;
	private String infocenter, restdate, usetime, parking, msg;
	
}
