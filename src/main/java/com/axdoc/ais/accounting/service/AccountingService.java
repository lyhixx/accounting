/**
 * 
 */
package com.axdoc.ais.accounting.service;

import com.axdoc.ais.accounting.exceptions.AccountingException;

/**
 * @author liyanhui 记账业务
 */
public interface AccountingService {
	/**
	 * 支付会计记账
	 * 
	 * @param trade_no
	 * 
	 * 
	 */
	public void accountingPay(String trade_no, String msgId) throws AccountingException;
}
