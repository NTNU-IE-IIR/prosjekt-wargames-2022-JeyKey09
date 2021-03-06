package no.ntnu.mathijoh.wargame.controllers;
import javafx.event.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import no.ntnu.mathijoh.wargame.handlers.FileHandler;
import no.ntnu.mathijoh.wargame.models.Army;
/**
 * Army Loads controller
 * The Controller is responsible for fetching the information on what file to load and what army to load to.
 * 
 */
public class LoadMenuController {
    
    private List<Army> armyList;

    @FXML private TextField filePathBox;

    @FXML private VBox root;

    @FXML private TextField delimiterBox;

    @FXML private MenuButton armyButton;

    @FXML
    private void closeWindow(ActionEvent e) {
        Stage stage = (Stage)root.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void loadArmy(ActionEvent e) throws FileNotFoundException, IllegalArgumentException {
        File file = new File(filePathBox.getText());
        String delimiter = delimiterBox.getText();
        String replacingArmy = armyButton.getText();
        if (file.exists() && !replacingArmy.equals("Select Army")) {
            try {
                if(delimiter.isEmpty()){
                    delimiter = ";";         
                }
                Army csvArmy = FileHandler.getArmyOfCSVFile(file, delimiter);
                boolean switchedArmy = false;
                for (int i = 0; i < armyList.size() && !switchedArmy; i++) {
                    if (armyList.get(i).getName().equals(replacingArmy)) {
                        armyList.set(i, csvArmy);
                        switchedArmy = true;
                    }
                    closeWindow(e);
                }
            } catch (Exception error) {
                Alert alert = new Alert(Alert.AlertType.ERROR, error.getMessage());
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File does not exist or army is not selected");
            alert.showAndWait();
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
     * Sets the armyList within the controller
     * 
     * @param armyList object
     */
    public void setArmyList(List<Army> armyList) {
        if (armyList == null) {
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
        EventHandler<ActionEvent> eventHandler = e -> armyButton.setText(((MenuItem) e.getTarget()).getText());
        for (Army army : armyList) {
            MenuItem menuItem = (new MenuItem(army.getName()));
            menuItem.setOnAction(eventHandler);
            armyButton.getItems().add(menuItem);
        }
    }



}
