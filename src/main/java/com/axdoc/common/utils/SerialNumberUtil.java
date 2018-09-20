package com.axdoc.common.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成流水号，生成的流水号为    prefix（前缀）+yyMMdd（日期）+xxxx（0000-9999）
 * 
 * @author zhaozhiming on 2016/5/16
 * @version 1.0
 * @since 1.0
 */
public class SerialNumberUtil {
	
	private static final int MAX_VALUE=99999;
	private static final String FORMAT = "yyMMdd";
	private static final Format DF= new SimpleDateFormat(FORMAT);
	private static final byte[] lock = new byte[0];
	private String prefix = null;
	private Date date = null;
	private int number=1;
	private static Map<String, SerialNumberUtil> map = new HashMap<String, SerialNumberUtil>();
	
	private SerialNumberUtil(String prefix,Date date){
		this.prefix = prefix;
		this.date = date;
	}
	
	public static SerialNumberUtil newInstance(String prefix){
		Date date = new Date();
		return newInstance(prefix,date);
	} 
	
	public static SerialNumberUtil newInstance(String prefix,Date date){
		SerialNumberUtil o = null;
		synchronized (lock) {
			String key = getKey(prefix, date);
			if(map.containsKey(key)){
				o = map.get(key);
				int number = o.getNumber();
				if(number<MAX_VALUE){
					o.setNumber(number+1);
				}else {
					o.setNumber(1);
				}
				
			} else {
				 o = new SerialNumberUtil(prefix,date);
				 map.put(key, o);
			}
		}
		return o;
	}
	
	
	
	public static String getKey(String prefix,Date date){
		return prefix+format(date);
	}

	private static String format(Date date){
		return DF.format(date);
	}
	
	public String toString(){
		return  prefix+ format(date) + String.format("%05d", number);
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
	
	
	public static void main(String[] args) {
		System.out.println(SerialNumberUtil.newInstance("",new Date()));
	}
}
