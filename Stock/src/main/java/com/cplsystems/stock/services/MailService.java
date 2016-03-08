package com.cplsystems.stock.services;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(final String to, final String from, final String subject, final String message) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) {
				try {
					mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

					mimeMessage.setFrom(new InternetAddress(from));
					mimeMessage.setSubject(subject);
					mimeMessage.setText(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		this.mailSender.send(preparator);
	}

	public void sendMail(String to, String from, String subject, String content, String attachmentFile) {
		MimeMessage message = this.mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content);

			FileSystemResource file = new FileSystemResource(attachmentFile);
			helper.addAttachment(file.getFilename(), file);
		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		this.mailSender.send(message);
	}
}
