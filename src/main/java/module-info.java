module no.ntnu.mathijoh.wargame {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens no.ntnu.mathijoh.wargame;
    exports no.ntnu.mathijoh.wargame;
    
    opens no.ntnu.mathijoh.wargame.handlers;
    exports no.ntnu.mathijoh.wargame.handlers;
    
    opens no.ntnu.mathijoh.wargame.models;
    exports no.ntnu.mathijoh.wargame.models;

    opens no.ntnu.mathijoh.wargame.models.units;
    exports no.ntnu.mathijoh.wargame.models.units;
    
    opens no.ntnu.mathijoh.wargame.models.map;
    exports no.ntnu.mathijoh.wargame.models.map;
    
    opens no.ntnu.mathijoh.wargame.factories;

}