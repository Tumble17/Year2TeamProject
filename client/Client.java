package client;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import menus.MultiMenu;
import character.User;
import multiplayer.GameOverStateMP;
import multiplayer.MultiPlayState;
import multiplayer.MultiPlayer;

/**
 * @author Rob Minford, Jack Dyer 1223659
 * 
 */
public class Client {

	DatagramSocket skt;

	public String serverAddress = "";
	int send = 7800;

	public static String username;

	public static String cords;
	static int userNum;
	static int updateX;
	static int updateY;
	// ENEMY DATA PASS
	static int enemyNum;
	static int upEnX;
	static int upEnY;
	static int upEnHP;
	static boolean upEnDead;

	InetAddress host = InetAddress.getByName(getServerAddress());

	public static LinkedList<User> arrOfUser = MultiPlayState.arrOfUser;

	/**
	 * Constructor, starts the thread which will listen for information from the
	 * server, also act on that information.
	 * 
	 * @param address
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public Client(String address) throws SocketException, UnknownHostException {

		this.serverAddress = address;
		MultiPlayer.isWaiting = false;

		skt = new DatagramSocket(send);

		Thread receive;
		receive = new Thread(new Runnable() {

			@Override
			public void run() {

				try {

					host = InetAddress.getByName(serverAddress);

					byte[] buffer = new byte[1000];
					while (true) {
						DatagramPacket packet = new DatagramPacket(buffer,
								buffer.length);
						skt.receive(packet);

						cords = new String(buffer);

						checklogin(cords);

						if (cords.startsWith("e")) {
							MultiPlayer.isWaiting = false;
							MultiPlayState.startEnemyPaint = true;
							// Splits the enemy data by a single space
							// Takes the enemy data and parses to usable
							// strings.

							String[] eX = cords.split(" ");

							setEnemyPosition(Integer.parseInt(eX[1]),
									Float.parseFloat(eX[2]),
									Float.parseFloat(eX[3]),
									Integer.parseInt(eX[4]),
									Boolean.parseBoolean(eX[5]),
									Boolean.parseBoolean(eX[6]));
							// if waiting to jion game.
						} else if (cords.startsWith("n")) {
							MultiPlayer.isWaiting = false;
							System.out.println("waiting to join game...");
						} else if (cords.startsWith("d")) {

							MultiPlayState.userIsActive = false;
							// when game is over.
						} else if (cords.startsWith("g")
								&& (cords.charAt(1) == 'o')) {

							MultiPlayer.userCl.sendUsername(Client.username);
							MultiPlayer.isGameOver = true;
							// if a user has died then they are not painted.
						} else if (cords.startsWith("o")) {

							String[] death = cords.split("  ");
							String user = death[1];

							MultiPlayState.arrOfUser.get(Integer.parseInt(user)).isPainted = false;
							// sets the scores.
						} else if (cords.startsWith("s")) {
							String[] scores = cords.split("  ");
							String firstUser = scores[1];
							int firstScore = Integer.parseInt(scores[2]);
							String secondUser = scores[3];
							int secondScore = Integer.parseInt(scores[4]);

							setScores(firstUser, firstScore, secondUser,
									secondScore);

						} else if (cords.startsWith("z")) {

							String[] usersHP = cords.split("  ");
							String index = usersHP[1];
							String health = usersHP[2];
							setUserHealth(Integer.parseInt(index),
									Integer.parseInt(health));

							// creating new username and password.
						} else if (cords.charAt(1) == '&') {

							if (cords.charAt(4) == 'b') {
								JOptionPane.showMessageDialog(null,
										"Username already Exists.",
										"Failed to Submit",
										JOptionPane.WARNING_MESSAGE);

							} else {
								JOptionPane.showMessageDialog(null,
										"Account Created");
								MultiMenu.closeNewUser();

							}

						} else if (cords.startsWith("a")) {
							MultiPlayer.isWaiting = false;

						} else if (cords.startsWith("0")
								|| cords.startsWith("1")) {

							// Sets the player positions.
							MultiPlayer.isWaiting = false;
							String[] x = cords.split("   ");

							setPlayerPosition(Integer.parseInt(String
									.valueOf(cords.charAt(0))), Integer
									.parseInt(x[1]), Integer.parseInt(x[2]));

						}
					}
				} catch (Exception e) {
				}
			}
		});
		receive.start();

	}

	/**
	 * Sets the user Health.
	 * 
	 * @param user
	 * @param health
	 */
	public void setUserHealth(int user, int health) {

		MultiPlayState.arrOfUser.get(user).hitPoints = health;

	}

	/**
	 * Sets player positions.
	 * 
	 * @param playerNumber
	 * @param x
	 * @param y
	 */
	public void setPlayerPosition(int playerNumber, int x, int y) {
		MultiPlayState.arrOfUser.get(playerNumber).xVal = (float) x;
		MultiPlayState.arrOfUser.get(playerNumber).yVal = (float) y;
	}

