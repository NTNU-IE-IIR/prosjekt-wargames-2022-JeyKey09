package no.ntnu.mathijoh.wargame;

import no.ntnu.mathijoh.wargame.units.*;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BattleTest {

    public List<Unit> sampleUnitList(int infantryNumber, String infantryName, int rangedNumber, String rangedName, 
    int cavalryNumber, String cavalryName, int commanderNumber, String commanderName)  {
    
    List<Unit> placeholderList = new ArrayList<>();
    for (int i = 0; i < infantryNumber; i++) {
        placeholderList.add(new InfantryUnit(infantryName, 100));
    }
    for (int i = 0; i < rangedNumber; i++) {
        placeholderList.add(new RangedUnit(rangedName, 100));
    }
    for (int i = 0; i < cavalryNumber; i++) {
        placeholderList.add(new CavalryUnit(cavalryName, 100));
    }
    for (int i = 0; i < commanderNumber; i++) {
        placeholderList.add(new CommanderUnit(commanderName, 180));
    }

    return placeholderList;
    }

    @Test
    public void posetivTestConstructor() {
        
    }

    @Test
    public void testSimulate() {
        Army armyOne = new Army("name", sampleUnitList(100, "inf", 100, "ran", 100, "cav", 1000, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 0, "ran", 0, "cav", 1, "commander"));
        Battle battle = new Battle(armyOne, armyTwo);
        assertNotNull(battle.simulate());
    }

    @Test
    public void testToString() {

    }
}
