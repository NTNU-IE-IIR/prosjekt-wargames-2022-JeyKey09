package no.ntnu.mathijoh.wargame.controllers;

import java.io.File;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.Battle;
import no.ntnu.mathijoh.wargame.models.map.BattleMap;
import no.ntnu.mathijoh.wargame.models.units.Unit;
import no.ntnu.mathijoh.wargame.panes.TilePane;

/**
 * The main menu controller
 * The controller that contains all the information needed
 */
public class MainMenuController {

    private ArrayList<Army> armyList;
    private int mapIndex;
    private BattleMap currentMap;
    private ArrayList<BattleMap> mapList;
    private Logger logger;
    private Battle battle;

    @FXML
    private Text mapText;

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
    private GridPane battleGrid;

    @FXML
    public void initialize() {
        mapIndex = 0;
        logger = System.getLogger("Main Menu");
        mapList = new ArrayList<>();
        armyList = new ArrayList<>(2);

        armyList.add(new Army("Army 1"));
        armyList.add(new Army("Army 2"));

        army1UnitClass
                .setCellValueFactory(unit -> new ReadOnlyObjectWrapper<>(unit.getValue().getClass().getSimpleName()));
        army2UnitClass
                .setCellValueFactory(unit -> new ReadOnlyObjectWrapper<>(unit.getValue().getClass().getSimpleName()));

        army1UnitHealth.setCellValueFactory(new PropertyValueFactory<>("health"));
        army2UnitHealth.setCellValueFactory(new PropertyValueFactory<>("health"));

        army1UnitName.setCellValueFactory(new PropertyValueFactory<>("name"));
        army2UnitName.setCellValueFactory(new PropertyValueFactory<>("name"));

        army1Type.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        army1Total.setCellValueFactory(list -> new ReadOnlyObjectWrapper<>(list.getValue().size()));

        army2Type.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        army2Total.setCellValueFactory(list -> new ReadOnlyObjectWrapper<>(list.getValue().size()));

        File dir = new File(getClass().getResource("maps").getPath().replace("%20", " "));
        for (File file : dir.listFiles()) {
            try {
                mapList.add(FileController.importMapFromFile(file));
            } catch (Exception e) {
                logger.log(Logger.Level.ERROR, e.getMessage());
            }
        }
        createMap();
        battleGrid.setGridLinesVisible(true);
    }

    private void createBattle() {
        Army army1 = new Army(armyList.get(0));
        Army army2 = new Army(armyList.get(1));
        this.battle = new Battle(army1, army2, currentMap);
        updateTableInfo(army1, army2);
        resetButton.setDisable(true);
    }

    private void updateBattleGrid(int x, int y, BattleMap map) {
        mapText.setText(map.getName());
        battleGrid.getChildren().clear();
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                TilePane tile = new TilePane(map.getTile(i, j), battleGrid.heightProperty(), y);
                battleGrid.add(tile, i, j);
            }
        }
    }

    /**
     * Gets new armies from loadMenu trough CentralController
     * 
     * @param e
     */
    @FXML
    private void loadArmy(ActionEvent e) {
        this.armyList = new ArrayList<>(CentralController.runLoadMenu(armyList, root));
        enableBattle();
        updateTableInfo(armyList.get(0), armyList.get(1));
    }

    @FXML
    private void nextTerrain(ActionEvent e) {
        mapIndex++;
        if (mapIndex >= mapList.size()) {
            mapIndex = 0;
        }
        createMap();
    }

    @FXML
    private void previousTerrain(ActionEvent e) {
        mapIndex--;
        if (mapIndex < 0) {
            mapIndex = mapList.size() - 1;
        }
        createMap();
    }

    private void createMap() {
        currentMap = new BattleMap(mapList.get(mapIndex));
        createBattle();
        enableBattle();
        updateBattleGrid(16, 16, currentMap);
    }

    /**
     * Makes the armies attack each other
     * 
     * @param e
     */
    @FXML
    private void battle() {
        if (battle.isNotFinished()) {
            battle.runStep();
            updateBattleGrid(16, 16, currentMap);
            if (!battle.isNotFinished()) {
                battleButton.setDisable(true);
                resetButton.setDisable(false);
            }
        }
    }

    /**
     * Purges the tables and updates them
     * 
     * @param aList
     */
    private void updateTableInfo(Army army1, Army army2) {
        purgeArmyTables();
        injectArmyTableView(army1Table, army1);
        injectArmyTableView(army2Table, army2);

        purgeUnitTables();
        army1.getAllUnits().forEach(unit -> army1UnitTable.getItems().add(unit));
        army2.getAllUnits().forEach(unit -> army2UnitTable.getItems().add(unit));

        army1Title.setText(army1.getName());
        army2Title.setText(army2.getName());
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

    private void enableBattle() {
        if (armyList.get(0).getSize() > 0 && armyList.get(1).getSize() > 0 && battle.isNotFinished()) {
            battleButton.setDisable(false);
        }
    }

    @FXML
    private void reset() {
        createMap();
        resetButton.setDisable(true);
    }
}