package com.axdoc.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DateUtil {
	public static long getNewDateLong(){
		return new Date().getTime()/1000;
	}
	
	public static long getminusDaysLong(int minusDays){
		return new DateTime().minusDays(minusDays).getMillis()/1000;
	}
	/**
	 * 比较日期
	 * @param startLong(毫秒)
	 * @param endLong(毫秒)
	 * @return
	 */
	public static int daysBetween(long startLong,long endLong){
		LocalDate start=new LocalDate(startLong);    
        LocalDate end=new LocalDate(endLong);   
        int daysBetween=Days.daysBetween(end,start).getDays();
        return daysBetween;
	} 
	
	public static long getStartTime(int subDays){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, subDays);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		return calendar.getTimeInMillis();
	}
	public static long getEndTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTimeInMillis();
	}
	
	public static String getAddMonths(){
		String dateStr = new DateTime().plusMonths(-1).dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");  
		return dateStr;
	}
	
	public static String getYearMonthDay(String dateStr){
		if("".equals(dateStr) || null == dateStr){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(date.getTime());
		Calendar birthday = new GregorianCalendar(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH));//2010年10月12日，month从0开始  
        Calendar now = Calendar.getInstance();  
        int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);  
        int month = now.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);  
        int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);  
        //按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。  
        if(day<0){  
            month -= 1;  
            now.add(Calendar.MONTH, -1);//得到上一个月，用来得到上个月的天数。  
            day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);  
        }  
        if(month<0){  
            month = (month+12)%12;  
            year--;  
        }  
        return year+"岁"+month+"个月";
	}
	
	public static String getYearMonthDay2(String dateStr){
		if("".equals(dateStr) || null == dateStr){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(date.getTime());
		Calendar birthday = new GregorianCalendar(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH));//2010年10月12日，month从0开始  
        Calendar now = Calendar.getInstance();  
        int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);  
        int month = now.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);  
        int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);  
        //按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。  
        if(day<0){  
            month -= 1;  
            now.add(Calendar.MONTH, -1);//得到上一个月，用来得到上个月的天数。  
            day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);  
        }  
        if(month<0){  
            month = (month+12)%12;  
            year--;  
        }  
        return year+"岁"+month+"个月";
	}
	
	/**
	 * 格式化日期
	 * @param time
	 * @param format
	 * @return
	 */
	public static String  formatDate(long time, String format){
		SimpleDateFormat sf = new SimpleDateFormat(format);  
		return sf.format(time);
		
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(424483200 * 1000L);
		System.out.println(sdf.format(date));
		System.out.println(getYearMonthDay2(sdf.format(date)));
	}
	
}
