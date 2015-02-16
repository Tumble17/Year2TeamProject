package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import login.SinglePlayerReadWriteTextFile;

/**
 * @author cxb264
 * Upgrades menu for single player game
 */
@SuppressWarnings("serial")
public class UpgradesSinglePlayer extends JFrame {
	final String username = "user";
	JButton buy1;
	JButton buy2;
	JButton buy3;
	JButton buy4;
	JButton buy5;
	JButton buy6;
	JTextField info = new JTextField();
	JLabel coinsLabel;
	JLabel swordLabel;
	JLabel armourLabel;

	public static int armourNo;

	int item1cost = 100;
	int item2cost = 1000;
	int item3cost = 1000000;	// cost of each of the upgrades
	int item4cost = 200;
	int item5cost = 450;
	int item6cost = 2000;

	// used to read and write local file
	SinglePlayerReadWriteTextFile read = new SinglePlayerReadWriteTextFile(); 

	/**
	 * Sets up the appearance of the Upgrades menu
	 * @param username - Username of the current user
	 */
	public UpgradesSinglePlayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon image = new ImageIcon("sprites/cloud_background.jpg");
		JLabel label = new JLabel(image);

		setContentPane(label);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 500;
		int height = 500;
		int x = (dim.width - width) / 2;
		int y = (dim.height - height) / 2;
		setLocation(x, y);

		setSize(width, height);

		try {
			coinsLabel = new JLabel("Coins: "
					+ SinglePlayerReadWriteTextFile.getCoins(username));
			coinsLabel.setSize(100, 20);
			coinsLabel.setLocation(20, 50);
			add(coinsLabel);
		} catch (IOException e3) {
			e3.printStackTrace();
		}

