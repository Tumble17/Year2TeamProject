package character;

import java.util.List;
import java.util.Random;

import multiplayer.MultiPlayState;
import multiplayer.MultiPlayer;

import org.newdawn.slick.Animation;

import singleplayer.PlayState;

/**
 * 
 * @author Jack Dyer 1223659
 *
 */
public class AIEnemy implements Character {

	// ------------------------------------
	// LIFE CYCLE FIELDS
	// ------------------------------------
	public int hitPoints;
	public float speedFactor = (float) 0.5;
	public boolean isActive;
	public boolean isAttacking;
	public int spPursueTarget;
	public int attackDamage = 10;
	public int iden;
	public float xVal;
	public float yVal;
	public static Animation curAni;
	public static Animation spCurAni;
	
	// GAME STATE FIELDS FOR COLLISION OF AREA
	private int bgWidth = MultiPlayState.bgWidth;
	private int bgHeight = MultiPlayState.bgHeight;
	
	public static boolean isExplode;
	public static int time;

	// ALLOWS SEAMLESS DELETION OF AIENEMY SEPARATE CLASS
	private List<AIEnemy> enemyToDieList = MultiPlayState.enemyToDie;
	
	/**
	 * Construction of an object with given hitpoints
	 * @param hitP the hitpoints of the enemy
	 */
	public AIEnemy(int hitP) {
		this.hitPoints = hitP;
	}

	@Override
	public void attack(double xVal, double yVal) {

	}

	@Override
	public void spawn(Character character) {

	}

	// MULTI PLAYER
	public void spawn(String s) {

		curAni = MultiPlayState.enemyLeft;

	}

	// SINGLE PLAYER
	public void spawn(int n) {
		isActive = true;
		isExplode = false;
		if (PlayState.waveNo % 2 == 1) {
			xVal = PlayState.bgWidth - PlayState.enemyWidth;
			spCurAni = PlayState.enemyLeft;
		} else {
			xVal = PlayState.gameAreaXStart;
			spCurAni = PlayState.enemyRight;
		}
		yVal = (float) ((PlayState.bgHeight / 2) * Math.random())
				+ PlayState.bgHeight / 4;
		spPursueTarget = getRandomUser();

		// TIME RESET
		time = 0;

	}

	// ---------------
	// ASSIGNED USER
	// ---------------
	public static int getRandomUser() {
		Random rand = new Random();
		int n = rand.nextInt(4);
		return n;
	}

	/**
	 * Pursues the coordinates passed as parameters, changes the animations
	 * @param userX the x coordinate of the pursuit
	 * @param userY the y coordinate of the pursuit
	 */
	public void pursue(float userX, float userY) {
		if (userX > xVal && xVal < bgWidth - PlayState.enemyWidth) {
			xVal += speedFactor;
			curAni = MultiPlayState.enemyRight;
			spCurAni = PlayState.enemyRight;
		}
		if (userX < xVal) {
			xVal -= speedFactor;
			curAni = MultiPlayState.enemyLeft;
			spCurAni = PlayState.enemyLeft;
		}
		if (userY > yVal && yVal < bgHeight - (100 + PlayState.enemyHeight)) {
			yVal += speedFactor;
		}
		if (userY < yVal) {
			yVal -= speedFactor;
		}

	}

	/**
	 * kills the AIEnemy
	 * @param en an AIEnemy
	 */
	public void die(final AIEnemy en) {
		Thread t = new Thread() {
			public void run() {
				try {

					en.isExplode = true;

					Thread.sleep(200);

				} catch (InterruptedException v) {
					System.out.println(v);
				}
				en.isExplode = false;
			}
		};

		t.start();

	}

}
