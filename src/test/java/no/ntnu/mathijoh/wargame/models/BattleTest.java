package no.ntnu.mathijoh.wargame.models;
//TODO: Modifiy the tests and clean up the code
import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.controllers.FileController;
import no.ntnu.mathijoh.wargame.models.map.BattleMap;
import no.ntnu.mathijoh.wargame.models.units.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        BattleMap map = assertDoesNotThrow(() -> FileController.importMapFromFile(new File("src\\test\\resources\\no\ntnu\\mathijoh\\wargame\\controllers\\maps\\example.txt"))); 
        Battle testBattle = new Battle(armyOne, armyTwo, map);
        assertNotNull(testBattle);

    }

    // @Test
    // public void testSimulate() {
    //     Army armyOne = new Army("name", sampleUnitList(100, "inf", 100, "ran", 100, "cav", 1, "commander"));
    //     Army armyTwo = new Army("name", sampleUnitList(0, "inf", 0, "ran", 0, "cav", 10000, "commander"));
    //     Battle battle = new Battle(armyOne, armyTwo);
    //     assertEquals(armyTwo,battle.simulate());
        
    //     armyOne = new Army("name", sampleUnitList(100, "inf", 100, "ran", 100, "cav", 1, "commander"));
    //     armyTwo = new Army("name", sampleUnitList(0, "inf", 0, "ran", 0, "cav", 1, "commander"));
    //     battle = new Battle(armyOne, armyTwo);
    //     assertEquals(armyOne,battle.simulate());
    // }

    // @Test
    // public void testToString() {
    //     Army armyOne = new Army("name", sampleUnitList(100, "inf", 100, "ran", 100, "cav", 1, "commander"));
    //     Army armyTwo = new Army("name", sampleUnitList(0, "inf", 100, "ran", 0, "cav", 1, "commander"));
    //     Battle battle = new Battle(armyOne, armyTwo);
    //     assertEquals(battle.toString(), "Battle [armyOne=" + armyOne + ", armyTwo=" + armyTwo + "]");
    // }
}
