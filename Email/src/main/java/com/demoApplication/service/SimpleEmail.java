
package com.demoApplication.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class SimpleEmail {

	public boolean sendEmail(String message, String subject, String to) {

		// take a boolean value value and make it false
		boolean f = false;

		String from = "s170692@rguktsklm.ac.in";

		// Get the system properties.
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		System.out.println("Properties of our system: " + properties);

		// Step 1: To get the session object.
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("s170692@rguktsklm.ac.in", "vijay692");
			}
		});

		session.setDebug(true);

		// Step 2: Compose the message.
		MimeMessage m = new MimeMessage(session);

		try {

			// From mail.
			m.setFrom(new InternetAddress(from));

			// Adding recipient.
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Adding subject to message.
			m.setSubject(subject);

			// Adding text to message.
			m.setText(message);

			// Step 3: Send the message using transport.
			Transport.send(m);

			System.out.println("Send successfully");
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
