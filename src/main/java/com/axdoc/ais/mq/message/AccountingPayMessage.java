package com.axdoc.ais.mq.message;

import java.util.Map;

/**
 * 账户支付成功消息实体
 */
public class AccountingPayMessage extends MqMessage {
	private Map<Object,Object> data;

	public Map<Object, Object> getData() {
		return data;
	}

	public void setData(Map<Object, Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "data=" + data + ", id=" + id + ", type=" + type + ", from=" + from
				+ ", occurTime=" + occurTime + ", occurType=" + occurType + ", priority=" + priority + ", channel_id="
				+ channel_id + ", uid=" + uid + ", causeId=" + causeId + ", mq_type=" + mq_type + ", mq_topic="
				+ mq_topic ;
	}
	
	
}
