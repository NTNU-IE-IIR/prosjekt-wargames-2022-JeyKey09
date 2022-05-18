package no.ntnu.mathijoh.wargame.models.map;

//TODD: Add movemnt cost for the terrain

/**
 * The different Terrains that will exist in the game
 * 
 * @author Mathias J. Kirkeby
 * @version 1.0
 */
public enum Terrain {
    PLAINS("Plains", 'P'),
    FOREST("Forest", 'F'),
    HILL("Hill", 'H'),
    NONE("None", 'N');

    private char terrainChar;
    private String name;

    private Terrain(String name, char terrainChar) {
        this.terrainChar = terrainChar;
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
     * Returns the name of the terrain
     * @return the name of the terrain
     */
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
            if (values[i].getName().equalsIgnoreCase(name)) {
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
            if (values[i].getTerrainCharacter() == Character.toUpperCase(name)) {
                result = values[i];
            }
        }
        return result;
    }
}