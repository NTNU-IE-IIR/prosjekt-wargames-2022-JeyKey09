package no.ntnu.mathijoh.wargame.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.ntnu.mathijoh.wargame.models.Army;

public class CentralController {
    
    private CentralController() {}

    public static List<Army> loadMenu(ArrayList<Army> armyList, Parent root) {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getResource("fxml/FileLoader.fxml"));
            Parent newRoot = loader.load();
            LoadMenuController loadController = loader.getController();
            loadController.setArmyList(armyList);
            Scene scene = new Scene(newRoot);
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Load army from File");
            stage.showAndWait();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
        return armyList;
    }

    private static URL getResource(String filePath){
        return CentralController.class.getResource(filePath);
    }
}
