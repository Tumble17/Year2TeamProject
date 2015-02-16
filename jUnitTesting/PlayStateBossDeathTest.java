/**
 * 
 */
package jUnitTesting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import character.Boss;

import singleplayer.PlayState;

public class PlayStateBossDeathTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		PlayState.waveNo = 5; // bosses will spawn only on wave 5, 10 or 15
		PlayState.spawnBoss();
		PlayState.boss.hitPoints = 0;
		PlayState.checkBossHitPoints(); // checks whether boss is dead
	}

	/**
	 * Test method for {@link singleplayer.PlayState}.
	 */
	@Test
	public void testBossDeath() {
		assertFalse(Boss.isBossActive); // boss should be inactive since hit
										// points are zero
	}

}
