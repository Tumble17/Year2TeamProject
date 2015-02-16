package jUnitTesting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import singleplayer.PlayState;

public class PlayStateCheckCollisionTest {

	private float xVal1;
	private float yVal1;
	private float enXVal;
	private float enYVal;
	private float xVal2;
	private float yVal2;
	private Boolean collision1;
	private Boolean collision2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		enXVal = 11.0f;
		enYVal = 10.0f;

		xVal1 = 10.0f;
		yVal1 = 10.0f;
		collision1 = PlayState.checkCollision(xVal1, yVal1, enXVal, enYVal);
		// enemy is close to user so collision should occur

		xVal2 = 10.0f;
		yVal2 = 100.0f;
		collision2 = PlayState.checkCollision(xVal2, yVal2, enXVal, enYVal);
		// enemy is not close to user so collision should not occur
	}

	/**
	 * Test method for {@link singleplayer.PlayState}.
	 */
	@Test
	public void testCheckCollisionFloatFloatFloatFloat() {
		assertTrue(collision1);

		assertFalse(collision2);
	}

}
