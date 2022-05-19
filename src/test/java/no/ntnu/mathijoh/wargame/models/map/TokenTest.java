package no.ntnu.mathijoh.wargame.models.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.factories.UnitFactory;
import no.ntnu.mathijoh.wargame.factories.UnitFactory.UnitType;
import no.ntnu.mathijoh.wargame.models.units.Unit;

public class TokenTest {
    @Test
    public void testGetColor() {
        Unit unit = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        Token token = new Token(unit, "red");
        assertEquals("red", token.getColor());
    }

    @Test
    public void testGetUnit() {
        Unit unit = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        Token token = new Token(unit, "red");
        assertEquals(unit, token.getUnit());
    }

    @Test
    public void testSetColor() {
        Unit unit = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        Token token = new Token(unit, "red");
        token.setColor("blue");
        assertEquals("blue", token.getColor());
    }

    @Test
    public void testSetUnit() {
        Unit unit = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        Token token = new Token(unit, "red");
        Unit unit2 = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        token.setUnit(unit2);
        assertEquals(unit2, token.getUnit());
    }

    @Test
    public void negativeTestSetUnit() {
        Unit unit = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        Token token = new Token(unit, "red");
        assertThrows(IllegalArgumentException.class, () -> token.setUnit(null));
    }
}
