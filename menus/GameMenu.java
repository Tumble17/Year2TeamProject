package menus;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import singleplayer.SinglePlayer;

/**
 * @author cxb264
 * Main menu for the game
 * The first thing the user sees when the game begins
 */
@SuppressWarnings("serial")
public class GameMenu extends JFrame {
	public static JFrame menu;
	private static Clip currentClip;
	private static Clip currentClip2;
	JFrame instructions;

	/**
	 * Sets the appearance of the GameMenu
	 * adds buttons with listeners
	 */
	public GameMenu() {
		ImageIcon singlePlayerImage = new ImageIcon(
				"sprites/singlePlayerButton.png");
		ImageIcon multiPlayerImage = new ImageIcon(
				"sprites/multiPlayerButton.png");
		ImageIcon instructionsImage = new ImageIcon(
				"sprites/instructionsButton.png");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 500;
		int height = 500;
		int x = (dim.width - width) / 2;
		int y = (dim.height - height) / 2;
		setLocation(x, y);					// sets position of window to centre of the user's screen

		JButton instructButton = new JButton(instructionsImage);
		instructButton.setEnabled(true);
		instructButton.addActionListener(new ActionListener() {	// adds ActionListener to instructButton 
			public void actionPerformed(ActionEvent e) {
				instructButtonMouseClicked();
			}
		});

		JButton singlePlayerButton = new JButton(singlePlayerImage);
		singlePlayerButton.addActionListener(new ActionListener() {	// adds ActionListener to singleButton 
			public void actionPerformed(ActionEvent evt) {
				singleActionPerformed();
			}
		});

		JButton multiButton = new JButton(multiPlayerImage);
		multiButton.addActionListener(new ActionListener() {	// adds ActionListener to multiButton 
			public void actionPerformed(ActionEvent evt) {
				try {
					multiActionPerformed();
				} catch (SocketException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		});

		ImageIcon image = new ImageIcon("sprites/main.png");
		JLabel label = new JLabel(image);

		setContentPane(label);						// sets background to main.png from label

		instructButton.setLocation(160, 419);
		instructButton.setSize(159, 40);			//adds multiButton at correct position and size
		instructButton.setVisible(true);
		add(instructButton);

		singlePlayerButton.setLocation(155, 320);
		singlePlayerButton.setSize(169, 41);		//adds multiButton at correct position and size
		singlePlayerButton.setVisible(true);
		add(singlePlayerButton);

		multiButton.setLocation(158, 370);
		multiButton.setSize(163, 41);				//adds multiButton at correct position and size
		multiButton.setVisible(true);
		add(multiButton);

		setSize(width, height);
		setVisible(true);
	}

	/**
	 * Called when multiButton is clicked
	 * Creates new MultiMenu and closes this window
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	protected void multiActionPerformed() throws SocketException,
			UnknownHostException {
		MultiMenu multiplayer = new MultiMenu();
		multiplayer.setVisible(true);
		dispose();
	}

	/**
	 * Called when singlePlayerButton is clicked
	 * Creates new SinglePlayer game and closes this window
	 * Stops menu music and starts game music
	 */
	protected void singleActionPerformed() {
		System.out.println("single player game");
		menuMusic();
		gameMusic();
		dispose();
		SinglePlayer.main(null);
	}
	
	/**
	 * Called when instructButton is clicked
	 * Creates new Instructions menu and closes this window
	 */
	private void instructButtonMouseClicked() {
		instructions = new Instructions();
		instructions.setVisible(true);
		dispose();
	}

	/**
	 * Stops or starts the menu music depending on whether it is playing already or not
	 */
	public static synchronized void menuMusic() {
		if (currentClip != null) {
			currentClip.stop();
		} else {
			try {
				Clip menuMusic = AudioSystem.getClip();
				AudioInputStream inputStream = AudioSystem
						.getAudioInputStream(new File("sounds/bonds.wav"));
				menuMusic.open(inputStream);
				menuMusic.start();
				menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
				currentClip = menuMusic;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Stops or starts the game music depending on whether it is playing already or not
	 */
	public static synchronized void gameMusic() {
		if (currentClip2 != null) {
			currentClip2.stop();
		} else {
			try {
				Clip clip = AudioSystem.getClip();
				AudioInputStream inputStream = AudioSystem
						.getAudioInputStream(new File("sounds/QuickSilver.wav"));
				clip.open(inputStream);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				currentClip2 = clip;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Begins the game and starts the menu music
	 * @param args
	 */
	public static void main(String args[]) {
		menu = new GameMenu();
		menuMusic();
	}


}
