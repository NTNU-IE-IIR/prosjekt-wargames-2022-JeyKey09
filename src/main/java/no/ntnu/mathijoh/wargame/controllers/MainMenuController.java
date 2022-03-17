package no.ntnu.mathijoh.wargame.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
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
import no.ntnu.mathijoh.wargame.models.units.Unit;

public class MainMenuController {

    private ArrayList<Army> armyList;
    
    @FXML
    private BorderPane root;

    @FXML
    private Text army1Name;

    @FXML
    private TableView<Army> army1Table;
    
    @FXML
    private TableColumn<Unit,String> army1Type; 

    @FXML
    private TableColumn<Unit,Integer> army1Total; 

    @FXML
    private TableView<Unit> armyUnit1Table;

    @FXML
    private TableColumn<Unit,String> army1UnitName; 

    @FXML
    private TableColumn<Unit,Integer> army1UnitHealth; 

    @FXML
    private Text army2Name;

    @FXML
    private TableView<Army> army2Table;
    
    @FXML
    private TableColumn<Unit,String> army2Type; 

    @FXML
    private TableColumn<Unit,Integer> army2Total; 

    @FXML
    private TableView<Unit> armyUnit2Table;

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
        
        Callback<CellDataFeatures<Unit, String>, ObservableValue<String>> getClassUnit = new Callback<CellDataFeatures<Unit, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Unit, String> p) {
              return new SimpleStringProperty(p.getClass().getName());
              }
            };

            Callback<CellDataFeatures<Army, Integer>, ObservableValue<String>> getCavalryAmount = new Callback<CellDataFeatures<Army, Integer>, ObservableValue<String>>() {
                public ObservableValue<String> call(CellDataFeatures<Army, Integer> p) {
                  return new SimpleStringProperty(String.valueOf(p.getValue().getCavalryUnit().size()));
                  }
                };
        
        army1UnitHealth.setCellValueFactory(new PropertyValueFactory<>("health"));
        army2UnitHealth.setCellValueFactory(new PropertyValueFactory<>("health"));

        army1UnitName.setCellValueFactory(new PropertyValueFactory<>("name"));
        army2UnitName.setCellValueFactory(new PropertyValueFactory<>("name"));

        
        updateInfo();
    }

    @FXML
    private void loadArmy(ActionEvent e) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/FileLoader.fxml"));
            Parent newRoot = loader.load();
            FileController fileController = loader.getController();
            fileController.setArmyList(armyList);
            Scene scene = new Scene(newRoot);
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Load army from File");
            stage.showAndWait();
        } catch (IOException e1) {
            System.out.println(e1.toString());
            //TODO: Handle exception
        }
    }

    public void updateInfo() {
    }


}
