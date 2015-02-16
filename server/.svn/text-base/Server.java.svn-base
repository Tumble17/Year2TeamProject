package server;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

import serverlogin.ServerLogin;

/**
 * @author Rob Minford 1189213
 * 
 * 		   THe purpose of this class is to handle all
 *         incoming traffic from the clients, send all changes to the game to
 *         all clients connected, create enemy and other game data to run the
 *         multi-player aspect of 'TotalWar'.
 * 
 */
public class Server {

	// Fields to be used.

	// ArrayLists to hold all enemy, users and to work out scores.
	LinkedList<AIEnemyMulti> arrayOfEnemy = new LinkedList<AIEnemyMulti>();
	static LinkedList<InetAddress> users = new LinkedList<InetAddress>();
	LinkedList<Integer> scoreList = new LinkedList<Integer>();
	LinkedList<String> userScores = new LinkedList<String>();

	// Port number, socket and player number.
	private static int port1 = 7800;
	private static DatagramSocket skt;
	public static int numberOfPlayers = 1;
	public String incomingRequest;
	public String pass;
	public String username;
	public String password;
	public Thread requestThread1;

	// Enemies
	private AIEnemyMulti enemy1 = null;
	private AIEnemyMulti enemy2 = null;
	private AIEnemyMulti enemy3 = null;
	private AIEnemyMulti enemy4 = null;

	// Coordinates of players.
	int p1x = 200;
	int p1y = 100;
	int p2x = 200;
	int p2y = 100;
	int p3x = 200;
	int p3y = 100;
	int p4x = 200;
	int p4y = 100;

	// Player HP.
	int p1HP = 100;
	int p2HP = 100;
	int p3HP = 100;
	int p4HP = 100;

	// read/write for checking log in details.
	ServerLogin readwrite;

	// Booleans to check when user is dead.
	private boolean p1IsActive = true;
	private boolean p2IsActive = true;
	private boolean p3IsActive = true;
	private boolean p4IsActive = true;

	// keeps Scores for each player.
	private int p1Score = 0;
	private int p2Score = 0;
	private int p3Score = 0;
	private int p4Score = 0;

	/**
	 * Constructor for Server object. - Initialises the readWrite object. -
	 * Initialises the datagramSocket which is used for all traffic. - Adds
	 * Enemy into enemy Linked List. - Adds players scores to score Linked List.
	 */
	public Server(DatagramSocket sock) {

		readwrite = new ServerLogin();

		skt = sock;

		arrayOfEnemy.add(enemy1);
		arrayOfEnemy.add(enemy2);
		arrayOfEnemy.add(enemy3);
		arrayOfEnemy.add(enemy4);

		scoreList.add(p1Score);
		scoreList.add(p2Score);
		scoreList.add(p3Score);
		scoreList.add(p4Score);

	}

	/**
	 * Starts the server.
	 * 
	 * @throws SocketException
	 * @throws UnknownHostException
	 */

