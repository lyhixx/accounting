/**
 * 
 */
package com.axdoc.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author liyanhui
 *
 */
public class SerialIdWorker {
	
	private long workerId;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	private static final String WORKER_ID_ENV_KEY = "WORKER_ID_ENV_KEY";
	
	private static String prefix = "301";
	
	private static Integer mem_index=1000;
	
	public static SerialIdWorker INSTANCE = new SerialIdWorker(Long.valueOf(StringUtils.isBlank(System.getenv(WORKER_ID_ENV_KEY))?"1":System.getenv(WORKER_ID_ENV_KEY)));
	
	public SerialIdWorker(){
		
	}
	
	public SerialIdWorker(long workerId){
		workerId = this.workerId;
	}
	
	public synchronized String nextId(){
		if(mem_index>9888){
			mem_index=1000;
		}else{
			mem_index++;
		}
		String now = sdf.format(new Date());
		return prefix+workerId+now+mem_index;
	}
	
	public static final SerialIdWorker getInstance(){
    	return SerialIdWorker.INSTANCE;
    }
	
	public static void main(String[] args) {
		System.out.println(SerialIdWorker.getInstance().nextId());
	}
}
