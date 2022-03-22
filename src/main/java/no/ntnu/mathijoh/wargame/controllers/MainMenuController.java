package no.ntnu.mathijoh.wargame.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.Battle;
import no.ntnu.mathijoh.wargame.models.units.Unit;

public class MainMenuController {

    private ArrayList<Army> armyList;
    
    @FXML
    private BorderPane root;

    @FXML
    private Text army1Title;

    @FXML
    private TableView<Army> army1Table;
    
    @FXML
    private TableColumn<Unit,String> army1Type; 

    @FXML
    private TableColumn<Unit,Integer> army1Total; 

    @FXML
    private TableView<Unit> army1UnitTable;

    @FXML
    private TableColumn<Unit,String> army1UnitClass; 

    @FXML
    private TableColumn<Unit,String> army1UnitName; 

    @FXML
    private TableColumn<Unit,Integer> army1UnitHealth; 

    @FXML
    private Text army2Title;

    @FXML
    private TableView<Army> army2Table;
    
    @FXML
    private TableColumn<Unit,String> army2Type; 

    @FXML
    private TableColumn<Unit,Integer> army2Total; 

    @FXML
    private TableView<Unit> army2UnitTable;

    @FXML
    private TableColumn<Unit,String> army2UnitClass; 

    @FXML
    private TableColumn<Unit,String> army2UnitName; 

    @FXML
    private TableColumn<Unit,Integer> army2UnitHealth; 

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

        updateTableInfo(this.armyList);
    }

    @FXML
    private void loadArmy(ActionEvent e) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/FileLoader.fxml"));
            Parent newRoot = loader.load();
            LoadMenuController loadController = loader.getController();
            loadController.setArmyList(armyList);
            Scene scene = new Scene(newRoot);
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Load army from File");
            stage.showAndWait();
            purgeTables();
            updateTableInfo(this.armyList);
        } catch (IOException e1) {
            //TODO: Handle the errors
        }
    }


    private void battle(){
        ArrayList<Army> armylist = new ArrayList<>(this.armyList);
        Battle battle = new Battle(armylist.get(0),armylist.get(1));
       // Thread battleThread = new ThreadLocal<>();
    }


    private void updateTableInfo(List<Army> aList) {
        army1Title.setText(aList.get(0).getName());
        army2Title.setText(aList.get(1).getName());
        aList.get(0).getAllUnits().forEach(unit -> army1UnitTable.getItems().add(unit));
        aList.get(1).getAllUnits().forEach(unit -> army1UnitTable.getItems().add(unit));
    }

    private void purgeTables() {
        army1Table.getItems().clear();
        army2Table.getItems().clear();
        army1UnitTable.getItems().clear();
        army2UnitTable.getItems().clear();
    }
}
