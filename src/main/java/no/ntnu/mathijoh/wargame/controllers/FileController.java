package no.ntnu.mathijoh.wargame.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import no.ntnu.mathijoh.wargame.models.ParameterChecker;
import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.units.*;

public class FileController {
    /**
     * 
     * @param file a file object of a CSV file. Delimiter used is ";"
     * @return a Army with every soldier from the CSV
     * @throws FileNotFoundException    if the file couldn't be found
     * @throws IllegalArgumentException if the CSV file contained invalid parameters
     */
    public Army getArmyOfCSVFile(File file) throws FileNotFoundException, IllegalArgumentException {
        Army placeholderArmy = null;
        try (Scanner cs = new Scanner(file, StandardCharsets.UTF_8)) {
            cs.useDelimiter(";");
            placeholderArmy = new Army(cs.nextLine());
            while (cs.hasNext()) {
                String[] info = cs.nextLine().split(";");
                if (info.length == 3) {
                    String unittype = info[0];
                    String unitName = info[1];
                    String healthString = info[2];
                    int unitHealth = Integer.parseInt(healthString);
                    if (ParameterChecker.checkValidParameter(unittype) && ParameterChecker.checkValidParameter(unitName)
                            && ParameterChecker.checkValidParameter(Integer.parseInt(healthString))) {
                        placeholderArmy.add((Unit) Class.forName("no.ntnu.mathijoh.wargame.models.units." + unittype)
                                .getConstructor(String.class, int.class).newInstance(unitName, unitHealth));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Couldn't find the file");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The file contains none valid arguments");
        } catch (Exception e) {
            placeholderArmy = null;
        }
        return placeholderArmy;
    }
}
