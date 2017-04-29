package com.deep.mailpages;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.deep.properties.MailProperties;

public class ComposeMail {
	
		public void composemail(MailProperties prop)
		{
			Scanner get = new Scanner(System.in);
			
			System.out.println();
			System.out.print("TO : ");
			String to = get.nextLine();
			System.out.print("Subject : ");
			String subject = get.nextLine();
			System.out.print("Message : ");
			String messages = get.nextLine();
		
			System.out.print("Dou u want to add attachment y/n :");
			char c = get.nextLine().charAt(0);
			
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

			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(prop.getUsename()));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
				message.setSubject(subject);
				message.setText(messages);
			
				if(c == 'y')
				{
					 System.out.print("Enter Path : ");
					 BodyPart mbp = new MimeBodyPart();
					 mbp.setText(messages);
									
					 Multipart mp = new MimeMultipart();
					 mp.addBodyPart(mbp);
						
					 mbp = new MimeBodyPart();
					 String filepath = get.nextLine();
					 
					 String filename = filepath.substring(filepath.lastIndexOf('\\')+1);
					 
					 DataSource source = new FileDataSource(filepath);
					 mbp.setDataHandler(new DataHandler(source));
					 mbp.setFileName(filename);
					 mp.addBodyPart(mbp);
					 
					 message.setContent(mp);
				}
							
					Transport.send(message);
					System.out.println("Mail Sent Succesfully.");
				} catch (Exception e) {
					System.out.println("Mail Sent Failed." + e);
				}

			new MainPage().mainpage(prop);
	}
}
