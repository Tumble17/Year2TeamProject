package character;

import org.newdawn.slick.Animation;
import singleplayer.PlayState;

/**
 *
 * @author Jack
 * @author Chau Nguyen 1254579
 * 
 */
public class Armour {
    
	// Variable for the user's health point as it changes when the armour changes. 
    private float userHP;
    // A variable used to update the health bar of the user.
    public int fullHP;
    
    // All of the Animation variables to overwrite the animations
    // that the user sees in the game.
    public Animation spStandRightArmourAni;
    public Animation spStandLeftArmourAni;
    public Animation spWalkRightArmourAni;
    public Animation spWalkLeftArmourAni;
    public Animation spLightAttRArmourAni;
    public Animation spLightAttLArmourAni;
    public Animation spStrongAttRArmourAni;
    public Animation spStrongAttLArmourAni;
    
    /**
     * Constructor: Construct the armour the user is currently wearing.
     * @param armourNo
     */
    public Armour(String armourNo) {
    	armourType(armourNo);
    }
    
    /**
     * Defines what armour the user is currently wearing.
     * @param armourNo
     */
    public void armourType(String armourNo) {
    	
    	// All of these armours will also set the user's health points. 
    	if(armourNo.equals("#armour1")) {
    		// Knight Armour
    		spStandRightArmourAni = PlayState.p1StandRight;
    		spStandLeftArmourAni = PlayState.p1StandLeft;
    		spWalkRightArmourAni = PlayState.p1Right;
    		spWalkLeftArmourAni = PlayState.p1Left;
    		spLightAttRArmourAni = PlayState.p1LAttRight;
    		spLightAttLArmourAni = PlayState.p1LAttLeft;
    		spStrongAttRArmourAni = PlayState.p1SAttRight;
    		spStrongAttLArmourAni = PlayState.p1SAttLeft;
    		
    		setUserHP(150);
    		fullHP = 150;
    	} else if(armourNo.equals("#armour2")) {
    		// Mage Armour
			spStandRightArmourAni = PlayState.p1A1StandRight;
    		spStandLeftArmourAni = PlayState.p1A1StandLeft;
    		spWalkRightArmourAni = PlayState.p1A1Right;
    		spWalkLeftArmourAni = PlayState.p1A1Left;
    		spLightAttRArmourAni = PlayState.p1A1LAttRight;
    		spLightAttLArmourAni = PlayState.p1A1LAttLeft;
    		spStrongAttRArmourAni = PlayState.p1A1SAttRight;
    		spStrongAttLArmourAni = PlayState.p1A1SAttLeft;
			
			setUserHP(300);
			fullHP = 300;
    	} else if (armourNo.equals("#armour3")) {
    		// Battle Mage Armour
			spStandRightArmourAni = PlayState.p1A2StandRight;
    		spStandLeftArmourAni = PlayState.p1A2StandLeft;
    		spWalkRightArmourAni = PlayState.p1A2Right;
    		spWalkLeftArmourAni = PlayState.p1A2Left;
    		spLightAttRArmourAni = PlayState.p1A2LAttRight;
    		spLightAttLArmourAni = PlayState.p1A2LAttLeft;
    		spStrongAttRArmourAni = PlayState.p1A2SAttRight;
    		spStrongAttLArmourAni = PlayState.p1A2SAttLeft;
    		
    		setUserHP(800);
    		fullHP = 800;
		}
    	
    }

    /*
     * Gets the user's health points so other classes could call this method.
     */
	public float getUserHP() {
		return userHP;
	}

	/*
	 * Sets the user's health points so other classes could call this method.
	 */
	public void setUserHP(float userHP) {
		this.userHP = userHP;
	}
    
}
