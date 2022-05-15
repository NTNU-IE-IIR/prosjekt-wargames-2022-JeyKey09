/**
 * Adding stuff for javafx...
 * I don't know what it does...
 * The program refuses to work without it
 */
module no.ntnu.mathijoh.wargame {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens no.ntnu.mathijoh.wargame.controllers;
    exports no.ntnu.mathijoh.wargame.controllers;
    opens no.ntnu.mathijoh.wargame.models.units;
    exports no.ntnu.mathijoh.wargame.models;
    exports no.ntnu.mathijoh.wargame.models.units;
    opens no.ntnu.mathijoh.wargame.models.map;
    exports no.ntnu.mathijoh.wargame.models.map;
    opens no.ntnu.mathijoh.wargame;
    exports no.ntnu.mathijoh.wargame;
    opens no.ntnu.mathijoh.wargame.factories;
}