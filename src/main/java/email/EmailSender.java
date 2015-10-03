package email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

public class EmailSender {

    private static final Logger logger = Logger.getLogger(EmailSender.class);
    Session mailSession;

    public void sendMessage(String to, String order) throws MessagingException {
        setMailServerProperties();
        draftEmailMessage();
        sendEmail();

    }

    private void setMailServerProperties() {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "586");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
    }

    private MimeMessage draftEmailMessage() throws AddressException, MessagingException {
        String to = "warriorman_2006@ukr.net";
        String emailSubject = "Test email subject";
        String emailBody = "Test";
        MimeMessage emailMessage = new MimeMessage(mailSession);
        /**
         * Set the mail recipients
         *
         */
        
        emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        
        emailMessage.setSubject(emailSubject);
        /**
         * If sending HTML mail
         *
         */
        emailMessage.setContent(emailBody, "text/html");
        /**
         * If sending only text mail
         *
         */
        //emailMessage.setText(emailBody);// for a text email
        return emailMessage;
    }

    private void sendEmail() throws AddressException, MessagingException {
        /**
         * Sender's credentials
         *
         */
        String fromUser = "openshift.onlinestore@gmail.com";
        String fromUserEmailPassword = "UAorders";

        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        /**
         * Draft the message
         *
         */
        MimeMessage emailMessage = draftEmailMessage();
        /**
         * Send the mail
         *
         */
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        logger.debug("Email sent successfully.");
    }
}
