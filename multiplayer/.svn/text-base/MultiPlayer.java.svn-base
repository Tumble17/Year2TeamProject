package multiplayer;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import client.Client;

/**
 * 
 * @author Jack Dyer 1223659
 *
 */
public class MultiPlayer extends BasicGame {

	// MULTIPLAYER MAIN --------------------------
	static AppGameContainer appgc;
	// MULTIPLAYER STATE CONTROLLERS -------------
	public static boolean isGameOver;
	public static boolean isUserActive;
	public static boolean isWaiting;

	public static Client userCl;

	public MultiPlayer(String gamename, Client cli) throws SocketException,
			UnknownHostException, SlickException {
		super(gamename);
		userCl = cli;

	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		if (isGameOver) {

		} else if (isWaiting) {
//			MultiPlayState.create();
			WaitingState.init(gc);

		} else {
			MultiPlayState.init(gc);
		}
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {

		if (isWaiting) {
				WaitingState.update(gc, i);
			
		} else if (isGameOver) {
			GameOverStateMP.update(gc, i);
		} else {
			try {
				MultiPlayState.update(gc, i);
			} catch (IOException e) {
			}
			
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (isWaiting) {
			WaitingState.render(gc, g);
		} else if (isGameOver) {
			GameOverStateMP.render(gc, g);
		} else {
			MultiPlayState.render(gc, g);
			
		}
	}

}