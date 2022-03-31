package no.ntnu.mathijoh.wargame.models.units;

import no.ntnu.mathijoh.wargame.models.Terrain;

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
        this.putTerrainAttackBonus(Terrain.FOREST, 2);
        this.putTerrainDefenceBonus(Terrain.FOREST, 2);
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
        this.putTerrainAttackBonus(Terrain.FOREST, 2);
        this.putTerrainDefenceBonus(Terrain.FOREST, 2);
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
