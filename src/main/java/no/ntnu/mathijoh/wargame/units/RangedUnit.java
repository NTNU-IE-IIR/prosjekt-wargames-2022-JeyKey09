package no.ntnu.mathijoh.wargame.units;

/**
 * A unit that focuses in ranged attacked.
 * They start with high attack but gets lower for every attack until the second 
 */
public class RangedUnit extends Unit{
    
    private int amountOfAttacks = 0;
    /**
     * 
     * @param name of the unit
     * @param health of the unit
     * @param attack of the unit
     * @param armor of the unit
     * @throws IllegalArgumentException if any values are null
     */
    public RangedUnit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        super(name, health, attack, armor);
    }
    /**
     * Creates a new ranged unit with 15 in attack and 8 in armor
     * @param name of the unit
     * @param health of the unit
     * @throws IllegalArgumentException if any values are null
     */
    public RangedUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 15, 8);
    }
    
    @Override
    public int getAttackBonus() {
        return 3;
    }

    @Override
    public int getResistBonus() {
        int resist;
        switch(amountOfAttacks){
            case 0:
                resist = 6;
                break;
            case 1:
                resist = 4;
                break;
            default:
                resist = 2;
        }
        amountOfAttacks += 1;
        return resist;
    }
}
