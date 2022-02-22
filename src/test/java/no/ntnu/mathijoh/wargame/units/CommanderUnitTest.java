package no.ntnu.mathijoh.wargame.units;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CommanderUnitTest {
    
    @Test
    public void posetivTestConstructor() {
        CommanderUnit testObject = new CommanderUnit("name", 10);
        
        assertEquals("name", testObject.getName());
        assertEquals(10, testObject.getHealth());
        assertEquals(25 ,testObject.getAttack());
        assertEquals(15, testObject.getArmor());
    }
}
