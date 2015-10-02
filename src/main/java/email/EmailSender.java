package email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    static final String ENCODING = "UTF-8";

    private String subject = "Subject";
    private String content = "Test";

    private final String smtpHost = "smtp.gmail.com";
    private final String smtpPort = "587";

    private final String address = "openshift.onlinestore@gmail.com";
    private final String login = "openshift.onlinestore";
    private final String password = "UAorders";

    public void sendMessage(String to) throws MessagingException {
        
        Authenticator auth = new MyAuthenticator(login, password);
        Properties props = System.getProperties(); 
        props.put("mail.smtp.port", smtpPort); 
        props.put("mail.smtp.host", smtpHost); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.mime.charset", ENCODING); 
        Session session = Session.getDefaultInstance(props, auth);
        
        Message msg = new MimeMessage(session); 
        msg.setFrom(new InternetAddress(address)); 
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
        msg.setSubject(subject); 
        msg.setText(content); 
        Transport.send(msg);
    }

    class MyAuthenticator extends Authenticator {

        private String user;
        private String password;

        MyAuthenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String user = this.user;
            String password = this.password;
            return new PasswordAuthentication(user, password);
        }
    }
}
