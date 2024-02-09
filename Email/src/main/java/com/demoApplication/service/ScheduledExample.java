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
public class ScheduledExample {

   
    private String from ="s170692@rguktsklm.ac.in";


    public boolean sendEmail() {
        boolean success = false;

        // Define email content
        String message = "Your email message cron";
        String subject = "Your email subject";
        String to = "vijayguntagani692@gmail.com";

        // Get the system properties.
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

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
            success = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
