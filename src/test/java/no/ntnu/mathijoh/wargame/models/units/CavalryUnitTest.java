package no.ntnu.mathijoh.wargame.models.units;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CavalryUnitTest {

    @Test
    public void testGetAttackBonus() {
        CavalryUnit testObject = new CavalryUnit("name", 10);

        assertEquals(6 ,testObject.getAttackBonus());
        assertEquals(2 ,testObject.getAttackBonus());
    }

    @Test
    public void testGetResistBonus() {
        CavalryUnit testObject = new CavalryUnit("name", 10);
        assertEquals(1, testObject.getResistBonus());
    }
}
