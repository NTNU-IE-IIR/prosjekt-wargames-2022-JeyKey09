package no.ntnu.mathijoh.wargame.models.map;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.models.units.InfantryUnit;
//TODO: Finish the tests for the map class
public class BattleMapTest {
    
    @Test
    void testGetTile() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        assertDoesNotThrow(() -> map.getTile(0, 0));
        assertThrows(IllegalArgumentException.class, () -> map.getTile(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> map.getTile(0, -1));
    }


    @Test
    void testChangeTerrain() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        assertDoesNotThrow(() -> map.changeTerrain(0, 0, Terrain.FOREST));
        assertEquals(map.getTile(0, 0).getTerrain(), Terrain.FOREST);
    }

    @Test
    void testPlaceUnit() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        Token token = new Token(new InfantryUnit("TestUnit", 10), "Red");
        assertDoesNotThrow(() -> map.placeToken(token, 0, 0));
        assertEquals(map.getTile(0, 0).getToken(), token);
        assertThrows(IllegalArgumentException.class, () ->  map.placeToken(token));
    }

    @Test
    void testFindUnitCordinates() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        InfantryUnit unit = new InfantryUnit("TestUnit", 10);
        Token token = new Token(unit, "Red");
        assertDoesNotThrow(() -> map.placeToken(token, 0, 0));
        assertDoesNotThrow(() -> map.findUnitCordinates(unit));
    }

    @Test
    void testFindUnitTile() {

    }

    @Test
    void testGetName() {

    }

    @Test
    void testGetPossibleTargets() {

    }


    @Test
    void testMoveUnit() {

    }

    @Test
    void testMoveUnit2() {

    }

    @Test
    void testRemoveUnit() {

    }

    @Test
    void testRemoveUnit2() {

    }

    @Test
    void testSetTile() {

    }

    @Test
    void testSetTile2() {

    }
}
