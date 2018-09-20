package com.axdoc.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {
	
	public static final SerializerFeature[] serializerFeatures = { SerializerFeature.WriteMapNullValue, // 输出空置字段
		   SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
		   SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
		   SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
		   SerializerFeature.WriteNullStringAsEmpty,// 字符类型字段如果为null，输出为""，而不是null
		 };

	private JsonUtil(){}

	public static <T> T parseObject(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}

	public static JSONObject parseObject(String json){
		return JSON.parseObject(json);
	}
	
	public static String toJSONString(Object object){
		return JSON.toJSONString(object,serializerFeatures);
	}
	public static String toJSONString(Object object,SerializerFeature...features ){
		return JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
	}
}