	/**
	 * Sets enemy position, these come in from the server.
	 * 
	 * @param enemyNum
	 * @param enX
	 * @param enY
	 * @param enHP
	 * @param enDead
	 * @param enAtt
	 */
	public void setEnemyPosition(int enemyNum, float enX, float enY, int enHP,
			boolean enDead, boolean enAtt) {
		MultiPlayState.arrOfEnemy.get(enemyNum).xVal = (float) enX;
		MultiPlayState.arrOfEnemy.get(enemyNum).yVal = (float) enY;
		MultiPlayState.arrOfEnemy.get(enemyNum).hitPoints = enHP;
		MultiPlayState.arrOfEnemy.get(enemyNum).isActive = enDead;
		MultiPlayState.arrOfEnemy.get(enemyNum).isAttacking = enAtt;

	}

	/**
	 * Sets the scores that come in at the end of the game.
	 * 
	 * @param first
	 * @param firstS
	 * @param second
	 * @param secondS
	 */
	public static void setScores(String first, int firstS, String second,
			int secondS) {
		GameOverStateMP.p1User = first;
		GameOverStateMP.p1Score = firstS;
		GameOverStateMP.p2User = second;
		GameOverStateMP.p2Score = secondS;
	}

	/**
	 * Closes the datagram socket.
	 */
	public void close() {
		skt.close();
	}

	/**
	 * SEnds the player coordinates to the server.
	 * 
	 * @param x
	 * @param y
	 */
	public void sendData(int x, int y) {
		try {
			String message = (Integer.toString(x) + "   " + Integer.toString(y) + "   ");

			byte[] b = message.getBytes();

			DatagramPacket request = new DatagramPacket(b, b.length, host, send);

			skt.send(request);

		} catch (IOException e) {
			System.out.println("unable to create connection");
			e.printStackTrace();
			skt.close();
		}

	}

	/**
	 * Sends requesting username and password to be checked by the server.
	 * 
	 * @param user
	 * @param password
	 * @throws IOException
	 */
	public void sendInfo(String user, String password) throws IOException {
		String ask = ("lr " + user + " " + password + " ");
		byte[] arr = ask.getBytes();
		DatagramPacket checkuser = new DatagramPacket(arr, arr.length, host,
				send);
		skt.send(checkuser);

	}

	/**
	 * If request comes from server to allow access to the game, this method
	 * will run through enabling buttons.
	 * 
	 * @param check
	 */
	public void checklogin(String check) {
		if (check.charAt(0) == 'p' && check.charAt(1) == 'a') {
			System.out.println("logged in");
			MultiMenu.showButtons();
			MultiMenu.loginButton.setEnabled(false);
			MultiMenu.newUserButton.setEnabled(false);

		}

		if (check.charAt(0) == 'f' && check.charAt(1) == 'a') {

			JOptionPane.showMessageDialog(null, "Login Unsuccessful.");
			MultiMenu.userField.setText("");
			MultiMenu.passField.setText("");
			username = null;

		}

	}

	/**
	 * Sends request to the server if the user wants to quit by the 'u' key.
	 * 
	 * @throws IOException
	 */
	public void sendClose() throws IOException {

		String ask = ("x");
		byte[] arr = ask.getBytes();
		DatagramPacket checkuser = new DatagramPacket(arr, arr.length, host,
				send);
		skt.send(checkuser);

	}

	/**
	 * Sends request if entering for a new user.
	 * 
	 * @param user
	 * @param password
	 * @throws IOException
	 */
	public void sendNewUser(String user, String password) throws IOException {
		String sendData = ("nu " + user + " " + password);

		byte[] arr = sendData.getBytes();
		DatagramPacket newUser = new DatagramPacket(arr, arr.length, host, send);
		skt.send(newUser);
	}

	/**
	 * SEnds request for the enemy details.
	 * 
	 * @throws IOException
	 */
	public void requestEnemy() throws IOException {

		String getEn = "g";
		DatagramPacket test = new DatagramPacket(getEn.getBytes(),
				getEn.getBytes().length, host, send);
		skt.send(test);

	}

	/**
	 * SEnds request for the user HP.
	 * 
	 * @throws IOException
	 */
	public void requestUsersHP() throws IOException {

		String getHPs = "z";
		DatagramPacket hps = new DatagramPacket(getHPs.getBytes(),
				getHPs.getBytes().length, host, send);
		skt.send(hps);
	}

	/**
	 * Sends if the client is attacking to the server.
	 * 
	 * @param enNum
	 * @param username
	 * @throws IOException
	 */
	public void sendAttack(int enNum, String username) throws IOException {

		String attack = "att  " + enNum + "  " + username + "  ";
		DatagramPacket attPacket = new DatagramPacket(attack.getBytes(),
				attack.getBytes().length, host, send);
		skt.send(attPacket);

	}

	/**
	 * SEnds the username to the server.
	 * 
	 * @param username
	 * @throws IOException
	 */
	public void sendUsername(String username) throws IOException {

		String name = "y  " + username + "  ";
		DatagramPacket usernamePacket = new DatagramPacket(name.getBytes(),
				name.getBytes().length, host, send);
		skt.send(usernamePacket);
	}

	/**
	 * @return serverAddress InetAddress
	 */
	public String getServerAddress() {
		return serverAddress;
	}

}
