package no.ntnu.mathijoh.wargame.units;

/**
 * Baseclass for every Unit class. 
 */
public class Unit {

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
     */
    public Unit(String name, int health, int attack, int armor) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
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
    public int getAttackBonus() {
        return 0;
    }

    /**
     * Will be used on the 
     * @return resistBonus to the unit
     */
    public int getResistBonus() {
        return 0;
    }

    /**
     * Calculates the damage done to the opponent and sets the health to it
     * @param opponent the opponent getting attacked
     */
    public void attack(Unit opponent) {
        int oHealth = opponent.getHealth();
        int oArmorBonus = opponent.getResistBonus(); 
        int attackBonus = this.getAttackBonus();
        int oHealthAfterAttack = oHealth - (this.getAttack() + attackBonus) + (opponent.getArmor() + oArmorBonus);
        if (oHealthAfterAttack < oHealth){
            opponent.setHealth(oHealthAfterAttack);
        }
    }
    
}