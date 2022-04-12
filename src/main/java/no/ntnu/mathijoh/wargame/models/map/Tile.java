package no.ntnu.mathijoh.wargame.models.map;

import no.ntnu.mathijoh.wargame.models.units.Unit;

/**
 * A tile on the map
 * It will replace the terrain with Map to allow for both terrain and units to 
 * be stored on the same key
 */
public class Tile {
    
    private Terrain terrain;
    private Unit unit;

    /**
     * Creates a new tile
     * @param terrain the terrain on the tile
     */

    public Tile(Terrain terrain) throws IllegalArgumentException{
        this.terrain = terrain;
        this.unit = null;
    }

    /**
     * Creates a new tile
     * @param terrain the terrain on the tile
     * @param unit the unit on the tile
     */
    public Tile(Terrain terrain, Unit unit) throws IllegalArgumentException{
        this.terrain = terrain;
        this.unit = null;
    }
 
    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    

}
