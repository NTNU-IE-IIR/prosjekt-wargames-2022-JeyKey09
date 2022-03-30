package no.ntnu.mathijoh.wargame.models.units;

import java.util.HashMap;

/**
 * Infantry units, normal foot soldiers basically
 */
public class InfantryUnit extends Unit {

    /**
     * Creates a new infantryUnit
     * 
     * @param name   of the unit
     * @param health of the unit
     * @param attack of the unit
     * @param armor  of the unit
     * @throws IllegalArgumentException if any parameters equals null
     */

    public InfantryUnit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        super(name, health, attack, armor);
        this.putTerrainAttackBonus('T', 2);
        this.putTerrainDefenceBonus('T', 2);
        
    }

    /**
     * Creates a new infantryUnit with 15 attak and 10 in armor
     * 
     * @param name   of the unit
     * @param health of the uni
     * @throws IllegalArgumentException if any parameters equals null
     */
    public InfantryUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 15, 10);
    }

    @Override
    public int getAttackBonus() {
        return 2;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
