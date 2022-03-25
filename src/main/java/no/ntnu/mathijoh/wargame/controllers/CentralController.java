package no.ntnu.mathijoh.wargame.controllers;

import java.net.URL;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.ntnu.mathijoh.wargame.models.Army;

/**
 * A class that main premise is to work as a communication and creation tool for
 * the MainMenuController
 * All code that creates a new instance of a scene are within this class for
 * better readabilty within
 * the MainMenuController
 */
public class CentralController {

    private CentralController() {
    }

    /**
     * Creation and loading of the loadMenu
     * 
     * @param armyList that is gonna be used as a reference for the load menu
     * @param root     the parent menu or main menu to lock the window within
     * @return List<Army> Back to Main Menu
     */
    public static List<Army> runLoadMenu(List<Army> armyList, Parent root) {
        try {
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
            // TODO: handle exception
        }
        return armyList;
    }

    private static URL getResource(String filePath) {
        return CentralController.class.getResource(filePath);
    }
}
