package no.ntnu.mathijoh.wargame.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.units.Unit;

public class MainMenuController {

    Army army1 = new Army("Army1");
    Army army2 = new Army("Army2");

    @FXML
    BorderPane root;

    @FXML
    TableView<Army> army1Table;

    @FXML
    TableView<Army> army2Table;

    @FXML
    TableView<Unit> armyUnit1Table;

    @FXML
    TableView<Unit> armyUnit2Table;

    @FXML
    Text army1Name;

    @FXML
    Text army2Name;

    @FXML
    Button battleButton;

    @FXML
    Button resetButton;

    @FXML
    private void loadArmy(ActionEvent e) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/FileLoader.fxml"));
            Parent newRoot = loader.load();
            FileController fileController = loader.getController();
            fileController.setArmy1(army1);
            fileController.setArmy2(army2); 
            Scene scene = new Scene(newRoot);
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Load army from File");
            stage.show();
        } catch (IOException e1) {
            //TODO: Handle exception
        }
    }


}
