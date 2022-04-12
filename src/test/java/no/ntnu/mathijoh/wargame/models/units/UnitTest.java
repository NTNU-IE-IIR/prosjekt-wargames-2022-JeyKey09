package no.ntnu.mathijoh.wargame.models.units;

import org.junit.jupiter.api.Test;

import no.ntnu.mathijoh.wargame.models.map.Terrain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class UnitTest {

    @Test
    void posetivTestConstructor() {
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
    void negativTestConstructor() {
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
    void testAttack() {

        Unit testObject = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        Unit testObject2 = new Unit("name", 10, 2, 1) {
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
        assertEquals(9, testObject2.getHealth());
    }

    @Test
    void testGetArmor() {
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
    void testGetAttack() {
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
    void testGetAttackBonus() {
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
    void testGetAndSetHealth() {
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
    void testGetName() {
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
    void testGetResistBonus() {
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
    void testToString() {
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

    // Can't test equals in this class since the reference point between the 2 testunit classes is "different"
    @Test
    void testHashcode() {
        Unit testUnit1 = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        Unit testUnit2 = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        
        assertEquals(testUnit1.hashCode(), testUnit2.hashCode());
    }

    @Test
    void testAttackWithTerraint() {
        Unit testUnit1 = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        testUnit1.putTerrainAttackBonus(Terrain.FOREST, 2);
        Unit testUnit2 = new Unit("name", 10, 2, 1) {
            @Override
            public int getAttackBonus() {
                return 0;
            }

            @Override
            public int getResistBonus() {
                return 0;
            }
        };
        testUnit1.attack(testUnit2,Terrain.FOREST);
        assertEquals(7, testUnit2.getHealth());
    }

    @Test
    void testGetTerrainAttackBonus() {
        
    }

    @Test
    void testGetTerrainDefenseBonus() {
        
    }

    @Test
    void testPutTerrainAttackBonus() {
        
    }

    @Test
    void testPutTerrainDefenceBonus() {
        
    }
}