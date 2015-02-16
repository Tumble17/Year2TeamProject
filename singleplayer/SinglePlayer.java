package singleplayer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import menus.GameMenu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author Jack Dyer 1223659
 * 
 */
public class SinglePlayer extends BasicGame {

	// MAIN -------------------------------
	static AppGameContainer appgc;
	// STATE CONTROLLERS
	public static boolean isPaused;
	public static boolean isGameOver;
	public static boolean userIsActive;
	

	public SinglePlayer(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		if (isGameOver) {
			
		} else if(isPaused){
	
		} else {
			try {
				PlayState.init(gc);
			} catch (IOException e) {e.printStackTrace();}
		}
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {

		if (isGameOver) {
			GameOverState.update(gc, i);
		} else if(isPaused){
			PauseState.update(gc, i);
		} else {
			try {
				PlayState.update(gc, i);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (isGameOver) {
			GameOverState.render(gc, g);
		} else if(isPaused){
			PauseState.render(gc, g);
		} else {
			try {
				PlayState.render(gc, g);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			
			appgc = new AppGameContainer(new SinglePlayer("Arena: Endless War"));
			appgc.setDisplayMode(PlayState.bgWidth, PlayState.bgHeight, false);
			appgc.setTargetFrameRate(200);
			appgc.setShowFPS(false);
			appgc.start();

		}

		catch (SlickException ex) {
			Logger.getLogger(SinglePlayer.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
	
//	@Override
//	public boolean closeRequested(){
//		
//		return true;
//	}
}
