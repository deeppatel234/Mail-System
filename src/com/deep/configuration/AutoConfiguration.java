package com.deep.configuration;

import java.io.Console;
import java.util.Scanner;

import com.deep.mailpages.MainPage;
import com.deep.properties.MailProperties;

public class AutoConfiguration {

		public void atoConfiguration()
		{
			Scanner getprop = new Scanner(System.in);
			MailProperties properties = new MailProperties(); 
			
			System.out.println("\nEnter Your Credential");
			
			System.out.print("E-Mail : ");
			String username = getprop.nextLine();
			properties.setUsename(username);
			
			System.out.print("Password : ");
			
			String password = getprop.nextLine();
					
			String temp = properties.getUsename().substring(
							properties.getUsename().indexOf('@')+1, 
							properties.getUsename().length());
				
			String serverName = temp.substring(0, temp.indexOf('.'));
			
			MainPage mp = new MainPage();
			
			properties.setPassword(password);
			properties.setUsename(username);
			
			properties.setPop3pPort("995");
			properties.setImapPort("993");
			properties.setSmtpPort("587");
			
			switch(serverName)
			{
				case "gmail":
					properties.setSmtpHost("smtp.gmail.com");
					properties.setPop3Host("pop.gmail.com");
					properties.setImapHost("imap.gmail.com");
					mp.mainpage(properties);
					break;
					
				case "live":
				case "hotmail":
				case "outlook":
					properties.setSmtpHost("smtp-mail.outlook.com");
					properties.setSmtpPort("25");
					properties.setPop3Host("pop-mail.outlook.com");
					properties.setImapHost("imap-mail.outlook.com");
					mp.mainpage(properties);
					break;
					
				case "gperi":
					properties.setSmtpHost("smtp.office365.com");
					properties.setPop3Host("outlook.office365.com");
					properties.setImapHost("outlook.office365.com");
					mp.mainpage(properties);
					break;
					
				
				default:
					System.out.println("\n");
					System.out.println("Auto Configuration is not available for " + serverName);
					System.out.println("1. Manual Configuration");
					System.out.println("2. Try Again");
					System.out.print("Enter Your Choice : ");
					int c = getprop.nextInt();
					switch(c)
					{
						case 1:
							new ManualConfiguration().manualConfiguration();
							break;
						case 2:
							new AutoConfiguration().atoConfiguration();
							break;
						default:
							new AutoConfiguration().atoConfiguration();
							break;
					}
					break;
			}
    	}
}








