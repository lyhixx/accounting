package com.axdoc.common.utils;

/**
 * mongo字段处理
 * @author martin625
 *
 */
public class MongoFieldsUtil {

	/**
	 * number类型转long
	 * @param field
	 * @return
	 * @throws NumberFormatException
	 */
	public static long formatNumber(Object field) throws NumberFormatException{
		if(field==null){
			throw new NullPointerException();
		}
		
		long rs = 0;
		if(field instanceof Integer){
			rs = ((Integer)field).longValue();
		}else if(field instanceof Double){
			rs = ((Double)field).longValue();
		}else if(field instanceof Long){
			rs = (Long)field;
		}else{
			rs = Long.valueOf(field.toString());
		}
		return rs;
	}
	
	public static Integer formarNumberToInteger(Object field){
		return (int)formatNumber(field);
	}
	
	
	public static void main(String[] args) {
		
		System.out.println( MongoFieldsUtil.formarNumberToInteger("avc") );
		
	}
	
}
