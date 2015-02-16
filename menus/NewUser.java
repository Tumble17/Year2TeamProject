package menus;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import serverlogin.ServerLogin;
import client.Client;
import documentListeners.PassFieldListener;
import documentListeners.UserFieldListener;

/**
 * @author cxb264
 * Frame allowing creation of new users
 * accessed from MultiMenu
 */
@SuppressWarnings("serial")
public class NewUser extends JFrame {

	Client client;

	UserFieldListener userListener = new UserFieldListener();
	PassFieldListener passListener = new PassFieldListener();

	ServerLogin write = new ServerLogin();

	/**
	 * Sets the appearance of the NewUser menu
	 * Adds buttons with listeners
	 * @param userCli - Client of the MultiMenu to allow communication with server
	 */
	public NewUser(Client userCli) {
		ImageIcon submitImage = new ImageIcon("sprites/submitButton.png");
		
		JLabel userLabel = new JLabel("Username:");
		JLabel passLabel = new JLabel("Password:");

		JTextField userField = new JTextField();
		JPasswordField passField = new JPasswordField();

		JButton submit = new JButton(submitImage);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 400;
		int height = 250;
		int x = (dim.width-width)/2;
		int y = (dim.height-height)/2;
		setLocation(x, y);  		// sets position of window to centre of the user's screen
		
		setSize(width, height);
		
		client = userCli;

		// Listeners for the username and password text fields
		userField.getDocument().addDocumentListener(userListener);
		passField.getDocument().addDocumentListener(passListener);

		submit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				try {
					submitButtonMouseClicked();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		ImageIcon image = new ImageIcon("sprites/cloud_background.jpg");
		JLabel label = new JLabel(image);
		
		setContentPane(label);

		userLabel.setForeground(new java.awt.Color(255, 255, 255));
		userLabel.setSize(150, 20);
		userLabel.setLocation(30, 30);
		add(userLabel);
		
		userField.setSize(150, 20);
		userField.setLocation(200, 30);
		add(userField);
		
		passLabel.setForeground(new java.awt.Color(255, 255, 255));
		passLabel.setSize(150, 20);
		passLabel.setLocation(30, 60);
		add(passLabel);
		
		passField.setSize(150, 20);
		passField.setLocation(200, 60);
		add(passField);
		
		submit.setSize(113, 41);
		submit.setLocation(130, 120);
		add(submit);

		setSize(width, height);
		setVisible(true);
	}

	/**
	 * Called when submit button clicked
	 * Sends request for new user creation to server using client
	 * @throws IOException
	 */
	protected void submitButtonMouseClicked() throws IOException {
		String username = userListener.getUsername();
		String password = passListener.getPassword();

		if (!password.equals("")
				&& !username.equals("")
				&& !username.contains(",") 									// relevant checks to stop
				&& !password.contains(",")) {									// interference with user data							
		
			// sends the request
			client.sendNewUser(username, password);
			
		} else {
			// notifies the user of their error
			JOptionPane.showMessageDialog(null, "username or password cannot be blank or contain ','"); 
		}
	}

}
