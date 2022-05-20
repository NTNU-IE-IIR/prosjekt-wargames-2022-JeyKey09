package no.ntnu.mathijoh.wargame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Main class for the game.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {    

        Parent root = FXMLLoader.load(getClass().getResource("controllers/fxml/MainMenu.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("WarGame");
        stage.show();
    }
    
    /**
     * Launches the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

}