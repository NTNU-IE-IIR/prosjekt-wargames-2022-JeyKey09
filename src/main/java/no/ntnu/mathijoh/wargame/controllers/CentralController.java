package no.ntnu.mathijoh.wargame.controllers;

import java.net.URL;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.ntnu.mathijoh.wargame.models.Army;

/**
 * A class that main premise is to work as a communication and creation tool for
 * the MainMenuController
 * All code that creates a new instance of a scene are within this class for
 * better readability as if they would be normally in the MainMenuController
 */
public class CentralController {

    private CentralController() {}

    /**
     * Creation and loading of the loadMenu
     * 
     * @param armyList that is gonna be used as a reference for the load menu
     * @param root     the parent menu or main menu to lock the window
     * @return List of armies that is loaded from the file
     */
    public static List<Army> runLoadMenu(List<Army> armyList, Parent root) {
        try {
            FXMLLoader loader = new FXMLLoader(getResource("fxml/FileLoader.fxml"));
            Parent newRoot = loader.load();
            LoadMenuController loadController = loader.getController();
            loadController.setArmyList(armyList);
            Scene scene = new Scene(newRoot);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Load army from File");
            stage.showAndWait();
        } catch (Exception exception) {
            Alert popup = new Alert(Alert.AlertType.ERROR, exception.getMessage());
            popup.showAndWait();
        }
        return armyList;
    }

    /**
     * Fetches the resource from the resources folder
     * @param filePath the path to the file
     * @return the resource
     */
    private static URL getResource(String filePath) {
        return CentralController.class.getResource(filePath);
    }

    /**
     * Creation and loading of the loadMenu
     * 
     * @param armyList that is gonna be used as a reference for the load menu
     * @param root     the parent menu or main menu to lock the window
     * @return A list of armies that has been modified 
     */
    public static List<Army> runArmyEditor(List<Army> armyList, Parent root) {
        try {
            FXMLLoader loader = new FXMLLoader(getResource("fxml/ArmyEditor.fxml"));
            Parent newRoot = loader.load();
            ArmyEditorController armyEditor = loader.getController();
            armyEditor.setArmyList(armyList);
            Scene scene = new Scene(newRoot);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Edit army");
            stage.showAndWait();
        } catch (Exception exception) {
            Alert popup = new Alert(Alert.AlertType.ERROR, exception.getMessage());
            popup.showAndWait();
        }
        return armyList;
    }
}
