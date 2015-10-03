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

    public void sendMessage(String to, String order) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.user", "openshift.onlinestore@gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("port", "25");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        try {
            Session smtpSession = Session.getDefaultInstance(props, null);
            smtpSession.setDebug(true);
            MimeMessage msg = new MimeMessage(smtpSession);
            msg.setText("Test");
            msg.setSubject("Test");
            msg.setFrom(new InternetAddress("openshift.onlinestore@gmail.com"));

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("warriorman_2006@ukr.net"));

            msg.saveChanges();
            Transport transport = smtpSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "openshift.onlinestore@gmail.com", "UAorders");
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            
        } catch (Exception mex) {            
            logger.debug(mex, mex);          
        }

    }

}
