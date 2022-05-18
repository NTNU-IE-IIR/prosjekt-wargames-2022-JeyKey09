package no.ntnu.mathijoh.wargame.controllers;

import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import no.ntnu.mathijoh.wargame.factories.UnitFactory;
import no.ntnu.mathijoh.wargame.fxmodels.UnitsTableView;
import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.ParameterChecker;
import no.ntnu.mathijoh.wargame.models.units.Unit;

//TODO: Refactor this to be more readable and less repetitive. Probably can use tabs pane to add more functionality.

/**
 * Army editor controller
 * The Controller is responsible for adding modifcation ability to the army.
 * @author Mathias J. Kirkeby
 * @version 1.0
 */
public class ArmyEditorController {

    private List<Army> armyList;
    private UnitsTableView tableArmy1;
    private UnitsTableView tableArmy2;

    @FXML
    private TextField nameArmy1;
    @FXML
    private TextField nameArmy2;
    @FXML
    private BorderPane tablePosition1;
    @FXML
    private BorderPane tablePosition2;
    @FXML
    private TextField nameUnit1;
    @FXML
    private TextField nameUnit2;
    @FXML
    private TextField healthUnit1;
    @FXML
    private TextField healthUnit2;
    @FXML
    private MenuButton typeUnit1;
    @FXML
    private MenuButton typeUnit2;
    @FXML
    private TextField amountUnit1;
    @FXML
    private TextField amountUnit2;
    @FXML
    private Button changeNameButton1;
    @FXML
    private Button changeNameButton2;

    @FXML
    public void initialize() {
        tableArmy1 = new UnitsTableView();
        tableArmy2 = new UnitsTableView();
        tablePosition1.setCenter(tableArmy1);
        tablePosition2.setCenter(tableArmy2);
    }

    /**
     * Sets the army list
     * @param armyList the army list
     * @throws IllegalArgumentException if armylist is null
     */
    public void setArmyList(List<Army> armyList) throws IllegalArgumentException{
        if (!ParameterChecker.checkValidParameter(armyList)) {
            throw new IllegalArgumentException("Army is not a valid type");
        }
        this.armyList = armyList;
        Arrays.stream(UnitFactory.UnitType.values()).forEach(unitType -> {
            MenuItem option = new MenuItem(unitType.getName());
            option.setOnAction(this::changeNewUnitType1);
            typeUnit1.getItems().add(option);
            option = new MenuItem(unitType.getName());
            option.setOnAction(this::changeNewUnitType2);
            typeUnit2.getItems().add(option);
        });
        injectArmyToTable(armyList.get(0), tableArmy1);
        injectArmyToTable(armyList.get(1), tableArmy2);
    }

    /**
     * Adds units from a army to a table
     * @param army the army to add
     * @param table the table to add to
     */
    private void injectArmyToTable(Army army, UnitsTableView table) {
        table.getItems().clear();
        army.getAllUnits().forEach(unit -> table.getItems().add(unit));
    }

    private void changeNewUnitType1(ActionEvent event) {
        typeUnit1.setText(((MenuItem) event.getSource()).getText());
    }

    private void changeNewUnitType2(ActionEvent event) {
        typeUnit2.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    private void addToArmy1(ActionEvent e) {
        addToArmy(armyList.get(0), tableArmy1, nameUnit1, healthUnit1, typeUnit1, amountUnit1);
    }

    private void addToArmy(Army army, UnitsTableView unitTable, TextField name, TextField health, MenuButton type,
            TextField amount) {
        if (name.getText().isEmpty() || health.getText().isEmpty() || type.getText().equals("Unit Type")
                || amount.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the fields");
            alert.showAndWait();
        } else {
            try {
                int healthValue = Integer.parseInt(health.getText());
                int amountValue = Integer.parseInt(amount.getText());
                List<Unit> newUnits = UnitFactory.createUnitList(
                        UnitFactory.UnitType.getUnitTypeFromName(type.getText()), name.getText(), healthValue,
                        amountValue);
                army.addAll(newUnits);
                unitTable.getItems().addAll(newUnits);
                name.clear();
                health.clear();
                type.setText("Unit Type");
                amount.clear();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid number");
                alert.showAndWait();
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }
        }
    }

    /**
     * 
     * @param e
     */
    @FXML
    private void addToArmy2(ActionEvent e) {
        addToArmy(armyList.get(1), tableArmy2, nameUnit2, healthUnit2, typeUnit2, amountUnit2);
    }

    @FXML
    private void removeFromArmy1(ActionEvent e) {
        if (tableArmy1.getSelectionModel().getSelectedItem() != null) {
            armyList.get(0).remove(tableArmy1.getSelectionModel().getSelectedItem());
            tableArmy1.getItems().remove(tableArmy1.getSelectionModel().getSelectedItem());
        }
        tableArmy1.refresh();
    }

    @FXML
    public void removeFromArmy2(ActionEvent e) {
        if (tableArmy2.getSelectionModel().getSelectedItem() != null) {
            armyList.get(1).remove(tableArmy2.getSelectionModel().getSelectedItem());
            tableArmy2.getItems().remove(tableArmy2.getSelectionModel().getSelectedItem());
        }
        tableArmy2.refresh();
    }

    @FXML
    private void renameArmy1(ActionEvent e) {
        if (!nameArmy1.getText().isEmpty()) {
            armyList.get(0).setName(nameArmy1.getText());
        } else {
            nameArmy1.setText(armyList.get(0).getName());
        }
        changeNameButton1.setOnAction(this::renameArmy1);
        changeNameButton1.setText("Change Name");

    }

    @FXML
    private void renameArmy2(ActionEvent e) {
        if (!nameArmy2.getText().isEmpty()) {
            armyList.get(1).setName(nameArmy1.getText());
        } else {
            nameArmy2.setText(armyList.get(1).getName());
        }
        changeNameButton2.setOnAction(this::openNameField2);
        changeNameButton2.setText("Change Name");
    }

    @FXML
    private void openNameField1(ActionEvent e) {
        nameArmy1.setDisable(false);
        changeNameButton1.setText("Apply");
        changeNameButton1.setOnAction(this::renameArmy1);
    }

    @FXML
    private void openNameField2(ActionEvent e) {
        nameArmy2.setDisable(false);
        changeNameButton2.setText("Apply");
        changeNameButton2.setOnAction(this::renameArmy2);
    }

}
