package jUnitTesting;

import static org.junit.Assert.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.junit.Before;
import org.junit.Test;
import server.Server;

public class ServerLoginTest {

	private Server server;
	private String localHost;
	private InetAddress host;
	private int send;
	private DatagramSocket skt;
	private String check;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		send = 7800;
		host = InetAddress.getByName(localHost);
		skt = new DatagramSocket(send);

		server = new Server(skt);
		server.initServer(); // sets up and runs the server

		String ask = ("lr rob rob "); // sample login request in the form 
										// 'lr username password'
		byte[] arr = ask.getBytes();
		DatagramPacket checkuser = new DatagramPacket(arr, arr.length, host,
				send);
		skt.send(checkuser);	// sends the request to the server

		byte[] buffer = new byte[1000];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		skt.receive(packet);	// receives server response

		check = new String(buffer);	// converts response to string
	}

	/**
	 * Test method for {@link server.Server}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		// if login is a success, server response is 'paUsername'
		assertTrue((check.charAt(0) == 'p' && check.charAt(1) == 'a'));	
		server.requestThread1.stop();
	}
}
