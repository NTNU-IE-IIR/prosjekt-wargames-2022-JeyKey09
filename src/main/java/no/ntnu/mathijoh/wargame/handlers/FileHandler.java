package no.ntnu.mathijoh.wargame.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import no.ntnu.mathijoh.wargame.factories.UnitFactory;
import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.map.BattleMap;
import no.ntnu.mathijoh.wargame.models.map.Terrain;
import no.ntnu.mathijoh.wargame.models.units.Unit;

/**
 * A handler meant to give the program read/write functonality to files
 * It can export and read CSV as the documentation asked for
 */
public class FileHandler {

    private FileHandler() {
    }

    /**
     * A loader to fetch a Army from a CSV file.
     * 
     * @param file    a file object of a CSV file.
     * @param pattern the pattern or symbol used to seperate the values in the CSV
     *                file
     * @return a Army with every soldier from the CSV
     * @throws FileNotFoundException    if the file couldn't be found
     * @throws IllegalArgumentException if any of the parameters are null, if a
     *                                  delimiter is not set, if the file is not a
     *                                  CSV file or if the file contains none valid
     *                                  parameters
     */
    public static Army getArmyOfCSVFile(File file, String pattern)
            throws FileNotFoundException, IllegalArgumentException, UnknownError {
        if (file == null) {
            throw new IllegalArgumentException("Need to specify a file to be used");
        }
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Need to specify a pattern to seperate the values");
        }
        if (!file.getAbsolutePath().matches("^.*\\.(csv)$") || !file.exists()) {
            throw new IllegalArgumentException("This is not a CSV file or the file doesn't exist");
        }
        Army placeholderArmy = null;
        try (Scanner cs = new Scanner(file)) {
            cs.useDelimiter(Pattern.compile(pattern));
            placeholderArmy = new Army(cs.nextLine());
            while (cs.hasNext()) {
                String[] info = cs.nextLine().split(pattern);
                if (info.length == 3 && !info[0].isEmpty()
                        && !info[1].isEmpty()
                        && Integer.parseInt(info[2]) > 0 ) {
                    String unittype = info[0];
                    String unitName = info[1];
                    String healthString = info[2];
                    int unitHealth = Integer.parseInt(healthString);
                    placeholderArmy.add(UnitFactory.createUnit(UnitFactory.UnitType.getUnitTypeFromName(unittype),
                            unitName, unitHealth));
                } else {
                    throw new IllegalArgumentException("This file contains none valid arguments");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Couldn't find the file");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The file contains none valid arguments");
        } catch (Exception e) {
            throw new UnknownError("We are unsure about what happened, please try again");
        }
        return placeholderArmy;
    }

    /**
     * A loader to fetch a Army from a CSV file.
     * Uses the default pattern ";"
     * 
     * @param file a file object of a CSV file.
     * @return a Army with every soldier from the CSV
     * @throws FileNotFoundException    if the file couldn't be found
     * @throws IllegalArgumentException if the CSV file contained invalid parameters
     *                                  or it is not a CSV file
     */
    public static Army getArmyOfCSVFile(File file)
            throws FileNotFoundException, IllegalArgumentException, UnknownError {
        return getArmyOfCSVFile(file, ";");

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

    /**
     * Reads a Terrain file that contains of 16x16 character that represents the
     * terrain
     * It only accepts squares
     * 
     * @param file a file object that contains a multiple terrain chars
     * @return a BattleMap with the terrain
     * @throws FileNotFoundException    if the file couldn't be found
     * @throws IllegalArgumentException if the file is not a valid map file
     */
    public static BattleMap importMapFromFile(File file) throws IllegalArgumentException, FileNotFoundException {
        if (!file.getAbsolutePath().matches("^.*\\.(txt)$")) {
            throw new IllegalArgumentException("This is not a txt file");
        }
        int width = 0;
        ArrayList<String> bufferList = new ArrayList<>();
        try (Scanner cs = new Scanner(file)) {
            while (cs.hasNext()) {
                String line = cs.nextLine();
                if (bufferList.isEmpty()) {
                    bufferList.add(line);
                    width = line.length();
                } else if (line.length() != width) {
                    throw new IllegalArgumentException("The map is not a square");
                } else {
                    bufferList.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Couldn't find the file");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The file is not a square");
        }
        Iterator<String> buffer = bufferList.iterator();
        BattleMap map = new BattleMap(file.getName().split(".txt")[0], width, bufferList.size());
        int y = 0;
        while (buffer.hasNext()) {
            String line = buffer.next();
            for (int i = 0; i < line.length(); i++) {
                map.changeTerrain(i, y, Terrain.getTerrainFromChar(line.charAt(i)));
            }
            y++;
        }

        return map;
    }
}
