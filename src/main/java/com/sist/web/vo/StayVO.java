package com.sist.web.vo;

import lombok.Data;

/*
 *  NO                NUMBER         
	CONTENTID         NUMBER         
	ROOMTYPE          VARCHAR2(1024) 
	CHECKINTIME       VARCHAR2(1024) 
	CHECKOUTTIME      VARCHAR2(1024) 
	CHKCOOKING        VARCHAR2(1024) 
	SUBFACILITY       VARCHAR2(1024) 
	FOODPLACE         VARCHAR2(1024) 
	RESERVATIONURL    VARCHAR2(1024) 
	INFOCENTER        VARCHAR2(1024) 
	PARKING           VARCHAR2(1024) 
	MSG               CLOB
 */
@Data
public class StayVO {
	
	private int no, contentid;
	private String roomtype, checkintime, checkouttime, chkcooking, subfacility, 
				   foodplace, reservationurl, infocenter, parking, msg;

}
