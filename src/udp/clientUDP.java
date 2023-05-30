/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udp;

/**
 *
 * @author Admin
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import org.apache.logging.log4j.*;
public class clientUDP 
{
    private static final Logger loggerDemo = LogManager.getLogger(clientUDP.class.getName());
	private static int SERVER_PORT = 9090;
	private static int CLIENT_PORT = 5001;
	private static DatagramSocket socket;

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub

		System.out.print("Connected to server on port "+ SERVER_PORT);
                loggerDemo.info("client ket noi den port cua server");
		socket = new DatagramSocket(CLIENT_PORT);
		InetAddress serverAddress = InetAddress.getByName("localhost");
		new Thread(() -> {
			while (true) {
				try {
					byte[] buf = new byte[1024];
					DatagramPacket packet = new DatagramPacket(buf, buf.length);
					socket.receive(packet);
					
					String received = new String(packet.getData(), 0, packet.getLength());
					System.out.println("Server: "+ received);
                                        loggerDemo.info("server gui tin nhan cho client");
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		//
		Scanner scanner = new Scanner(System.in);
		//
		new Thread(() -> {
			while (true) {
				String message = scanner.nextLine();
				byte[] buf = message.getBytes();
				DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddress, SERVER_PORT);
				try {
					socket.send(packet);
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}

