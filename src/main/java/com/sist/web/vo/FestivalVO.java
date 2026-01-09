package com.sist.web.vo;

import lombok.Data;

/*
 *  NO                NUMBER         
	CONTENTID         NUMBER         
	EVENTSTARTDATE    VARCHAR2(1024) 
	EVENTENDDATE      VARCHAR2(1024) 
	AGELIMIT          VARCHAR2(1024) 
	PLAYTIME          VARCHAR2(1024) 
	EVENTPLACE        VARCHAR2(1024) 
	EVENTHOMEPAGE     VARCHAR2(1024) 
	USETIME           VARCHAR2(1024) 
	SPENDTIME         VARCHAR2(1024) 
	MSG               CLOB
 */
@Data
public class FestivalVO {
	
	private int no, contentid;
	private String eventstartdate, eventenddate, agelimit, playtime, eventplace, eventhomepage, usetime, spendtime, msg;

}
