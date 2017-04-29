package com.deep.mailpages;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import com.deep.configuration.ManualConfiguration;
import com.deep.mainclass.MainClass;
import com.deep.properties.MailProperties;

public class MainPage {
	
	public void mainpage(MailProperties properties)
	{
		Scanner input = new Scanner(System.in);
		
		String temp = properties.getUsename().substring(
				properties.getUsename().indexOf('@')+1, 
				properties.getUsename().length());
	
		String serverName = temp.substring(0, temp.indexOf('.'));

		Properties props = new Properties();
	    props.setProperty("mail.store.protocol", "imaps");
	  
	    try
	    {
		    Session session = Session.getInstance(props, null);
		    Store store = session.getStore();
		    store.connect(properties.getImapHost(), properties.getUsename(), properties.getPassword());
		    
		    Folder[] folderList = null;
	        
		    String list = "";
		    
	        switch(serverName)
			{
				case "gmail":
					folderList = store.getFolder("[Gmail]").list();
					list = "1. Compose Mail \n2. Inbox ";
					break;
				case "live":
				case "hotmail":
				case "outlook":
				case "gperi":
					folderList = store.getDefaultFolder().list();
					list = "1. Compose Mail";
			        break;
				default:
					try
					{
						folderList = store.getDefaultFolder().list();
					}catch(Exception e)
					{
						System.out.println("Check yor host and ports");
						new ManualConfiguration().manualConfiguration();
					}
					break;
			}
			
	        System.out.println();
	        System.out.println("------- Welcome to " + serverName + "-------");
	        
	        System.out.println(list);  
	        
	        int t = 0,p = 0,a = 2;
	        
	        if(serverName.equals("gmail"))
	        {
	        	t = 1;
	        	p = 1;
	        	a = 3;
	        }
	        for (int i = 0; i < folderList.length; i++) {
	            System.out.println((t+2)+ ". " + folderList[i].getName());
	            t++;
	        }
	        
			System.out.print("Enter Your Choices : ");
			int folderchoice = input.nextInt();
			Folder inbox = null;
			
			if(folderchoice == 1)
			{
				new ComposeMail().composemail(properties);
			}
			else if (folderchoice > 1 && folderchoice < folderList.length+a)
			{
					if (folderchoice == 2 && p == 1)
					{
						inbox = store.getFolder("INBOX");
					    System.out.println("Inbox");
					}
					else
					{
					   inbox = store.getFolder(folderList[folderchoice-2-p].getFullName());
						System.out.println(folderList[folderchoice-2-p].getFullName());
					}
				    inbox.open(Folder.READ_WRITE);
				   
			        Message[] messages = inbox.getMessages();
			        int end = check(messages.length);
			        
			        Message[] mess = messages;
			        int j = messages.length-1;
			        for(int i = 0 ; i < messages.length ; i++)
			        {
			        	mess[i] = messages[j];
			        	j--;
			        }
   			        
			        new MailView().mailview(mess,0,end,properties);
			}
			else
			{
				System.out.println("Enter Correct Choice..");
				new MainPage().mainpage(properties);
			}
			
	    }catch(Exception e)
	    {
	    	System.out.println("Something wrong please try again." + e);
	    }
		//input.close();
	}

		int check(int a)
		{
			if(a > 5)
				return 5;
			return a;
		}
}
