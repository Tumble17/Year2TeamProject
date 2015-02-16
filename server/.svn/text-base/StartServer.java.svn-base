package server;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author Rob Minford
 *
 */
public class StartServer {

	/**Starts the server.
	 * @param args
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws SocketException,
			UnknownHostException {
		DatagramSocket skt = new DatagramSocket(7800);

		Server server = new Server(skt);
		System.out.println("server starting");

		server.initServer();

	}

}