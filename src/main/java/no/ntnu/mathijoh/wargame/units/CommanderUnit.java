package no.ntnu.mathijoh.wargame.units;

public class CommanderUnit extends CavalryUnit{

    public CommanderUnit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        super(name, health, attack, armor);
    }

    public CommanderUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 25, 15);
    }
    
}
