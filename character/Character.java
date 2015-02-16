package character;

/**
 * 
 * @author Jack Dyer 1223659
 *
 */
public interface Character {
    
	// LIFE CYCLE FIELDS
    double attack = 0;
    double defence = 0;
    double hitP = 100;
    double xVal = 50;
    double yVal = 50;
    
    // INTERFACE LIFE CYCLE METHODS
    public void attack(double xVal, double yVal);
    public void spawn(Character character);

}
