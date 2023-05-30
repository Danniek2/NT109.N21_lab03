/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp;

import java.net.*;
import java.io.*;
import org.apache.logging.log4j.*;

public class server extends Thread 
{

    private static final Logger loggerDemo = LogManager.getLogger(server.class.getName());
	private ServerSocket serverSocket;

	   public server(int port) throws IOException
	   {
	      serverSocket = new ServerSocket(port);
	      serverSocket.setSoTimeout(10000);
	   }

	   public void run()
	   {
	      while(true)
	      {
	         try
	         {
	             System.out.println("Waiting for client on port " +
	             serverSocket.getLocalPort() + "...");
                     loggerDemo.info("lang nghe client tu port 9090");
	             Socket server = serverSocket.accept();
	             System.out.println("Just connected to "
	                   + server.getRemoteSocketAddress());
                     loggerDemo.info("doi connected tu dia chi client");
	             DataInputStream in =
	                   new DataInputStream(server.getInputStream());
	             System.out.println(in.readUTF());
	             DataOutputStream out =
	                  new DataOutputStream(server.getOutputStream());
	             out.writeUTF("Thank you for connecting to "
	               + server.getLocalSocketAddress() + "\nGoodbye!");
	             server.close();
                     loggerDemo.info("viet thong diep gui qua client va goodbye");
	          }catch(SocketTimeoutException s)
	          {
	             System.out.println("Socket timed out!");
                     loggerDemo.info("het thoi gian time out");
	             break;
	          }catch(IOException e)
	          {
	             e.printStackTrace();
	             break;
	          }
	       }
	    }
	    public static void main(String [] args)
	    {
	       int port = 9090;
	       try
	       {
	          Thread t = new server(port);
	          t.start();
	       }catch(IOException e)
	       {
	    	   e.printStackTrace();
	       }
//              loggerDemo.info("test file log ra console_server");
//              loggerDemo.error("test file log ra console_server");
//              loggerDemo.fatal("test file log ra console_server");
	    }
}