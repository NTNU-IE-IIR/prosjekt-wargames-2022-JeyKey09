package no.ntnu.mathijoh.wargame.units;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RangedUnitTest {

    @Test
    public void posetivTestConstructor() {
        RangedUnit testObject = new RangedUnit("name", 11);
        
        assertEquals("name", testObject.getName());
        assertEquals(11, testObject.getHealth());
        assertEquals(15, testObject.getAttack());
        assertEquals(8, testObject.getArmor());
    }

    @Test
    public void testGetAttackBonus() {
        RangedUnit testObject = new RangedUnit("name", 11);
        
        assertEquals(3, testObject.getAttackBonus());
    }

    @Test
    public void testGetResistBonus() {
        RangedUnit testObject = new RangedUnit("name", 11);

        assertEquals(6, testObject.getResistBonus());
        assertEquals(4, testObject.getResistBonus());
        assertEquals(2, testObject.getResistBonus());
        assertEquals(2, testObject.getResistBonus());
    }
}
