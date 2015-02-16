package jUnitTesting;

import static org.junit.Assert.*;
import login.SinglePlayerReadWriteTextFile;
import org.junit.Before;
import org.junit.Test;

public class UpgradesSinglePlayerSwordPurchaseTest {
	
	private String firstSword;
	private String secondSword;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		firstSword = SinglePlayerReadWriteTextFile.getSword("user");
		
		if(SinglePlayerReadWriteTextFile.getSword("user").equals("~sword1")){
			// if sword1 currently equipped, change to sword2
			SinglePlayerReadWriteTextFile.setSword("user", "sword2");			
			// this is called in UpgradesSinglePlayer on an ActionListener
		} else {
			// if sword1 not currently equipped, change to sword1
			SinglePlayerReadWriteTextFile.setSword("user", "sword1");
			// this is called in UpgradesSinglePlayer on an ActionListener
		}
		
		secondSword = SinglePlayerReadWriteTextFile.getSword("user");
	}

	/**
	 * Test method for {@link login.SinglePlayerReadWriteTextFile}.
	 */
	@Test
	public void testGetSword() {
		// sword from before should be different from afterwards
		assertNotSame(firstSword, secondSword);
	}

}
