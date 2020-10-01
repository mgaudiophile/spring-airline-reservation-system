package com.synergisticit.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailService {

	private JavaMailSender mailSender;
	
	public MailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public SimpleMailMessage sendEmail(String email, String subject, String msg) {
		log.debug("sending email to: " + email);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(subject);
		message.setText(msg);
		
		mailSender.send(message);
		
		return message;
	}
}
