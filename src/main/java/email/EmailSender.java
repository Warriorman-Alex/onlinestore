package email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

public class EmailSender {
    private static final Logger logger = Logger.getLogger(EmailSender.class);
    
    Properties mailServerProperties;
    Session getMailSession;
    MimeMessage generateMailMessage;

    public void sendMessage(String to, String order) throws MessagingException {
        
        logger.debug("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "465");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        
        logger.debug("Mail Server Properties have been setup successfully..");

        logger.debug("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("warriorman_2006@ukr.net"));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("warriorman_2006@ukr.net"));
        generateMailMessage.setSubject(order);
        String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
        generateMailMessage.setContent(emailBody, "text/html");
        logger.debug("Mail Session has been created successfully..");
        
        logger.debug("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

		// Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.ukr.net", "warriorman_2006@ukr.net", "45s84as7");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}


