package com.axdoc.ais.accounting.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.axdoc.ais.accounting.dao.pay.IOrderDao;
import com.axdoc.ais.accounting.entity.pay.PayOrder;
import com.axdoc.ais.accounting.exceptions.AccountingException;
import com.axdoc.ais.accounting.service.AccountingService;
import com.axdoc.common.utils.LoggerUtil;

@Service("accountingService")
public class AccountingServiceImpl implements AccountingService {
	
	public static Logger logger = LoggerFactory.getLogger("accounting");

	@Autowired
	private IOrderDao orderDao;

	@Override
	public void accountingPay(String trade_no, String msgId) throws AccountingException {
		logger.info(LoggerUtil.format("会计记账-支付", "req", "trade_no", trade_no, "msgId", msgId));
		try {
			if (StringUtils.isEmpty(trade_no)) {
				throw new AccountingException(AccountingException.MISSING_ARGS, "缺少参数trade_no");
			}
			PayOrder payOrder = orderDao.query(trade_no);
			if (payOrder == null) {
				throw new AccountingException(AccountingException.NOT_EXSIT, "支付订单不存在");
			}
			// 分析订单金额组成
			if (StringUtils.isEmpty(payOrder.getAid())) {
				throw new AccountingException(AccountingException.NOT_EXSIT, "cid不存在");
			}
			long cid = Long.parseLong(StringUtils.isEmpty(payOrder.getAid()) ? "111111111" : payOrder.getAid()); // 用户cid
			BigDecimal totalFee = payOrder.getTotal_fee(); // 实际需要支付现金金额
			BigDecimal cashFee = payOrder.getCash_fee()==null?new BigDecimal(0):payOrder.getCash_fee(); // 实际已经支付现金金额
			BigDecimal couponFee = payOrder.getCoupon_fee()==null?new BigDecimal(0):payOrder.getCoupon_fee();// 券优惠金额
			BigDecimal saleFee = payOrder.getSale_fee()==null?new BigDecimal(0):payOrder.getSale_fee();// 促销、活动优惠金额
			BigDecimal vipFee = payOrder.getVip_sale_fee()==null?new BigDecimal(0):payOrder.getVip_sale_fee();// vip优惠金额
			BigDecimal platform_price = payOrder.getPlatform_price();// 平台服务费

			Date accountingTime = cashFee.compareTo(new BigDecimal(0))==1?payOrder.getPay_end_time():payOrder.getCreate_time();//支付的会计记账时间
			String bizNo = payOrder.getBizid();
			
			// 调用账户服务-事务记账
		} catch (AccountingException e) {
			logger.error(LoggerUtil.format("会计记账-支付", "resp", "trade_no", trade_no, "msgId", msgId, "desc", "会计记账-支付记账业务异常"), e);
		} catch (Exception e) {
			logger.error(LoggerUtil.format("会计记账-支付", "resp", "trade_no", trade_no, "msgId", msgId, "desc", "会计记账-支付记账系统异常"), e);
		}
	}
	/**
	 * 一答问答及查看会计记账
	 * @param oriFee 订单原价 分
	 * @param cashFee 实际支付价 分
	 * @author liyanhui
	 * @date 2018年9月18日
	 */
	private void _accountingQa(String tradeNo, String msgId, String bizNo, int oriFee, int cashFee, long cid, Date accountingTime){
		//根据金额判断是一答提问还是一答查看，<5元为一答查看
		if(oriFee < 500){
			//一答查看
		}else{
			//一答提问
			//一答提问开始会计记账-确认收入 
				//收入类-主营业务收入-一答提问户 贷记cashFee
				//资产类-应收账款户-个人外部户 借记cashFee
			//生成一答提问会计记账分录
			
		}
	}
}
