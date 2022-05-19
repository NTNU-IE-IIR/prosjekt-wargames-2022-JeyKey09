package no.ntnu.mathijoh.wargame.models.map;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.models.units.InfantryUnit;
public class BattleMapTest {
    
    @Test
    public void testGetTile() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        assertDoesNotThrow(() -> map.getTile(0, 0));
        assertThrows(IllegalArgumentException.class, () -> map.getTile(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> map.getTile(0, -1));
    }


    @Test
    public void testChangeTerrain() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        assertDoesNotThrow(() -> map.changeTerrain(0, 0, Terrain.FOREST));
        assertEquals(Terrain.FOREST, map.getTile(0, 0).getTerrain());
    }

    @Test
    public void testPlaceUnit() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        Token token = new Token(new InfantryUnit("TestUnit", 10), "Red");
        assertDoesNotThrow(() -> map.placeToken(token, 0, 0));
        assertEquals(map.getTile(0, 0).getToken(), token);
        assertThrows(IllegalArgumentException.class, () ->  map.placeToken(token));
    }

    @Test
    public void testFindUnitCordinates() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        InfantryUnit unit = new InfantryUnit("TestUnit", 10);
        Token token = new Token(unit, "Red");
        assertDoesNotThrow(() -> map.placeToken(token, 0, 0));
        assertDoesNotThrow(() -> map.findUnitCordinates(unit));
    }

    @Test
    public void testFindUnitTile() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        InfantryUnit unit = new InfantryUnit("TestUnit", 10);
        Token token = new Token(unit, "Red");
        assertDoesNotThrow(() -> map.placeToken(token, 0, 0));
        assertEquals(token,map.findUnitTile(unit).getToken());
    }

    @Test
    public void testGetName() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        assertEquals("Testmap", map.getName());
    }

    @Test
    public void testGetPossibleTargets() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        InfantryUnit unit = new InfantryUnit("TestUnit", 10);
        InfantryUnit unit2 = new InfantryUnit("TestUnit", 10);
        map.placeToken(new Token(unit, "red"), 0, 0);
        map.placeToken(new Token(unit2, "blue"), 6, 0);
        assertEquals(1, map.getPossibleTargets(unit).size());
    }


    @Test
    public void testMoveUnit() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        InfantryUnit unit = new InfantryUnit("TestUnit", 10);
        map.placeToken(new Token(unit, "red"), 0, 0);
        assertDoesNotThrow(() -> map.moveUnit(unit, 1, 0));
        assertEquals(map.getTile(1, 0).getToken().getUnit(), unit);
    }

    @Test
    public void testMoveUnit2() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        InfantryUnit unit = new InfantryUnit("TestUnit", 10);
        map.placeToken(new Token(unit, "red"), 0, 0);
        assertDoesNotThrow(() -> map.moveUnit(unit,map.getTile(1,9)));
        assertEquals(map.getTile(1, 9).getToken().getUnit(), unit);
    }

    @Test
    public void testRemoveUnit() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        InfantryUnit unit = new InfantryUnit("TestUnit", 10);
        map.placeToken(new Token(unit, "red"), 0, 0);
        assertDoesNotThrow(() -> map.removeUnit(unit));
        assertEquals(null, map.getTile(0, 0).getToken());
    }

    @Test
    public void testRemoveTokenFromTile() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        InfantryUnit unit = new InfantryUnit("TestUnit", 10);
        map.placeToken(new Token(unit, "red"), 0, 0);
        assertDoesNotThrow(() -> map.removeTokenFromTile(0,0));
        assertEquals(null, map.getTile(0, 0).getToken());
    }

    @Test
    public void testSetTile() {
        BattleMap map = new BattleMap("Testmap",10, 10);
        Tile tile = new Tile(Terrain.FOREST);
        assertDoesNotThrow(() -> map.setTile(0, 0, tile));
        assertEquals(map.getTile(0, 0), tile);
    }
}
