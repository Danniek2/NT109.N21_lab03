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
public class serverUDP 
{
    private static final Logger loggerDemo = LogManager.getLogger(serverUDP.class.getName());
	private static DatagramSocket socket;
	private static int PORT = 9090;
	public static InetAddress address;
	public static int port;

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub

		socket = new DatagramSocket(PORT);
		System.out.println("Server started on port "+ PORT);
                loggerDemo.info("server bat dau tao port");
		Scanner scanner = new Scanner(System.in);
	
	new Thread(() -> {
		while (true) {
			try {
				byte[] buf = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				address = packet.getAddress();
				port = packet.getPort();
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println("Client: "+ received);
                                loggerDemo.info("client ket noi vao va nhan tin");
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}).start();
	
	new Thread(() -> {
		while (true) {
			String message = scanner.nextLine();
			byte[] buf = message.getBytes();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
			try {
				socket.send(packet);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}).start();
	}
}
