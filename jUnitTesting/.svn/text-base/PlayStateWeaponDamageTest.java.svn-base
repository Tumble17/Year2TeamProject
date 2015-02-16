package jUnitTesting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import singleplayer.PlayState;

import character.AIEnemy;
import character.Weapon;

public class PlayStateWeaponDamageTest {

	private AIEnemy en;
	private Weapon wpn;
	private AIEnemy en2;
	private Weapon wpn2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		en = new AIEnemy(100);
		wpn = new Weapon("~sword1");
		PlayState.atkRange = 20.0f;
		PlayState.y = 10;
		en.xVal = 10.0f;
		en.yVal = 10.0f;
		en.isActive = true;
		PlayState.userTimer = 0; // sets up collision between first sword and
									// first enemy

		en2 = new AIEnemy(100);
		wpn2 = new Weapon("~sword2");
		PlayState.atkRange = 20.0f;
		PlayState.y = 10;
		en.xVal = 10.0f;
		en.yVal = 10.0f;
		en.isActive = true;
		PlayState.userTimer = 0;// sets up collision between second sword and
								// second enemy

		PlayState.checkLightEnemyCollision(en, wpn);
		PlayState.checkLightEnemyCollision(en2, wpn2);

	}

	/**
	 * Test method for {@link singleplayer.PlayState}.
	 */
	@Test
	public void testWeaponDamage() {
		assertNotSame(en.hitPoints, en2.hitPoints);
		assertTrue(en.hitPoints > en2.hitPoints);
		// first enemy health should be greater than second enemy health because
		// it was his by a weaker weapon
	}

}
