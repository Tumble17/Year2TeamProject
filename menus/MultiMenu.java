package menus;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import client.Client;

import multiplayer.MultiPlayState;
import multiplayer.MultiPlayer;
import documentListeners.PassFieldListener;
import documentListeners.UserFieldListener;

/**
 * @author cxb264
 * Multiplayer menu accessed through GameMenu
 * Displays login and buttons
 */
@SuppressWarnings("serial")
public class MultiMenu extends JFrame {

	JFrame upgrades;
	static JFrame newUser;
	JFrame instructions;

	UserFieldListener userListener = new UserFieldListener();
	PassFieldListener passListener = new PassFieldListener();
	Client userCli;
	public static AppGameContainer appgc;
	
	static String username;
	static String server;

	/**
	 * get method for username
	 * @return username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @param username - string that the username is to be set to
	 */
	public void setUsername(String username) {
		MultiMenu.username = username;
	}

	/**
	 * Creates new MultiMenu and displays option pane for IP input
	 * Also creates new client for this user
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public MultiMenu() throws SocketException, UnknownHostException {
		String test = JOptionPane.showInputDialog(null, "Enter Server IP Address");
		
		userCli = new Client(test);
		initComponents();
	}

	/**
	 * Initialises the components of the MultiMenu
	 * Adds ActionListeners
	 */
	private void initComponents() {
		ImageIcon loginImage = new ImageIcon("sprites/loginButton.png");
		ImageIcon newUserImage = new ImageIcon("sprites/newUserButton.png");
		ImageIcon playImage = new ImageIcon("sprites/playButton.png");
		ImageIcon mainMenuImage = new ImageIcon("sprites/mainMenuButton.png");

		playButton = new JButton(playImage);
		loginButton = new JButton(loginImage);
		userField = new JTextField();
		usernameLabel = new JLabel();
		passwordLabel = new JLabel();
		passField = new JPasswordField();
		newUserButton = new JButton(newUserImage);
		messages = new JTextField();
		newUserLabel = new JLabel();
		mainMenuButton = new JButton(mainMenuImage);
		serverLabel = new JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Main");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 500;
		int height = 500;
		int x = (dim.width-width)/2;
		int y = (dim.height-height)/2;
		setLocation(x, y);					// sets position of window to centre of the user's screen
		
		setSize(width, height);

		playButton.setEnabled(false);
		
		playButton.addActionListener(new ActionListener() { 	// adds actionListener to playButton
            public void actionPerformed(ActionEvent e) {
					try {
						playButtonMouseClicked();
					} catch (SocketException e1) {
						e1.printStackTrace();
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (SlickException e1) {
						e1.printStackTrace();
					}
            }
        }); 
		
		mainMenuButton.addActionListener(new ActionListener() { 	// adds actionListener to mainMenuButton
			public void actionPerformed(ActionEvent e){
				GameMenu menu = new GameMenu();
				menu.setVisible(true);
				dispose();
			}
		});

		loginButton.addMouseListener(new java.awt.event.MouseAdapter() { 	// adds actionListener to loginButton
			public void mouseClicked(MouseEvent evt) {
				try {
					loginButtonMouseClicked();
				} catch (SocketException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		newUserButton.addActionListener(new ActionListener() { 	// adds actionListener to newUserButton
            public void actionPerformed(ActionEvent e) {
            	newUserButtonMouseClicked();
            }
        });

		userField.setToolTipText("Enter your username");
		userField.getDocument().addDocumentListener(userListener);

		passField.setToolTipText("Enter your password");
		passField.getDocument().addDocumentListener(passListener);
		
		usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
		usernameLabel.setText("Username:");

		passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
		passwordLabel.setText("Password:");
		
		messages.setEditable(false);
		
		newUserLabel.setForeground(new java.awt.Color(255, 255, 255));
		newUserLabel.setText("Don't have an account?");
		
		ImageIcon image = new ImageIcon("sprites/redBack.png");
		JLabel label = new JLabel(image);
		
		setContentPane(label);
		
		userField.setLocation(100, 20);
		userField.setSize(150, 20);
		add(userField);
		
		usernameLabel.setLocation(30, 20);
		usernameLabel.setSize(150, 20);
		add(usernameLabel);
		
		passwordLabel.setLocation(30, 60);
		passwordLabel.setSize(150, 20);
		add(passwordLabel);
		
		serverLabel.setLocation(30, 100);
		serverLabel.setSize(150, 20);
		add(serverLabel);
		
		passField.setLocation(100, 60);
		passField.setSize(150, 20);
		add(passField);
		
		loginButton.setLocation(100, 100);
		loginButton.setSize(93, 40);
		add(loginButton);
		
		newUserLabel.setLocation(28, 350);
		newUserLabel.setSize(150, 20);
		add(newUserLabel);
		
		newUserButton.setLocation(25, 380);
		newUserButton.setSize(142, 41);
		add(newUserButton);
		
		playButton.setLocation(310, 20);
		playButton.setSize(147, 41);
		add(playButton);
		
		mainMenuButton.setSize(156, 40);
		mainMenuButton.setLocation(306, 80);
		add(mainMenuButton);
		
		ImageIcon image2 = new ImageIcon("sprites/thanatos/thanatosLeft.png");
		JLabel label2 = new JLabel(image2);
		
		label2.setSize(1200, 350);
		label2.setLocation(315, 150);
		add(label2);
		
	}


	/**
	 * Notifies the user of a successful login and enables playButton
	 * Sets the client username to the entered username
	 * Called from the Client class when login is successful
	 */
	public static void showButtons() {
		JOptionPane.showMessageDialog(null, "Login Successful. Welcome " + getUsername() + ".");
		playButton.setEnabled(true);
		playButton.setVisible(true);
		Client.username = getUsername();
	}

	/**
	 * Shows NewUser frame allowing new user to be created
	 */
	protected void newUserButtonMouseClicked() {
		newUser = new NewUser(userCli);
	}
	
	/**
	 * Closes the NewUser frame
	 * Client calls this when new user creation is successful
	 */
	public static void closeNewUser(){
		newUser.dispose();
	}


	/**
	 * Called when loginButton is clicked
	 * Sends a login request to server through client
	 * @throws IOException
	 */
	public void loginButtonMouseClicked() throws IOException {

		setUsername(userListener.getUsername());
		
		userCli.sendInfo(userListener.getUsername(), passListener.getPassword());

	}

	

	/**
	 * Stops menu music and starts game music
	 * Sets up and begins new Multiplayer game
	 * @throws SocketException
	 * @throws UnknownHostException
	 * @throws SlickException
	 */
	private void playButtonMouseClicked() throws SocketException, UnknownHostException, SlickException {
		GameMenu.menuMusic();
		GameMenu.gameMusic();
		appgc = new AppGameContainer(new MultiPlayer("RPG", userCli));
		appgc.setDisplayMode(MultiPlayState.bgWidth, MultiPlayState.bgHeight, false);
		appgc.setTargetFrameRate(60);
		appgc.setShowFPS(false);
		appgc.start();
		
	}
	
	// Variables declaration - do not modify
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	public static JButton loginButton;
	public static JPasswordField passField;
	private static JButton playButton;
	public static JTextField userField;
	public static JButton newUserButton;
	private JTextField messages;
	private JLabel newUserLabel;
	private JButton mainMenuButton;
	private JLabel serverLabel;
	// End of variables declaration

}
