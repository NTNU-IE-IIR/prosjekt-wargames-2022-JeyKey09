package no.ntnu.mathijoh.wargame.controllers;

import javafx.event.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.mathijoh.wargame.models.ParameterChecker;
import no.ntnu.mathijoh.wargame.models.units.Unit;
import no.ntnu.mathijoh.wargame.models.Army;

public class FileController {
    
    Army army1 = new Army("Army1");

    Army army2 = new Army("Army2");

    @FXML
    TextField filePathBox;

    @FXML
    TextField delimiterBox;
    
    @FXML
    MenuButton armyButton;
    
    @FXML
    private void closeWindow(ActionEvent e) {

    }  
    
    @FXML
    private void loadArmy(ActionEvent e) throws FileNotFoundException, IllegalArgumentException {
        File file = new File(filePathBox.getText());
        String delimiter = delimiterBox.getText();
        String replacingArmy = armyButton.getText();
        if(file.exists() && (replacingArmy == army1.getName() || replacingArmy == army2.getName())){
            try {
                Army csvArmy = getArmyOfCSVFile(file);
            } catch (Exception error) {
                //TODO: handle exception
            }
            armyButton.setText("Select Army");
        }
    }
    
    @FXML
    private void browse(ActionEvent e) {
        FileChooser browser = new FileChooser();
        browser.setInitialFileName(".csv");
        Stage newStage = new Stage();
        File file = browser.showOpenDialog(newStage);
        filePathBox.setText(file.toPath().toString());
    }

    /**
     * 
     * @param file a file object of a CSV file. Delimiter used is ";"
     * @return a Army with every soldier from the CSV
     * @throws FileNotFoundException    if the file couldn't be found
     * @throws IllegalArgumentException if the CSV file contained invalid parameters
     */
    public Army getArmyOfCSVFile(File file) throws FileNotFoundException, IllegalArgumentException {
        Army placeholderArmy = null;
        try (Scanner cs = new Scanner(file)) {
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
    
    /**
     * Sets the first army within the controller
     * @param Army object
     */
    public void setArmy1(Army army) {
        if (!ParameterChecker.checkValidParameter(army)) {
            throw new IllegalArgumentException("Army is not a valid type");
        }
        this.army1 = army;
        updateMenuButton();
    }

    /**
     * Sets the second army within the controller
     * @param Army object
     */
    public void setArmy2(Army army) {
        if (!ParameterChecker.checkValidParameter(army)) {
            throw new IllegalArgumentException("Army is not a valid type");
        }
        this.army2 = army;
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
        MenuItem menuItem1 = (new MenuItem(army1.getName()));
        MenuItem menuItem2 = (new MenuItem(army2.getName()));
        
        menuItem1.setOnAction(eventHandler);
        menuItem2.setOnAction(eventHandler);

        armyButton.getItems().add(menuItem1);
        armyButton.getItems().add(menuItem2);
    }
}
