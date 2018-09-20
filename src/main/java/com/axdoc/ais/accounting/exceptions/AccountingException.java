package com.axdoc.ais.accounting.exceptions;

import com.axdoc.common.exception.BizException;

public class AccountingException extends BizException {

	private static final long serialVersionUID = 1L;
	
	/** 缺少参数 */
	public static final int MISSING_ARGS = 3001;
	
	public static final int NOT_EXSIT = 3002;

	public AccountingException() {
		super();
	}

	public AccountingException(int code, String msg, Object... args) {
		super(code, msg, args);
	}
}
