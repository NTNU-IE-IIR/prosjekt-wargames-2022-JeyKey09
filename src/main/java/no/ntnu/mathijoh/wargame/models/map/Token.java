package no.ntnu.mathijoh.wargame.models.map;

import no.ntnu.mathijoh.wargame.models.units.Unit;

public class Token {

    private Unit unit;
    private String color;
    
    public Token (Unit unit, String color) {
        this.unit = unit;
        this.color = color;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
