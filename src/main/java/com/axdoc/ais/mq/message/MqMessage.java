package com.axdoc.ais.mq.message;

public abstract class MqMessage implements Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String id;
	protected int type;
	protected String from;
	protected long occurTime;
	protected long occurType;
	protected int priority;
	protected String channel_id;
	protected String uid;
	protected String causeId;
	protected int mq_type;
	protected String mq_topic;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public long getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(long occurTime) {
		this.occurTime = occurTime;
	}
	public long getOccurType() {
		return occurType;
	}
	public void setOccurType(long occurType) {
		this.occurType = occurType;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCauseId() {
		return causeId;
	}
	public void setCauseId(String causeId) {
		this.causeId = causeId;
	}
	public int getMq_type() {
		return mq_type;
	}
	public void setMq_type(int mq_type) {
		this.mq_type = mq_type;
	}
	public String getMq_topic() {
		return mq_topic;
	}
	public void setMq_topic(String mq_topic) {
		this.mq_topic = mq_topic;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
