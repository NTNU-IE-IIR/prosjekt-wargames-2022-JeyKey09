package no.ntnu.mathijoh.wargame.models;
//TODO: Modifiy the tests and clean up the code
import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.controllers.FileController;
import no.ntnu.mathijoh.wargame.models.map.BattleMap;
import no.ntnu.mathijoh.wargame.models.units.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
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
        
        assertThrows(IllegalArgumentException.class, () -> {new Battle (null, null, null);});
        Army armyOne = new Army("name", sampleUnitList(100, "inf", 100, "ran", 100, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 100, "ran", 0, "cav", 1, "commander"));
        BattleMap map = new BattleMap("TestMap", 100, 100);
        Battle testBattle = new Battle(armyOne, armyTwo, map);
        assertNotNull(testBattle);
    }

    /**
     * The test has a a really low probability of failing, but it is still a good idea to test for the correct behavior.
     */
    @Test
    public void testSimulate() {
        Army armyOne = new Army("name", sampleUnitList(1, "inf", 1, "ran", 1, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 0, "ran", 0, "cav", 90, "commander"));
        BattleMap map = new BattleMap("Test",10,10);
        Battle testBattle = new Battle(armyOne, armyTwo, map);
        assertEquals(armyTwo,testBattle.simulate());
        
        armyOne = new Army("name", sampleUnitList(1, "inf", 1, "ran", 1, "cav", 1, "commander"));
        armyTwo = new Army("name", sampleUnitList(90, "inf", 0, "ran", 0, "cav", 1, "commander"));
        map = new BattleMap("Test",10,10);
        testBattle = new Battle(armyOne, armyTwo, map);
        assertEquals(armyTwo,testBattle.simulate());
    }

    @Test
    public void testToString() {
        Army armyOne = new Army("name", sampleUnitList(10, "inf", 10, "ran", 10, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 10, "ran", 0, "cav", 1, "commander"));
        BattleMap map = new BattleMap("Test",10,10);
        Battle testBattle = new Battle(armyOne, armyTwo, map);
        assertEquals(testBattle.toString(), "Battle [armyOne=" + armyOne + ", armyTwo=" + armyTwo + ", map=" + map + "]");
    }

    @Test
    public void negativeTestConstructor(){
        Army armyOne = new Army("name", sampleUnitList(100, "inf", 100, "ran", 100, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 100, "ran", 0, "cav", 1, "commander"));
        BattleMap map = new BattleMap("Test",10,10);
        assertThrows(IllegalArgumentException.class, () -> {new Battle (armyOne, armyTwo, null);});
        assertThrows(IllegalArgumentException.class, () -> {new Battle (armyOne, armyOne, map);});
        assertThrows(IllegalArgumentException.class, () -> {new Battle (armyOne, null, map);});
        assertThrows(IllegalArgumentException.class, () -> {new Battle (null, armyTwo, map);});
    }

    @Test
    void testGetVictoryArmy() {
        Army armyOne = new Army("name", sampleUnitList(1, "inf", 1, "ran", 1, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 0, "ran", 0, "cav", 90, "commander"));
        BattleMap map = new BattleMap("Test",10,10);
        Battle testBattle = new Battle(armyOne, armyTwo, map);
        while (testBattle.isNotFinished()){
            testBattle.runStep();
        }
        assertEquals(testBattle.getVictoryArmy(), armyTwo);
    }

    @Test
    void testIsNotFinished() {
        Army armyOne = new Army("name", sampleUnitList(1, "inf", 1, "ran", 1, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 0, "ran", 0, "cav", 90, "commander"));
        BattleMap map = new BattleMap("Test",10,10);
        Battle testBattle = new Battle(armyOne, armyTwo, map);
        assertTrue(testBattle.isNotFinished());
        testBattle.simulate();
        assertFalse(testBattle.isNotFinished());
    }

    /**
     * Does a negativ and a posetiv test.
     */
    @Test
    void getVictoryArmy() {
        Army armyOne = new Army("name", sampleUnitList(1, "inf", 1, "ran", 1, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 0, "ran", 0, "cav", 90, "commander"));
        BattleMap map = new BattleMap("Test",10,10);
        Battle testBattle = new Battle(armyOne, armyTwo, map);
        assertThrows(IllegalStateException.class, () -> testBattle.getVictoryArmy());
        assertEquals(testBattle.simulate(), testBattle.getVictoryArmy());
    }


    @Test
    void testRunStep() {
        Army armyOne = new Army("name", sampleUnitList(1, "inf", 1, "ran", 1, "cav", 1, "commander"));
        Army armyTwo = new Army("name", sampleUnitList(0, "inf", 0, "ran", 0, "cav", 90, "commander"));
        BattleMap map = new BattleMap("Test",10,10);
        Battle testBattle = new Battle(armyOne, armyTwo, map);
        while(testBattle.isNotFinished()){
            testBattle.runStep();
        }
        assertEquals(testBattle.getVictoryArmy(), armyTwo);
    }


}
