package no.ntnu.mathijoh.wargame.models;


import org.junit.jupiter.api.Test;
import no.ntnu.mathijoh.wargame.models.units.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class ArmyTest {

    public List<Unit> sampleUnitList(int infantryNumber, int rangedNumber, 
        int cavalryNumber, int commanderNumber)  {
        
        List<Unit> placeholderList = new ArrayList<>();
        for (int i = 0; i < infantryNumber; i++) {
            placeholderList.add(new InfantryUnit("infantryName", 100));
        }
        for (int i = 0; i < rangedNumber; i++) {
            placeholderList.add(new RangedUnit("rangedName", 100));
        }
        for (int i = 0; i < cavalryNumber; i++) {
            placeholderList.add(new CavalryUnit("cavalryName", 100));
        }
        for (int i = 0; i < commanderNumber; i++) {
            placeholderList.add(new CommanderUnit("commanderName", 180));
        }

        return placeholderList;
    }

    @Test
    public void testAddandRemove() {
        Army testArmy = new Army("Test");
        Unit testUnit1 = new InfantryUnit("name", 10);
        testArmy.add(testUnit1);
        assertEquals(testUnit1, testArmy.getAllUnits().get(0));
        testArmy.remove(testUnit1);
        assertTrue(testArmy.getAllUnits().isEmpty());
        
        try {
            testArmy.remove(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Unit can't be null", e.getMessage());
        }
        assertEquals(0, testArmy.getAllUnits().size());

        try {
            testArmy.add(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Unit can't be null", e.getMessage());
        }
        assertEquals(0, testArmy.getAllUnits().size());
    }

    @Test
    public void testAddAllAndGetAll() {
        Army testArmy = new Army("testArmy");
        List<Unit> sampleList = sampleUnitList(10, 3,  6, 3);
        testArmy.addAll(sampleList);
        assertEquals(testArmy.getAllUnits(), sampleList);
        try {
            testArmy.addAll(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Unit list can't be null", e.getMessage());
        }
        assertEquals(testArmy.getAllUnits(), sampleList);
    }

    @Test
    public void testGetName() {
        Army testArmy = new Army("testArmy");
        assertEquals("testArmy", testArmy.getName());
    }

    @Test
    public void testGetRandom() {
        Army testArmy = new Army("TestArmy");
        try {
            testArmy.getRandom();
        } catch (IllegalStateException e) {
            assertEquals("There is no units in the Army",e.getMessage());
        }
        
        Unit newUnit = new InfantryUnit("name", 10);
        testArmy.add(newUnit);

        assertEquals(testArmy.getRandom(), newUnit);
    }

    @Test
    public void testHasUnits() {
        Army testArmy = new Army("TestingArmy");
        assertEquals(false, testArmy.hasUnits());
        testArmy.add(new InfantryUnit("name", 10));
        assertEquals(true, testArmy.hasUnits());
    }

    @Test
    public void testEquals() {
        Army testArmy = new Army("Test");
        Army testArmy2 = new Army("Test");
        assertEquals(testArmy, testArmy2);
        
        Unit testUnit1 = new InfantryUnit("name", 10);
        Unit testUnit2 = new InfantryUnit("name", 10);
        testArmy.add(testUnit1);
        testArmy2.add(testUnit1);
        assertEquals(testArmy, testArmy2);

        testArmy.add(testUnit1);
        testArmy2.add(testUnit2);
        assertEquals(testArmy, testArmy2);
        assertEquals(testArmy.hashCode(), testArmy2.hashCode());
    }

    @Test
    public void testGetRangedUnit() {
        Army testArmy = new Army("Test");
        testArmy.addAll(sampleUnitList(10,20,30,40));
        assertEquals(20,testArmy.getRangedUnits().size());
    }
    
    @Test
    public void testGetInfantryUnit() {
        Army testArmy = new Army("Test");
        testArmy.addAll(sampleUnitList(10,20,30,40));
        assertEquals(10,testArmy.getInfantryUnits().size());
    }
    
    @Test
    public void testGetCommanderUnit() {
        Army testArmy = new Army("Test");
        testArmy.addAll(sampleUnitList(10,20,30,40));
        assertEquals(40,testArmy.getCommanderUnits().size());
    }
    
    @Test
    public void testGetCavalryUnit() {
        Army testArmy = new Army("Test");
        testArmy.addAll(sampleUnitList(10,20,30,40));
        assertEquals(30,testArmy.getCavalryUnits().size());
    }

}
