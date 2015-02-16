package multiplayer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import singleplayer.PlayState;


/**
 * A game state where the user will be put into a 
 * loading screen to wait for other players.
 * 
 * @author Jack Dyer 1223659
 * @author Chau Nguyen 1254579
 *
 */
public class WaitingState {
	
	// Variables for the images in the loading screen.
	static Image loadingBg;
	static Image loading;
	
	/**
	 * Initialises the game container of MultiPlayState.
	 * 
	 * @param gc
	 * @throws SlickException
	 */
	public static void init(GameContainer gc) throws SlickException {
		MultiPlayState.init(gc);
		
	}

	public static void update(GameContainer gc, int i) throws SlickException {
	}
	
	/**
	 * Renders the graphics within the game container.
	 * 
	 * @param gc
	 * @param g
	 * @throws SlickException
	 */
	public static void render(GameContainer gc, Graphics g) throws SlickException {
//		loadingBg = new Image("sprites/loadingScreen/loadBg.png");
		loading = new Image("sprites/loadingScreen/LOADING.png");
//		loadingBg.draw((PlayState.bgWidth / 2) - (loadingBg.getWidth() / 2), 0);
		loading.draw((PlayState.bgWidth / 2) - (loading.getWidth() / 2), 200);
		
		
	}

	
	
}
