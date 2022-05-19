package no.ntnu.mathijoh.wargame.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.models.units.Unit;

public class UnitFactoryTest {

    @Test
    public void testCreateUnit() {
        Unit unit = UnitFactory.createUnit(UnitFactory.UnitType.COMMANDERUNIT, "Commander", 15);
        assertEquals("Commander", unit.getName());
    }

    @Test
    public void testCreateUnitList() {
        List<Unit> unitList = UnitFactory.createUnitList(UnitFactory.UnitType.COMMANDERUNIT, "Commander", 15, 10);
        assertEquals(10, unitList.size());
    }

    @Test
    public void negativeTestCreateUnit() {
        assertThrows(IllegalArgumentException.class, () ->  {UnitFactory.createUnit(UnitFactory.UnitType.COMMANDERUNIT, "", -15);});
        assertThrows(IllegalArgumentException.class, () ->  {UnitFactory.createUnit(UnitFactory.UnitType.COMMANDERUNIT, "Commander", 0);});
    }
    
    @Test
    public void negativeTestCreateListUnit() {
        assertThrows(IllegalArgumentException.class, () ->  {UnitFactory.createUnitList(UnitFactory.UnitType.COMMANDERUNIT, "", 15, 10);});
        assertThrows(IllegalArgumentException.class, () ->  {UnitFactory.createUnitList(UnitFactory.UnitType.COMMANDERUNIT, "Commander", 15, 0);});
        assertThrows(IllegalArgumentException.class, () ->  {UnitFactory.createUnitList(UnitFactory.UnitType.COMMANDERUNIT, "Infantry", -15, 10);});
    }

    @Test
    public void testGetUnitTypeFromName() {
        assertEquals(UnitFactory.UnitType.COMMANDERUNIT, UnitFactory.UnitType.getUnitTypeFromName("CommanderUnit"));
        assertEquals(UnitFactory.UnitType.COMMANDERUNIT, UnitFactory.UnitType.getUnitTypeFromName("ComMANderUnit"));
    }

    @Test
    public void negativeTestGetUnitTypeFromName() {
        assertThrows(IllegalArgumentException.class, () -> UnitFactory.UnitType.getUnitTypeFromName(""));
        assertThrows(IllegalArgumentException.class, () -> UnitFactory.UnitType.getUnitTypeFromName(null));
        assertEquals(null, UnitFactory.UnitType.getUnitTypeFromName("AUnitType"));

    }
}