	public void initServer() {

		// Creates new Thread which handles listener socket.
		requestThread1 = new Thread(new Runnable() {

			public void run() {

				try {

					// byte array for incoming data;
					byte[] buffer1 = new byte[1000];

					while (true) {

						// Open up a port to listen from clients.
						DatagramPacket request = new DatagramPacket(buffer1,
								buffer1.length);
						skt.receive(request);

						incomingRequest = new String(buffer1);

						// Check if enemies have been initialised. Check user's
						// health. See Javadoc for each method at method body
						// (below).

						checkEnemy();

						checkHealth();

						// Check if request is login request.
						if (incomingRequest.charAt(0) == 'l'
								&& incomingRequest.charAt(1) == 'r') {

							String[] arr = incomingRequest.split(" ");
							username = arr[1];
							password = arr[2];

							// checks if the username and password are correct
							// and if true sends message to user which will
							// allow them to play the game.

							if (ServerLogin.checkPassword(username, password) == true) {

								System.out
										.println("A user had been logged in.");

								pass = "paUsername";
								byte[] logonTmpArr = pass.getBytes();

								DatagramPacket userCorrectLogon = new DatagramPacket(
										logonTmpArr, logonTmpArr.length,
										request.getAddress(), request.getPort());
								skt.send(userCorrectLogon);

							} else {

								System.out
										.println("A user has failed to log on");

								String pass = "faUsername";
								byte[] logonTmpArr = pass.getBytes();

								// If failed failed to log on send message to
								// client to alert the user.

								DatagramPacket userFailedLogon = new DatagramPacket(
										logonTmpArr, logonTmpArr.length,
										request.getAddress(), request.getPort());
								skt.send(userFailedLogon);

							}

						} else if (incomingRequest.charAt(0) == 'n'
								&& incomingRequest.charAt(1) == 'u') {

							// creates a new user, see method description below.
							createNewUser(incomingRequest, request);

						} else if (incomingRequest.charAt(0) == 'x') {
							// if the user quits, user is deleted from the user
							// array, game will go to 'waiting for players'
							// state.

							deleteUserFromArray(request.getAddress());

						} else if (incomingRequest.charAt(0) == ('g')) {
							// if the array user array is full, so the correct
							// number of players are logged and ready to play
							// then server starts to send information about
							// enemies to all players on.
							if (users.size() == numberOfPlayers) {

								sendEn(request, skt);
								enemy1.counter++;
								enemy2.counter++;
								enemy3.counter++;
								enemy4.counter++;

								// checks for any attacks against the users, if
								// so changes that users HP accordingly, full
								// method in AIEnemyMulti class.
								for (AIEnemyMulti en : arrayOfEnemy) {
									// the user of the switch statement is if
									// the game is 1,2,3 or 4 players.
									switch (numberOfPlayers) {
									case 1:
										numberOfPlayers = 1;
										p1HP = AIEnemyMulti.checkCollision(en,
												p1x, p1y, users.get(0), p1HP);
										break;
									case 2:
										numberOfPlayers = 2;
										p1HP = AIEnemyMulti.checkCollision(en,
												p1x, p1y, users.get(0), p1HP);
										p2HP = AIEnemyMulti.checkCollision(en,
												p2x, p2y, users.get(1), p2HP);
										break;
									case 3:
										numberOfPlayers = 3;
										p1HP = AIEnemyMulti.checkCollision(en,
												p1x, p1y, users.get(0), p1HP);
										p2HP = AIEnemyMulti.checkCollision(en,
												p2x, p2y, users.get(1), p2HP);
										p3HP = AIEnemyMulti.checkCollision(en,
												p3x, p3y, users.get(2), p3HP);

										break;
									case 4:
										numberOfPlayers = 4;
										p1HP = AIEnemyMulti.checkCollision(en,
												p1x, p1y, users.get(0), p1HP);
										p2HP = AIEnemyMulti.checkCollision(en,
												p2x, p2y, users.get(1), p2HP);
										p3HP = AIEnemyMulti.checkCollision(en,
												p3x, p3y, users.get(2), p3HP);
										p4HP = AIEnemyMulti.checkCollision(en,
												p4x, p4y, users.get(3), p3HP);

										break;

									default:
										System.out.println();
										break;

									}

								}

							}

						} else if (incomingRequest.charAt(0) == 'a'
								&& incomingRequest.charAt(1) == 't') {

							// will set the new enemy health and send to all
							// players for update.
							editEnemy(incomingRequest, request);
							String tmp = "an"
									+ findIndexInUserArray(request.getAddress());
							for (int j = 0; j < users.size(); j++) {
								DatagramPacket tmpPacket = new DatagramPacket(
										tmp.getBytes(), tmp.getBytes().length,
										users.get(j), port1);
								skt.send(tmpPacket);
							}

						} else if (incomingRequest.charAt(0) == 'z') {

							// sends user HP of all players to everyone.
							sendUserHP(0, p1HP, skt, request);
							sendUserHP(1, p2HP, skt, request);
							sendUserHP(2, p3HP, skt, request);
							sendUserHP(3, p4HP, skt, request);
						} else if (incomingRequest.charAt(0) == 'y') {

							// this is called when the game ends, will send
							// score information to all users.
							String[] nameSplit = incomingRequest.split("  ");
							String username = nameSplit[1];
							int usernameIndex = users.indexOf(request
									.getAddress());

							String usernameScore = username + "  "
									+ scoreList.get(usernameIndex);

							userScores.add(usernameScore);

							System.out.println("scores send");

							if (userScores.size() > 1) {
								String buildString = "s  " + userScores.get(0)
										+ "  " + userScores.get(1) + "  ";
								for (int j = 0; j < users.size(); j++) { // start

									DatagramPacket send = new DatagramPacket(
											buildString.getBytes(), buildString
													.getBytes().length, users
													.get(j), port1);

									skt.send(send);

								}
								// clears fields and breaks from the listener
								// loop.
								users.clear();
								userScores.clear();
								scoreList.clear();
								arrayOfEnemy.clear();
								break;

							}

						} else {

							// breaking up the request so data can be used.
							String personPlayerNumber = Integer
									.toString(findIndexInUserArray(request
											.getAddress()));
							String personSend = personPlayerNumber + "   "
									+ incomingRequest + "   ";
							String arr[] = incomingRequest.split("   ");
							int x = Integer.parseInt(arr[0]);
							int y = Integer.parseInt(arr[1]);

							int personCheck = Integer
									.parseInt(personPlayerNumber);

							// sets players coordinates to fields.
							if (personCheck == 0) {
								p1x = x;
								p1y = y;

							}
							if (personCheck == 1) {
								p2x = x;
								p2y = y;

							}
							if (personCheck == 2) {
								p3x = x;
								p3y = y;

							}
							if (personCheck == 3) {
								p4x = x;
								p4y = y;

							}

							// If the user is not in the array and the array is
							// full. i.e. maximum number of players. sends a
							// message to the client alerting them.
							if (checkArray(request.getAddress()) == false
									&& (users.size() >= numberOfPlayers)) {

								String full = "no room, already "
										+ numberOfPlayers + " players";

								DatagramPacket send = new DatagramPacket(full
										.getBytes(), full.getBytes().length,
										request.getAddress(), request.getPort());
								skt.send(send);

							}

							// If the user is not in the array, add them to the
							// array.
							if (checkArray(request.getAddress()) == false
									&& !(users.size() >= numberOfPlayers)) {
								users.add(request.getAddress());
							}

							// If they are in the array and the array is full.
							if (users.size() == numberOfPlayers) {

								// Sends player coordinates to all users.
								for (int j = 0; j < users.size(); j++) {

									DatagramPacket send = new DatagramPacket(
											personSend.getBytes(), personSend
													.getBytes().length, users
													.get(j), port1);

									skt.send(send);

								}

								// Not enough users. Sends an alert to the user.
							} else {
								String noUsers = "not enough users, please wait";
								DatagramPacket send = new DatagramPacket(
										noUsers.getBytes(),
										noUsers.getBytes().length, request
												.getAddress(), port1);
								skt.send(send);

							}

						}

						// if the players are not active then end the game.
						if (p1IsActive == false && p2IsActive == false
								&& p3IsActive == false && p4IsActive == false) {
							sendEndState();
							// users.clear();
							// skt.close();

						}

					}

				} catch (Exception e) {
					System.out.println("exception thrown by server");
					e.printStackTrace();
				}

			}

			/**
			 * When the game is over sends a message to all clients so a game
			 * over state screen can be displayed.
			 * 
			 * @throws IOException
			 */
			private void sendEndState() throws IOException {

				System.out.println("game over");

				for (int i = 0; i < users.size(); i++) {

					String tmp = "go";
					DatagramPacket send = new DatagramPacket(tmp.getBytes(),
							tmp.getBytes().length, users.get(i), port1);
					skt.send(send);

				}
			}

			/**
			 * Checks the health of the users, if someone is dead then calls the
			 * isDead method (see below) and sets the state to inActive.
			 * 
			 * @throws IOException
			 */
			public void checkHealth() throws IOException {
				if (p1HP <= 0) {
					isDead(0);
					p1IsActive = false;
				}

				if (p2HP <= 0) {
					isDead(1);
					p2IsActive = false;

				}
				if (p3HP <= 0) {
					isDead(2);
					p3IsActive = false;

				}
				if (p4HP <= 0) {
					isDead(3);
					p4IsActive = false;

				}

			}

			/**
			 * If someone has died this method will be called from
			 * checkHealth(), a message is sent to all users about the player
			 * death so they will not be painted.
			 * 
			 * @param i
			 * @throws IOException
			 */
			private void isDead(int i) throws IOException {

				users.get(i);
				String dead = "dead";
				DatagramPacket send = new DatagramPacket(dead.getBytes(), dead
						.getBytes().length, users.get(i), 7801);

				skt.send(send);
				// loops though array of users.
				for (int j = 0; j < users.size(); j++) {
					String deleteOtherUser = "od  " + Integer.toString(i)
							+ "  ";
					DatagramPacket whichUser = new DatagramPacket(
							deleteOtherUser.getBytes(), deleteOtherUser
									.getBytes().length, users.get(j), port1);

					skt.send(whichUser);

				}

			}

			/**
			 * deletes user from user array (if they quit).
			 * 
			 * @param inetAddress
			 * @throws IOException
			 */
			private void deleteUserFromArray(InetAddress inetAddress)
					throws IOException {

				users.remove(inetAddress);
			}

			/**
			 * When request to create a new user this method is called. It takes
			 * the string, breaks it into username and password then saves it in
			 * the input.txt file. Before the username and password are saved
			 * the method checks if they already exists. A message is sent back
			 * to the client accordingly.
			 * 
			 * @param testString
			 * @param request
			 * @throws IOException
			 */
			private void createNewUser(String testString, DatagramPacket request)
					throws IOException {
				String[] arr = testString.split(" ");
				String username = arr[1];
				String password = arr[2];

				if (ServerLogin.searchUsername(username)) {
					String test = "u&p bad";
					DatagramPacket send = new DatagramPacket(test.getBytes(),
							test.getBytes().length, request.getAddress(),
							request.getPort());

					skt.send(send);
				} else {
					ServerLogin.createNewUser(username, password);
					String test = "u&p good";
					DatagramPacket send = new DatagramPacket(test.getBytes(),
							test.getBytes().length, request.getAddress(),
							request.getPort());

					skt.send(send);
				}

			}

			/**
			 * Handles when a user is attacked by an enemy. Depending on which
			 * user has been attacked 20is deducted from the relevant HP.
			 * 
			 * @param input
			 * @param request
			 */
			private void editEnemy(String input, DatagramPacket request) {
				String[] tmp = input.split("  ");
				int i = Integer.parseInt(tmp[1]);

				request.getAddress();
				int index = users.indexOf(request.getAddress());

				int score = scoreList.get(index);

				if (i == 0) {
					enemy1.hitPoints -= 20;
					if (enemy1.hitPoints <= 0) {
						scoreList.set(index, score += 100);
						enemy1.isActive = false;
						enemy1 = null;

					}
				}
				if (i == 1) {
					enemy2.hitPoints -= 20;

					if (enemy2.hitPoints <= 0) {
						scoreList.set(index, score += 100);
						enemy2.isActive = false;
						enemy2 = null;

					}
				}
				if (i == 2) {
					enemy3.hitPoints -= 20;
					if (enemy3.hitPoints <= 0) {
						scoreList.set(index, score += 100);
						enemy3.isActive = false;
						enemy3 = null;

					}
				}
				if (i == 3) {
					enemy4.hitPoints -= 20;
					if (enemy4.hitPoints <= 0) {
						scoreList.set(index, score += 100);
						enemy4.isActive = false;
						enemy4 = null;
					}
				}

			}

		});

		requestThread1.start();

	}

