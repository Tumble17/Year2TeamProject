package singleplayer;

import menus.GameMenu;
import menus.UpgradesSinglePlayer;

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
public class PauseState {
	private static Image pauseBg;
	private static Image paused;
	private static Image resume1;
	private static Image resume2;
	private static Image upgrades1;
	private static Image upgrades2;
	private static Image exit1;
	private static Image exit2;

	static int buttonCounter = 0;

	public static void update(GameContainer gc, int i) throws SlickException {

		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_P)) {
			SinglePlayer.isPaused = false;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			if (buttonCounter > 0) {
				buttonCounter--;
			}
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			if (buttonCounter < 2) {
				buttonCounter++;
			}
		}
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			switch (buttonCounter){
			case 0:
				SinglePlayer.isPaused = false;
				break;
			case 1:
				SinglePlayer.appgc.destroy();
				UpgradesSinglePlayer up = new UpgradesSinglePlayer();
				GameMenu.gameMusic();
				up.setVisible(true);
				break;
			case 2:
				SinglePlayer.appgc.destroy();
				GameMenu.gameMusic();	
				break;
			}
		}

	}

	public static void render(GameContainer gc, Graphics g)
			throws SlickException {
		pauseBg = new Image("sprites/pauseMenu/pauseBg.png");
		paused = new Image("sprites/pauseMenu/PAUSED.png");
		resume1 = new Image("sprites/pauseMenu/RESUME.png");
		resume2 = new Image("sprites/pauseMenu/RESUME-2.png");
		upgrades1 = new Image("sprites/pauseMenu/UPGRADES.png");
		upgrades2 = new Image("sprites/pauseMenu/UPGRADES-2.png");
		exit1 = new Image("sprites/pauseMenu/EXIT-GAME.png");
		exit2 = new Image("sprites/pauseMenu/EXIT-GAME-2.png");

		pauseBg.draw(0,0);
		paused.draw((PlayState.bgWidth / 2) - (paused.getWidth() / 2), 50);
		
		switch (buttonCounter) {

		case 0:
			
			resume2.draw((PlayState.bgWidth / 2) - (resume2.getWidth() / 2), 250);
			upgrades1.draw((PlayState.bgWidth / 2) - (upgrades1.getWidth() / 2),
					350);
			exit1.draw((PlayState.bgWidth / 2) - (exit1.getWidth() / 2), 450);
			break;
		
		case 1:
			resume1.draw((PlayState.bgWidth / 2) - (resume2.getWidth() / 2), 250);
			upgrades2.draw((PlayState.bgWidth / 2) - (upgrades1.getWidth() / 2),
					350);
			exit1.draw((PlayState.bgWidth / 2) - (exit1.getWidth() / 2), 450);
			break;
		
		case 2:
			resume1.draw((PlayState.bgWidth / 2) - (resume2.getWidth() / 2), 250);
			upgrades1.draw((PlayState.bgWidth / 2) - (upgrades1.getWidth() / 2),
					350);
			exit2.draw((PlayState.bgWidth / 2) - (exit1.getWidth() / 2), 450);
			break;
		}

	}
}
