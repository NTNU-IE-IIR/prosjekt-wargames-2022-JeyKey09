package no.ntnu.mathijoh.wargame.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.ParameterChecker;
import no.ntnu.mathijoh.wargame.models.factories.UnitFactory;
import no.ntnu.mathijoh.wargame.models.units.Unit;

/**
 * A controller meant to give the program read/write functonality to files
 * It can export and read CSV as the documentation asked for
 */
public class FileController {

    private FileController() {
    }

    /**
     * 
     * @param file a file object of a CSV file. Defualt Delimiter used is ";"
     * @return a Army with every soldier from the CSV
     * @throws FileNotFoundException    if the file couldn't be found
     * @throws IllegalArgumentException if the CSV file contained invalid parameters
     *                                  or it is not a CSV file
     */
    public static Army getArmyOfCSVFile(File file, String pattern)
            throws FileNotFoundException, IllegalArgumentException, UnknownError {
        if (!file.getAbsolutePath().matches("^.*\\.(csv)$")) {
            throw new IllegalArgumentException("This is not a CSV file");
        }
        if (pattern.isEmpty()) {
            pattern = ";";
        }
        Army placeholderArmy = null;
        try (Scanner cs = new Scanner(file)) {
            cs.useDelimiter(Pattern.compile(pattern));
            placeholderArmy = new Army(cs.nextLine());
            while (cs.hasNext()) {
                String[] info = cs.nextLine().split(pattern);
                if (info.length == 3 && ParameterChecker.checkValidParameter(info[0])
                        && ParameterChecker.checkValidParameter(info[1])
                        && ParameterChecker.checkValidParameter(Integer.parseInt(info[2]))) {
                    String unittype = info[0];
                    String unitName = info[1];
                    String healthString = info[2];
                    int unitHealth = Integer.parseInt(healthString);
                    placeholderArmy.add(UnitFactory.createUnit(UnitFactory.UnitType.valueOf(unittype.toUpperCase()), unitName, unitHealth));
                } else {
                    throw new IllegalArgumentException("This file contains none valid arguments");
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

    /**
     * Saves a Army into a file
     * 
     * @param file a file object to write to
     * @param army that is gonna be saved
     * @throws FileNotFoundException    if the file wasen't created from the
     *                                  printWriter function
     * @throws IllegalArgumentException if the file does not have a valid .csv
     *                                  extension or if the army is null
     */
    public static void saveArmyInCSV(File file, Army army) throws FileNotFoundException, IllegalArgumentException {
        if (!file.getAbsolutePath().matches("^.*\\.(csv)$")) {
            throw new IllegalArgumentException("This is not a CSV File");
        }
        if (army == null) {
            throw new IllegalArgumentException("The army is not valid");
        }
        try (PrintWriter outFile = new PrintWriter(file)) {
            outFile.println(army.getName());
            for (Unit unit : army.getAllUnits()) {
                String unitInText = String.format("%s;%s;%d", unit.getClass().getSimpleName(), unit.getName(),
                        unit.getHealth());
                outFile.println(unitInText);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Couldn't write to this file location");
        } catch (Exception e) {
            throw new IllegalArgumentException("The army is not valid");
        }
    }
}