	/**
	 * Runs the enemyMove method, then sends coordinates of enemies out to all
	 * players
	 * 
	 * @param request
	 * @param skt
	 * @throws IOException
	 */
	private void sendEn(DatagramPacket request, DatagramSocket skt)
			throws IOException {

		enemyMove();
		// 1st enemy
		String iden = "0 ";
		String hp = Integer.toString(enemy1.gethp()) + " ";
		String xVal = String.valueOf(enemy1.xVal) + " ";
		String yVal = String.valueOf(enemy1.yVal) + " ";
		String isDead = String.valueOf(enemy1.isActive) + " ";
		String isAttacking = String.valueOf(enemy1.isAttacking) + " ";
		String toSend = iden + xVal + yVal + hp + isDead + isAttacking;

		String test = "e " + toSend;
		DatagramPacket ens = new DatagramPacket(test.getBytes(),
				test.getBytes().length, request.getAddress(), request.getPort());

		skt.send(ens);

		// Server2nd enemy
		String iden2 = "1 ";
		String hp2 = Integer.toString(enemy2.gethp()) + " ";
		String xVal2 = String.valueOf(enemy2.xVal) + " ";
		String yVal2 = String.valueOf(enemy2.yVal) + " ";
		String isDead2 = String.valueOf(enemy2.isActive) + " ";
		String isAttacking2 = String.valueOf(enemy2.isAttacking) + " ";

		String toSend2 = iden2 + xVal2 + yVal2 + hp2 + isDead2 + isAttacking2;

		String test2 = "e " + toSend2;
		DatagramPacket ens2 = new DatagramPacket(test2.getBytes(),
				test2.getBytes().length, request.getAddress(),
				request.getPort());

		skt.send(ens2);

		// 3rd enemy
		String iden3 = "2 ";
		String hp3 = Integer.toString(enemy3.gethp()) + " ";
		String xVal3 = String.valueOf(enemy3.xVal) + " ";
		String yVal3 = String.valueOf(enemy3.yVal) + " ";
		String isDead3 = String.valueOf(enemy3.isActive) + " ";
		String isAttacking3 = String.valueOf(enemy3.isAttacking) + " ";

		String toSend3 = iden3 + xVal3 + yVal3 + hp3 + isDead3 + isAttacking3;

		String test3 = "e " + toSend3;
		DatagramPacket ens3 = new DatagramPacket(test3.getBytes(),
				test3.getBytes().length, request.getAddress(),
				request.getPort());

		skt.send(ens3);
		// 4th enemy
		String iden4 = "3 ";
		String hp4 = Integer.toString(enemy4.gethp()) + " ";
		String xVal4 = String.valueOf(enemy4.xVal) + " ";
		String yVal4 = String.valueOf(enemy4.yVal) + " ";
		String isDead4 = String.valueOf(enemy4.isActive) + " ";
		String isAttacking4 = String.valueOf(enemy4.isAttacking) + " ";

		String toSend4 = iden4 + xVal4 + yVal4 + hp4 + isDead4 + isAttacking4;

		String test4 = "e " + toSend4;
		DatagramPacket ens4 = new DatagramPacket(test4.getBytes(),
				test4.getBytes().length, request.getAddress(),
				request.getPort());

		skt.send(ens4);

	}

