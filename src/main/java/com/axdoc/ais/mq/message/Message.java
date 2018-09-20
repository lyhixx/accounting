package com.axdoc.ais.mq.message;

public interface Message {


	
	/**
	 * 消息唯一id
	 * @return
	 */
	public String getId();
	public void setId(String id);
	/**
	 * 消息类型
	 * @return
	 */
	public int getType();
	public void setType(int type);
	
	/**
	 * 消息来源
	 * @return
	 */
	public String getFrom();
	public void setFrom(String from);
	
	/**
	 * 触发时间
	 * @return
	 */
	public long getOccurTime();
	public void setOccurTime(long occurTime);
	/**
	 * 触发类型
	 * @return
	 */
	public long getOccurType();
	public void setOccurType(long occurType);
	
	/**
	 * 消息优先级
	 * @return
	 */
	public int getPriority();
	public void setPriority(int priority);
	/**
	 * 用户Id
	 * @return
	 */
	public String getUid();
	public void setUid(String uid);
	/**
	 * 渠道Id
	 * @return
	 */
	public String getChannel_id();
	public void setChannel_id(String channel_id);
	/**
	 * 
	 * @return
	 */
	public int getMq_type();
	public void setMq_type(int mq_type);
	
	/**
	 * 
	 * @return
	 */
	public String getMq_topic();
	public void setMq_topic(String mq_topic);
	
	/**
	 * 
	 * @return
	 */
	public String getCauseId();
	public void setCauseId(String causeId);

}
