import java.io.*;
import java.net.*;
import java.util.*;

public class ClientSide
{
	static Scanner input = new Scanner(System.in);
	static int start = 1;
	static int count = 0;
	static int serverPort = 0;
	static String serverName = "";
	static String message = "";
	
	public static void main (String[] args)
	{		
		while(start == 1)
		{
			if(count > 0)
			{
				System.out.println("\nKeep sending message?");
				System.out.println("1 for yes, 2 for no");
				int answer = input.nextInt();
				
				if(answer == 1)
				{
					count++;
					refresh();
					inputInfo();
					sendData();					
				}
				
				else
				{
					start = 0;
					System.exit(0);
				}
			}
			
			else
			{
				System.out.println("Hi, this is a program that sends messages to open ports.");
				refresh();
				inputInfo();
				sendData();
				count++;
			}
		}
	}
	
	public static void refresh()
	{
		serverPort = 0;
		serverName = "";
		message = "";
	}
	
	public static void inputInfo()
	{		
		System.out.println("\nWhat is the IP address?");
		serverName = input.next();
		
		System.out.println("\nWhat is the server port?");
		serverPort = input.nextInt();
		input.nextLine();
		
		System.out.println("\nWhat is the message you wish to send?");
		message = input.nextLine();
	}
	
	public static void sendData()
	{
		try
		{                   
			System.out.println("\nSending the following: \n" + message + "\n\nTo: " + serverName + ", " + serverPort);
			
			Socket socketOne = new Socket(serverName, serverPort);//wait for server to accept
			DataOutputStream dos = new DataOutputStream(socketOne.getOutputStream());
			dos.writeUTF(message);
			
			System.out.println("\nSent!");
			socketOne.close();
			dos.close(); // and flush
		}

		catch(Exception e)
		{
			System.out.println("\n\nMessage not sent! Error!");
			System.out.println(e);
		}
	}
	
	//public void testData()
	{
		serverName = "10.83.247.210"; // either ASCII or numeric form is OK
		serverPort = 3250;
		message    = "Hi";
	}
}

