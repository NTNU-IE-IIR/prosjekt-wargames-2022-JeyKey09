package no.ntnu.mathijoh.wargame.models.units;

import no.ntnu.mathijoh.wargame.models.map.Terrain;

/**
 * A unit that focuses in ranged attacked.
 * They start with high attack but gets lower for every attack until the second
 */
public class RangedUnit extends Unit {

    private int amountOfAttacks = 0;

    /**
     * Constructor for RangedUnit class
     * 
     * @param name   of the unit
     * @param health of the unit
     * @param attack of the unit
     * @param armor  of the unit
     * @throws IllegalArgumentException if any values are null
     */
    public RangedUnit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        super(name, health, attack, armor);
        this.putTerrainAttackBonus(Terrain.FOREST, 2);
        this.putTerrainAttackBonus(Terrain.FOREST, -1);
    }

    /**
     * Constructor for RangedUnit class that gives it 15 in armor and 8 in attack
     * 
     * @param name   of the unit
     * @param health of the unit
     * @throws IllegalArgumentException if any values are null
     */
    public RangedUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 15, 8);
        this.putTerrainAttackBonus(Terrain.FOREST, 2);
        this.putTerrainAttackBonus(Terrain.FOREST, -1);
    }

    @Override
    public int getAttackBonus() {
        return 3;
    }


    @Override
    public int getResistBonus() {
        int resist;
        switch (amountOfAttacks) {
            case 0:
                resist = 6;
                break;
            case 1:
                resist = 4;
                break;
            default:
                resist = 2;
        }
        amountOfAttacks++;
        return resist;
    }
}
