package no.ntnu.mathijoh.wargame.handlers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.map.BattleMap;
import no.ntnu.mathijoh.wargame.models.units.*;

public class FileHandlerTest {

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
    public void testGetArmyOfFile() {
        File file = new File(getClass().getResource("test.csv").getPath().replace("%20", " "));
        assertDoesNotThrow(() -> {
            Army placeholderArmy = FileHandler.getArmyOfCSVFile(file);
            assertEquals("Human-army", placeholderArmy.getName());
            assertEquals(true, placeholderArmy.hasUnits());
            assertEquals(2, placeholderArmy.getCavalryUnits().size());
            assertEquals(1, placeholderArmy.getCommanderUnits().size());
        });

    }

    @Test
    public void testGetArmyOfFileAnotherDelimiter() {
        File file = new File(getClass().getResource("testotherdelimiter.csv").getPath().replace("%20", " "));
        assertDoesNotThrow(() -> {
            Army placeholderArmy = FileHandler.getArmyOfCSVFile(file, ",");
            assertEquals("Human-army", placeholderArmy.getName());
            assertEquals(true, placeholderArmy.hasUnits());
            assertEquals(2, placeholderArmy.getCavalryUnits().size());
            assertEquals(1, placeholderArmy.getCommanderUnits().size());
        });
    }

    @Test
    public void testGetArmyOfFileWithWrongFormat() {
        File file = new File(getClass().getResource("wrongtest.csv").getPath().replace("%20", " "));
        assertThrows(IllegalArgumentException.class, () -> FileHandler.getArmyOfCSVFile(file, ""));
    }

    @Test
    public void testSaveArmyToFile() {
        File folder = new File(getClass().getResource("").getPath().replace("%20", " "));
        File savelocation = new File(folder.getAbsolutePath() + "/save.csv");
        Army testArmy = new Army("Army1", sampleUnitList(10, 20, 30, 1));

        assertDoesNotThrow(() -> FileHandler.saveArmyInCSV(savelocation, testArmy));
        assertTrue(savelocation.exists());
        assertDoesNotThrow(() -> {
            Army loadedArmy = FileHandler.getArmyOfCSVFile(savelocation);
            assertEquals(testArmy, loadedArmy);
        });
    }

    @Test
    public void testSaveArmyToWrongFileType() {
        File folder = new File(getClass().getResource("").getPath().replace("%20", " "));
        File savelocation = new File(folder.getAbsolutePath() + "/save.csv.txt");
        Army testArmy = new Army("Army1", sampleUnitList(10, 20, 30, 1));
        assertThrows(IllegalArgumentException.class, () -> FileHandler.saveArmyInCSV(savelocation, testArmy));
        assertFalse(savelocation.exists());
    }

    @Test
    public void testImportMapFromFile() {
        File terrainfFile = new File(getClass().getResource("maps/example.txt").getPath().replace("%20", " "));
        assertDoesNotThrow(() -> {
            BattleMap map = FileHandler.importMapFromFile(terrainfFile);
            assertNotNull(map);
        });
    }

    @Test
    public void negativeTestImportMapFromFile() {
        File terrainfFile = new File(getClass().getResource("maps/wrongExample.txt").getPath().replace("%20", " "));
        assertThrows(IllegalArgumentException.class, () -> FileHandler.importMapFromFile(terrainfFile));
    }
}
