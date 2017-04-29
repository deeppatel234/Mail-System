package com.deep.mailpages;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Scanner;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;

import com.deep.properties.MailProperties;

public class MailView {

	public void mailview(Message[] messages,int start,int end,MailProperties properties)
	{		
		
		try
		{
				for (int i = start; i < end; i++) {  
		        	Message message = messages[i];  
		        	System.out.println();
		        	System.out.println("---------------------------------"); 
		        	System.out.println("Email Number " + (i + 1));        
		        	System.out.println("Subject: " + message.getSubject()); 
		        	System.out.println("From: " + message.getFrom()[0]); 
		      //  	System.out.println("Text: " + message.getContent().toString());          
				}
				
				System.out.println("1.View Mail    2.Next    3.Prev.   4.Back");
				Scanner in = new Scanner(System.in); 
				int num = in.nextInt();
				
				switch(num)
				{
					case 1:
						int c = in.nextInt();
						if (c > start && c < end)
						{
						   	System.out.println();
				        	System.out.println("=================================="); 
				        	System.out.println("Email Number " + (c));        
				        	System.out.println("Subject: " + messages[c-1].getSubject()); 
				        	System.out.println("From: " + messages[c-1].getFrom()[0]); 
				        	System.out.println("Text: " + messages[c-1].getContent().toString());
				        	
				        	String contentType = messages[c-1].getContentType();
				   
				        	int e;
							   
				        	if (contentType.contains("multipart")) {
				        		System.out.println("1.Download Attachment    2.Reply    3.Forward    4.Delete    5.Back   ");
				        		e = in.nextInt();
				        		switch(e)
					        	{
					        			case 1:
					        				Multipart multiPart = (Multipart) messages[c-1].getContent();
					        				System.out.println("Downloading......");
					        				for (int i = 0; i < multiPart.getCount(); i++) {
					        				    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
					        				    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
					        				    	String destFilePath = "A:/Attachment/" + part.getFileName();
					        				    	
					        				    	
					        				    	FileOutputStream output = new FileOutputStream(destFilePath);
					        				    	 
					        				    	InputStream input = part.getInputStream();
					        				    	 
					        				    	byte[] buffer = new byte[409600000];
					        				    	 
					        				    	int byteRead;
					        				    	 
					        				    	while ((byteRead = input.read(buffer)) != -1) {
					        				    	    output.write(buffer, 0, byteRead);
					        				    	}
					        				    	output.close();
					        				    }
					        				}
					        				System.out.println("Download Complate");
					        				break;
					        			case 2:
					        				new ReplayMail().replaymail(properties,messages[c-1]);
					        				break;
					        			case 3:
					        				new ForwardMail().forwardmail(properties,messages[c-1]);
					        				break;
					        			case 4:
					        				System.out.println("Hello");
					        				Scanner in1 = new Scanner(System.in);
					        				String d = in1.nextLine();
					        				if(d.equals("y"))
					        				{
					        				   messages[c-1].setFlag(Flags.Flag.DELETED, true);
					        				   System.out.println("Mail Deleted.");
					        				}
					        				break;
					        			default:
					        				mailview(messages, start, end,properties);
					        				break;
					        	}
				        	}
				        	else
				        	{
				        		System.out.println("1.Reply    2.Forward    3.Back    4.Delete");
				        		e = in.nextInt();
				        		switch(e)
					        	{
					        			case 1:
					        				new ReplayMail().replaymail(properties,messages[c-1]);
					        				break;
					        			case 2:
					        				new ForwardMail().forwardmail(properties,messages[c-1]);
					        				break;
					        			case 4:
					        				System.out.println("Hellow");
					        				Scanner in2 = new Scanner(System.in);
					        				String d = in2.nextLine();
					        				if(d.toLowerCase().charAt(0) == 'y')
					        				{
					        				   messages[c-1].setFlag(Flags.Flag.DELETED, true);
					        				   System.out.println("Mail Deleted.");
					        				}
					        			break;
					        			default:
					        				mailview(messages, start, end,properties);
					        				break;
					        	}
				        	}
						}
						else
						{
							System.out.println("Enter correct choice..");							
						}
						break;
					case 2:
						if(messages.length > end+5)
							mailview(messages, end, end+5,properties);
						else
							mailview(messages, end, messages.length,properties);
						break;
					case 3:
						if(start > 5)
							mailview(messages, start-5, start,properties);
						else
							mailview(messages, 0, end, properties);
						break;
					case 4:
						new MainPage().mainpage(properties);
					default:
						mailview(messages, start, end,properties);
						break;
				}
								
				mailview(messages, start, end,properties);
				
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
