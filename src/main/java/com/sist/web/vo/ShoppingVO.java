package com.sist.web.vo;

import lombok.Data;

/*
 *  NO              NUMBER         
	CONTENTID       NUMBER         
	SALEITEM        VARCHAR2(1024) 
	SALEITEMCOST    VARCHAR2(1024) 
	FAIRDAY         VARCHAR2(1024) 
	INFOCENTER      VARCHAR2(1024) 
	RESTDATE        VARCHAR2(1024) 
	PARKING         VARCHAR2(1024) 
	OPENTIME        VARCHAR2(1024) 
	MSG             CLOB
 */
@Data
public class ShoppingVO {
	
	private int no, contentid;
	private String saleitem, saleitemcost, fairday, infocenter, restdate, parking, opentime, msg;

}
