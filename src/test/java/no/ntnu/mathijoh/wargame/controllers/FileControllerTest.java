package no.ntnu.mathijoh.wargame.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.map.Map;
import no.ntnu.mathijoh.wargame.models.units.*;

public class FileControllerTest {

    public List<Unit> sampleUnitList(int infantryNumber, int rangedNumber,
            int cavalryNumber, int commanderNumber) {

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
        assertDoesNotThrow(() -> {
            Army placeholderArmy = FileController.getArmyOfCSVFile(file, "");
            assertEquals("Human-army", placeholderArmy.getName());
            assertEquals(true, placeholderArmy.hasUnits());
            assertEquals(2, placeholderArmy.getCavalryUnits().size());
            assertEquals(1, placeholderArmy.getCommanderUnits().size());
        });

    }

    @Test
    void testGetArmyOfFileAnotherDelimiter() {
        File file = new File(getClass().getResource("testotherdelimiter.csv").getPath().replace("%20", " "));
        assertDoesNotThrow(() -> {
            Army placeholderArmy = FileController.getArmyOfCSVFile(file, ",");
            assertEquals("Human-army", placeholderArmy.getName());
            assertEquals(true, placeholderArmy.hasUnits());
            assertEquals(2, placeholderArmy.getCavalryUnits().size());
            assertEquals(1, placeholderArmy.getCommanderUnits().size());
        });
    }

    @Test
    void testGetArmyOfFileWithWrongFormat() {
        File file = new File(getClass().getResource("wrongtest.csv").getPath().replace("%20", " "));
        assertThrows(IllegalArgumentException.class, () -> FileController.getArmyOfCSVFile(file, ""));
    }

    @Test
    void testSaveArmyToFile() throws FileNotFoundException, IllegalArgumentException, UnknownError {
        File folder = new File(getClass().getResource("").getPath().replace("%20", " "));
        File savelocation = new File(folder.getAbsolutePath() + "/save.csv");
        Army testArmy = new Army("Army1", sampleUnitList(10, 20, 30, 1));

        assertDoesNotThrow(() -> FileController.saveArmyInCSV(savelocation, testArmy));
        assertTrue(savelocation.exists());

        Army loadedArmy = FileController.getArmyOfCSVFile(savelocation, "");
        assertEquals(testArmy, loadedArmy);
    }

    @Test
    void testSaveArmyToWrongFileType() {
        File folder = new File(getClass().getResource("").getPath().replace("%20", " "));
        File savelocation = new File(folder.getAbsolutePath() + "/save.csv.txt");
        Army testArmy = new Army("Army1", sampleUnitList(10, 20, 30, 1));
        assertThrows(IllegalArgumentException.class, () -> FileController.saveArmyInCSV(savelocation, testArmy));
        assertFalse(savelocation.exists());
    }

    @Test
    void testImportMapFromFile(){
        File terrainfFile = new File(getClass().getResource("terrains/example.txt").getPath().replace("%20", " "));
        assertDoesNotThrow(() -> {
            Map map = FileController.importMapFromFile(terrainfFile);
            assertNotNull(map);
        });
    }
}
