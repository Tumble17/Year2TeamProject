package jUnitTesting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import character.AIEnemy;
import character.Armour;
import singleplayer.PlayState;

public class PlayStateUserDamgeTest {

	private AIEnemy en;
	private Armour armour;
	private float firstHP;
	private float secondHP;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		en = new AIEnemy(100);
		armour = new Armour("#armour1");
		en.spPursueTarget = 0;
		PlayState.x = 11;
		PlayState.y = 10;
		en.yVal = 10.0f;
		en.xVal = 10.0f;
		PlayState.armour = armour;

		firstHP = PlayState.armour.getUserHP();

		PlayState.checkEnemyAttack(en, armour, 10); // user is hit so hit points
													// should decrease

		secondHP = PlayState.armour.getUserHP();
	}

	/**
	 * Test method for {@link singleplayer.PlayState}.
	 */
	@Test
	public void test() {
		assertNotSame(firstHP, secondHP);
		assertTrue(firstHP > secondHP); // HP before collision should be larger
										// than HP afterwards
	}

}
