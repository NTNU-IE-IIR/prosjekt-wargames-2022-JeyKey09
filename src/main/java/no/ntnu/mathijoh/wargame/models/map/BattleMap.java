package no.ntnu.mathijoh.wargame.models.map;

/**
 * This is a class meant for keeping the different tiles in the game
 * and allowing for units to move and "see" within the map.
 * @author Mathias J. Kirkeby
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import no.ntnu.mathijoh.wargame.models.units.Unit;

public class BattleMap {

    /**
     * The gridMap of the game
     * The key should follow the format of X-Y since the gridMap consists of a grid
     */
    private HashMap<String, Tile> gridMap;

    private String name;

    /**
     * Creates a empty BattleMap with a name    
     * 
     * @param name The name of the map
     * @throws IllegalArgumentException if the name is null or empty
     */
    public BattleMap(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        this.name = name;
        gridMap = new HashMap<>();
    }


    /**
     * Creates a BattleMap with x*y tiles
     * 
     * @param x The width of the map
     * @param y The height of the map
     * @param name The name of the map
     */
    public BattleMap(String name, int rows, int columns) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        this.name = name;
        gridMap = new HashMap<>();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                setTile(x, y);
            }
        }
    }

    /**
     * Creates a duplicate of a BattleMap
     * 
     * @param map The map to be copied
     */
    public BattleMap(BattleMap map) {
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
     * Adds a new tile to the gridMap
     * If the tile already exists, it will only change the terrain within that tile
     * using the changeTerrain method
     * 
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     * @param terrain the terrain in that conrdinate
     * @throws IllegalArgumentException if the key is invalid or the terrain is null
     */
    public void setTile(int x, int y, Terrain terrain) throws IllegalArgumentException {
        if (terrain == null) {
            throw new IllegalArgumentException("Terrain cannot be null");
        }
        if(gridMap.containsKey(getKey(x, y))){
            changeTerrain(x, y, terrain);
        } else {
            gridMap.put(getKey(x, y), new Tile(terrain));
        }
    }


    /**
     * Changes a terrain in a tile
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     * @param terrain the terrain to be changed to
     */
    public void changeTerrain(int x, int y, Terrain terrain){
        if(gridMap.get(getKey(x, y)) == null){
            throw new IllegalArgumentException("Tile does not exist");
        }
        if(terrain == null){
            throw new IllegalArgumentException("Terrain cannot be null");
        }
        gridMap.get(getKey(x, y)).setTerrain(terrain);
    }

    /**
     * Creates a blank tile with no terrain
     * Will return a error if the tile already exists
     * 
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     * @throws IllegalArgumentException if the tile already exists
     */
    public void setTile(int x, int y) throws IllegalArgumentException {
        if(gridMap.containsKey(getKey(x, y))){
            throw new IllegalArgumentException("The tile already exists");
        } else {
            setTile(x,y,Terrain.NONE);
        }
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

    /**
     * Gets the name of the map
     * 
     * @return the name of the map
     */
    public String getName() {
        return name;
    }

    /**
     * Finds the unit cordinates on the map
     * 
     * @param unit the unit to find the cordinates of
     * @return the cordinates of the unit
     * @throws IllegalArgumentException
     */
    public int[] findUnitCordinates(Unit unit) throws IllegalArgumentException {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        Tile tile = findUnitTile(unit);
        if (tile == null) {
            throw new IllegalArgumentException("Unit not found");
        }
        return findTileCordinates(tile);
    }

    /**
     * Fetches the tile that the unit is on
     * 
     * @param unit the unit to find the tile of
     * @return the tile that the unit is on
     */
    public Tile findUnitTile(Unit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        Tile result = null;
        Iterator<String> it = gridMap.keySet().iterator();
        while (it.hasNext() && result == null) {
            String key = it.next();
            Tile tile = gridMap.get(key);
            if (tile.getToken() != null && tile.getToken().getUnit() == unit) {
                result = tile;
            }
        }
        return result;
    }

    /**
     * Removes a unit and places it on the tile
     * 
     * @param unit the unit to be moved
     * @param x    the x cordinate of the tile
     * @param y    the y cordinate of the tile
     * @throws IllegalArgumentException if the unit is null or if the cordinatesis
     *                                  nowhere
     */
    public void moveUnit(Unit unit, int x, int y) throws IllegalArgumentException {
        if (gridMap.get(getKey(x, y)).getToken() != null
                && !gridMap.get(getKey(x, y)).getToken().getUnit().equals(unit)) {
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

    /**
     * Moves a unit from one tile to another
     * 
     * @param unit the unit to be moved
     * @param tile the tile to move the unit to
     * @throws IllegalArgumentException if the unit is alraeady on the tile or the
     *                                  tile is null
     */
    public void moveUnit(Unit unit, Tile tile) throws IllegalArgumentException {
        if (tile == null) {
            throw new IllegalArgumentException("Tile can not be null");
        }
        if (tile.getToken() != null && !tile.getToken().getUnit().equals(unit)) {
            throw new IllegalArgumentException("There is already a unit at that position");
        }
        try {
            int[] destination = findTileCordinates(tile);
            moveUnit(unit, destination[0], destination[1]);
        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

    /**
     * Returns the key for the tile
     * 
     * @param tile
     * @return the key for the tile
     */
    private int[] findTileCordinates(Tile tile) {
        if (tile == null) {
            throw new IllegalArgumentException("Tile can not be null");
        }
        int[] result = new int[2];
        boolean notFound = true;
        Iterator<String> it = gridMap.keySet().iterator();
        while (it.hasNext() && notFound) {
            String key = it.next();
            if (gridMap.get(key).equals(tile)) {
                notFound = false;
                String[] cords = key.split("-");
                result[0] = Integer.parseInt(cords[0]);
                result[1] = Integer.parseInt(cords[1]);
            }
        }
        if (notFound) {
            throw new IllegalArgumentException("Tile not found");
        }
        return result;
    }

    /**
     * Returns the key for a tile with the given cordinates
     * 
     * @param x the x cordinate
     * @param y the y cordinate
     * @return the key for the tile
     */
    private String getKey(int x, int y) {
        return String.format("%s-%s", x, y);
    }

    /**
     * Places a unit on the map.
     * TODO: Implement a better placement system, Now it just places the unit on the first tile it finds
     * 
     * @param token the token to place on the map
     */
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

    /**
     * Removes a unit specefied by the cordinates on the map
     * 
     * @param x the row of the unit
     * @param y the coulmn of the unit
     */
    public void removeUnit(int x, int y) {
        gridMap.get(getKey(x, y)).setToken(null);
    }

    /**
     * Removes a unit based based on the unit given
     * 
     * @param unit the unit to remove
     * @throws IllegalArgumentException if the unit is null or if the unit is not on the map
     */
    public void removeUnit(Unit unit) throws IllegalArgumentException {
        if(unit == null){
            throw new IllegalArgumentException("Unit can't be null");
        }
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
    public Map<Tile, Unit> getPossibleTargets(Unit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit can't be null");
        }
        // Creates temporary objects to store information
        HashMap<Tile, Unit> result = new HashMap<>();
        ArrayList<Tile> checkedTiles = new ArrayList<>();
        ArrayList<Tile> tilesForChecking = new ArrayList<>();
        // Finds the tile the unit is on
        int[] cordinates = findUnitCordinates(unit);
        // Adds the tile the unit is on to the list of tiles to check
        Tile rootTile = gridMap.get(getKey(cordinates[0], cordinates[1]));
        // Gets the color of the unit (What team or army it is part of)
        Token tokenOfUnit = rootTile.getToken();
        // Adds the tile to the unit to the list of tiles to check
        tilesForChecking.add(rootTile);
        // Loops through the list of tiles to check
        while (!tilesForChecking.isEmpty() && result.isEmpty()) {
            // Gets the first tile in the list
            Tile parentTile = tilesForChecking.remove(0);
            // Gets the list of tiles that are adjacent to the tile
            ArrayList<Tile> nTiles = getNeighborTiles(parentTile);
            // Loops through the list of tiles that are adjacent to the tile
            for (Tile tile : nTiles) {
                // If the tile is not a checked tile
                if (!checkedTiles.contains(tile)) {
                    checkedTiles.add(tile);
                    // If the tile has a unit on it
                    if (tile.getToken() == null) {
                        // Adds the tile to the list of tiles to check becouse you can move trough it
                        tilesForChecking.add(tile);
                        // Else if the tile has a unit on it and it is on the enemy team
                    } else if (!tile.getToken().getColor().equals(tokenOfUnit.getColor())) {
                        // Adds it to the result
                        result.put(parentTile, tile.getToken().getUnit());
                    }
                }
            }
        }
        return result;
    }

    /**
     * Gets the list of tiles that are adjacent to the tile
     * 
     * @param tile
     * @return
     */
    private ArrayList<Tile> getNeighborTiles(Tile tile) {
        if (tile == null) {
            throw new IllegalArgumentException("Tile can't be null");
        }
        ArrayList<Tile> nTiles = new ArrayList<>();
        // Gets the cordinates of the tile
        int[] cords = findTileCordinates(tile);
        int x = cords[0];
        int y = cords[1];
        // For every column before, after and on the tile
        for (int i = y - 1; i <= y + 1; i++) {
            // For every row above, on and below the tile
            for (int j = x - 1; j <= x + 1; j = j + 1) {
                Tile nTile = gridMap.get(getKey(j, i));
                // If the tile is not the same tile and not null
                if (nTile != null && !nTile.equals(tile)) {
                    // Adds it to the list of tiles
                    nTiles.add(nTile);
                }
            }
        }
        // Returns the list of tiles
        return nTiles;
    }
}