package no.ntnu.mathijoh.wargame.controllers;

import java.io.File;
import java.lang.System.Logger;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.mathijoh.wargame.fxmodels.ArmyTableView;
import no.ntnu.mathijoh.wargame.fxmodels.TilePane;
import no.ntnu.mathijoh.wargame.fxmodels.UnitListDataHolder;
import no.ntnu.mathijoh.wargame.fxmodels.UnitsTableView;
import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.Battle;
import no.ntnu.mathijoh.wargame.models.map.BattleMap;
import no.ntnu.mathijoh.wargame.models.units.Unit;

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

    private TableView<UnitListDataHolder> army1Table;

    private TableView<Unit> army1UnitTable;

    @FXML
    private Text army2Title;

    @FXML
    private VBox army2Box;

    @FXML
    private VBox army2UnitBox;
        
    @FXML
    private Tab army1Tab;

    @FXML
    private Tab army2Tab;

    @FXML
    private VBox army1Box;

    @FXML
    private VBox army1UnitBox;

    @FXML
    private Text army2UnitTitle;

    private TableView<UnitListDataHolder> army2Table;

    private TableView<Unit> army2UnitTable;

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

        army1Table = new ArmyTableView();
        army2Table = new ArmyTableView();
        army1UnitTable = new UnitsTableView();
        army2UnitTable = new UnitsTableView();

        army1Box.getChildren().add(army1Table);
        army2Box.getChildren().add(army2Table);
        army1UnitBox.getChildren().add(army1UnitTable);
        army2UnitBox.getChildren().add(army2UnitTable);



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
        updateInfo(army1, army2);
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
        createMap();
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
            army1Table.refresh();
            army2Table.refresh();
            army1UnitTable.refresh();
            army2UnitTable.refresh();        
        }
    }

    /**
     * Purges the tables and updates them
     * 
     * @param aList
     */
    private void updateInfo(Army army1, Army army2) {
        purgeArmyTables();
        
        army1Tab.setText(army1.getName());
        army2Tab.setText(army2.getName());

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
    private void injectArmyTableView(TableView<UnitListDataHolder> tableView, Army army) {
        tableView.getItems().add(new UnitListDataHolder("Infantry", army.getInfantryUnits()));
        tableView.getItems().add(new UnitListDataHolder("Cavalry", army.getCavalryUnits()));
        tableView.getItems().add(new UnitListDataHolder("Ranger", army.getRangedUnits()));
        tableView.getItems().add(new UnitListDataHolder("Commander", army.getCommanderUnits()));
        tableView.getItems().add(new UnitListDataHolder("Total Units", army.getAllUnits()));
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