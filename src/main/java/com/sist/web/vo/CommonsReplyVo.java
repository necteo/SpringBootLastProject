package com.sist.web.vo;
/*
 *  NO         NOT NULL NUMBER       
	CNO                 NUMBER       
	ID                  VARCHAR2(20) 
	NAME       NOT NULL VARCHAR2(51) 
	SEX                 VARCHAR2(6)  
	MSG        NOT NULL CLOB         
	GROUP_ID            NUMBER       
	GROUP_STEP          NUMBER       
	GROUP_TAB           NUMBER       
	ROOT                NUMBER       
	DEPTH               NUMBER       
	REGDATE             DATE
 */

import java.util.Date;

import lombok.Data;

@Data
public class CommonsReplyVo {
	
	private int no, cno, group_id, group_step, group_tab, root, depth, page;
	private String id, name, sex, msg, dbday;
	private Date regdate;

}
