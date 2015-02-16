package menus;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * @author cxb264
 * Instructions menu accessed through GameMenu
 * Displays controls and story
 */
@SuppressWarnings("serial")
public class Instructions extends JFrame {

	/**
	 * Sets the appearance of the Instructions menu
	 * adds buttons with listeners
	 */
	public Instructions() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		ImageIcon image = new ImageIcon("sprites/cloud_background.jpg");
		JLabel label = new JLabel(image);

		setContentPane(label);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 500;
		int height = 500;
		int x = (dim.width - width) / 2;
		int y = (dim.height - height) / 2;
		setLocation(x, y); 			// sets position of window to centre of the user's screen

		setSize(width, height);
		setVisible(true);
		
		ImageIcon instructionsIcon = new ImageIcon("sprites/instructionsButton.png");
		JLabel instructionsLabel = new JLabel(instructionsIcon);
		
		instructionsLabel.setSize(159, 40);
		instructionsLabel.setLocation(20, 20);
		add(instructionsLabel);
		
		ImageIcon mainMenuIcon = new ImageIcon("sprites/mainMenuButton.png");
		JButton mainMenuButton = new JButton(mainMenuIcon);
		
		mainMenuButton.setSize(156, 40);
		mainMenuButton.setLocation(300, 20);
		add(mainMenuButton);
		
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){				// adds actionListener to mainMenuButton
				GameMenu menu = new GameMenu();
				menu.setVisible(true);
				dispose();
			}
		});
		
		ImageIcon controlsIcon = new ImageIcon("sprites/controls.png");
		JLabel controlsLabel = new JLabel(controlsIcon);
		
		controlsLabel.setSize(399, 164);
		controlsLabel.setLocation(20, 70);
		add(controlsLabel);

		ImageIcon storyIcon = new ImageIcon("sprites/storyButton.png");
		JLabel storyLabel = new JLabel(storyIcon);
		
		storyLabel.setSize(95, 40);
		storyLabel.setLocation(20, 240);
		add(storyLabel);
		
		// story text field
		JTextArea storyText = new JTextArea("Four unknown soldiers must fight for their    freedom in the Arena. What they don't know  is that it can only end with their demise.");
		storyText.setLocation(20, 300);
		storyText.setSize(399, 100);
		storyText.setLineWrap(true);
		storyText.setOpaque(false);
		storyText.setFont(new Font ("", Font.PLAIN, 18));
		add(storyText);
	}

}
