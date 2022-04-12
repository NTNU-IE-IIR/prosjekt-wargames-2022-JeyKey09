package no.ntnu.mathijoh.wargame.models;
/**
 * This is a class meant for keeping the different terrains in the game
 * The map is 
 */

import java.util.HashMap;

public class Map {

    /**
     * The map of the game
     * The key should follow the format of X-Y since the map consists of a grid
     */
    private HashMap<String, Terrain> terrainMap;

    public Map() {
        terrainMap = new HashMap<>();
    }

    /**
     * Adds a terrain to the map
     * The key shouød be in the format of X-Y
     * @param key the cordinates of the terrain type, in the format of X-Y
     * @param terrain the terrain in that conrdinate
     * @throws IllegalArgumentException if the key is invalid or the terrain is null
     */
    public void setTerrain(String key, Terrain terrain) throws IllegalArgumentException{
        if(key == null || key.split("-").length != 2) {
            throw new IllegalArgumentException("Key must be in the format of X-Y");
        }
        if(terrain == null) {
            throw new IllegalArgumentException("Terrain cannot be null");
        }
        terrainMap.put(key, terrain);
    }
    
    /**
     * Gets the Terrain at that position
     * @param key the cordinates of the terrain type, in the format of X-Y
     * @return the terrain at that position
     */
    public Terrain getTerrain(String key) {
        return terrainMap.get(key);
    }

    /**
     * Returns a arraylist of all the terrains in the map
     * where it starts with the top left corner and goes to the bottom right corner
     * 
     * @return a arraylist of all the terrains in the map
     */
    public Terrain[] getAllTerrains() {
        Terrain[] terrains = new Terrain[terrainMap.size()];
        int i = 0;
        for(String key : terrainMap.keySet()) {
            terrains[i] = terrainMap.get(key);
            i++;
        }
        return terrains;
    }
}
