package no.ntnu.mathijoh.wargame.controllers;

import java.io.File;

import org.junit.jupiter.api.Test;

public class FileControllerTest {
    @Test
    void testGetArmyOfFile() {
        File file = new File(getClass().getResource("/csv/test.csv").getPath());
        FileController fileController = new FileController();
        fileController.getArmyOfCSVFile(file);
    }
}