	/**
	 * Moves enemy to chosen user.
	 */
	private void enemyMove() {
		for (AIEnemyMulti en : arrayOfEnemy) {
			if (en.target == 0) {
				if (p1IsActive == true) {
					en.pursue(p1x, p1y);
				} else
					en.target++;
			}
			if (en.target == 1) {
				if (p2IsActive == true) {
					en.pursue(p2x, p2y);
				} else
					en.target--;
			}
			if (en.target == 2) {
				if (p3IsActive == true) {
					en.pursue(p1x, p1y);
				} else
					en.target++;
			}
			if (en.target == 3) {
				if (p4IsActive == true) {
					en.pursue(p1x, p1y);
				} else
					en.target++;
			}
		}

	}

	/**
	 * Checks if an enemy is dead then creates new enemy objects in the array.
	 */
	public void checkEnemy() {

		if (enemy1 == null) {

			enemy1 = new AIEnemyMulti();
			enemy1.spawn();
			arrayOfEnemy.set(0, enemy1);

		}

		if (enemy2 == null) {

			enemy2 = new AIEnemyMulti();
			enemy2.spawn();
			arrayOfEnemy.set(1, enemy2);
		}
		if (enemy3 == null) {

			enemy3 = new AIEnemyMulti();
			enemy3.spawn();
			arrayOfEnemy.set(2, enemy3);
		}
		if (enemy4 == null) {

			enemy4 = new AIEnemyMulti();
			enemy4.spawn();
			arrayOfEnemy.set(3, enemy4);

		}
	}

	/**
	 * Checks for a user in an array by their InetAddress.
	 * 
	 * @param address
	 * @return boolean if user is in array.
	 */
	private boolean checkArray(InetAddress address) {
		boolean x = false;
		for (int i = 0; i < users.size(); i++) {
			if (address.equals(users.get(i))) {
				x = true;
			}

		}
		return x;
	}

	/**
	 * Finds the index of a user in the array by their InetAddress.
	 * 
	 * @param inet
	 * @return integer index of array.
	 */
	private int findIndexInUserArray(InetAddress inet) {
		return users.indexOf(inet);
	}

	/**
	 * Sends the user HP to the client.
	 * 
	 * @param index
	 * @param hp
	 * @param skt
	 * @param request
	 * @throws IOException
	 */
	private void sendUserHP(int index, int hp, DatagramSocket skt,
			DatagramPacket request) throws IOException {

		String x = "z  " + Integer.toString(index) + "  "
				+ Integer.toString(hp) + "  ";

		DatagramPacket send = new DatagramPacket(x.getBytes(),
				x.getBytes().length, request.getAddress(), port1);
		skt.send(send);

	}

}