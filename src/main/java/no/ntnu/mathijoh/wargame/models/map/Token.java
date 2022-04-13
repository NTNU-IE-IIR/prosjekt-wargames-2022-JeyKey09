package no.ntnu.mathijoh.wargame.models.map;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import no.ntnu.mathijoh.wargame.models.units.Unit;

public class Token {

    private Unit unit;
    private String color;
    private Image image;
    
    public Token (Unit unit, String color) {
        this.unit = unit;
        this.image = new Image(getClass().getResourceAsStream(unit.getClass().getSimpleName()+".png"));
        this.color = color;
    }
    
    public Unit getUnit() {
        return unit;
    }

    public Image getImage() {
        return image;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
        this.image = new Image(getClass().getResourceAsStream(unit.getClass().getSimpleName()+".png"));
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
