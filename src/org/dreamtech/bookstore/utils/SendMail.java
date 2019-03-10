package org.dreamtech.bookstore.utils;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @Program: BookStore
 * @Description: 发送邮件工具
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
public class SendMail {

    /**
     * 发送邮件
     *
     * @param target  目标地址
     * @param title   邮件标题
     * @param content 邮件内容
     * @return 是否发送成功
     */
    public static boolean send(String target, String title, String content) {
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.debug", "false");
            prop.setProperty("mail.host", "smtp.qq.com");
            prop.setProperty("mail.smtp.auth", "true");
            prop.setProperty("mail.transport.protocol", "smtp");

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getInstance(prop);
            Transport ts = session.getTransport();
            ts.connect("smtp.qq.com", "2023503307", "fvvceyfbtukneaba");
            Message message = createSimpleMail(session, target, title, content);
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建邮件对象
     *
     * @param session session
     * @param target  目标地址
     * @param title   邮件题目
     * @param content 邮件内容
     * @return 邮件对象
     * @throws Exception 异常
     */
    private static MimeMessage createSimpleMail(Session session, String target, String title, String content)
            throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("2023503307@qq.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(target));
        message.setSubject(title);
        message.setContent(content, "text/html;charset=UTF-8");
        return message;
    }
}