		try {					// works out current sword and displays it using SinglePlayerReadWriteTextFile
			String sword;
			if(SinglePlayerReadWriteTextFile.getSword(username).endsWith("1")){
				sword = "Wooden Sword";
			} else if(SinglePlayerReadWriteTextFile.getSword(username).endsWith("2")){
				sword = "Long Sword";
			} else {
				sword = "Sanguine";
			}
			swordLabel = new JLabel("Current sword: "
					+ sword);
			swordLabel.setSize(300, 20);
			swordLabel.setLocation(200, 20);
			add(swordLabel);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		try {					// works out current armour and displays it using SinglePlayerReadWriteTextFile
			String armour;
			if(SinglePlayerReadWriteTextFile.getArmour(username).endsWith("1")){
				armour = "Basic Armour";
			} else if(SinglePlayerReadWriteTextFile.getArmour(username).endsWith("2")){
				armour = "Mage Armour";
			} else {
				armour = "Battle Mage Armour";
			}
			
			armourLabel = new JLabel("Current armour: "
					+ armour);
			armourLabel.setSize(300, 20);
			armourLabel.setLocation(200, 50);
			add(armourLabel);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		Border border = BorderFactory.createLineBorder(Color.BLACK);

		// loads all of the images
		ImageIcon woodenSword = new ImageIcon(
				"sprites/woodensword/WoodenSword Stand.png");
		JLabel woodenSwordLabel = new JLabel(woodenSword);
		woodenSwordLabel.setSize(30, 30);

		ImageIcon longSword = new ImageIcon(
				"sprites/longsword/Longsword Stand.png");
		JLabel longSwordLabel = new JLabel(longSword);
		longSwordLabel.setSize(30, 30);

		ImageIcon sanguineSword = new ImageIcon(
				"sprites/sanguine/Sanguine Stand.png");
		JLabel sanguineSwordLabel = new JLabel(sanguineSword);
		sanguineSwordLabel.setSize(30, 30);

		ImageIcon basicArmour = new ImageIcon("sprites/pAIStandRight.png");
		JLabel basicArmourLabel = new JLabel(basicArmour);
		basicArmourLabel.setSize(50, 55);

		ImageIcon mageArmour = new ImageIcon(
				"sprites/mageplayer/p1a1 StandRight.png");
		JLabel mageArmourLabel = new JLabel(mageArmour);
		mageArmourLabel.setSize(50, 55);

		ImageIcon battleMageArmour = new ImageIcon(
				"sprites/bmageplayer/p1a2 StandRight.png");
		JLabel battleMageArmourLabel = new JLabel(battleMageArmour);
		battleMageArmourLabel.setSize(50, 55);

		woodenSwordLabel.setLocation(20, 80);
		add(woodenSwordLabel);
		longSwordLabel.setLocation(20, 140);
		add(longSwordLabel);
		sanguineSwordLabel.setLocation(20, 200);
		add(sanguineSwordLabel);

		basicArmourLabel.setLocation(5, 260);
		add(basicArmourLabel);
		mageArmourLabel.setLocation(5, 320);
		add(mageArmourLabel);
		battleMageArmourLabel.setLocation(5, 380);
		add(battleMageArmourLabel);

		JTextPane stats1 = new JTextPane();
		JTextPane stats2 = new JTextPane();
		JTextPane stats3 = new JTextPane();
		JTextPane stats4 = new JTextPane();
		JTextPane stats5 = new JTextPane();
		JTextPane stats6 = new JTextPane();

		stats1.setText("Wooden Sword - 25 damage, " + item1cost + " coins");
		stats2.setText("Long Sword - 50 damage, " + item2cost + " coins");
		stats3.setText("Sanguine - Increasing damage, " + item3cost
				+ " coins");
		stats4.setText("Basic Armour - 150 health, " + item4cost + " coins");
		stats5.setText("Mage Armour - 300 health, " + item5cost + " coins");
		stats6.setText("Battle Mage Armour - 800 health, " + item6cost
				+ " coins");

		stats1.setBorder(border);
		stats2.setBorder(border);
		stats3.setBorder(border);
		stats4.setBorder(border);
		stats5.setBorder(border);
		stats6.setBorder(border);

		stats1.setEditable(false);
		stats2.setEditable(false);
		stats3.setEditable(false);
		stats4.setEditable(false);
		stats5.setEditable(false);
		stats6.setEditable(false);

		stats1.setSize(306, 20);
		stats2.setSize(306, 20);
		stats3.setSize(306, 20);
		stats4.setSize(306, 20);
		stats5.setSize(306, 20);
		stats6.setSize(306, 20);

		ImageIcon buyImage = new ImageIcon("sprites/buyButton.png");

		buy1 = new JButton(buyImage);
		buy2 = new JButton(buyImage);
		buy3 = new JButton(buyImage);
		buy4 = new JButton(buyImage);
		buy5 = new JButton(buyImage);
		buy6 = new JButton(buyImage);

		buy1.setSize(79, 40);
		buy2.setSize(79, 40);
		buy3.setSize(79, 40);
		buy4.setSize(79, 40);
		buy5.setSize(79, 40);
		buy6.setSize(79, 40);

		stats1.setLocation(60, 100);
		add(stats1);
		stats2.setLocation(60, 160);
		add(stats2);
		stats3.setLocation(60, 220);
		add(stats3);
		stats4.setLocation(60, 280);
		add(stats4);
		stats5.setLocation(60, 340);
		add(stats5);
		stats6.setLocation(60, 400);
		add(stats6);

		buy1.setLocation(385, 90);
		add(buy1);
		buy2.setLocation(385, 150);
		add(buy2);
		buy3.setLocation(385, 210);
		add(buy3);
		buy4.setLocation(385, 270);
		add(buy4);
		buy5.setLocation(385, 330);
		add(buy5);
		buy6.setLocation(385, 390);
		add(buy6);

		// Adds ActionListeners to the buy buttons
		// Along with relevant checks that the user has enough coins
		// If purchase is a success it rewrites the user's coins to the new value
		// Also sets the current sword/armour to chosen upgrade
		// Updates text fields at the top appropriately
		buy1.addActionListener(new ActionListener() {		

			public void actionPerformed(ActionEvent e) {
				try {
					if (Integer.valueOf(SinglePlayerReadWriteTextFile.getCoins(username)) >= item1cost) {
						int newCoins = (Integer.parseInt(SinglePlayerReadWriteTextFile
								.getCoins(username)) - item1cost);
						SinglePlayerReadWriteTextFile.setCoins(username,
								Integer.toString((newCoins)));

						coinsLabel.setText("Coins: "
								+ SinglePlayerReadWriteTextFile.getCoins(username));
						SinglePlayerReadWriteTextFile.setSword(username, "sword1");
						swordLabel.setText("Current sword: Wooden Sword");

						buy1.setEnabled(false);
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Not enough coins to buy this item.");
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		buy2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (Integer.valueOf(SinglePlayerReadWriteTextFile.getCoins(username)) >= item2cost) {
						int newCoins = (Integer.parseInt(SinglePlayerReadWriteTextFile
								.getCoins(username)) - item2cost);
						SinglePlayerReadWriteTextFile.setCoins(username,
								Integer.toString((newCoins)));

						coinsLabel.setText("Coins: "
								+ SinglePlayerReadWriteTextFile.getCoins(username));

						SinglePlayerReadWriteTextFile.setSword(username, "sword2");
						swordLabel.setText("Current sword: Long Sword");

						buy2.setEnabled(false);
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Not enough coins to buy this item.");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		buy3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (Integer.valueOf(SinglePlayerReadWriteTextFile.getCoins(username)) >= item3cost) {
						int newCoins = (Integer.parseInt(SinglePlayerReadWriteTextFile
								.getCoins(username)) - item3cost);
						SinglePlayerReadWriteTextFile.setCoins(username,
								Integer.toString((newCoins)));

						coinsLabel.setText("Coins: "
								+ SinglePlayerReadWriteTextFile.getCoins(username));
						SinglePlayerReadWriteTextFile.setSword(username, "sword3");
						swordLabel.setText("Current sword: Sanguine");

						buy3.setEnabled(false);
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Not enough coins to buy this item.");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		buy4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (Integer.valueOf(SinglePlayerReadWriteTextFile.getCoins(username)) >= item4cost) {
						int newCoins = (Integer.parseInt(SinglePlayerReadWriteTextFile
								.getCoins(username)) - item4cost);
						SinglePlayerReadWriteTextFile.setCoins(username,
								Integer.toString((newCoins)));

						coinsLabel.setText("Coins: "
								+ SinglePlayerReadWriteTextFile.getCoins(username));
						SinglePlayerReadWriteTextFile.setArmour(username, "armour1");
						armourLabel.setText("Current armour: Basic Armour");

						armourNo = 0;

						buy4.setEnabled(false);
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Not enough coins to buy this item.");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		buy5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (Integer.valueOf(SinglePlayerReadWriteTextFile.getCoins(username)) >= item5cost) {
						int newCoins = (Integer.parseInt(SinglePlayerReadWriteTextFile
								.getCoins(username)) - item5cost);
						SinglePlayerReadWriteTextFile.setCoins(username,
								Integer.toString((newCoins)));

						coinsLabel.setText("Coins: "
								+ SinglePlayerReadWriteTextFile.getCoins(username));
						SinglePlayerReadWriteTextFile.setArmour(username, "armour2");
						armourLabel.setText("Current armour: Mage Armour");

						armourNo = 1;

						buy5.setEnabled(false);
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Not enough coins to buy this item.");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		buy6.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (Integer.valueOf(SinglePlayerReadWriteTextFile.getCoins(username)) >= item6cost) {
						int newCoins = (Integer.parseInt(SinglePlayerReadWriteTextFile
								.getCoins(username)) - item6cost);
						SinglePlayerReadWriteTextFile.setCoins(username,
								Integer.toString((newCoins)));

						coinsLabel.setText("Coins: "
								+ SinglePlayerReadWriteTextFile.getCoins(username));
						SinglePlayerReadWriteTextFile.setArmour(username, "armour3");
						armourLabel.setText("Current armour: Battle Mage Armour");

						armourNo = 2;

						buy6.setEnabled(false);
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Not enough coins to buy this item.");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		setVisible(true);

	}

}
