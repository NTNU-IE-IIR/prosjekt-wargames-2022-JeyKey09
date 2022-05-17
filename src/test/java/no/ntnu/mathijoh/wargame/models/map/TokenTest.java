package no.ntnu.mathijoh.wargame.models.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.factories.UnitFactory;
import no.ntnu.mathijoh.wargame.factories.UnitFactory.UnitType;
import no.ntnu.mathijoh.wargame.models.units.Unit;

public class TokenTest {
    @Test
    void testGetColor() {
        Unit unit = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        Token token = new Token(unit, "red");
        assertEquals("red", token.getColor());
    }

    // @Test
    // void testGetImage() {
    //     Unit unit = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
    //     Token token = new Token(unit, "red");
    //     assertNotNull(token.getImage());
    // }

    @Test
    void testGetUnit() {
        Unit unit = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        Token token = new Token(unit, "red");
        assertEquals(unit, token.getUnit());
    }

    @Test
    void testSetColor() {
        Unit unit = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        Token token = new Token(unit, "red");
        token.setColor("blue");
        assertEquals("blue", token.getColor());

    }

    @Test
    void testSetUnit() {
        Unit unit = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        Token token = new Token(unit, "red");
        Unit unit2 = UnitFactory.createUnit(UnitType.INFANTRYUNIT, "Testy Mc Testylot", 20);
        token.setUnit(unit2);
        assertEquals(unit2, token.getUnit());
    }
}
