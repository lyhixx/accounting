package com.axdoc.ais.mq.service;

import com.axdoc.ais.mq.message.Message;


public interface MsgService {
	/**
	 * 处理消息
	 * @param msg
	 */
	public void handle(Message msg);
}
