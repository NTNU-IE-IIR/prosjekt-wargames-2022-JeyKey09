package no.ntnu.mathijoh.wargame.models.map;

/**
 * This is a class meant for keeping the different terrains in the game
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import no.ntnu.mathijoh.wargame.models.units.Unit;

public class Map {

    /**
     * The gridMap of the game
     * The key should follow the format of X-Y since the gridMap consists of a grid
     */
    private HashMap<String, Tile> gridMap;

    private String name;

    /**
     * The constructor for the Map class
     * 
     * @param name The name of the map
     */
    public Map(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        this.name = name;
        gridMap = new HashMap<>();
    }

    /**
     * Constructer for the Map class
     * 
     * @param map The map to be copied
     */
    public Map(Map map) {
        this.name = map.getName();
        this.gridMap = new HashMap<>();
        for (String key : map.getGridMap().keySet()) {
            this.gridMap.put(key, new Tile(map.getGridMap().get(key).getTerrain()));
        }
    }

    /**
     * Returns a copy of the GridMap
     * 
     * @return a copy of the GridMap
     */
    private HashMap<String, Tile> getGridMap() {
        return gridMap;
    }

    /**
     * Adds a terrain to the gridMap
     * The key shouød be in the format of X-Y
     * 
     * @param key     the cordinates of the terrain type, in the format of X-Y
     * @param terrain the terrain in that conrdinate
     * @throws IllegalArgumentException if the key is invalid or the terrain is null
     */
    public void setTerrain(int x, int y, Terrain terrain) throws IllegalArgumentException {
        if (terrain == null) {
            throw new IllegalArgumentException("Terrain cannot be null");
        }
        gridMap.put(getKey(x, y), new Tile(terrain));
    }

    /**
     * Gets the Terrain at that position
     * 
     * @param key the cordinates of the terrain type, in the format of X-Y
     * @return the terrain at that position
     */
    public Tile getTile(int x, int y) {
        return gridMap.get(getKey(x, y));
    }

    public String getName() {
        return name;
    }

    public int[] findUnitCordinates(Unit unit) throws IllegalArgumentException {
        int[] result = new int[2];
        boolean notFound = true;
        Iterator<String> it = gridMap.keySet().iterator();
        while (it.hasNext() && notFound) {
            String key = it.next();
            if (gridMap.get(key).getToken().getUnit().equals(unit)) {
                notFound = false;
                String[] cords = key.split("-");
                result[0] = Integer.parseInt(cords[0]);
                result[1] = Integer.parseInt(cords[1]);
            }
        }
        if (notFound) {
            throw new IllegalArgumentException("Unit not found");
        }
        return result;
    }

    public void moveUnit(Unit unit, int x, int y) throws IllegalArgumentException {
        if (gridMap.get(getKey(x, y)).getToken() != null) {
            throw new IllegalArgumentException("There is already a unit at that position");
        }
        try {
            int[] cords = findUnitCordinates(unit);
            Token token = gridMap.get(getKey(cords[0], cords[1])).getToken();
            gridMap.get(String.format("%s-%s", cords[0], cords[1])).setToken(null);
            gridMap.get(getKey(x, y)).setToken(token);
        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

    private String getKey(int x, int y) {
        return String.format("%s-%s", x, y);
    }

    public void placeUnit(Token token) {
        Iterator<String> it = gridMap.keySet().iterator();
        boolean finished = false;
        while (it.hasNext() && !finished) {
            String key = it.next();
            if (gridMap.get(key).getToken() == null) {
                finished = true;
                gridMap.get(key).setToken(token);
            }
        }
        if (!finished) {
            throw new IllegalArgumentException("No free space on the map");
        }
    }

    public void removeUnit(int x, int y) {
        gridMap.get(getKey(x, y)).setToken(null);
    }

    public void removeUnit(Unit unit) throws IllegalArgumentException {
        try {
            int[] cords = findUnitCordinates(unit);
            gridMap.get(getKey(cords[0], cords[1])).setToken(null);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unit not found");
        }
    }

    /**
     * Finds the closest enemy unit given the unit and
     * the tile the unit should be moved to for attacking it
     * 
     * @param unit the unit that is attacking
     * @return HashMap where the key is the tile it should move to
     *         and the value the closest enemy unit
     */
    public HashMap<Tile, Unit> getPossibleTargets(Unit unit) {
        HashMap<Tile, Unit> result = new HashMap<>();
        ArrayList<Tile> checkedTiles = new ArrayList<>();
        ArrayList<String> keysToTilesForChecking = new ArrayList<>();
        int[] cordinates = findUnitCordinates(unit);
        Token tokenOfUnit = gridMap.get(getKey(cordinates[0], cordinates[1])).getToken();

        keysToTilesForChecking.add(getKey(cordinates[0], cordinates[1]));

        while (!keysToTilesForChecking.isEmpty() && result.isEmpty()) {
            String key = keysToTilesForChecking.remove(0);

            checkedTiles.add(gridMap.get(key));
            int x = Integer.parseInt(key.split("-")[0]);
            int y = Integer.parseInt(key.split("-")[1]);

            for (int i = y - 1; i <= y + 1; i++) {
                for (int j = x - 1; j <= x + 1; j = j + 2) {

                    Tile tile = gridMap.get(getKey(j, i));

                    if (tile != null && !checkedTiles.contains(tile)) {
                        checkedTiles.add(tile);
                        if (tile.getToken() == null) {
                            keysToTilesForChecking.add(getKey(j, i));
                        } else if (!tile.getToken().getColor().equals(tokenOfUnit.getColor())) {
                            result.put(tile, tile.getToken().getUnit());
                        }
                    }
                }
            }
        }
        return result;
    }
}
