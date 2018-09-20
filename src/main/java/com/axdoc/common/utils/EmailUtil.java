package com.axdoc.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;


public class EmailUtil {
	private MimeMessage message;
    private Session session;
    private Transport transport;

    private String mailHost ;
    private String sender_username ;
    private String sender_password ;

    private Properties properties = new Properties();
    
    private final Logger logger = Logger.getLogger(EmailUtil.class);
    
	/*
     * 初始化方法
     */
	public EmailUtil(boolean debug) throws IOException {
		InputStream in = EmailUtil.class.getResourceAsStream("/MailServer.properties");

		properties.load(in);
		this.mailHost = properties.getProperty("mail.smtp.host");
		this.sender_username = properties.getProperty("mail.sender.username");
		this.sender_password = properties.getProperty("mail.sender.password");

		session = Session.getInstance(properties);
		session.setDebug(debug);// 开启后有调试信息
		message = new MimeMessage(session);
	}
    
    /**
     * 发送邮件
     * @param subject 主题
     * @param sendHtml 内容
     * @param receiveUser 收件人
     * @param ccUsers 抄送人
     * @param attachment 附件
     */
	public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser,List<String> ccUsers, File attachment) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sender_username);
            message.setFrom(from);

            // 收件人
            //InternetAddress to = new InternetAddress(receiveUser);
            //message.setRecipient(Message.RecipientType.TO, to);
            List<InternetAddress> addresses = new ArrayList<InternetAddress>();
            addresses.add(new InternetAddress(receiveUser));
            if(ccUsers!=null){
            	for(String cc:ccUsers){
            		 addresses.add(new InternetAddress(cc));
            	}
            }
            message.setRecipients(Message.RecipientType.TO, addresses.toArray(new InternetAddress[]{}));
            
            // 邮件主题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            
            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            
            // 添加附件的内容
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                
                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
                
                //MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }
            
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println(subject+",send success!["+receiveUser+"]");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	
	
	/**
     * 发送邮件
     * @param subject 主题
     * @param sendHtml 内容
     * @param receiveUser 收件人
     * @param ccUsers 抄送人
     * @param attachment 附件
     */
	public void doSendHtmlEmailMoreFiles(String subject, String sendHtml, String receiveUser,List<String> ccUsers, List<File> attachments) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sender_username);
            message.setFrom(from);

            // 收件人
//            InternetAddress to = new InternetAddress(receiveUser);
//            message.setRecipient(Message.RecipientType.TO, to);
//            
//            if(ccUsers!=null){
//	            InternetAddress[] ccs = new InternetAddress[ccUsers.size()];
//	            for(int i=0;i<ccs.length;i++){
//	            	ccs[i] = new InternetAddress(ccUsers.get(i));
//	            }
//	            message.setRecipients(Message.RecipientType.CC, ccs);
//            }
            
            List<InternetAddress> addresses = new ArrayList<InternetAddress>();
            addresses.add(new InternetAddress(receiveUser));
            if(ccUsers!=null){
            	for(String cc:ccUsers){
            		 addresses.add(new InternetAddress(cc));
            	}
            }
            message.setRecipients(Message.RecipientType.TO, addresses.toArray(new InternetAddress[]{}));

            // 邮件主题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            
            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            
            // 添加附件的内容
            if (attachments != null) {
            	for(File attachment:attachments){
	                BodyPart attachmentBodyPart = new MimeBodyPart();
	                DataSource source = new FileDataSource(attachment);
	                attachmentBodyPart.setDataHandler(new DataHandler(source));
	                
	                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
	                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
	                //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
	                //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
	                
	                //MimeUtility.encodeWord可以避免文件名乱码
	                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
	                multipart.addBodyPart(attachmentBodyPart);
            	}
            }
            
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println(subject+",send success!["+receiveUser+"]");
        } catch (Exception e) {
        	logger.info("send Email error:"+e);
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                	logger.info("send Email error:"+e);
                    e.printStackTrace();
                }
            }
        }
    }
	
}
