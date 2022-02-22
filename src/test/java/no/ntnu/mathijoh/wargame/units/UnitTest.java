package no.ntnu.mathijoh.wargame.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class UnitTest {

    @Test
    public void posetivTestConstructor() {
        Unit testObject = null;

        try {
            testObject = new Unit("name", 10, 10, 10) {
                @Override
                public int getAttackBonus() {
                    return 0;
                }

                @Override
                public int getResistBonus() {
                    return 0;
                }
            };
        } catch (IllegalArgumentException e) {
        }

        assertEquals(10, testObject.getAttack());
        assertEquals(10, testObject.getHealth());
        assertEquals(10, testObject.getArmor());
        assertEquals("name", testObject.getName());
    }

    @Test
    public void negativTestConstructor() {
        Unit testObject = null;

        try {
            testObject = new Unit(null, 10, 10, 10) {
                @Override
                public int getAttackBonus() {
                    return 0;
                }

                @Override
                public int getResistBonus() {
                    return 0;
                }
            };
        } catch (IllegalArgumentException e) {
            
        }

        assertNull(testObject);
    }

    @Test
    public void testAttack() {

        Unit testObject = new Unit("name", 10, 2, 0) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        Unit testObject2 = new Unit("name", 10, 2, 0) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }

        };

        testObject.attack(testObject2);
        assertEquals(10, testObject.getHealth());
        assertEquals(8, testObject2.getHealth());
    }

    @Test
    public void testGetArmor() {
        Unit testObjUnit = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };

        assertEquals(1, testObjUnit.getArmor());
    }

    @Test
    public void testGetAttack() {
        Unit testObjUnit = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        
        assertEquals(2, testObjUnit.getAttack());
    }

    @Test
    public void testGetAttackBonus() {
        Unit testObjUnit = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        assertEquals(0, testObjUnit.getAttackBonus());
    }

    @Test
    public void testGetAndSetHealth() {
        Unit testObjUnit = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        assertEquals(10, testObjUnit.getHealth());
        testObjUnit.setHealth(2);
        assertEquals(2, testObjUnit.getHealth());
        testObjUnit.setHealth(-2);
        assertEquals(0, testObjUnit.getHealth());
    }

    @Test
    public void testGetName() {
        Unit testObjUnit = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        assertEquals("name", testObjUnit.getName());
    }

    @Test
    public void testGetResistBonus() {
        Unit testObjUnit = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        assertEquals(0, testObjUnit.getResistBonus());
    }

    @Test
    public void testToString() {
        Unit testObjUnit = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        assertEquals("Unit [armor=1, attack=2, health=10, name=name]", testObjUnit.toString());

        

    }

    @Test
    public void testEqualsAndHashcode() {
        Unit testUnit1 = new InfantryUnit("name", 10);
        Unit testUnit2 = new InfantryUnit("name", 10);
        assertEquals(testUnit1, testUnit2);
        assertEquals(testUnit1.hashCode(), testUnit2.hashCode());
    }
}