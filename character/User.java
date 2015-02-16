package character;

import multiplayer.MultiPlayState;
import multiplayer.MultiPlayer;

import org.newdawn.slick.Animation;

/**
 * 
 * @author Jack Dyer 1223659
 *
 */
public class User implements Character{

	// LIFE CYCLE FIELDS
    public String userName = "TEST";
    public int hitPoints;
    public int defence;
    public int attack;
    public int level;
    public int exp;
    public int coinsLeft;
    public float xVal;
    public float yVal;
    public Animation curAni;
    public boolean isPainted = true;
	public boolean isAttacking = false;
    
    @Override
    public void attack(double xVal, double yVal) {
        
    }

    @Override
    public void spawn(Character character) {
        
    }
    public void spawn(){
        xVal = MultiPlayState.gameAreaXStart;
        yVal = MultiPlayState.gameAreaYStart;
        hitPoints = 100;
        
    }
    public void heal(){
        if(hitPoints < 100){
            hitPoints += 1;
        }
    }
    
    public void die(User user){
        
    }

}
