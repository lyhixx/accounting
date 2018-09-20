package com.axdoc.common.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件发送类
 * <p>Company: meinong</p>
 * @author: jshi
 * @date: 2016年6月24日 上午10:43:22
 * @version: V1.0
 */
public class MailUtil {
    private static final Logger log = LoggerFactory.getLogger(MailUtil.class);
    private MimeMessage message;
    private Session session;
    private Transport transport;

    private String mailHost;
    private String sender_username;
    private String sender_password;

    private Properties properties = new Properties();

    
    /**
     * 构造方法 初始化发送方信息.
     * @param smtpHost 发送方host地址
     * @param sendUserName 发送方用户名
     * @param sendPassWord 发送方密码
     * @param debug 是否开启debug调试功能
     */
    public MailUtil(String smtpHost, String sendUserName, String sendPassWord,
            boolean debug) {
        this.mailHost = smtpHost;
        this.sender_username = sendUserName;
        this.sender_password = sendPassWord;
        properties.setProperty("mail.smtp.host", mailHost);
        properties.setProperty("mail.sender.username", sender_username);
        properties.setProperty("mail.sender.password", sender_username);
        session = Session.getInstance(properties);
        session.setDebug(debug);// 开启后有调试信息
        message = new MimeMessage(session);
    }
    /**
     * 邮件发送
     * @param subject 邮件主题
     * @param sendHtml 邮件内容
     * @param receiveUser 收件人地址
     * @param toCopy 抄送人地址
     */
    public void doSendHtmlEmail(String subject, String sendHtml,String[] receiveUser,String[] toCopy){
    	doSendHtmlEmail(subject, sendHtml, receiveUser, null, toCopy, null);
    }
    /**
     * 邮件发送.
     * @param subject 邮件主题
     * @param sendHtml 邮件内容
     * @param receiveUser 收件人地址
     * @param attachments 附件
     * @param toCopy 抄送人地址
     * @param images 正文图片cid键对值
     */
    public void doSendHtmlEmail(String subject, String sendHtml,
            String[] receiveUser, String[] attachments, String[] toCopy,
            Map<String, String> images) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sender_username);
            message.setFrom(from);
            // 邮件主题
            message.setSubject(subject);
            // 收件人
            message.setRecipients(Message.RecipientType.TO,
                    toAddress(receiveUser));
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            // 添加附件的内容
            if (attachments != null && attachments.length > 0) {
                addAttachment(attachments, multipart);
            }
            if (images != null) {
                // 添加正文图片
                addImages(images, multipart);
            }
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            if (toCopy != null) {
                // 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
                message.setRecipients(Message.RecipientType.CC,
                        toAddress(toCopy));
            }
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            log.info("mail: " + subject + " send success!");
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
     * 添加附件.
     * @param files 附件地址
     * @param multipart 文件组件
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    private void addAttachment(String[] files, Multipart multipart)
            throws MessagingException, UnsupportedEncodingException {
        for (String filePath : files) {
            File file = new File(filePath);
            if (file.exists()) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                // MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(file
                        .getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }
        }
    }

    /**
     * 将String 类型数组转换为地址类型数组.
     * 
     * @param address
     * @return address[]
     * @throws AddressException
     */
    private Address[] toAddress(String[] address) throws AddressException {
        Address[] ccAdresses = new InternetAddress[address.length];
        for (int i = 0; i < address.length; i++) {
            ccAdresses[i] = new InternetAddress(address[i]);
        }
        return ccAdresses;
    }

    /**
     * 正文中添加图片.
     * 
     * @param map 图片cid键对值
     * @param multipart 文件组件
     * @throws MessagingException
     */
    private void addImages(Map<String, String> map, Multipart multipart)
            throws MessagingException {
        for (Entry<String, String> entry : map.entrySet()) {
            String filePath = entry.getValue();
            // 创建用于保存图片的MimeBodyPart对象，并将它保存到MimeMultipart中
            MimeBodyPart gifBodyPart = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(filePath);// 图片所在的目录的绝对路径
            gifBodyPart.setDataHandler(new DataHandler(fds));
            gifBodyPart.setContentID(entry.getKey()); // cid的值
            multipart.addBodyPart(gifBodyPart);
        }
    }

   

}
