package no.ntnu.mathijoh.wargame.controllers;

import javafx.event.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import no.ntnu.mathijoh.wargame.models.ParameterChecker;
import no.ntnu.mathijoh.wargame.models.units.Unit;
import no.ntnu.mathijoh.wargame.models.Army;

public class FileController {

    private ArrayList<Army> armyList;

    @FXML
    private TextField filePathBox;

    @FXML
    private VBox root;

    @FXML
    private TextField delimiterBox;

    @FXML
    private MenuButton armyButton;

    @FXML
    private void closeWindow(ActionEvent e) {
        root.getScene().getWindow().onCloseRequestProperty();
    }

    @FXML
    private void loadArmy(ActionEvent e) throws FileNotFoundException, IllegalArgumentException {
        File file = new File(filePathBox.getText());
        String delimiter = delimiterBox.getText();
        String replacingArmy = armyButton.getText();
        if (file.exists() && !replacingArmy.equals("Select Army")) {
            try {
                Army csvArmy = getArmyOfCSVFile(file);
                boolean switchedArmy = false;
                for (int i = 0; i < armyList.size() && !switchedArmy; i++) {
                    if (armyList.get(i).getName().equals(replacingArmy)) {
                        armyList.remove(i);
                        armyList.add(csvArmy);
                        switchedArmy = true;
                    }
                }
            } catch (Exception error) {
                // TODO: handle exception
            }
            armyButton.setText("Select Army");
            updateMenuButton();
        }
    }

    @FXML
    private void browse(ActionEvent e) {
        FileChooser browser = new FileChooser();
        browser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
        File file = browser.showOpenDialog(root.getScene().getWindow());
        if (file != null) {
            filePathBox.setText(file.toPath().toString());
        }
    }

    /**
     * 
     * @param file a file object of a CSV file. Delimiter used is ";"
     * @return a Army with every soldier from the CSV
     * @throws FileNotFoundException    if the file couldn't be found
     * @throws IllegalArgumentException if the CSV file contained invalid parameters
     */
    public Army getArmyOfCSVFile(File file) throws FileNotFoundException, IllegalArgumentException, UnknownError {
        // TODO: Add functionality for multiple Delimiters
        Army placeholderArmy = null;
        try (Scanner cs = new Scanner(file)) {
            cs.useDelimiter(";");
            placeholderArmy = new Army(cs.nextLine());
            while (cs.hasNext()) {
                String[] info = cs.nextLine().split(";");
                if (info.length == 3 && ParameterChecker.checkValidParameter(info[0]) && ParameterChecker.checkValidParameter(info[1])
                && ParameterChecker.checkValidParameter(Integer.parseInt(info[2]))) {
                    String unittype = info[0];
                    String unitName = info[1];
                    String healthString = info[2];
                    int unitHealth = Integer.parseInt(healthString);
                    placeholderArmy.add((Unit) Class.forName("no.ntnu.mathijoh.wargame.models.units." + unittype)
                                .getConstructor(String.class, int.class).newInstance(unitName, unitHealth));
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

    public void saveArmyInCSV(File file, Army army) throws FileNotFoundException, IllegalArgumentException {
        if(file.getAbsolutePath().matches("/^.*\\.(csv)$/ig")) {
            throw new IllegalArgumentException("This is not a csv File");
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
        } catch (Exception e) {}
    }

    /**
     * Sets the first army within the controller
     * 
     * @param Army object
     */
    public void setArmyList(ArrayList<Army> armyList) {
        if (!ParameterChecker.checkValidParameter(armyList)) {
            throw new IllegalArgumentException("Army is not a valid type");
        }
        this.armyList = armyList;
        updateMenuButton();
    }

    /**
     * Updates the menuButton with the new Armies
     */
    private void updateMenuButton() {
        if (!armyButton.getItems().isEmpty()) {
            armyButton.getItems().removeAll(armyButton.getItems());
        }
        EventHandler<ActionEvent> eventHandler = new EventHandler<>() {
            public void handle(ActionEvent e) {
                MenuItem optionPressed = (MenuItem) e.getTarget();
                armyButton.setText(optionPressed.getText());
            }
        };
        for (Army army : armyList) {
            MenuItem menuItem = (new MenuItem(army.getName()));
            menuItem.setOnAction(eventHandler);
            armyButton.getItems().add(menuItem);
        }
    }
}
