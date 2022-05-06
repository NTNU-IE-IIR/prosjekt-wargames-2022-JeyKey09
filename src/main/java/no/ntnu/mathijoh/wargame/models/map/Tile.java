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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((terrain == null) ? 0 : terrain.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (terrain != other.terrain)
            return false;
        if (token == null) {
            if (other.token != null)
                return false;
        } else if (!token.equals(other.token))
            return false;
        return true;
    }
}
