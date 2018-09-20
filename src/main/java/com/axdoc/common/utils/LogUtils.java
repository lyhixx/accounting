package com.axdoc.common.utils;

import java.util.HashMap;
import java.util.Map;

public class LogUtils {
	private String docid;//医生ID
	private String qid;
	private String func;
	private String batchNo;
	private String phase;//周期标识
	private String biz="RcmdDataCollector";
	private Map<String, Object> extra=new HashMap<String, Object>();
	public static String REQ="req";
	public static String RES="res";
	public static String WARN="warn";
	public static String ERROR="error";
	public static String HANDLING="handling";
	
	
	public LogUtils(String func,String phase,String docid,String qid
			) {
		super();
		this.docid = docid;
		this.qid = qid;
		this.func = func;
		this.phase = phase;
	}
	public LogUtils(){}
	public LogUtils(String func,String phase){
		super();
		this.func = func;
		this.phase=phase;
	}
	public LogUtils( String func, String phase,String docid) {
		super();
		this.docid = docid;
		this.func = func;
		this.phase = phase;
	}

	public LogUtils put(String key,Object value){
		this.extra.put(key, value);
		return this;
	}
	
	
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getBiz() {
		return biz;
	}
	public void setBiz(String biz) {
		this.biz = biz;
	}
	public Map<String, Object> getExtra() {
		return extra;
	}
	public void setExtra(Map<String, Object> extra) {
		this.extra = extra;
	}
	
	public String getBatchNo() {
		return batchNo;
	}
	public LogUtils setBatchNo(String batchNo) {
		this.batchNo = batchNo;
		return this;
	}
	
	@Override
	public String toString() {
		return JsonUtil.toJSONString(this);
	}
	public static void main(String[] args) {
		
		long l=System.currentTimeMillis();
		System.out.println(new LogUtils("执行每日初始化",LogUtils.REQ).setBatchNo("1111").put("startTime", l).toString());
		System.out.println(new LogUtils("执行每日初始化",LogUtils.RES).put("startTime", l).put("耗时", System.currentTimeMillis()-l).toString());
	}
}
