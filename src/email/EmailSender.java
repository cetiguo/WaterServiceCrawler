package email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender {
	public static int addressCount = 1;
	
	public Properties properties(){
		Properties prop = new Properties();
		prop.setProperty("debug", "false");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.host", "smtp.qq.com");
		return prop;
	}
	
	public static void sendMessage(String str){
		Properties prop = null;
		
		try{
			Session session = Session.getInstance(prop, new Authenticator(){
				@Override
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication("cetiguo@foxmail.com", "hqsplmtcgzjbbjbe");
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("cetiguo@foxmail.com"));
			msg.setSubject("停水通知");
			msg.setRecipient(RecipientType.TO, new InternetAddress("cetiguo@foxmail.com"));
			msg.setContent(str,"text/html;charset=UTF-8");
			Transport.send(msg);
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
}
