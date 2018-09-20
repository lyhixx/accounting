package com.axdoc.common.exception;

/**
 * 业务异常基类
 * 
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -5875371379845226068L;

	/**
	 * Token 验证不通过
	 */
	public static final BizException TOKEN_IS_ILLICIT = new BizException(90040005, "Token 验证非法");
	/**
	 * 会话超时　获取session时，如果是空，throws 下面这个异常 拦截器会拦截爆会话超时页面
	 */
	public static final BizException SESSION_IS_OUT_TIME = new BizException(90040006, "会话超时");

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected int code;

	public BizException(int code, String msg, Object... args) {
		super(String.format(msg, args));
		this.code = code;
		this.msg = String.format(msg, args);
	}

	public BizException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msg
	 * @param args
	 * @return
	 */
	public BizException newInstance(String msg, Object... args) {
		return new BizException(this.code, msg, args);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message) {
		super(message);
	}
}
