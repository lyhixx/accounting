package com.axdoc.ais.accounting.dao.pay.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.axdoc.ais.accounting.dao.pay.IOrderRefundDao;
import com.axdoc.ais.accounting.entity.pay.PayOrderRefund;

@Repository
public class OrderRefundDaoImpl implements IOrderRefundDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<PayOrderRefund> queryByTradeNo(String trade_no) {
		String sql = "select * from pay_order_refund where trade_no = ? order by refund_index desc";
		List<PayOrderRefund> payOrderRefunds = jdbcTemplate.query(sql,new Object[]{trade_no}, new ResultSetExtractor<List<PayOrderRefund>>(){

			@Override
			public List<PayOrderRefund> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<PayOrderRefund> payOrderRefunds = new ArrayList<PayOrderRefund>();
				while (rs.next()) {
					PayOrderRefund payOrderRefund = new PayOrderRefund();
					payOrderRefund.setRefund_no(rs.getString(1));
					payOrderRefund.setTrade_no(rs.getString(2));
					payOrderRefund.setType(rs.getInt(3));
					payOrderRefund.setRefund_index(rs.getInt(4));
					payOrderRefund.setRefund_fee(rs.getBigDecimal(5));
					payOrderRefund.setRefund_fee_type(rs.getString(6));
					payOrderRefund.setRefund_id(rs.getString(7));
					payOrderRefund.setRefund_channel(rs.getString(8));
					payOrderRefund.setCash_refund_fee(rs.getBigDecimal(9));
					payOrderRefund.setCoupon_refund_fee(rs.getBigDecimal(10));
					payOrderRefund.setCoupon_refund_count(rs.getInt(11));
					payOrderRefund.setCoupon_refund_id(rs.getString(12));
					payOrderRefund.setRefund_status(rs.getString(13));
					payOrderRefund.setCreate_time(rs.getTimestamp(14));
					payOrderRefund.setUpdate_time(rs.getTimestamp(15));
					payOrderRefund.setDelete_flag(rs.getInt(16));
					payOrderRefunds.add(payOrderRefund);
				}
				return payOrderRefunds;
			}});
		return payOrderRefunds;
	}

	@Override
	public List<PayOrderRefund> queryByTradeNo(String trade_no, String refund_channel) {
		String sql = "select * from pay_order_refund where trade_no = ? and refund_channel = ? order by refund_index desc";
		List<PayOrderRefund> payOrderRefunds = jdbcTemplate.query(sql,new Object[]{trade_no, refund_channel}, new ResultSetExtractor<List<PayOrderRefund>>(){

			@Override
			public List<PayOrderRefund> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<PayOrderRefund> payOrderRefunds = new ArrayList<PayOrderRefund>();
				while (rs.next()) {
					PayOrderRefund payOrderRefund = new PayOrderRefund();
					payOrderRefund.setRefund_no(rs.getString(1));
					payOrderRefund.setTrade_no(rs.getString(2));
					payOrderRefund.setType(rs.getInt(3));
					payOrderRefund.setRefund_index(rs.getInt(4));
					payOrderRefund.setRefund_fee(rs.getBigDecimal(5));
					payOrderRefund.setRefund_fee_type(rs.getString(6));
					payOrderRefund.setRefund_id(rs.getString(7));
					payOrderRefund.setRefund_channel(rs.getString(8));
					payOrderRefund.setCash_refund_fee(rs.getBigDecimal(9));
					payOrderRefund.setCoupon_refund_fee(rs.getBigDecimal(10));
					payOrderRefund.setCoupon_refund_count(rs.getInt(11));
					payOrderRefund.setCoupon_refund_id(rs.getString(12));
					payOrderRefund.setRefund_status(rs.getString(13));
					payOrderRefund.setCreate_time(rs.getTimestamp(14));
					payOrderRefund.setUpdate_time(rs.getTimestamp(15));
					payOrderRefund.setDelete_flag(rs.getInt(16));
					payOrderRefunds.add(payOrderRefund);
				}
				return payOrderRefunds;
			}});
		return payOrderRefunds;
	}

	
	
}
