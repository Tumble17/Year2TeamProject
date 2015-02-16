/**
 * 
 */
package jUnitTesting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import singleplayer.PlayState;
import character.Boss;

/**
 * @author Clem
 * 
 */
public class PlayStateBossSpawnTest {

	private Boolean bossSpawned1;
	private Boolean bossSpawned2;

	/**
	 * @throws org.newdawn.slick.SlickException
	 */
	@Before
	public void setUp() throws SlickException {

		PlayState.waveNo = 4;
		PlayState.spawnBoss();
		bossSpawned1 = Boss.isBossActive; // should be false because wave number
											// is not multiple of 5

		PlayState.waveNo = 5;
		PlayState.spawnBoss();
		bossSpawned2 = Boss.isBossActive; // should be true because wave number
											// is multiple of 5

	}

	/**
	 * Test method for {@link singleplayer.PlayState}.
	 */
	@Test
	public void testSpawnBoss() {
		assertFalse(bossSpawned1);
		assertTrue(bossSpawned2);
	}

}
