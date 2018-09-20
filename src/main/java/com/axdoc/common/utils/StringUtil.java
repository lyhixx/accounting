package com.axdoc.common.utils;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

public class StringUtil {

	/**
	 * 判断字符是否为空(包括 null,"","null","Null","N/A")
	 * 
	 * @param str
	 * @return true：为空 /false：不为空
	 */
	public static boolean isNull(String str) {
		boolean isnull = false;
		if (str == null || "".equals(str) || "null".equals(str) || "Null".equals(str) || "N/A".equals(str)) {
			isnull = true;
		}
		return isnull;
	}

	/**
	 * 判断对象是否为空(包括 null,"" )
	 * 
	 * @param object
	 * @return true：为空 /false：不为空
	 */
	public static boolean isNull_(Object O) {
		boolean isnull = false;
		if (O == null || "".equals(O)) {
			isnull = true;
		}
		return isnull;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String fromatStrNullToBlank(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String changeIntegerToStr(Integer value) {
		return value == null ? "" : value.toString();
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String changeDoubleToStr(Double value) {
		return value == null ? "" : new BigDecimal(value.doubleValue()).toString();
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static Integer changeStringToInteger(String value) {
		return value == null || value.equals("") ? null : Integer.parseInt(value);
	}

	/**
	 * object转String类型
	 * 
	 * @param object
	 * @return
	 */
	public static String toString(Object object) {
		if (isNull_(object)) {
			return "";
		} else {
			return object.toString();
		}
	}

	/**
	 * 将字符串转化为数字
	 * 
	 * @param str
	 * @return
	 */
	public static Integer toInteger(String str) {
		if (str == "" || str == null || "".equals(str) || "null".equals(str) || "Null".equals(str) || "N/A".equals(str)
				|| !NumberUtils.isDigits(str)) {
			return 0;
		}
		return Integer.valueOf(str);
	}

	/**
	 * 字符串转数组
	 * 
	 * @param s
	 * @return
	 */
	public static String[] convertArray(String s) {
		s = s.substring(1, s.length());
		s = s.substring(0, s.length() - 1);
		s = s.replaceAll("\"", "");
		return s.split(",");
	}
	/**
	 * 格式化
	 * @param s 9:0
	 * @return 09:00
	 */
	public static String formatToHHmm(String s){
		String[] arr = s.split(":");
		return String.format("%02d", Integer.parseInt(arr[0]))+":"+String.format("%02d", Integer.parseInt(arr[1]));
	}
	/**
	 * 内容过滤手机号
	 * @param s
	 * @return
	 */
	public static String formatMobile(String s){
		String REGEX_MOBILE = "((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}";
        return s.replaceAll(REGEX_MOBILE,"***");
	}
}
