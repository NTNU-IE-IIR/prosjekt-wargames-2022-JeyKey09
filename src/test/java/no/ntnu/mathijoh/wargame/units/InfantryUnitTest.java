package no.ntnu.mathijoh.wargame.units;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class InfantryUnitTest {
    
    @Test
    public void posetivTestConstructor() {
        InfantryUnit testObject = new InfantryUnit("name", 29);

        assertEquals("name", testObject.getName());
        assertEquals(29, testObject.getHealth());
        assertEquals(15, testObject.getAttack());
        assertEquals(10, testObject.getArmor());
    }

    @Test
    public void testGetAttackBonus() {
        InfantryUnit testObject = new InfantryUnit("name", 29);
        assertEquals(2, testObject.getAttackBonus());
    }

    @Test
    public void testGetResistBonus() {
        InfantryUnit testObject = new InfantryUnit("name", 29);
        assertEquals(1, testObject.getResistBonus());
    }
}
