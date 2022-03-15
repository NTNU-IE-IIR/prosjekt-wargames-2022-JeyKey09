/**
 * Adding stuff for javafx...
 * I don't know what it does...
 * The program refuses to work without it
 */
module no.ntnu.mathijoh.wargame {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens no.ntnu.mathijoh.wargame.controllers to javafx.fxml;
    opens no.ntnu.mathijoh.wargame.models;
    opens no.ntnu.mathijoh.wargame.models.units;
    
    exports no.ntnu.mathijoh.wargame;
}