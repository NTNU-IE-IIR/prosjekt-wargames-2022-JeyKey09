package no.ntnu.mathijoh.wargame.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.models.Army;

public class FileControllerTest {
    @Test
    void testGetArmyOfFile() {
        File file = new File(getClass().getResource("test.csv").getPath().replace("%20", " "));
        FileController fileController = new FileController();
        Army placeholderArmy = null;
        try {
            placeholderArmy = fileController.getArmyOfCSVFile(file);    
            assertEquals("Human-army", placeholderArmy.getName());
            assertEquals(true, placeholderArmy.hasUnits());
            assertEquals(2, placeholderArmy.getCavalryUnit().size());
            assertEquals(1, placeholderArmy.getCommanderUnit().size());
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
