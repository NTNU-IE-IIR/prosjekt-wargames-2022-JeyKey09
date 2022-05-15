package no.ntnu.mathijoh.wargame.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.models.units.Unit;

public class UnitFactoryTest {

    @Test
    void testCreateUnit() {
        Unit unit = UnitFactory.createUnit(UnitFactory.UnitType.COMMANDERUNIT, "Commander", 15);
        assertEquals("Commander", unit.getName());
    }

    @Test
    void testCreateUnitList() {
        List<Unit> unitList = UnitFactory.createUnitList(UnitFactory.UnitType.COMMANDERUNIT, "Commander", 15, 10);
        assertEquals(10, unitList.size());
    }
    

    @Test
    void negativeTestCreateUnit() {
        assertThrows(IllegalArgumentException.class, () ->  {UnitFactory.createUnit(UnitFactory.UnitType.COMMANDERUNIT, "", -15);});
        assertThrows(IllegalArgumentException.class, () ->  {UnitFactory.createUnit(UnitFactory.UnitType.COMMANDERUNIT, "Commander", 0);});
    }
    
    @Test
    void negativeTestCreateListUnit() {
        assertThrows(IllegalArgumentException.class, () ->  {UnitFactory.createUnitList(UnitFactory.UnitType.COMMANDERUNIT, "", 15, 10);});
        assertThrows(IllegalArgumentException.class, () ->  {UnitFactory.createUnitList(UnitFactory.UnitType.COMMANDERUNIT, "Commander", 15, 0);});
        assertThrows(IllegalArgumentException.class, () ->  {UnitFactory.createUnitList(UnitFactory.UnitType.COMMANDERUNIT, "Infantry", -15, 10);});
    }
}
