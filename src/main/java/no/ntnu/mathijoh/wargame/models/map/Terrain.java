package no.ntnu.mathijoh.wargame.models.map;
//TODD: Add a test for the Tile class

//TODD: Add movemnt cost for the terrain

/**
 * The different Terrains that will exist in the game
 * 
 * @author Mathias J. Kirkeby
 * @version 1.0
 */
public enum Terrain {
    PLAINS("Plains", 'P', "rgba(108, 151, 78, 1)"),
    FOREST("Forest", 'F', "rgba(58, 77, 45, 1)"),
    HILL("Hill", 'H', "rgba(154, 156, 140, 1)"),
    NONE("None", 'N', "rgba(0, 0, 0, 0)");

    private char terrainChar;
    private String name;
    private String color;

    private Terrain(String name, char terrainChar, String color) {
        this.terrainChar = terrainChar;
        this.color = color;
        this.name = name;
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
     * 
     * @return the color of the terrain
     */
    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the terrain based on the char representation
     * 
     * @param name
     * @return the terrain that is the
     */
    public static Terrain getTerrainFromName(String name) {
        Terrain result = null;
        Terrain[] values = Terrain.values();

        for (int i = 0; result == null && i < values.length; i++) {
            if (values[i].getName().equals(name)) {
                result = values[i];
            }
        }

        return result;

    }

    /**
     * Returns the terrain based on the char representation
     * 
     * @param name the char representation of the terrain
     * @return the terrain
     */
    public static Terrain getTerrainFromChar(Character name) {
        Terrain result = null;
        Terrain[] values = Terrain.values();

        for (int i = 0; result == null && i < values.length; i++) {
            if (values[i].getTerrainCharacter() == name) {
                result = values[i];
            }
        }
        return result;
    }
}