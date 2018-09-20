package com.axdoc.ais.accounting.entity;

import java.sql.Timestamp;

/**
 * PayOrderChart entity. @author MyEclipse Persistence Tools
 */
public class PayOrderChart implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8790741642198130801L;
	// Fields
	private Integer id;
	private String trade_no;
	private Integer action;
	private String content;
	private Timestamp create_time;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

	
}