package no.ntnu.mathijoh.wargame.models;

/**
 * The different Terrains that will exist in the game
 * 
 * @author Mathias J. Kirkeby
 * @version 1.0
 */
public enum Terrain {
    PLAINS('P'),
    FOREST('F'),
    MOUNTAIN('M'),
    HILL('H');

    private char terrainChar;

    private Terrain(char terrain) {
        this.terrainChar = terrain;
    }

    /**
     * Returns the char representation of the terrain
     * 
     * @return the char representation of the terrain
     */
    public char getTerrainCharacter() {
        return terrainChar;
    }
}