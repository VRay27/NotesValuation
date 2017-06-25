/**
 * 
 */
package com.noteanalyzer.mvc.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class EmailService {
	
	@Autowired
    private Environment environment;
	
	public boolean sendEmail(String senderEmailId, String subject,String bodyText) {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.smtp.starttls.enable"));
		props.put("mail.smtp.host",  environment.getRequiredProperty("mail.smtp.host"));
		props.put("mail.smtp.port", environment.getRequiredProperty("mail.smtp.port"));
		props.put("mail.smtp.ssl.trust",  environment.getRequiredProperty("mail.smtp.ssl.trust"));

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(environment.getRequiredProperty("mail.userName"),environment.getRequiredProperty("mail.password"));
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("notes@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(senderEmailId));
			message.setSubject(subject);
			message.setContent(bodyText, "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @return the environment
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	
}