package no.ntnu.mathijoh.wargame.units;

/**
 * Base class for every Unit class. 
 */
public abstract class Unit {

    private String name;
    private int health;
    private int attack;
    private int armor;
    
    /**
     * Constructor of the Unit Class
     * @param name of the unit
     * @param health to the unit
     * @param attack power of the unit
     * @param armor of the unit
     * @throws IllegalArgumentException if name is null
     */
    
    protected Unit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        if(name != null){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can't be null");
        }
        
        setHealth(health);

        if(attack >= 0){
            this.attack = attack;
        }
        if(armor >= 0){
            this.armor = armor;
        }
    }

    /**
     * @return name of the unit
     */
    public String getName() {
        return name;
    }

    /**
     * @return health of the unit
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the unit. 
     * if the value is lower then 0, then sets it to 0 
     * @param health the new health of the unit
     */
    public void setHealth(int health) {
        if(health < 0 ){
            this.health = 0;
        } else {
            this.health = health;
        }
    }

    /**
     * @return attack to the unit
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @return armor to the unit
     */
    public int getArmor() {
        return armor;
    }

    /**
     * @return attackBonus to the unit
     */
    public abstract int getAttackBonus();

    /**
     * @return resistBonus to the unit
     */
    public abstract int getResistBonus();

    /**
     * Calculates the damage done to the opponent and sets the health to it
     * @param opponent the opponent getting attacked
     * @exception IllegalArgumentException if opponent is null
     */
    public void attack(Unit opponent) throws IllegalArgumentException{
        if(opponent != null){
            int oHealth = opponent.getHealth();
            int oArmorBonus = opponent.getResistBonus(); 
            int attackBonus = this.getAttackBonus();
            int oHealthAfterAttack = oHealth - (this.getAttack() + attackBonus) + (opponent.getArmor() + oArmorBonus);
            if (oHealthAfterAttack < oHealth){
                opponent.setHealth(oHealthAfterAttack);
            }
        }else {
            throw new IllegalArgumentException("Opponent can't be null");
        }
    }

    /**
     * Converts the object to String and returns it
     * @return String of the variables to unit
     */
    @Override
    public String toString() {
        return "Unit [armor=" + armor + ", attack=" + attack + ", health=" + health + ", name=" + name + "]";
    }
    
    
}