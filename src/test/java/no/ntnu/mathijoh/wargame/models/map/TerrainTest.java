package no.ntnu.mathijoh.wargame.models.map;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TerrainTest {

    @Test
    public void testGetName() {
        assertEquals("Forest", Terrain.FOREST.getName());
    }

    @Test
    public void testGetTerrainCharacter() {
        assertEquals('F', Terrain.FOREST.getTerrainCharacter());
    }

    @Test
    public void testGetTerrainFromChar() {
        assertEquals(Terrain.FOREST, Terrain.getTerrainFromChar('F'));
        assertEquals(Terrain.FOREST, Terrain.getTerrainFromChar('f'));
    }

    @Test
    public void testGetTerrainFromName() {
        assertEquals(Terrain.FOREST, Terrain.getTerrainFromName("Forest"));
        assertEquals(Terrain.FOREST, Terrain.getTerrainFromName("FoREst"));
    }

}
