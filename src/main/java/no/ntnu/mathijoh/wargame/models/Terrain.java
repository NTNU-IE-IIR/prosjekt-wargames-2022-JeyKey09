package no.ntnu.mathijoh.wargame.models;

import java.util.ArrayList;

import javafx.util.converter.CharacterStringConverter;

/**
 * The different Terrains that will exist in the game
 * 
 * @author Mathias J. Kirkeby
 * @version 1.0
 */
public enum Terrain {
    PLAINS('P',"rgba(108, 151, 78, 1)"),
    FOREST('F',"rgba(58, 77, 45, 1)"),
    HILL('H',"rgba(154, 156, 140, 1)");

    private char terrainChar;
    private String color;

    private Terrain(char terrain, String color) {
        this.terrainChar = terrain;
        this.color = color;
    }

    /**
     * Returns the char representation of the terrain
     * 
     * @return the char representation of the terrain
     */
    public char getTerrainCharacter() {
        return terrainChar;
    }

    /**
     * Returns the color of the terrain
     * @return the color of the terrain
     */
    public String getColor() {
        return color;
    }

    public static Terrain getTerrainFromName(String name) {
        Terrain result = null;
        Terrain[] values = Terrain.values();

        for(int i = 0; result == null && i < values.length; i++){
            if(String.format("%s", values[i].getTerrainCharacter()).equals(name)){
                result = values[i];
            }
        }

        return result;

    }

    public static Terrain getTerrainFromName(Character name) {
        Terrain result = null;
        Terrain[] values = Terrain.values();

        for(int i = 0; result == null && i < values.length; i++){
            if(values[i].getTerrainCharacter() == name){
                result = values[i];
            }
        }

        return result;

    }
}