package no.ntnu.mathijoh.wargame.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import no.ntnu.mathijoh.wargame.models.Army;
import no.ntnu.mathijoh.wargame.models.units.Unit;

public class MainMenuController{
    
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

    

}