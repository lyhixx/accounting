package com.axdoc.ais.accounting.dao;

import java.util.List;

import com.axdoc.ais.accounting.entity.PayOrderRefund;

/**
 * 退款表dao
 *
 */
public interface IOrderRefundDao {
	/**
	 * 查询退款记录
	 * @param trade_no
	 * @return
	 */
	List<PayOrderRefund> queryByTradeNo(String trade_no);
	/**
	 * 查询退款记录
	 * @param trade_no
	 * @param refund_channel
	 * @return
	 */
	List<PayOrderRefund> queryByTradeNo(String trade_no, String refund_channel);
	
}
