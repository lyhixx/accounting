package com.axdoc.ais.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.axdoc.ais.mq.message.Message;
import com.axdoc.ais.mq.service.MessageConvertService;
import com.axdoc.ais.mq.service.MsgServiceFactory;
import com.axdoc.common.utils.LoggerUtil;
/**
 * 监听mq消息(消费)
 *
 */
public class MqListener  implements MessageListener{
	protected  Logger logger =  LoggerFactory.getLogger("MqListener");
	@Autowired
	private MessageConvertService messageConvertService;
	@Override
	 public void onMessage(org.springframework.amqp.core.Message msg) {
        try{
        	logger.info(LoggerUtil.format("接收消息", "req","msg",msg));
        	String data = new String(msg.getBody(),"utf-8");  
            Message axMessage=messageConvertService.convertToMessage(data);
            if(null != axMessage){
            	  MsgServiceFactory.getInstance().handle(axMessage);
            } 
        }catch(Exception e){
        	logger.error(LoggerUtil.format("接收消息", "error","msg","异常"),e);
        }
    }
}
