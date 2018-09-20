package com.axdoc.common.utils;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;


public class LoggerUtil {

	
	/**
	 * 输出封装bean的json格式日志
	 * @param logger
	 * @param func
	 * @param phase
	 * @param param 参数
	 * @param result 返回值
	 */
//	public static String format(String func,String phase,List objlist){
//		JSONObject jsonObject = new JSONObject(true);
//		jsonObject.put("func", func);
//		jsonObject.put("phase", phase);
//		if(null != objlist){
//			JSONArray jsonarr = new JSONArray();
//			for(Object param:objlist){
////				jsonObject.putAll((JSONObject)JSONObject.toJSON(param));
//				jsonarr.add((JSONObject)JSONObject.toJSON(param));
//			}
//			jsonObject.put("list", jsonarr);
//		}
//		return jsonObject.toString();
//	} 
	
	/**
	 * 输出封装bean的json格式日志
	 * @param logger
	 * @param func
	 * @param phase
	 * @param param 参数
	 */
	public static String format(String func,String phase,Object param){
		JSONObject jsonObject = new JSONObject(true);
		jsonObject.put("func", func);
		jsonObject.put("phase", phase);
		if(null != param){
			jsonObject.putAll((JSONObject)JSONObject.toJSON(param));
		}
		return jsonObject.toString();
	}
	
	
	/**
	 * 输出封装bean的json格式日志
	 * @param logger
	 * @param func
	 * @param phase
	 * @param param 参数
	 * @param result 返回值
	 */
//	public static String format(String func,String phase,String json,Object result){
//		JSONObject jsonObject = new JSONObject(true);
//		jsonObject.put("func", func);
//		jsonObject.put("phase", phase);
//		jsonObject.put("return", result);
//		if(!StringUtils.isEmpty(json)){
//			jsonObject.putAll(JSONObject.parseObject(json));
//		}
//		return jsonObject.toString();
//	} 
	
	/**
	 * 输出封装bean的json格式日志
	 * @param logger
	 * @param func
	 * @param phase
	 * @param json 参数
	 */
	public static String format(String func,String phase,String json){
		JSONObject jsonObject = new JSONObject(true);
		jsonObject.put("func", func);
		jsonObject.put("phase", phase);
		if(!StringUtils.isEmpty(json)){
			jsonObject.putAll(JSONObject.parseObject(json));
		}
		return jsonObject.toString();
	}
	/**
	 * 输出封装bean的json格式日志
	 * @param func
	 * @param phase
	 * @param args
	 * @return
	 */
	public static String format(String func,String phase,Object... args){
		JSONObject jsonObject = new JSONObject(true);
		jsonObject.put("func", func);
		jsonObject.put("phase", phase);
		try {
			for (int i=0;i<args.length;i++,i++) {
				jsonObject.put(args[i].toString(), args[i+1]);
			}
			return jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
