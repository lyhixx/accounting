package com.axdoc.ais.mq.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axdoc.ais.accounting.service.AccountingService;
import com.axdoc.ais.mq.message.AccountingPayMessage;
import com.axdoc.ais.mq.message.Message;
import com.axdoc.ais.mq.service.MsgService;
import com.axdoc.common.utils.LoggerUtil;
/**
 * 账户支付成功-消息处理
 *
 */
@Component("accountingPayService")
public class AccountingPayServiceImpl implements MsgService {
	
	public static Logger logger = LoggerFactory.getLogger("MsgHandle");
	
	@Autowired
	private AccountingService accountingService;
	@Override
	public void handle(Message msg) {
		try {
			AccountingPayMessage message=(AccountingPayMessage) msg;
			logger.info(LoggerUtil.format("处理支付消息", "req","msg",message));
			String trade_no=message.getData().get("trade_no").toString();
			//开始记账
			accountingService.accountingPay(trade_no, msg.getId());
		} catch (Exception e) {
			logger.error(LoggerUtil.format("处理支付消息", "error","msg",msg),e);
		}
	}
	
}
