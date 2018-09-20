package com.axdoc.common.utils;

import java.util.Random;


/**
 * 几率工具类
 * 构造函数参数需要特定格式：
 * 
 * 几率之和大于100，则会抛出异常；
 * 几率之和小于100, 则有几率返回不在目标值范围内的值，默认为-1
 * 
 * jdk7 1.java.util.concurrent.ThreadLocalRandom.current().nextInt(10) 
 * 这个新的API综合了其他两种方法（1.Math.round(Math.random() * 10)   
 * 2.new java.util.Random().nextInt(10) ）的优点：
 * 单一实例/静态访问，就像Math.random()一样灵活。
 * ThreadLocalRandom也比其他任何处理高并发的方法要更快。
 * @author zhaoch
 * 2010-7-6
 */
public class RandomUtils {
	
	//private static Random r = new Random();
	/**
	 * 判定是否命中
	 * 随机种子默认为100
	 * @param rate 成功率
	 * @return boolean
	 */
	public static boolean isHit(int rate){
		return isHit(rate,100);
	}
	
	/**
	 * 判定是否命中
	 * @param rate 成功率
	 * @param seed 随机种子
	 * @return boolean
	 */
	public static boolean isHit(int rate,int seed){
		if(rate>=seed){
			return true;
		}
		int[][] range = new int[][]{{0,rate},{1,seed-rate}};
		int result = getMultiRandom(range,seed);
		return result==0;
	}
	
	/**
	 * 根据范围设定，返回目标值
	 * @param range {1,4}表示从1-4进行随机
	 * @return int
	 */
	public static int getRangeRandom(int[] range){
		if(range.length!=2){
			//throw new RandomException("随机数范围不正确");
		}
		Random r = new Random();
		return  range[0] + r.nextInt(range[1]-range[0]+1);	
	}
	
	/**
	 * 据范围设定，返回目标值 
	 * @param start 范围开始值
	 * @param end  范围结束值
	 * @return int
	 */
	public static int getRangeRandom(int start ,int end){
		Random r = new Random();
		return  start + r.nextInt(end-start+1);	
	}
	
	/**
	 * 多个随机
	 * {{目标值1,几率1},{目标值2,几率2},{目标值3,几率3}}
	 * int[][] a = {{1,25},{2,60},{3,15}};	
	 * @param entitys
	 * @return 目标值
	 */
	public static int getMultiRandom(int[][] entitys){
		Random r = new Random();
		int seed =100 ;
		int num = 0;
		int random = r.nextInt(seed);
		//Battle.logger.info("random-----:"+random);
		for(int[] entity:entitys){
			num += entity[1];
			if(random<num){
				return entity[0];
			}
		}
		return -1;
	}
	
	/**
	 * 指定随机种子，多个随机
	 * {{目标值1,几率1},{目标值2,几率2},{目标值3,几率3}}
	 * int[][] a = {{1,25},{2,60},{3,15}};	
	 * @param entitys
	 * @return int
	 */
	public static int getMultiRandom(int[][] entitys,int seed){
		Random r = new Random();
		int num = 0;
		int random = r.nextInt(seed);
		for(int[] entity:entitys){
			num += entity[1];
			if(random<num){
				return entity[0];
			}
		}
		return -1;
	}
	
	/**
	 * 多个范围随机
	 * {{{1,3},{55}},{{4,6},{30}},{{7,9},{15}}}
	 * 1-3有55%的出现概率，4-6有30%的概率，7-9有15%的概率
	 * @param entitys
	 * @return int
	 */
	public static int getMultiRangeRandom(int[][][] entitys){
		Random r = new Random();
		int seed =100 ;
		int num = 0;
		int random = r.nextInt(seed);
		for(int[][] entity:entitys){
			num += entity[1][0];
			if(random<num){
				return getRangeRandom(entity[0]);
			}
		}
		return -1;
	}
	
	
	
}
