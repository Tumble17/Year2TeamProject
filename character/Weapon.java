package character;

import java.io.IOException;

import login.SinglePlayerReadWriteTextFile;

import org.newdawn.slick.Animation;

import singleplayer.PlayState;

/**
 * Changes the appearance of the user's weapon.
 *
 * @author Jack
 * @author Chau Nguyen 1254579
 * 
 */
public class Weapon {
    
	// Variables for the attack damage on both attacks.
    private int wpnAttack;
    private int wpnSAttack;
    
    // This weapon contains a unique abilities 
    // to gain extra damage after each kill
    public boolean isSanguine = false;
    
    // Animation variable to overwrite 
    // the current animations in PlayState
    public Animation spStandRightWpnAni;
    public Animation spStandLeftWpnAni;
    public Animation spWalkRightWpnAni;
    public Animation spWalkLeftWpnAni;
    public Animation spLightAttRWpnAni;
    public Animation spLightAttLWpnAni;
    public Animation spStrongAttRWpnAni;
    public Animation spStrongAttLWpnAni;
    
    /**
     * Constructor: Constructs the weapons that the user is currently using.
     * 
     * @param wpnNo
     * @throws NumberFormatException
     * @throws IOException
     */
    public Weapon(String wpnNo) throws NumberFormatException, IOException {
    	weaponType(wpnNo);
    }
    
    
    public void weaponType(String wpnNo) throws NumberFormatException, IOException {
    	
    	// All of the weapon's attack damage are set for both light and strong attacks
    	if(wpnNo.equals("~sword1")) {
    		// Wooden Sword
    		spStandRightWpnAni = PlayState.wsStandRight;
    		spStandLeftWpnAni = PlayState.wsStandLeft;
    		spWalkRightWpnAni = PlayState.wsStandRight;
    		spWalkLeftWpnAni = PlayState.wsStandLeft;
    		spLightAttRWpnAni = PlayState.wsLAttRight;
    		spLightAttLWpnAni = PlayState.wsLAttLeft;
    		spStrongAttRWpnAni = PlayState.wsSAttRight;
    		spStrongAttLWpnAni = PlayState.wsSAttLeft;
    		
    		setWpnAttack(20);
    		setWpnSAttack(40);
    	} else if(wpnNo.equals("~sword2")) {
    		// Longsword
    		spStandRightWpnAni = PlayState.lsStandRight;
    		spStandLeftWpnAni = PlayState.lsStandLeft;
    		spWalkRightWpnAni = PlayState.lsStandRight;
    		spWalkLeftWpnAni = PlayState.lsStandLeft;
    		spLightAttRWpnAni = PlayState.lsLAttRight;
    		spLightAttLWpnAni = PlayState.lsLAttLeft;
    		spStrongAttRWpnAni = PlayState.lsSAttRight;
    		spStrongAttLWpnAni = PlayState.lsSAttLeft;
    		
    		setWpnAttack(40);
    		setWpnSAttack(80);
    	} else if(wpnNo.equals("~sword3")) {
    		// Sanguine
    		isSanguine = true;
    		
    		spStandRightWpnAni = PlayState.sStandRight;
    		spStandLeftWpnAni = PlayState.sStandLeft;
    		spWalkRightWpnAni = PlayState.sRight;
    		spWalkLeftWpnAni = PlayState.sLeft;
    		spLightAttRWpnAni = PlayState.sLAttRight;
    		spLightAttLWpnAni = PlayState.sLAttLeft;
    		spStrongAttRWpnAni = PlayState.sSAttRight;
    		spStrongAttLWpnAni = PlayState.sSAttLeft;
    		
    		setWpnAttack(Integer.parseInt(SinglePlayerReadWriteTextFile.getSanguine("user")));
    		setWpnSAttack(Integer.parseInt(SinglePlayerReadWriteTextFile.getSanguine("user")));
    	}
    	
    }


    /*
     * Getter and Setter methods for the Light attack damage.
     */
	public int getWpnAttack() {
		return wpnAttack;
	}


	public void setWpnAttack(int wpnAttack) {
		this.wpnAttack = wpnAttack;
	}


	/*
	 * Getter and Setter methods for the Strong attack damage. 
	 */
	public int getWpnSAttack() {
		return wpnSAttack;
	}


	public void setWpnSAttack(int wpnSAttack) {
		this.wpnSAttack = wpnSAttack;
	}
    
}
