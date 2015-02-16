package multiplayer;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author Jack Dyer 1223659
 *
 */
public class GameOverStateMP {

	public static int userPoints;
	public static int userCoins;
	public static int userWaves;
	public static int userKills;
	
	public static String p1User;
	public static String p2User;
	public static int p1Score;
	public static int p2Score;

	static List<Integer> pointsDigits = new LinkedList<Integer>();
	static List<Integer> wavesDigits = new LinkedList<Integer>();
	static List<Integer> killsDigits = new LinkedList<Integer>();

	private static int buttonCounter = 0;
	private static Image gameOverBg;
	private static Image gameOver;
	private static Image first;
//	private static Image second;
//	private static Image third;
//	private static Image fourth;
//	private static Image kills;
//	private static Image waves;
	private static Image points;
//	private static Image upgrades1;
//	private static Image upgrades2;
//	private static Image newGame1;
//	private static Image newGame2;
	private static Image exit1;
	private static Image exit2;

	private static LinkedList<Image> digitArray = new LinkedList<Image>();
	
	public static void init(GameContainer gc){
		try{
//			InputStream inputStream = ResourceLoader.getResourceAsStream("visitor1.ttf");
//			
//			Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
//			font = font.deriveFont(24f);
//			gameFont = new TrueTypeFont(font, false);
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void update(GameContainer gc, int i) throws SlickException {

		while (userPoints > 0) {
			pointsDigits.add(0, userPoints % 10);
			userPoints = userPoints / 10;
		}
//		while (userWaves > 0) {
//			wavesDigits.add(0, userWaves % 10);
//			userWaves = userWaves / 10;
//		}
//		while (userKills > 0) {
//			killsDigits.add(0, userKills % 10);
//			userKills = userKills / 10;
//		}

		Input input = gc.getInput();
//		if (input.isKeyPressed(Input.KEY_U)) {
//			MultiPlayer.isGameOver = false;
//		}
//		if (input.isKeyPressed(Input.KEY_UP)) {
//			if (buttonCounter > 0) {
//				buttonCounter--;
//			}
//		}
//		if (input.isKeyPressed(Input.KEY_DOWN)) {
//			if (buttonCounter < 1) {
//				buttonCounter++;
//			}
//		}
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			switch (buttonCounter) {

			case 0:
//				GameMenu menu = new GameMenu();
//				menu.setVisible(true);
//				GameMenu.gameMusic();
//				GameMenu.menuMusic();
				MultiPlayer.appgc.destroy();
				break;
//			case 1:
//				MultiPlayer.appgc.exit();
//				break;
			}
		}

	}

	public static void render(GameContainer gc, Graphics g)
			throws SlickException {
		gameOverBg = new Image("sprites/gameOverMenu/gameOverBg.png");
		gameOver = new Image("sprites/gameOverMenu/GAME-OVER.png");
		first = new Image("sprites/gameOverMenu/1st.png");
//		third = new Image("sprites/gameOverMenu/3rd.png");
//		fourth = new Image("sprites/gameOverMenu/4th.png");
		points = new Image("sprites/gameOverMenu/POINTS.png");
//		upgrades1 = new Image("sprites/gameOverMenu/UPGRADES.png");
//		upgrades2 = new Image("sprites/gameOverMenu/UPGRADES-2.png");
		exit1 = new Image("sprites/gameOverMenu/EXIT-GAME.png");
		exit2 = new Image("sprites/gameOverMenu/EXIT-GAME-2.png");
//		for (int i = 0; i < 10; i++) {
//			digitArray.add(new Image("sprites/gameOverMenu/digits/png-" + i
//					+ ".png"));
//		}

		gameOverBg.draw(0, 0);
		gameOver.draw((MultiPlayState.bgWidth / 2) - (gameOver.getWidth() / 2), 50);
		
		String p1 = p1User + ":   " + p1Score;
		String p2 = p2User + ":   " + p2Score;
		
		if(p1Score > p2Score){
			
		g.drawString(p1, (MultiPlayState.bgWidth / 2) - p1.length(), 300);
		
		g.drawString(p2, (MultiPlayState.bgWidth / 2) - p2.length(), 400);
		} else {
			
			g.drawString(p2, (MultiPlayState.bgWidth / 2) - p2.length(), 300);
			g.drawString(p1, (MultiPlayState.bgWidth / 2) - p1.length(), 400);
			
			
			
		}
//		first.draw((MultiPlayState.bgWidth / 2) - first.getWidth(), 100);
//		third.draw((MultiPlayState.bgWidth / 2) - third.getWidth(), 200);
//		fourth.draw((MultiPlayState.bgWidth / 2) - fourth.getWidth(), 400);

//		kills.draw((MultiPlayState.bgWidth / 2) - kills.getWidth(), 200);
//		int killsBuffer = 0;
//		for (int digits : killsDigits) {
//			digitArray.get(digits).draw((MultiPlayState.bgWidth / 2) + killsBuffer, 210);
//			killsBuffer+=30;
//			
//		}

//		waves.draw((MultiPlayState.bgWidth / 2) - waves.getWidth(), 255);
//		int wavesBuffer = 0;
//		for (int digits : wavesDigits) {
//			digitArray.get(digits).draw((MultiPlayState.bgWidth / 2) + wavesBuffer, 260);
//			wavesBuffer+=30;
//		}

//		points.draw((MultiPlayState.bgWidth / 2) - points.getWidth(), 300);
//		int pointsBuffer = 0;
//		for (int digits : pointsDigits) {
//			digitArray.get(digits).draw((MultiPlayState.bgWidth / 2) + pointsBuffer, 310);
//			pointsBuffer+=30;
//		}

		switch (buttonCounter) {
		case 0:

//			upgrades2.draw(
//					(MultiPlayState.bgWidth / 2) - (upgrades1.getWidth() / 2), 450);
			exit2.draw((MultiPlayState.bgWidth / 2) - (exit1.getWidth() / 2), 550);
			break;

//		case 1:
//			upgrades1.draw(
//					(MultiPlayState.bgWidth / 2) - (upgrades1.getWidth() / 2), 450);
//			exit2.draw((MultiPlayState.bgWidth / 2) - (exit1.getWidth() / 2), 550);
//			break;
//
		}

	}
	
}
