package character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import singleplayer.PlayState;

/** 
 * 
 * @author Jack Dyer 1223659
 *
 */
public class Boss extends AIEnemy {

	// SIZE FIELDS
	public static int width;
	public int height;
	public int attWidth;
	int attHeight;
	public int deadWidth;
	int deadHeight;

	// LIFE CYCLE FIELDS
	public static String type;
	public float bossFullHP;
	public static boolean isBossActive;
	public static boolean isAttacking;
	public static boolean isBossDeath;
	public static int bossTime;
	public static float xVal;
	public static float yVal;
	static float yVector = 1;
	static float xVector = -1;
	static float speedFactor = (float) 0.5;

	// ANIMATION FIELDS
	public static Animation bossCurAni;
	public static Animation moveLeftAni;
	public static Animation moveRightAni;
	public static Animation attackAni;
	public static Animation deathAni;

	public Boss(int hitP) {
		super(hitP);
	}

	public void init(int name) throws SlickException {
		isBossActive = true;
		isAttacking = false;

		// Spawns boss with correct variables
		switch (name) {
		case 1:
			type = "Gazeti";
			bossCurAni = PlayState.gazLeft;
			moveLeftAni = PlayState.gazLeft;
			attackAni = PlayState.gazAtt;
			deathAni = PlayState.gazDead;

			width = 64;
			height = 106;
			attWidth = 118;
			attHeight = 113;
			deadWidth = 113;
			deadHeight = 135;
			bossFullHP = 500;
			break;
		case 2:
			type = "Ghost";
			bossCurAni = PlayState.ghostLeft;
			moveLeftAni = PlayState.ghostLeft;
			moveRightAni = PlayState.ghostRight;
			attackAni = PlayState.ghostAttLeft;
			deathAni = PlayState.ghostDeath;

			width = 116;
			height = 100;
			attWidth = 90;
			attHeight = 134;
			deadWidth = 125;
			deadHeight = 120;
			bossFullHP = 600;
			break;
		case 3:
			type = "Thanatos";
			bossCurAni = PlayState.thanatosLeft;
			moveLeftAni = PlayState.thanatosLeft;
			moveRightAni = PlayState.thanatosRight;
			attackAni = PlayState.thanatosAttLeft;
			deathAni = PlayState.thanatosDeath;

			width = 138;
			height = 248;
			attWidth = 356;
			attHeight = 292;
			deadWidth = 234;
			deadHeight = 264;
			bossFullHP = 700;
			break;

		}

		// SPAWNS
		xVal = PlayState.bgWidth - (width + 20);
		yVal = (float) (PlayState.gameAreaHeight / 2)
				+ (PlayState.gameAreaYStart / 2);

		bossTime = 1;

	}

	/**
	 * Moves the Boss
	 * @param mobility the string to decide the movement path
	 */
	public void move(String mobility) {

		if (yVal > (PlayState.gameAreaYStart + PlayState.gameAreaHeight)
				- height) {
			yVector = -1;
		}
		if (yVal < PlayState.gameAreaYStart) {
			yVector = 1;
		}
		yVal += yVector * speedFactor;

		if (mobility == "mobile") {
			if (xVal + width > PlayState.bgWidth) {
				xVector = -1;
			}
			if (xVal < PlayState.gameAreaXStart) {
				xVector = 1;
			}

			if (xVector == 1) {
				bossCurAni = moveRightAni;
			} else if (xVector == -1) {
				bossCurAni = moveLeftAni;
			}

			xVal += xVector * speedFactor;
		}
	}

	

	/**
	 * Separate thread to allow the attack animation to run
	 */
	public void attack() {
		Thread t = new Thread() {
			public void run() {
				isAttacking = true;

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				isAttacking = false;
			}

		};
		t.start();
	}
	
	/**
	 * Kills the boss
	 * @param g the boss to die
	 */
	public void die(final Boss g) {
		Thread t = new Thread() {
			public void run() {

				g.isBossDeath = true;
				bossCurAni = deathAni;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				g.isBossDeath = false;
				PlayState.boss = null;

			}

		};
		t.start();

	}

}