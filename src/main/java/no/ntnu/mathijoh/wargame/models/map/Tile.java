package no.ntnu.mathijoh.wargame.models.map;
/**
 * A tile on the map
 * It will replace the terrain with Map to allow for both terrain and units to 
 * be stored on the same key
 */
public class Tile {
    
    private Terrain terrain;
    private Token token;

    /**
     * Creates a new tile
     * @param terrain the terrain on the tile
     */

    public Tile(Terrain terrain) throws IllegalArgumentException{
        this.terrain = terrain;
        this.token = null;
    }

    /**
     * Creates a new tile
     * @param terrain the terrain on the tile
     * @param token the token on the tile
     */
    public Tile(Terrain terrain, Token token) throws IllegalArgumentException{
        this.terrain = terrain;
        this.token = null;
    }
 
    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
