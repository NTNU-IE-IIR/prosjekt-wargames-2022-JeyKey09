package no.ntnu.mathijoh.wargame.controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.Battle;
import no.ntnu.mathijoh.wargame.models.units.Unit;

/**
 * The main menu controller
 * The controller that contains all the information needed
 */
public class MainMenuController {

    private ArrayList<Army> armyList;

    @FXML
    private BorderPane root;

    @FXML
    private Text army1Title;

    @FXML
    private TableView<List<Unit>> army1Table;

    @FXML
    private TableColumn<List<Unit>, String> army1Type;

    @FXML
    private TableColumn<List<Unit>, Integer> army1Total;

    @FXML
    private TableView<Unit> army1UnitTable;

    @FXML
    private TableColumn<Unit, String> army1UnitClass;

    @FXML
    private TableColumn<Unit, String> army1UnitName;

    @FXML
    private TableColumn<Unit, Integer> army1UnitHealth;

    @FXML
    private Text army2Title;

    @FXML
    private TableView<List<Unit>> army2Table;

    @FXML
    private TableColumn<List<Unit>, String> army2Type;

    @FXML
    private TableColumn<List<Unit>, Integer> army2Total;

    @FXML
    private TableView<Unit> army2UnitTable;

    @FXML
    private TableColumn<Unit, String> army2UnitClass;

    @FXML
    private TableColumn<Unit, String> army2UnitName;

    @FXML
    private TableColumn<Unit, Integer> army2UnitHealth;

    @FXML
    private Button battleButton;

    @FXML
    private Button resetButton;

    @FXML
    public void initialize() {

        armyList = new ArrayList<>(2);

        armyList.add(new Army("Army 1"));
        armyList.add(new Army("Army 2"));

        Callback<CellDataFeatures<Unit, String>, ObservableValue<String>> getUnitClass = new Callback<CellDataFeatures<Unit, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Unit, String> unit) {
                return new ReadOnlyObjectWrapper<>(unit.getValue().getClass().getSimpleName());
            }
        };

        army1UnitClass.setCellValueFactory(getUnitClass);
        army2UnitClass.setCellValueFactory(getUnitClass);

        army1UnitHealth.setCellValueFactory(new PropertyValueFactory<>("health"));
        army2UnitHealth.setCellValueFactory(new PropertyValueFactory<>("health"));

        army1UnitName.setCellValueFactory(new PropertyValueFactory<>("name"));
        army2UnitName.setCellValueFactory(new PropertyValueFactory<>("name"));

        Callback<CellDataFeatures<List<Unit>, Integer>, ObservableValue<Integer>> getSize = new Callback<CellDataFeatures<List<Unit>, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<List<Unit>, Integer> list) {
                return new ReadOnlyObjectWrapper<>(list.getValue().size());
            }
        };

        army1Type.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        army1Total.setCellValueFactory(getSize);

        army2Type.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        army2Total.setCellValueFactory(getSize);

        updateTableInfo();
    }

    /**
     * Gets new armies from loadMenu trough CentralController
     * 
     * @param e
     */
    @FXML
    private void loadArmy(ActionEvent e) {
        this.armyList = new ArrayList<>(CentralController.runLoadMenu(armyList, root));
        updateTableInfo();
    }


    @FXML
    private void nextTerrain(ActionEvent e) {
        
    }

    @FXML
    private void previousTerrain(ActionEvent e) {
        
    }

    /**
     * Makes the armies attack each other
     * 
     * @param e
     */
    private void battle() {
        ArrayList<Army> armylist = new ArrayList<>(this.armyList);
        Battle battle = new Battle(armylist.get(0), armylist.get(1));
        // Thread battleThread = new ThreadLocal<>();
    }

    /**
     * Purges the tables and updates them
     * 
     * @param aList
     */
    private void updateTableInfo() {
        purgeArmyTables();
        injectArmyTableView(army1Table, this.armyList.get(0));
        injectArmyTableView(army2Table, this.armyList.get(1));

        purgeUnitTables();
        this.armyList.get(0).getAllUnits().forEach(unit -> army1UnitTable.getItems().add(unit));
        this.armyList.get(1).getAllUnits().forEach(unit -> army1UnitTable.getItems().add(unit));

        army1Title.setText(this.armyList.get(0).getName());
        army2Title.setText(this.armyList.get(1).getName());
    }

    /**
     * Injects a army unit within the tableview
     * 
     * @param tableView its gonna inject
     * @param army      its gonna get troops from
     */
    private void injectArmyTableView(TableView<List<Unit>> tableView, Army army) {
        tableView.getItems().add(army.getCavalryUnits());
        tableView.getItems().add(army.getCommanderUnits());
        tableView.getItems().add(army.getInfantryUnits());
        tableView.getItems().add(army.getRangedUnits());
    }

    /**
     * Cleans the UnitTables
     */
    private void purgeUnitTables() {
        army1UnitTable.getItems().clear();
        army2UnitTable.getItems().clear();
    }

    /**
     * Cleans the ArmyTables
     */
    private void purgeArmyTables() {
        army1Table.getItems().clear();
        army2Table.getItems().clear();
    }
}