package no.ntnu.mathijoh.wargame.models;

/**
 * The different Terrains that will exist in the game
 * 
 * @author Mathias J. Kirkeby
 * @version 1.0
 */
public enum Terrain {
    PLAINS('P',"6c974e"),
    FOREST('F',"3a4d2d"),
    HILL('H',"9a9c8c");

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
}