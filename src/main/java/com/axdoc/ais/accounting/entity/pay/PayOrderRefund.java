package com.axdoc.ais.accounting.entity.pay;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * PayOrderRefund entity. @author MyEclipse Persistence Tools
 */
public class PayOrderRefund implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6596936884386808193L;
	
	// Fields
	private String refund_no;
	private String trade_no;
	private Integer refund_index;
	private BigDecimal refund_fee;
	private String refund_fee_type;
	private String refund_id;
	private String refund_channel;
	private BigDecimal cash_refund_fee;
	private BigDecimal coupon_refund_fee;
	private Integer coupon_refund_count;
	private String coupon_refund_id;
	private String refund_status;
	private Timestamp create_time;
	private Timestamp update_time;
	private Integer delete_flag;
	
	private Integer type;
	
	public String getRefund_no() {
		return refund_no;
	}
	public void setRefund_no(String refund_no) {
		this.refund_no = refund_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public Integer getRefund_index() {
		return refund_index;
	}
	public void setRefund_index(Integer refund_index) {
		this.refund_index = refund_index;
	}
	public BigDecimal getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(BigDecimal refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getRefund_fee_type() {
		return refund_fee_type;
	}
	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}
	public String getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
	public String getRefund_channel() {
		return refund_channel;
	}
	public void setRefund_channel(String refund_channel) {
		this.refund_channel = refund_channel;
	}
	public BigDecimal getCash_refund_fee() {
		return cash_refund_fee;
	}
	public void setCash_refund_fee(BigDecimal cash_refund_fee) {
		this.cash_refund_fee = cash_refund_fee;
	}
	public BigDecimal getCoupon_refund_fee() {
		return coupon_refund_fee;
	}
	public void setCoupon_refund_fee(BigDecimal coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}
	public Integer getCoupon_refund_count() {
		return coupon_refund_count;
	}
	public void setCoupon_refund_count(Integer coupon_refund_count) {
		this.coupon_refund_count = coupon_refund_count;
	}
	public String getCoupon_refund_id() {
		return coupon_refund_id;
	}
	public void setCoupon_refund_id(String coupon_refund_id) {
		this.coupon_refund_id = coupon_refund_id;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	public String getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	
}