package no.ntnu.mathijoh.wargame.models;
/**
 * This is a class meant for keeping the different terrains in the game
 */

import java.util.HashMap;

public class Map {

    /**
     * The map of the game
     * The key should follow the format of X-Y since the map consists of a grid
     */
    private HashMap<String, Terrain> terrainMap;

    private String name;

    public Map(String name) throws IllegalArgumentException {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        this.name = name;
        terrainMap = new HashMap<>();
    }

    /**
     * Adds a terrain to the map
     * The key shouød be in the format of X-Y
     * @param key the cordinates of the terrain type, in the format of X-Y
     * @param terrain the terrain in that conrdinate
     * @throws IllegalArgumentException if the key is invalid or the terrain is null
     */
    public void setTerrain(int x, int y, Terrain terrain) throws IllegalArgumentException{
        if(terrain == null) {
            throw new IllegalArgumentException("Terrain cannot be null");
        }
        terrainMap.put(String.format("%s-%s",x,y), terrain);
    }
    
    /**
     * Gets the Terrain at that position
     * @param key the cordinates of the terrain type, in the format of X-Y
     * @return the terrain at that position
     */
    public Terrain getTerrain(int x, int y) {
        return terrainMap.get(String.format("%s-%s",x,y));
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

    public String getName() {
        return name;
    }
}
