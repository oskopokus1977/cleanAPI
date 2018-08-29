package com.util;


import org.springframework.scheduling.annotation.Async;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


public class MailSender {

    public static void send(String subject, String text, String[] toEmailStr) {

        ResourceBundle mailProperty = PropertyResourceBundle.getBundle("mail");

        final String username = mailProperty.getString("username");
        final String password = mailProperty.getString("password");
        Properties props = new Properties();

        props.put("mail.smtp.host", mailProperty.getString("smtp.host"));
        props.put("mail.mime.charset", mailProperty.getString("mime.charset"));
        props.put("mail.smtp.socketFactory.port", mailProperty.getString("smtp.socketFactory.port"));
        props.put("mail.smtp.socketFactory.class", mailProperty.getString("smtp.socketFactory.class"));
        props.put("mail.smtp.auth", mailProperty.getString("smtp.auth"));
        props.put("mail.smtp.port", mailProperty.getString("smtp.port"));

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setSubject(subject);
            message.setText(text);

            //convert String[]->InternetAddress[]
            InternetAddress[] sentTo = new InternetAddress[toEmailStr.length];
            for (int i = 0; i < toEmailStr.length; i++) {
                sentTo[i] = new InternetAddress(toEmailStr[i]);
            }

            Transport.send(message, sentTo);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
