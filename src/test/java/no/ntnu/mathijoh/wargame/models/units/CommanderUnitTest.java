package no.ntnu.mathijoh.wargame.models.units;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

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
