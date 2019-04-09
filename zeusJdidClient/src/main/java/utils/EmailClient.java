package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailClient {
  private static final String senderEmail = "zeuspi2019@gmail.com";//change with your sender email
  private static final String senderPassword = "Zeus20182019";//change with your sender password
                                                 
  public static String sendAsHtml(String to, String title, String html)  {
      System.out.println("Sending email to " + to);


      try {
          Session session = createSession();

          //create message using session
          MimeMessage message = new MimeMessage(session);
		prepareEmailMessage(message, to, title, html);
		   //sending message
	      Transport.send(message);
	      System.out.println("Done");
	  	return "ok"; 
      } catch (MessagingException e) {
		return "erreur";
	}

   
  }

  private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
          throws MessagingException {
      message.setContent(html, "text/html; charset=utf-8");
      message.setFrom(new InternetAddress(senderEmail));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
      message.setSubject(title);
  }

  private static Session createSession() {
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      Session session = Session.getInstance(props, new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(senderEmail, senderPassword);
          }
      });
      return session;
  }

}
