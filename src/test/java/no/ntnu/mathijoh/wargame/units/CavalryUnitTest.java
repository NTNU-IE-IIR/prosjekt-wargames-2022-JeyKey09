package no.ntnu.mathijoh.wargame.units;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CavalryUnitTest {

    @Test
    public void posetivTestConstructor() {
        CavalryUnit testObject = new CavalryUnit("name", 10);
        
        assertEquals(20 ,testObject.getAttack());
        assertEquals(10, testObject.getHealth());
        assertEquals(12, testObject.getArmor());
        assertEquals("name", testObject.getName());
    }


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
