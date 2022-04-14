package no.ntnu.mathijoh.wargame.models;

import java.util.HashMap;

import no.ntnu.mathijoh.wargame.models.map.Map;
import no.ntnu.mathijoh.wargame.models.map.Tile;
import no.ntnu.mathijoh.wargame.models.units.Unit;

/**
 * This class is used to simulate a battle between 2 armies
 */
public class Battle {
    private Army armyOne;
    private Army armyTwo;

    /**
     * Constructor for the Batte class
     * 
     * @param armyOne First Army
     * @param armyTwo Second Army
     * @throws IllegalArgumentException if any of the parameters is null
     */
    public Battle(Army armyOne, Army armyTwo) throws IllegalArgumentException {
        if (armyOne == null || armyTwo == null) {
            throw new IllegalArgumentException("None of the argument can be null");
        }
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * Simulates the battle between the armies
     * 
     * @return the army that still has units after the battle
     */
    public Army simulate() {
        boolean isUnitOneAttacking = true;
        Army victoryArmy;

        while (armyOne.hasUnits() && armyTwo.hasUnits()) {
            Army attackingArmy;
            Army defendingArmy;

            if (isUnitOneAttacking) {
                attackingArmy = this.armyOne;
                defendingArmy = this.armyTwo;
            } else {
                attackingArmy = this.armyTwo;
                defendingArmy = this.armyOne;
            }
            isUnitOneAttacking = !(isUnitOneAttacking);

            Unit defendingUnit = defendingArmy.getRandom();
            attackingArmy.getRandom().attack(defendingUnit);

            if (defendingUnit.getHealth() == 0) {
                defendingArmy.remove(defendingUnit);
            }
        }

        if (armyOne.hasUnits()) {
            victoryArmy = armyOne;
        } else {
            victoryArmy = armyTwo;
        }

        return victoryArmy;
    }

        /**
     * Simulates the battle between the armies
     * 
     * @return the army that still has units after the battle
     */
    public Army simulate(Map map) {
        boolean isUnitOneAttacking = true;
        Army victoryArmy;

        while (armyOne.hasUnits() && armyTwo.hasUnits()) {
            Army attackingArmy;
            Army defendingArmy;

            if (isUnitOneAttacking) {
                attackingArmy = this.armyOne;
                defendingArmy = this.armyTwo;
            } else {
                attackingArmy = this.armyTwo;
                defendingArmy = this.armyOne;
            }
            
            isUnitOneAttacking = !(isUnitOneAttacking);
            
            Unit attackingUnit = attackingArmy.getRandom();
            HashMap<Tile,Unit> possibleTarget = map.getPossibleTargets(attackingUnit);
            Tile toMove = possibleTarget.keySet().iterator().next();
            Unit defendingUnit = possibleTarget.get(toMove);
            int[] defendingUnitcord = map.findUnitCordinates(defendingUnit);
            Tile defTile = map.getTile(defendingUnitcord[0], defendingUnitcord[1]);
            attackingUnit.attack(defendingUnit, toMove.getTerrain(), defTile.getTerrain());
            if (defendingUnit.getHealth() == 0) {
                defendingArmy.remove(defendingUnit);
            }
        }

        if (armyOne.hasUnits()) {
            victoryArmy = armyOne;
        } else {
            victoryArmy = armyTwo;
        }

        return victoryArmy;
    }


    @Override
    public String toString() {
        return "Battle [armyOne=" + armyOne + ", armyTwo=" + armyTwo + "]";
    }
}
