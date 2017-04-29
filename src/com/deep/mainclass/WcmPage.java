package com.deep.mainclass;

import java.util.Scanner;

import com.deep.configuration.AutoConfiguration;
import com.deep.configuration.ManualConfiguration;

public class WcmPage {

	public void wcmpage()
	{
		Scanner in = new Scanner(System.in);
		int choice = 0;
				
		System.out.println("Welcome to My Mail System.");
	//	System.out.println("Developed By Deep Patel");
		System.out.println("Auto Configuration is available only for GMAIL , MICROSOFT , GPERI");
		
		do
		{
			System.out.println();
			System.out.println("1. Automatic Configuration");
			System.out.println("2. Manual Configuration");
			System.out.println("3. About");
			System.out.println("4. Exit");
			
			System.out.print("Enter Your Choice : ");
			
			choice = in.nextInt();
			switch(choice)
			{
				case 1:
					AutoConfiguration ac = new AutoConfiguration();
					ac.atoConfiguration();
					break;
				case 2:
					ManualConfiguration mc = new ManualConfiguration();
					mc.manualConfiguration();
					break;
				case 3:
					break;
				case 4:
					break;
				default:
					System.out.println("Enter Correct Choice..");
					break;
			}
		}while(choice != 4);
	}
}
