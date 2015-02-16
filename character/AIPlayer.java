package character;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.newdawn.slick.Animation;

import singleplayer.PlayState;


/**
 * 
 * @author Jack Dyer 1223659
 *
 */
public class AIPlayer implements Character {

	// IDENTITY
	public String userName = "GARY";

	// STATISTICS
	public boolean isActive;
	public float hitPoints;
	public int atkDmg = PlayState.wpn.getWpnAttack();
	public float speedFactor = (float) 0.5;
	public float xVal;
	public float yVal;

	// CURRENT ANIMATION
	public static Animation curAni;

	// COLLISIONS
	public int pursueTarget;
	public boolean collision;
	public static int time;

	// GAME FIELDS
	private int bgWidth = PlayState.bgWidth;
	private int bgHeight = PlayState.bgHeight;

	// CREATE INSTANCE WITH HEALTH
	public AIPlayer(float hitP) {
		this.hitPoints = hitP;
	}

	@Override
	public void attack(double xVal, double yVal) {
	}

	// ATTACK AN ENEMY
	public void attack(AIEnemy en) {
		// REMOVE HEALTH FROM en
		en.hitPoints -= atkDmg;
		// CHECK FOR DEATH
		if (en.hitPoints <= 0) {
			en.isActive = false;
			PlayState.enemyToDie.add(en);
			// DEATH ANIMATION
//			en.die(en);

		}
	}

	@Override
	public void spawn(Character character) {
	}

	public void spawn() {
		// SET xVal and yVal to be sent
		xVal = PlayState.gameAreaXStart;
		yVal = (float) ((bgHeight / 2) * Math.random()) + bgHeight / 4;
		curAni = PlayState.armour.spStandRightArmourAni;

		isActive = true;
		// SET TO NIL COLLISION FOR START AND
		collision = false;
		// RESET ATTACK TIMER
		time = 0;

	}

	// CURRENTLY UNUSED, PURSUES RNDM ENEMY
	public static int getRandomEnemy() {
		Random rand = new Random();
		int n = rand.nextInt(5);
		return n;
	}

	/**
	 * Moves towards an enemies coordinates based upon their position in
	 * comparison with the AIPlayer
	 * 
	 * @param userX
	 *            the float x coordinate of the coordinates to be pursued
	 * @param userY
	 *            the float y value of the coordinates to be pursued
	 */
	public void pursue(float userX, float userY) {

		if (userX > xVal && xVal < bgWidth - PlayState.userWidth) {
			xVal += speedFactor;
			curAni = PlayState.armour.spWalkRightArmourAni;
		}
		if (userX < xVal && xVal > PlayState.gameAreaXStart) {
			xVal -= speedFactor;
			curAni = PlayState.armour.spWalkLeftArmourAni;
		}
		if (userY < yVal && yVal > PlayState.gameAreaYStart) {
			yVal -= speedFactor;
		}
		if (userY > yVal
				&& yVal < (PlayState.gameAreaYStart + PlayState.gameAreaHeight)
						- PlayState.userHeight) {
			yVal += speedFactor;
		}

	}
}
