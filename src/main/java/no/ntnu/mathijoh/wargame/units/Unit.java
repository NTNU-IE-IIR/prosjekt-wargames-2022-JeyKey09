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
     * @throws IllegalArgumentException if any of the parameters is null 
     */
    
    protected Unit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name can't be null or nothing");
        }
        if(attack < 0){
            throw new IllegalArgumentException("Attack can't be less then 0");
        }
        if(armor < 0){
            throw new IllegalArgumentException("Armor can't be less then 0");
        }
        if(health < 0){
            throw new IllegalArgumentException("Health can't be less then 0");
        }
        
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.health = health;
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
        if(opponent == null){
            throw new IllegalArgumentException("Opponent can't be null");
        }
        int oHealth = opponent.getHealth();
        int oArmorBonus = opponent.getResistBonus(); 
        int attackBonus = this.getAttackBonus();
        int oHealthAfterAttack = oHealth - (this.getAttack() + attackBonus) + (opponent.getArmor() + oArmorBonus);
        if (oHealthAfterAttack < oHealth){
            opponent.setHealth(oHealthAfterAttack);
        }
    }

    @Override
    public String toString() {
        return "Unit [armor=" + armor + ", attack=" + attack + ", health=" + health + ", name=" + name + "]";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + armor;
        result = prime * result + attack;
        result = prime * result + health;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Unit other = (Unit) obj;
        if (armor != other.armor)
            return false;
        if (attack != other.attack)
            return false;
        if (health != other.health)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}