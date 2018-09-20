package com.axdoc.ais.mq.service;

import com.axdoc.ais.mq.message.Message;


public interface MessageConvertService {
	
	public Message convertToMessage(String message) ;
	
}
