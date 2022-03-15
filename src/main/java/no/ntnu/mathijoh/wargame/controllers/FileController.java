package no.ntnu.mathijoh.wargame.controllers;

import java.io.File;
import java.util.Scanner;

import no.ntnu.mathijoh.wargame.models.Army;

public class FileController {

    public Army getArmyOfCSVFile(File file) {
        Army placeholderArmy = null;
        try (Scanner cs = new Scanner(file)) {
            cs.useDelimiter(";");
            while (cs.hasNext()) {
                String test = cs.next();
                System.out.println(test);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return placeholderArmy;
    }

}
