package com.deep.mailpages;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.deep.properties.MailProperties;

public class ForwardMail {

		public void forwardmail(MailProperties prop,Message message)
		{
				Scanner get = new Scanner(System.in);
				
				System.out.println();
				System.out.print("TO : ");
				String to = get.nextLine();
				
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", prop.getSmtpHost());
				props.put("mail.smtp.port", prop.getSmtpPort());

				Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(prop.getUsename(),prop.getPassword());
					}
				  });

					try
					{
						  Message message2 = new MimeMessage(session);  
						  message2.setSubject("Fwd: " + message.getSubject());  
						  message2.addRecipient(Message.RecipientType.TO,  
						  new InternetAddress(to));  
						  
						  BodyPart messageBodyPart = new MimeBodyPart();  
						  messageBodyPart.setText("Oiginal message:\n\n");  
						  
						  Multipart multipart = new MimeMultipart();  
						  multipart.addBodyPart(messageBodyPart);  
						  
						  messageBodyPart = new MimeBodyPart();  
						  messageBodyPart.setDataHandler(message.getDataHandler());  
						  
						  multipart.addBodyPart(messageBodyPart);  
						  
						  message2.setContent(multipart);  
											    
						  Transport.send(message2);
						  System.out.println("message forwarded ....");
					}
					catch(Exception e)
						{
							System.out.println("message forward failed");
						}
				  
		}
}
