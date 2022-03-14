package com.homework.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.homework.entity.Account;

import net.bytebuddy.utility.RandomString;



public class UtilityTool {
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL =request.getRequestURI().toString();
		System.out.println("siteURL+getServletPath" +siteURL+","+ request.getServletPath());
		return siteURL.replace(request.getServletPath(), "");
		
		}
	
	public static JavaMailSenderImpl prepareMailSender() {
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("fromeggtochicken@gmail.com"); 
		mailSender.setPassword("rvmqobkcoysopnjd");
		
		Properties mailProperties = new Properties();
		mailProperties.setProperty("mail.smtp.auth","true");
		mailProperties.setProperty("mail.smtp.starttls.enable","true");
		
		mailSender.setJavaMailProperties(mailProperties);
		
		return mailSender;
	}
public static void sendVerificationEmail(Account acc,HttpServletRequest request) throws UnsupportedEncodingException, MessagingException, UnknownHostException {
		
		JavaMailSenderImpl mailSender = UtilityTool.prepareMailSender();
		
		String toAddress =acc.getAccountDetail().getUserEmail();
		String subject="Please verify your registration to  continue shopping";
		String content ="Dear[[name]],Please click the&nbsp;<div><a href=\"[[URL]]\" target=\"_self\">verify </a><div><font face=\"Comic Sans MS\"></font></div></div>";
	
		MimeMessage message =mailSender.createMimeMessage();
		MimeMessageHelper helper= new MimeMessageHelper(message);
		helper.setFrom("fromeggtochicken@gmail.com","FromEggToChicken");
		
		helper.setTo(toAddress);
		helper.setSubject(subject);
		String randomCode = RandomString.make(54);

		acc.setVerificationcode(randomCode);
		
		content =content.replace("[[name]]", acc.getUserAccount());
		String myip=InetAddress. getLocalHost().getHostAddress();
		
		String verifyURL=myip+UtilityTool.getSiteURL(request) +"/verify?code="+acc.getVerificationcode()+"&email="+toAddress;
		System.out.println("/verify?code="+randomCode+"&email="+toAddress);
		content=content.replace("[[URL]]", verifyURL);
		
		helper.setText(content,true);
		mailSender.send(message);
		
	}

}
