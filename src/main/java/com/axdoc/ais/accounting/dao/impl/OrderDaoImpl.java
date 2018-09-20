package com.axdoc.ais.accounting.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.axdoc.ais.accounting.dao.IOrderDao;
import com.axdoc.ais.accounting.entity.PayOrder;
import com.axdoc.ais.accounting.entity.PayOrderChart;

@Component("orderDao")
public class OrderDaoImpl implements IOrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public PayOrder query(String trade_no) {
		List<PayOrder> payOrders = _queryOrderByParam("trade_no", trade_no,"");
		if(payOrders.size()>0){
			return payOrders.get(0);
		}else{
			return null;
		}
	}

	private List<PayOrder> _queryOrderByParam(String name,Object value,String postfix){
		String sql = "select trade_no,type,content,uid,docid,bizid,status,total_fee," +
				"cash_fee,coupon_fee,transaction_id,pay_end_time,platform,create_time,update_time," +
				"delete_flag,device_info,trade_state,bank_type,coupon_id,sale_id,sale_fee,vip_sale_id,vip_sale_fee,aid,channel,platform_price,pay_channel from pay_order where "+name+"=? "+postfix;
		try {
			List<PayOrder> payOrders = jdbcTemplate.query(sql, new Object[]{value}, new ResultSetExtractor<List<PayOrder>>(){
					@Override
					public List<PayOrder> extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						List<PayOrder> payOrders = new ArrayList<PayOrder>();
						while(rs.next())
						{
							PayOrder payOrder = new PayOrder();
							payOrder.setTrade_no(rs.getString(1));
							payOrder.setType(rs.getInt(2));
							payOrder.setContent(rs.getString(3));
							payOrder.setUid(rs.getString(4));
							payOrder.setDocid(rs.getString(5));
							payOrder.setBizid(rs.getString(6));
							payOrder.setStatus(rs.getInt(7));
							payOrder.setTotal_fee(rs.getBigDecimal(8));
							payOrder.setCash_fee(rs.getBigDecimal(9));
							payOrder.setCoupon_fee(rs.getBigDecimal(10));
							payOrder.setTransaction_id(rs.getString(11));
							payOrder.setPay_end_time(rs.getTimestamp(12));
							payOrder.setPlatform(rs.getInt(13));
							payOrder.setCreate_time(rs.getTimestamp(14));
							payOrder.setUpdate_time(rs.getTimestamp(15));
							payOrder.setDelete_flag(rs.getInt(16));
							payOrder.setDevice_info(rs.getString(17));
							payOrder.setTrade_state(rs.getString(18));
							payOrder.setBank_type(rs.getString(19));
							payOrder.setCoupon_id(rs.getString(20));
							payOrder.setSale_id(rs.getString(21));
							payOrder.setSale_fee(rs.getBigDecimal(22));
							payOrder.setVip_sale_id(rs.getString(23));
							payOrder.setVip_sale_fee(rs.getBigDecimal(24));
							payOrder.setAid(rs.getString(25));
							payOrder.setChannel(rs.getString(26));
							payOrder.setPlatform_price(rs.getBigDecimal(27));
							payOrder.setPay_channel(rs.getString(28));
							payOrders.add(payOrder);
						}
						return payOrders;
					}
				});
			return payOrders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public PayOrder query(String trade_no, String trx_no) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public int queryCount(String bt) {
//		int count = 0;
//		StringBuffer sql = new StringBuffer();
//		sql.append("select count(trade_no) from pay_order where (status = 2 or status = 5) and create_time<? ");
//		count = jdbcTemplate.queryForInt(sql.toString(), new Object[] { bt });
//		return count;
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<PayOrder> queryPayOrderByBatch(String bt, int batchCount, int offset) {
		List<PayOrder> list = new ArrayList<PayOrder>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from pay_order where (status = 2 or status = 5) and create_time<? order by create_time asc  ");
		sql.append(" limit "+batchCount+" offset "+offset );	
		list = (List<PayOrder>) jdbcTemplate.query(sql.toString(), new Object[] { bt }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PayOrder po = new PayOrder();
				po.setTrade_no(rs.getString("trade_no"));
				po.setStatus(rs.getInt("status"));
				return po;
			}
		});	
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<PayOrderChart> queryPayOrderChart(String trade_no) {
		List<PayOrderChart> list = new ArrayList<PayOrderChart>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from pay_order_chart where trade_no = ? ");
		list = (List<PayOrderChart>) jdbcTemplate.query(sql.toString(), new Object[] { trade_no }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PayOrderChart poc = new PayOrderChart();
				poc.setTrade_no(rs.getString("trade_no"));
				poc.setCreate_time(rs.getTimestamp("create_time"));
				return poc;
			}
		});	
		return list;
	}

//	@Override
//	public int queryCountAfter(String at) {
//		int count = 0;
//		StringBuffer sql = new StringBuffer();
//		sql.append("select count(trade_no) from pay_order where (status = 2 or status = 5) and create_time >= ? ");
//		count = jdbcTemplate.queryForInt(sql.toString(), new Object[] { at });
//		return count;
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<PayOrder> queryPayOrderByBatchAfter(String at, int batchCount, int offset) {
		List<PayOrder> list = new ArrayList<PayOrder>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from pay_order where (status = 2 or status = 5) and create_time >= ? order by create_time asc  ");
		sql.append(" limit "+batchCount+" offset "+offset );	
		list = (List<PayOrder>) jdbcTemplate.query(sql.toString(), new Object[] { at }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PayOrder po = new PayOrder();
				po.setTrade_no(rs.getString("trade_no"));
				po.setStatus(rs.getInt("status"));
				po.setBizid(rs.getString("bizid"));
				po.setTransaction_id(rs.getString("transaction_id"));
				return po;
			}
		});	
		return list;
	}
	
	
}
