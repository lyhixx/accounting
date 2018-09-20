package com.axdoc.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	public static String mongodb_ip;
	public static String mongodb_port;
	public static String mongodb_user;
	public static String mongodb_passwd;
	public static String mongodb_db;
	
	public static String doctor_list_url;
	public static String doctor_info_url;
	public static String doctor_schedule_query_url;
	public static String doctor_last_schedule_query_url;
	public static String doctor_schedule_modify_url;
	public static String doctor_patient_mark_url;
	public static String doctor_skill_modify_url;
	public static String doctor_achieve_modify_url;
	public static String patient_mark_query_url;
	public static String patient_mark_modify_url;
	public static String login_auth_url;
	public static String login_auth_domain;
	public static String city_list_query_url;
	public static String dept_list_query_url;
	public static String worker_list_query_url;
	public static String hospital_list_query_url;
	public static String hospital_info_query_url;
	public static String patient_info_query_url;
	public static String account_merchant_create_url;
	public static String accountno_info_query_url;
	public static String biz_update_auth;
	public static String biz_doc_check_group;
	
	public static String trade_time_difference; //打大款相隔时间
	public static String h5_index_path;//h5读取页面路径
	public static String to_mail_users;
	public static String tidoctor_to_mail_users;
	public static String copy_mail_users;
	public static String validnum; //有效引流数
	
	public static String consultation_url; //咨询图片路径
	public static String consultation_url_thumbUrl; //咨询图片路径缩略
	
	public static String account_countmerchant_url;//账单信息
	
	public static String groupselect_url;//地虎医生获取患者分组列表
	public static String group_delete_user_url;//地虎医生删除分组中的病人
	public static String group_update_ulr;//地虎医生修改病人分组名称
	public static String group_add_url;//新增病人分组
	public static String user_mark_url;//地虎医生修改患者备注
	public static String get_user_mark_url;//获取患者备注
	public static String group_add_user_url;//地虎医生添加病人到分组
	public static String group_select_users_url;//分组内查询（地虎医生获取分组中病人列表）
	public static String merchant_balance_sub_account_url;//查询机构账户详情
	public static String biz_send_doc_url;//发消息
	public static String upload_url;//上传
	public static String biz_doc_login;//地虎医生登录
	public static String group_select_users_by_keyword;//地虎医生搜索病人列表
	public static String group_delete_url;//地虎医生删除分组
	public static String biz_doc_reset_pwd;//地虎医生修改密码
	public static String mail_flag; //发送邮件标识
	public static String worker_part_info_query_url;//联系人邮件发送
	
	public static String keyWords; //发消息关键字
	public static String baseMsg; //患者发言基础消息内容
	
	
	static {
		logger.info("----[加载配置文件内容]----start------");
		PropertiesLoader p = new PropertiesLoader("application.properties");
		mongodb_ip = p.getProperty("mongodb_ip");
		mongodb_port = p.getProperty("mongodb_port");
		mongodb_user = p.getProperty("mongodb_user");
		mongodb_passwd = p.getProperty("mongodb_passwd");
		mongodb_db = p.getProperty("mongodb_db");
		
		doctor_list_url = p.getProperty("doctor_list_url");
		doctor_info_url = p.getProperty("doctor_info_url");
		doctor_schedule_query_url = p.getProperty("doctor_schedule_query_url");
		doctor_last_schedule_query_url = p.getProperty("doctor_last_schedule_query_url");
		doctor_schedule_modify_url = p.getProperty("doctor_schedule_modify_url");
		doctor_patient_mark_url = p.getProperty("doctor_patient_mark_url");
		doctor_skill_modify_url = p.getProperty("doctor_skill_modify_url");
		doctor_achieve_modify_url = p.getProperty("doctor_achieve_modify_url");
		patient_mark_query_url = p.getProperty("patient_mark_query_url");
		patient_mark_modify_url = p.getProperty("patient_mark_modify_url");
		login_auth_url = p.getProperty("login_auth_url");
		login_auth_domain = p.getProperty("login_auth_domain");
		city_list_query_url = p.getProperty("city_list_query_url");
		dept_list_query_url = p.getProperty("dept_list_query_url");
		worker_list_query_url = p.getProperty("worker_list_query_url");
		hospital_list_query_url = p.getProperty("hospital_list_query_url");
		hospital_info_query_url = p.getProperty("hospital_info_query_url");
		patient_info_query_url = p.getProperty("patient_info_query_url");
		account_merchant_create_url = p.getProperty("account_merchant_create_url");
		accountno_info_query_url = p.getProperty("accountno_info_query_url");
		biz_update_auth = p.getProperty("biz_update_auth");
		biz_doc_check_group = p.getProperty("biz_doc_check_group");
		
		consultation_url = p.getProperty("consultation_url");
		consultation_url_thumbUrl = p.getProperty("consultation_url_thumbUrl");
		account_countmerchant_url = p.getProperty("account_countmerchant_url");
		groupselect_url = p.getProperty("groupselect_url");
		group_delete_user_url = p.getProperty("group_delete_user_url");
		group_update_ulr = p.getProperty("group_update_ulr");
		group_add_url = p.getProperty("group_add_url");
		user_mark_url = p.getProperty("user_mark_url");
		get_user_mark_url = p.getProperty("get_user_mark_url"); 
		group_add_user_url = p.getProperty("group_add_user_url"); 
		group_select_users_url = p.getProperty("group_select_users_url"); 
		merchant_balance_sub_account_url = p.getProperty("merchant_balance_sub_account_url");
		biz_send_doc_url = p.getProperty("biz_send_doc_url");
		upload_url = p.getProperty("upload_url");
		h5_index_path = p.getProperty("h5_index_path");
		biz_doc_login = p.getProperty("biz_doc_login");
		group_select_users_by_keyword = p.getProperty("group_select_users_by_keyword");
		group_delete_url = p.getProperty("group_delete_url");
		biz_doc_reset_pwd = p.getProperty("biz_doc_reset_pwd");
		//充值相隔时间
		trade_time_difference = p.getProperty("trade_time_difference");
		//邮件接收人
		to_mail_users = p.getProperty("to_mail_users");
		tidoctor_to_mail_users= p.getProperty("tidoctor_to_mail_users");
		//邮件抄送人
		copy_mail_users = p.getProperty("copy_mail_users");
		//邮件抄送人
		validnum = p.getProperty("validnum");
		//邮件发送标识
		mail_flag = p.getProperty("mail_flag");
		keyWords =  p.getProperty("keyWords");
		worker_part_info_query_url = p.getProperty("worker_part_info_query_url");
		baseMsg = p.getProperty("baseMsg");
		
		
		logger.info("----[加载配置文件内容]----end------");
	}
}
