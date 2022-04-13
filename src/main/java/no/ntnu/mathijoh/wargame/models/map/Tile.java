package no.ntnu.mathijoh.wargame.models.map;
/**
 * A tile on the map
 * It will replace the terrain with Map to allow for both terrain and units to 
 * be stored on the same key
 */
public class Tile {
    
    private Terrain terrain;
    private Token unit;

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
    public Tile(Terrain terrain, Token unit) throws IllegalArgumentException{
        this.terrain = terrain;
        this.unit = null;
    }
 
    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Token getToken() {
        return unit;
    }

    public void setToken(Token token) {
        this.unit = token;
    }
}
