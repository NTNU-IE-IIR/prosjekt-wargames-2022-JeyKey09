package no.ntnu.mathijoh.wargame.models;

import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.Battle;
import no.ntnu.mathijoh.wargame.models.units.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

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
    public void testPosetivAndNegativConstructor() {
        Battle testBattle = null;
        try {
            testBattle = new Battle(null, null);   
        } catch (IllegalArgumentException e) {
            assertEquals("None of the argument can be null", e.getMessage());
        }
        assertNull(testBattle);
        Army armyOne = new Army("name", sampleUnitList(100, "inf", 100, "ran", 100, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 100, "ran", 0, "cav", 1, "commander"));
        testBattle = new Battle(armyOne, armyTwo);
        assertNotNull(testBattle);

    }

    @Test
    public void testSimulate() {
        Army armyOne = new Army("name", sampleUnitList(100, "inf", 100, "ran", 100, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 0, "ran", 0, "cav", 10000, "commander"));
        Battle battle = new Battle(armyOne, armyTwo);
        assertEquals(armyTwo,battle.simulate());
        
        armyOne = new Army("name", sampleUnitList(100, "inf", 100, "ran", 100, "cav", 1, "commander"));
        armyTwo = new Army("name", sampleUnitList(0, "inf", 0, "ran", 0, "cav", 1, "commander"));
        battle = new Battle(armyOne, armyTwo);
        assertEquals(armyOne,battle.simulate());
    }

    @Test
    public void testToString() {
        Army armyOne = new Army("name", sampleUnitList(100, "inf", 100, "ran", 100, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 100, "ran", 0, "cav", 1, "commander"));
        Battle battle = new Battle(armyOne, armyTwo);
        assertEquals(battle.toString(), "Battle [armyOne=" + armyOne + ", armyTwo=" + armyTwo + "]");
    }
}
