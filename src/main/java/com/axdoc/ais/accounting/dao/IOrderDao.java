package com.axdoc.ais.accounting.dao;

import java.util.List;

import com.axdoc.ais.accounting.entity.PayOrder;
import com.axdoc.ais.accounting.entity.PayOrderChart;

/**
 * 订单
 * @author martin625
 *
 */
public interface IOrderDao {
	/**
	 * 查询支付订单信息
	 * @param trade_no
	 * @return
	 */
	public PayOrder query(String trade_no);
	/**
	 * 查询支付订单信息
	 * @param trade_no
	 * @param trx_no
	 * @return
	 */
	public PayOrder query(String trade_no,String trx_no);
	/**
	 * 根据时间查询数量
	 * @param bt
	 * @return
	 */
//	public int queryCount(String bt);
	/**
	 * 之后
	 * @param at
	 * @return
	 */
//	public int queryCountAfter(String at);
	/**
	 * 分批查询数据
	 * @param bt
	 * @param batchCount
	 * @param offset
	 * @return
	 */
	public List<PayOrder> queryPayOrderByBatch(String bt,int batchCount,int offset);
	/**之后
	 * 
	 * @param at
	 * @param batchCount
	 * @param offset
	 * @return
	 */
	public List<PayOrder> queryPayOrderByBatchAfter(String at,int batchCount,int offset);
	/**
	 * queryPayOrderChart
	 * @param trade_no
	 * @return
	 */
	public List<PayOrderChart> queryPayOrderChart(String trade_no);
}
