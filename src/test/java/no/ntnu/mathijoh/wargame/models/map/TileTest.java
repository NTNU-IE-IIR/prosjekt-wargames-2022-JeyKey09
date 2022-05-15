package no.ntnu.mathijoh.wargame.models.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.factories.UnitFactory;
import no.ntnu.mathijoh.wargame.factories.UnitFactory.UnitType;
import no.ntnu.mathijoh.wargame.models.units.Unit;

public class TileTest {
    @Test
    void testGetTerrain() {
        Tile tile = new Tile(Terrain.PLAINS);
        assertEquals(Terrain.PLAINS, tile.getTerrain());
    }

    @Test
    void testGetAndSetToken() {
        Tile tile = new Tile(Terrain.PLAINS);
        assertEquals(null, tile.getToken());
        Unit unit = UnitFactory.createUnit(UnitType.CAVALRYUNIT, "Testy mc Testylot", 20);
        tile.setToken(new Token(unit, "red"));
        assertEquals(unit, tile.getToken().getUnit());
    }

    @Test
    void testSetTerrain() {
        Tile tile = new Tile(Terrain.PLAINS);
        tile.setTerrain(Terrain.FOREST);
        assertEquals(Terrain.FOREST, tile.getTerrain());
    }
}
