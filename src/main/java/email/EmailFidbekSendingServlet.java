
package email;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet("/EmailFidbekSendingServlet")
public class EmailFidbekSendingServlet extends HttpServlet{
     private static final Logger logger = Logger.getLogger(EmailFidbekSendingServlet.class);
         
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields        
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
 
        String resultMessage = "";
        
        EmailSender sender = new EmailSender();
 
        try {
            sender.sendMessageContactUser(subject, content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            logger.debug("Exception", ex);
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/WEB-INF/view/result.jsp").forward(
                    request, response);
        }
    }
}
