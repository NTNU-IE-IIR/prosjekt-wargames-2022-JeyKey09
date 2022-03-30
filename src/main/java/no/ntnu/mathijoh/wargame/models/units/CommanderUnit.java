package no.ntnu.mathijoh.wargame.models.units;

public class CommanderUnit extends CavalryUnit {

    /**
     * Constructor for CommanderUnit class
     * 
     * @param name   of the Unit
     * @param health how much health it had
     * @param attack of the Unit
     * @param armor  of the Unit
     * @throws IllegalArgumentException if any arguments are null
     */
    public CommanderUnit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        super(name, health, attack, armor);
    }

    /**
     * Costructor for Commander Unit that gives it 25 in attack and 15 in armor
     * 
     * @param name   of the Unit
     * @param health how much health it has
     * @throws IllegalArgumentException if any arguments are null
     */
    public CommanderUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 25, 15);
    }

}
