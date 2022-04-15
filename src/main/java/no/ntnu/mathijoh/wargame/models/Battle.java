package no.ntnu.mathijoh.wargame.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import no.ntnu.mathijoh.wargame.models.map.BattleMap;
import no.ntnu.mathijoh.wargame.models.map.Tile;
import no.ntnu.mathijoh.wargame.models.units.Unit;

/**
 * This class is used to simulate a battle between 2 armies
 */
public class Battle{
    private Army armyOne;
    private Army armyTwo;
    private BattleMap map;
    private Army victoryArmy;
    private Random randomGenerator;


    /**
     * Constructor for the Batte class
     * 
     * @param armyOne First Army
     * @param armyTwo Second Army
     * @throws IllegalArgumentException if any of the parameters is null
     */
    public Battle(Army armyOne, Army armyTwo, BattleMap map) throws IllegalArgumentException {
        if (armyOne == null || armyTwo == null) {
            throw new IllegalArgumentException("None of the argument can be null");
        }
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        this.map = map;
        this.victoryArmy = null;
        this.randomGenerator = new Random();
    }

    /**
     * Simulates the battle between the armies
     * 
     * @return the army that still has units after the battle
     */
    public Army simulate() {
        while(victoryArmy == null){
            runStep();
        }
        return victoryArmy;
    }

    /**
     * Runs a single step of the battle
     */
    public void runStep() {
        if (armyOne.hasUnits() && armyTwo.hasUnits()) {
            Army attackingArmy;
            Army defendingArmy;

            if (this.randomGenerator.nextBoolean()) {
                attackingArmy = this.armyOne;
                defendingArmy = this.armyTwo;
            } else {
                attackingArmy = this.armyTwo;
                defendingArmy = this.armyOne;
            }
            
            Unit attackingUnit = attackingArmy.getRandom();
            
            Map<Tile,Unit> possibleTarget = map.getPossibleTargets(attackingUnit);
            
            Tile toMove = possibleTarget.keySet().iterator().next();
            
            Unit defendingUnit = possibleTarget.get(toMove);
            
            map.moveUnit(attackingUnit, toMove);
            
            Tile defTile = map.findUnitTile(defendingUnit);
            
            attackingUnit.attack(defendingUnit, toMove.getTerrain(), defTile.getTerrain());
            
            if (defendingUnit.getHealth() == 0) {
                map.removeUnit(defendingUnit);
                defendingArmy.remove(defendingUnit);
            }
        }

        else if (armyOne.hasUnits()) {
            this.victoryArmy = armyOne;
        } else {
            this.victoryArmy = armyTwo;
        }
    }

    public boolean isNotFinished() {
        return armyOne.hasUnits() && armyTwo.hasUnits();
    }

    public Army getVictoryArmy() throws IllegalStateException {
        if(victoryArmy == null){
            throw new IllegalStateException("Battle has not been finished yet");
        }
        return victoryArmy;
    }

    @Override
    public String toString() {
        return "Battle [armyOne=" + armyOne + ", armyTwo=" + armyTwo + ", map=" + map + "]";
    }


}
