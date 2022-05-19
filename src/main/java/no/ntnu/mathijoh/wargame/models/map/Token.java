package no.ntnu.mathijoh.wargame.models.map;

import no.ntnu.mathijoh.wargame.models.units.Unit;
//TODO: Change color to be Enum or something else then string. (String can be written in inf ways)

/**
 * Token class
 * The Token class is responsible for holding a unit and a color.
 * It is used to represent a unit on the map.
 * 
 * @author Mathias J. Kirkeby
 * @version 1.0
 */
public class Token {

    private Unit unit;
    private String color;

    /**
     * Constructor for Token
     * 
     * @param unit  the unit
     * @param color the color
     * @throws IllegalArgumentException if unit is null
     */
    public Token(Unit unit, String color) throws IllegalArgumentException {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.unit = unit;
        this.color = color;
    }

    /**
     * Gets the unit
     * 
     * @return the unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the unit for the token
     * 
     * @param unit the unit to set
     * @throws IllegalArgumentException if unit is null
     */
    public void setUnit(Unit unit) throws IllegalArgumentException {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.unit = unit;
    }

    /**
     * Gets the color
     * 
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color
     * 
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
}
