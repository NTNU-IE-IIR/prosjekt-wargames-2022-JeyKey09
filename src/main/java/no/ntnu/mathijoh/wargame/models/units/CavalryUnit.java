package no.ntnu.mathijoh.wargame.models.units;
/**
 * CavalryUnit that is a type of unit. 
 */
public class CavalryUnit extends Unit{

    private boolean firstAttack = true;
    /**
     * Creates a Unit of type CavalryUnit
     * @param name of the unit
     * @param health how much health it has
     * @param attack of the unit
     * @param armor of the unit
     * @throws IllegalArgumentException if any of the paramters is null
     */
    public CavalryUnit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        super(name, health, attack, armor);
    }
    
    /**
     * Creates a Unit of type CavalryUnit with 20 in attack and 12 in armor
     * @param name of the unit 
     * @param health how much health it had
     * @throws IllegalArgumentException if any parameters is null
     */
    public CavalryUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 20, 12);
    }

    
    @Override
    public int getAttackBonus() {
        int attackBonus;
        if(firstAttack){
            attackBonus = 6;
            this.firstAttack = false;
        } else {
            attackBonus = 2;
        }
        return attackBonus;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
