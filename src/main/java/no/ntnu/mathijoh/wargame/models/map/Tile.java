package no.ntnu.mathijoh.wargame.models.map;

/**
 * A tile on the map
 * It will replace the terrain with BattleMap to allow for both terrain and units to 
 * be stored on the same key
 */
public class Tile{
    
    private Terrain terrain;
    private Token token;

    /**
     * Creates a new tile
     * @param terrain the terrain on the tile
     * @throws IllegalArgumentException if the terrain is null
     */
    public Tile(Terrain terrain) throws IllegalArgumentException{
        if(terrain == null){
            throw new IllegalArgumentException("Terrain can't be null");
        }
        this.terrain = terrain;
        this.token = null;
    }

    /**
     * Creates a new tile
     * @param terrain the terrain on the tile
     * @param token the token on the tile
     * @throws IllegalArgumentException if the token or terrain is null
     */
    public Tile(Terrain terrain, Token token) throws IllegalArgumentException{
        if(terrain == null || token == null){
            throw new IllegalArgumentException("Terrain and token can't be null");
        }
        this.terrain = terrain;
        this.token = token;
    }
 
    /**
     * Returns the terrain on the tile
     * @return the terrain on the tile
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Sets the terrain on the tile
     * @param terrain the terrain on the tile
     */
    public void setTerrain(Terrain terrain) throws IllegalArgumentException{
        if(terrain == null){
            throw new IllegalArgumentException("Terrain can't be null");
        }
        this.terrain = terrain;
    }

    /**
     * Returns the token on the tile
     * @return the token on the tile
     */
    public Token getToken() {
        return token;
    }

    /**
     * Sets the token on the tile
     * @param token the token on the tile
     */
    public void setToken(Token token) {
        this.token = token;
    }
}
