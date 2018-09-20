package com.axdoc.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 集合工具
 * 
 * @author martin625
 *
 */
public class ListUtils {

	public static <E> List<E> removeAll(Collection<E> src, Collection<E> oth) {
		LinkedList<E> result = new LinkedList<E>(src);// 大集合用linkedlist
		HashSet<E> othHash = new HashSet<E>(oth);// 小集合用hashset
		Iterator<E> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
		while (iter.hasNext()) {
			if (othHash.contains(iter.next())) {
				iter.remove();
			}
		}
		return result;

	}
	
	public static String getStr(List<String> list){
		StringBuffer sb=new StringBuffer();
		for (String s : list) {
			sb.append(s).append(",");
		}
		return sb.toString().substring(0,sb.toString().length()-1);
	}
	public static String getStr(Set<String> list){
		StringBuffer sb=new StringBuffer();
		for (String s : list) {
			sb.append(s).append(",");
		}
		return sb.toString().substring(0,sb.toString().length()-1);
	}
	
	/**
	 * 两个集合取交集
	 * 
	 * @param src
	 * @param oth
	 * @return
	 * @date 2016年7月15日 下午2:04:16
	 * @author liyifeng
	 */
	public static <E> List<E> retainAll(Collection<E> src, Collection<E> oth) {
		LinkedList<E> newRs = new LinkedList<E>();
		LinkedList<E> result = new LinkedList<E>(src);// 大集合用linkedlist
		HashSet<E> othHash = new HashSet<E>(oth);// 小集合用hashset
		Iterator<E> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
		E element;
		while (iter.hasNext()) {
			element = iter.next();
			if (othHash.contains(element)) {
				newRs.add(element);
			}
		}
		return newRs;
	}

	public static void main(String[] args) {

		List<String> aa = new ArrayList<String>();

		List<String> a1 = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			aa.add("" + i);
		}

		for (int i = 0; i < 5; i++) {
			a1.add("" + i);
		}
		System.out.println("aa-->" + aa);
		System.out.println("a1-->" + a1);

		long start = System.currentTimeMillis();

		aa = removeAll(aa, a1);

		// aa = retainAll(aa, a1);
		System.out.println("耗时:" + (System.currentTimeMillis() - start) + "毫秒,aa-->" + aa);
		System.out.println(getStr(a1));

	}

}
