package no.ntnu.mathijoh.wargame.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.units.*;

public class FileControllerTest {

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
    void testGetArmyOfFile() {
        File file = new File(getClass().getResource("test.csv").getPath().replace("%20", " "));
        Army placeholderArmy = null;
        try {
            placeholderArmy = FileController.getArmyOfCSVFile(file, "");    
            assertEquals("Human-army", placeholderArmy.getName());
            assertEquals(true, placeholderArmy.hasUnits());
            assertEquals(2, placeholderArmy.getCavalryUnits().size());
            assertEquals(1, placeholderArmy.getCommanderUnits().size());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetArmyOfFileAnotherDelimiter() {
        File file = new File(getClass().getResource("testotherdelimiter.csv").getPath().replace("%20", " "));
        Army placeholderArmy = null;
        try {
            placeholderArmy = FileController.getArmyOfCSVFile(file, ",");    
            assertEquals("Human-army", placeholderArmy.getName());
            assertEquals(true, placeholderArmy.hasUnits());
            assertEquals(2, placeholderArmy.getCavalryUnits().size());
            assertEquals(1, placeholderArmy.getCommanderUnits().size());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetArmyOfFileWithWrongFormat() {
        File file = new File(getClass().getResource("wrongtest.csv").getPath().replace("%20", " "));
        Army placeholderArmy = null;
        try {
            placeholderArmy = FileController.getArmyOfCSVFile(file, "");    
            assertTrue(false);
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        assertNull(placeholderArmy);
    }
    
    @Test
    void testSaveArmyToFile() {
        File folder = new File(getClass().getResource("").getPath().replace("%20", " "));   
        File savelocation = new File(folder.getAbsolutePath()+"/save.csv");
        Army testArmy = new Army("Army1",sampleUnitList(10, 20, 30, 1));
        try {
           FileController.saveArmyInCSV(savelocation, testArmy);
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(savelocation.exists());
        Army loadedArmy = null;
        try {
            loadedArmy = FileController.getArmyOfCSVFile(savelocation, "");
        } catch (Exception e) {

        }
        assertEquals(testArmy, loadedArmy);
    }

    

    @Test
    void testSaveArmyToWrongFileType() {
        File folder = new File(getClass().getResource("").getPath().replace("%20", " "));   
        File savelocation = new File(folder.getAbsolutePath()+"/save.csv.txt");
        Army testArmy = new Army("Army1",sampleUnitList(10, 20, 30, 1));
        try {
            FileController.saveArmyInCSV(savelocation, testArmy);
            assertTrue(false);
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        assertFalse(savelocation.exists());
    }
}