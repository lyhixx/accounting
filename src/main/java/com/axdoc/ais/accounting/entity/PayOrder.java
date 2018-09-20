package com.axdoc.ais.accounting.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * PayOrder entity. @author MyEclipse Persistence Tools
 */
public class PayOrder implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7407279722623216689L;
	
	// Fields
	private String trade_no;
	private Integer type;
	private String content;
	private String uid;
	private String docid;
	private String bizid;
	private Integer status;
	private BigDecimal total_fee;
	private BigDecimal cash_fee;
	private BigDecimal coupon_fee;
	private String coupon_id; //优惠卷
	private Integer coupon_count;
	private String openid;
	private String is_subscribe;
	private String trade_type;
	private String trade_state;
	private String bank_type;
	private String fee_type;
	private String transaction_id;
	private Timestamp pay_end_time;
	private Integer platform;
	private String device_info;
	private Timestamp create_time;
	private Timestamp update_time;
	private Integer delete_flag;
	
	//sale_id 
	private String sale_id;
	private BigDecimal sale_fee;
	
	private String vip_sale_id;
	private BigDecimal vip_sale_fee;
	
	//账户字段
	String aid;
	String channel;
	
	private BigDecimal platform_price;
	
	private String pay_channel;
	
	public PayOrder(){
		
	}
	
	public PayOrder(String trade_no, Integer type, String content, String uid,
			String docid, String bizid, Integer status, BigDecimal total_fee,
			BigDecimal cash_fee, BigDecimal coupon_fee, String transaction_id,
			Timestamp pay_end_time, Integer platform, Timestamp create_time,
			Timestamp update_time, Integer delete_flag,
			String sale_id, BigDecimal sale_fee,String vip_sale_id, BigDecimal vip_sale_fee,
			String aid,String channel,BigDecimal platform_price,String pay_channel) {
		super();
		this.trade_no = trade_no;
		this.type = type;
		this.content = content;
		this.uid = uid;
		this.docid = docid;
		this.bizid = bizid;
		this.status = status;
		this.total_fee = total_fee;
		this.cash_fee = cash_fee;
		this.coupon_fee = coupon_fee;
		this.transaction_id = transaction_id;
		this.pay_end_time = pay_end_time;
		this.platform = platform;
		this.create_time = create_time;
		this.update_time = update_time;
		this.delete_flag = delete_flag;
		this.sale_id = sale_id;
		this.sale_fee = sale_fee;
		this.vip_sale_id = vip_sale_id;
		this.vip_sale_fee = vip_sale_fee;
		this.aid = aid;
		this.channel = channel;
		this.platform_price = platform_price;
		this.pay_channel=pay_channel;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		this.total_fee = total_fee;
	}

	public BigDecimal getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(BigDecimal cash_fee) {
		this.cash_fee = cash_fee;
	}

	public BigDecimal getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(BigDecimal coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Timestamp getPay_end_time() {
		return pay_end_time;
	}

	public void setPay_end_time(Timestamp pay_end_time) {
		this.pay_end_time = pay_end_time;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
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

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public Integer getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public PayOrder(String trade_no, Integer type, String content, String uid,
			String docid, String bizid, Integer status, BigDecimal total_fee,
			BigDecimal cash_fee, BigDecimal coupon_fee, String coupon_id,
			Integer coupon_count, String openid, String is_subscribe,
			String trade_type, String trade_state, String bank_type,
			String fee_type, String transaction_id, Timestamp pay_end_time,
			Integer platform, Timestamp create_time, Timestamp update_time,
			Integer delete_flag,String sale_id, BigDecimal sale_fee,
			String vip_sale_id, BigDecimal vip_sale_fee,String aid,String channel,BigDecimal platform_price) {
		super();
		this.trade_no = trade_no;
		this.type = type;
		this.content = content;
		this.uid = uid;
		this.docid = docid;
		this.bizid = bizid;
		this.status = status;
		this.total_fee = total_fee;
		this.cash_fee = cash_fee;
		this.coupon_fee = coupon_fee;
		this.coupon_id = coupon_id;
		this.coupon_count = coupon_count;
		this.openid = openid;
		this.is_subscribe = is_subscribe;
		this.trade_type = trade_type;
		this.trade_state = trade_state;
		this.bank_type = bank_type;
		this.fee_type = fee_type;
		this.transaction_id = transaction_id;
		this.pay_end_time = pay_end_time;
		this.platform = platform;
		this.create_time = create_time;
		this.update_time = update_time;
		this.delete_flag = delete_flag;
		this.sale_id = sale_id;
		this.sale_fee = sale_fee;
		this.vip_sale_id = vip_sale_id ;
		this.vip_sale_fee = vip_sale_fee;
		this.aid = aid;
		this.channel = channel;
		this.platform_price = platform_price;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getSale_id() {
		return sale_id;
	}

	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}

	public BigDecimal getSale_fee() {
		return sale_fee;
	}

	public void setSale_fee(BigDecimal sale_fee) {
		this.sale_fee = sale_fee;
	}

	public String getVip_sale_id() {
		return vip_sale_id;
	}

	public void setVip_sale_id(String vip_sale_id) {
		this.vip_sale_id = vip_sale_id;
	}

	public BigDecimal getVip_sale_fee() {
		return vip_sale_fee;
	}

	public void setVip_sale_fee(BigDecimal vip_sale_fee) {
		this.vip_sale_fee = vip_sale_fee;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public BigDecimal getPlatform_price() {
		return platform_price;
	}

	public void setPlatform_price(BigDecimal platform_price) {
		this.platform_price = platform_price;
	}

	public String getPay_channel() {
		return pay_channel;
	}

	public void setPay_channel(String pay_channel) {
		this.pay_channel = pay_channel;
	}

	
	
}