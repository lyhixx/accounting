package com.axdoc.ais.mq.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axdoc.ais.mq.message.Message;
import com.axdoc.common.utils.LoggerUtil;


/**
 * 消息处理工厂
 * @author zhaochanghuai
 *
 */
public class MsgServiceFactory {
	public static Logger logger = LoggerFactory.getLogger("listen_message");
	
	private Map<String,MsgService> services = new HashMap<String,MsgService>();
	
	private static MsgServiceFactory instance ;
	
	public static MsgServiceFactory getInstance(){
		return instance;
	}
	
	public void init(){
		System.out.println("init");
		instance = this;
	}
	
	public MsgService getService(String className){
		MsgService ms =  services.get(className);
		return ms;
	}
	
	/**
	 * 处理消息
	 * @param msg
	 * @throws PolicyException 
	 */
	public void handle(Message msg) {
		try{
			if(msg!=null){
				MsgService service = this.getService(msg.getClass().getName());
				service.handle(msg);
			}
			
		}catch(Exception wme){
			logger.error(LoggerUtil.format("消息工厂处理消息", "error","msg",msg.getId()),wme);
			throw wme;
		}
	}
	public Map<String, MsgService> getServices() {
		return services;
	}

	public void setServices(Map<String, MsgService> services) {
		this.services = services;
	}

	
	
	
}
