/**
 * 
 */
package com.cplsystems.stock.services;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */
@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendHTMLMail(final String to, final String from, final String subject, final String message) {

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
        mailSender.send(preparator);
	}
	
}
