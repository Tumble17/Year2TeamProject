package jUnitTesting;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import singleplayer.PlayState;
import character.AIEnemy;
import character.Armour;

public class PlayStateArmourTest {

	private Armour armour1;
	private Armour armour2;
	private AIEnemy en;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		armour1 = new Armour("#armour1");
		armour2 = new Armour("#armour2");
		en = new AIEnemy(100);
		en.attackDamage = 10;

		PlayState.x = 10;
		PlayState.y = 10;
		en.xVal = 10;
		en.yVal = 10; // sets user and enemy coordinates to be within range
		en.spPursueTarget = 0;
		PlayState.armour = armour1; // sets armour to armour1

		PlayState.checkEnemyAttack(en, armour1, 10); // enemy attacks the user
														// with armour1

		AIEnemy.time = 0;
		PlayState.x = 10;
		PlayState.y = 10;
		en.xVal = 10;
		en.yVal = 10;
		en.spPursueTarget = 0;
		PlayState.armour = armour2; // sets armour to armour2

		PlayState.checkEnemyAttack(en, armour2, 10); // enemy attacks the user
														// with armour2

	}

	/**
	 * Test method for {@link singleplayer.PlayState}.
	 */
	@Test
	public void testCheckCollisionFloatFloatFloatFloat() {
		assertNotSame(armour1.getUserHP(), armour2.getUserHP());
		assertTrue(armour1.getUserHP() < armour2.getUserHP()); // armour2 should
																// have more HP
																// because it is
																// stronger
	}

}
