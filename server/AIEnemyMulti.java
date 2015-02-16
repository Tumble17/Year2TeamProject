package server;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

/**
 * @author Rob Minford 1189213 & Jack Dyer 1223659
 * 
 */
public class AIEnemyMulti {

	public int hitPoints = 100;
	public float speedFactor = (float) 0.5;
	public boolean isActive = true;
	public float xVal;
	public float yVal;
	public boolean isAttacking = false;
	public int spPursueTarget;
	public int bgWidth = 1260;
	public int bgHeight = 680;
	public int target;
	public static int enemyHeight = 53;
	public static int enemyWidth = 51;
	public static int userWidth = 51;
	public static int userHeight = 64;
	public int counter;

	/**
	 * Checks the collisions and amends the HP of the given user.
	 * 
	 * @param en
	 * @param userX
	 * @param userY
	 * @param inet
	 * @param hp
	 * @return integer HP
	 * @throws IOException
	 */
	public static int checkCollision(AIEnemyMulti en, int userX, int userY,
			InetAddress inet, int hp) throws IOException {

		en.isAttacking = false;

		if (en.xVal + enemyWidth >= userX && en.xVal <= userX + userWidth
				&& en.yVal + enemyHeight >= userY
				&& en.yVal <= userY + userHeight && en.counter > 500) {

			en.isAttacking = true;
			hp = hp - 5;
			en.counter = 0;

		} else {
			en.isAttacking = false;
		}
		return hp;

	}

	/**
	 * Spawns an enemy.
	 */
	public void spawn() {
		xVal = bgWidth - 64;
		yVal = (float) ((bgHeight / 2) * Math.random()) + bgHeight / 4;
		target = getRandomUser();
	}

	/**
	 * @return HP.
	 */
	public int gethp() {
		return hitPoints;
	}

	/**
	 * @return random integer smaller than player number.
	 */
	public static int getRandomUser() {
		Random rand = new Random();
		int n = rand.nextInt(Server.numberOfPlayers);
		return n;
	}

	/**
	 * alters the coordinates of the enemies towards their target player.
	 * 
	 * @param userX
	 * @param userY
	 */
	public void pursue(float userX, float userY) {
		if (userX < xVal) {
			xVal -= speedFactor;

		}

		if (userX > xVal) {
			xVal += speedFactor;

		}
		if (userY > yVal) {
			yVal += speedFactor;
		}
		if (userY < yVal) {
			yVal -= speedFactor;
		}


	}

	
}
