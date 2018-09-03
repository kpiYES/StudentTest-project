package com.app.service.util;

import com.app.exceptions.SendingMailException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private static final String ADRESSER_EMAIL = "mihailpopov003@gmail.com";
    private static final String ADRESSER_PASSWORD = "matata22";
    private static final String SUBJECT = "Results of passed test";
    private Properties properties;
    private String addresseeEmail;
    private String content;

    public EmailSender(String addresseeEmail, String content) {
        this.addresseeEmail = addresseeEmail;
        this.content = content;
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    public void sendEmail() {
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ADRESSER_EMAIL, ADRESSER_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ADRESSER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresseeEmail));
            message.setSubject(SUBJECT);
            message.setText(content);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new SendingMailException("Couldn't send email", e);
        }
    }
}
