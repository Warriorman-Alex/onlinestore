package email;


import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class EmailSender {

    private static final Logger logger = Logger.getLogger(EmailSender.class);

    private final String username = "openshift.onlinestore@gmail.com";
    private final String password = "UAorders";

    public void sendMessageConfirmOrder(String to, String order, String content) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Thank you for your purchase. Order number - " + order);

            
            message.setContent(content, "text/html");

            Transport.send(message);

            logger.debug("Email message send successfully");

        } catch (MessagingException e) {
            logger.debug("Exception", e);
        }

    }
    
    public void sendMessageContactUser(String subject, String message){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(username));
            mess.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(username));
            mess.setSubject(subject);
            
            mess.setText(message);

            Transport.send(mess);

            logger.debug("Email message send successfully");

        } catch (MessagingException e) {
            logger.debug("Exception", e);
        }
    }
    
    public void sendConfirmMessage(){
        
    }
            
}
