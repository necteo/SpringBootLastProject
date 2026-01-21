package com.sist.web.vo;

import java.util.Date;

import lombok.Data;

/*
 *  NO         NOT NULL NUMBER       
	CNO                 NUMBER       
	ID                  VARCHAR2(20) 
	RDAY       NOT NULL VARCHAR2(30) 
	RTIME      NOT NULL VARCHAR2(30) 
	RINWON     NOT NULL VARCHAR2(20) 
	REGDATE             DATE         
	ISRESERVER          NUMBER
	ISCANCEL			NUMBER
 */
@Data
public class ReserveVO {
	
	private int no, cno, isReserve, isCancel;
	private String id, rday, rtime, rinwon, dbday;
	private Date regdate;
	private SeoulVO svo = new SeoulVO();

}
