/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp;
import java.net.*;
import java.io.*;
import org.apache.logging.log4j.*;

public class client 
{
    private static final Logger loggerDemo = LogManager.getLogger(client.class.getName());
    public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		  String tenServer = "localhost";
	      int port = 9090;
	      try
	      {
	         System.out.println("Ket noi toi " + tenServer
	                             + " on port " + port);
                 loggerDemo.info("ket noi toi tenServer va port cho truoc");
	         Socket client = new Socket(tenServer, port);
	         System.out.println("Just connected to "
	                      + client.getRemoteSocketAddress());
                 loggerDemo.info("connercted tenServer/Dia chi ip:port");
	         OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out =
	                       new DataOutputStream(outToServer);

	         out.writeUTF("Hello from "
	                      + client.getLocalSocketAddress());
                 loggerDemo.info("viet hello from gui qua server");
	         InputStream inFromServer = client.getInputStream();
	         DataInputStream in = new DataInputStream(inFromServer);
	         System.out.println("Server says " + in.readUTF());
                 loggerDemo.info("nhan lai thong tin ben server viet");
	         client.close();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
              
//              loggerDemo.info("test file log ra console_client");
//              loggerDemo.error("test file log ra console_client");
//              loggerDemo.fatal("test file log ra console_client");
	}   
}
