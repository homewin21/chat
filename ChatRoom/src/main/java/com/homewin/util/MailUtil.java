package com.homewin.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil implements  Runnable {
	private String email;
	private String code;
	
	
	public MailUtil(String email, String code) {
		super();
		this.email = email;
		this.code = code;
	}


	@Override
	public void run() {
		// 1.创建连接对象javax.mail.Session
				// 2.创建邮件对象 javax.mail.Message
				// 3.发送一封激活邮件
		String send="1583418506@qq.com";//所用发送的邮箱，需要开启smtp协议
		String host="smtp.qq.com";//qq邮箱用的主机
		Properties properties =System.getProperties();//获取系统属性
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");
		try {
			//QQ邮箱需要下面这段代码，163邮箱不需要
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", sf);
			// 1.获取默认session对象
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("1583418506@qq.com", "pwbisdrorktojcdb"); // 发件人邮箱账号、授权码
				}
			});
			//创建邮件对象
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(send));//发件地址
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));//接收地址
			message.setSubject("账号激活");
			String content = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1><h3><a href='http://localhost:8080/RegisterDemo/ActiveServlet?code="
					+ code + "'>http://localhost:8080/MailTest/ActiveServlet?code=" + code
					+ "</href></h3></body></html>";
			message.setContent(content, "text/html;charset=UTF-8");
			// 3.发送邮件
			Transport.send(message);
			System.out.println("邮件成功发送!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

} 
