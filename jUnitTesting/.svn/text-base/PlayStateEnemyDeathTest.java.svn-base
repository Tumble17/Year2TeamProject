package jUnitTesting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import character.AIEnemy;
import character.Weapon;

import singleplayer.PlayState;

public class PlayStateEnemyDeathTest {

	private AIEnemy en;
	private Weapon wpn;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		en = new AIEnemy(10);
		wpn = new Weapon("~sword1");
		PlayState.atkRange = 20.0f;
		PlayState.y = 10;
		en.xVal = 10.0f;
		en.yVal = 10.0f;
		en.isActive = true;
		PlayState.userTimer = 0;

		PlayState.checkLightEnemyCollision(en, wpn);
		// enemy is hit by a weapon with a higher weapon damage than the health
		// and so enemy should become inactive
	}

	/**
	 * Test method for {@link singleplayer.PlayState}.
	 */
	@Test
	public void testEnemyDeath() {
		assertTrue(en.hitPoints <= 0);
		assertFalse(en.isActive);
	}
}
