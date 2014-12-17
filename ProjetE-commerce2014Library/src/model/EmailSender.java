/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Alexandre
 */
public class EmailSender {
    private final static String username = "beerforthewinproject@gmail.com";
    private final static String nameSite = "BeerForTheWin";
    private final static String password = "projectJava2014";
    private final static Properties props = new Properties();
    static{
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    }
   
    public static void sendEmail(String emailFrom, String subject, String message) 
            throws MessagingException, UnsupportedEncodingException{
        Authenticator auth = new Authenticator() {       
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        
        send(session, emailFrom, subject, message);
    }
    
    private static void send(Session session, String toEmail, 
            String subject, String body) throws MessagingException, 
            UnsupportedEncodingException{
          MimeMessage msg = new MimeMessage(session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");
 
          msg.setFrom(new InternetAddress(username, nameSite));
          msg.setReplyTo(InternetAddress.parse(username, false));
          msg.setSubject(subject, "UTF-8");
          msg.setContent(body, "text/html");
 
          msg.setRecipients(Message.RecipientType.TO, 
                  InternetAddress.parse(toEmail, false));
          
          System.out.println("Message is ready");
          Transport.send(msg); 
 
          System.out.println("EMail Sent Successfully!!");      
    }
}