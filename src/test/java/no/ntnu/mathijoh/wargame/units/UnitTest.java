package no.ntnu.mathijoh.wargame.units;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class UnitTest {
    
    @Test
    public void testGetNamePosetiv() {
        Unit test = new Unit("Hello",213,53,12);
        assertEquals("Hello", test.getName());
    }

    @Test 
    public void testGetHealthPosetiv() {
        Unit test = new Unit("Hello",213,53,12);
        assertEquals(213, test.getHealth());
    }

    @Test 
    public void testGetAttackPosetiv() {
        Unit test = new Unit("Hello",213,53,12);
        assertEquals(53, test.getAttack());
    }

    @Test 
    public void testGetArmorPosetiv() {
        Unit test = new Unit("Hello",213,53,12);
        assertEquals(12, test.getArmor());
    }    
}
