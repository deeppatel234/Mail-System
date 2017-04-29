package com.deep.configuration;

import java.util.Scanner;

import com.deep.mailpages.MainPage;
import com.deep.properties.MailProperties;

public class ManualConfiguration {

		public void manualConfiguration()
		{
			
			System.out.println("------Manual Configuration------");
			
			System.out.println("Default Ports :  SMTP -> 25,587  IMAP -> 993   POP3 -> 995");
			
			Scanner getman = new Scanner(System.in);
			MailProperties properties = new MailProperties(); 
			
			System.out.print("E-Mail : ");
			String username = getman.nextLine();
			properties.setUsename(username);
			
			System.out.print("Password : ");
			String password = getman.nextLine();
			properties.setPassword(password);
			
			System.out.print("Enter SMTP Host : ");
			properties.setSmtpHost(getman.nextLine());
			System.out.print("Enter SMTP Port : ");
			properties.setSmtpPort(getman.nextLine());
			
			System.out.print("Enter IMAP Host : ");
			properties.setImapHost(getman.nextLine());
			System.out.print("Enter IMAP Port : ");
			properties.setImapPort(getman.nextLine());
			
			System.out.print("Enter POP3 Host : ");
			properties.setPop3Host(getman.nextLine());
			System.out.print("Enter POP3 Port : ");
			properties.setPop3pPort(getman.nextLine());
			
			new MainPage().mainpage(properties);
		}
}
