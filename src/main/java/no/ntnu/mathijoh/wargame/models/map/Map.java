package no.ntnu.mathijoh.wargame.models.map;
/**
 * This is a class meant for keeping the different terrains in the game
 */

import java.util.HashMap;
import java.util.Iterator;

import no.ntnu.mathijoh.wargame.models.units.Unit;

public class Map {

    /**
     * The map of the game
     * The key should follow the format of X-Y since the map consists of a grid
     */
    private HashMap<String, Tile> map;

    private String name;

    public Map(String name) throws IllegalArgumentException {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        this.name = name;
        map = new HashMap<>();
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
        map.put(String.format("%s-%s",x,y), new Tile(terrain));
    }
    
    /**
     * Gets the Terrain at that position
     * @param key the cordinates of the terrain type, in the format of X-Y
     * @return the terrain at that position
     */
    public Tile getTile(int x, int y) {
        return map.get(String.format("%s-%s",x,y));
    }


    public String getName() {
        return name;
    }

    public int[] findUnitCordinates(Unit unit){
        int[] result = new int[2];
        boolean notFound = true;
        Iterator<String> it = map.keySet().iterator();
        while(it.hasNext() && notFound){
            String key = it.next();
            if(map.get(key).getUnit().equals(unit)) {
                notFound = false;
                String[] cords = key.split("-");
                result[0] = Integer.parseInt(cords[0]);
                result[1] = Integer.parseInt(cords[1]);
            }
        }
        return result;
    }
}
