package no.ntnu.mathijoh.wargame.models.units;

import java.util.HashMap;

/**
 * Base class for every Unit class.
 * 
 * @author Mathias Johansen
 * @version 2.0
 */
public abstract class Unit {

    private String name;
    private int health;
    private int attack;
    private int armor;
    private HashMap<Character, Integer> terrainAttackBonus;
    private HashMap<Character, Integer> terrainDefenseBonus;

    /**
     * Constructor of the Unit Class
     * 
     * @param name   of the unit
     * @param health to the unit
     * @param attack power of the unit
     * @param armor  of the unit
     * @throws IllegalArgumentException if any of the parameters is null
     */
    protected Unit(String name, int health, int attack, int armor, HashMap<Character, Integer> unitTerrainAttackBonus,
            HashMap<Character, Integer> unitTerrainDefenceBonus) throws IllegalArgumentException {
        if (!checkValidParameter(name)) {
            throw new IllegalArgumentException("Name can't be null or nothing");
        }
        if (!checkValidParameter(health)) {
            throw new IllegalArgumentException("Health can't be less then 0");
        }
        if (!checkValidParameter(attack)) {
            throw new IllegalArgumentException("Attack can't be less then 0");
        }
        if (!checkValidParameter(armor)) {
            throw new IllegalArgumentException("Armor can't be less then 0");
        }
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.health = health;
        this.terrainAttackBonus = new HashMap<>(unitTerrainAttackBonus);
        this.terrainDefenseBonus = new HashMap<>(unitTerrainDefenceBonus);
    }

    protected Unit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        if (!checkValidParameter(name)) {
            throw new IllegalArgumentException("Name can't be null or nothing");
        }
        if (!checkValidParameter(health)) {
            throw new IllegalArgumentException("Health can't be less then 0");
        }
        if (!checkValidParameter(attack)) {
            throw new IllegalArgumentException("Attack can't be less then 0");
        }
        if (!checkValidParameter(armor)) {
            throw new IllegalArgumentException("Armor can't be less then 0");
        }
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.health = health;
        this.terrainAttackBonus = new HashMap<>();
        this.terrainDefenseBonus = new HashMap<>();
    }

    /**
     * Returns the name of the unit
     * 
     * @return name of the unit
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the health of the unit
     * 
     * @return health of the unit
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the unit.
     * if the value is lower then 0, then sets it to 0
     * 
     * @param health the new health of the unit
     */
    public void setHealth(int health) {
        this.health = Math.max(health, 0);
    }

    /**
     * Returns the attack power of the unit
     * 
     * @return attack to the unit
     */
    public int getAttack() {
        return attack;
    }


    /**
     * Returns the armor of the unit
     * 
     * @return armor to the unit
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Returns the Attackbonus to the unit
     * 
     * @return attackBonus to the unit
     */
    public abstract int getAttackBonus();


    public abstract int getResistBonus();

    public void putTerrainAttackBonus(Character terrain, Integer bonus) throws IllegalArgumentException{
        this.terrainAttackBonus.put(terrain, bonus);
    }

    public void putTerrainDefenceBonus(Character terrain, Integer bonus) {
        this.terrainDefenseBonus.put(terrain, bonus);
    }

    public Integer getTerrainAttackBonus(Character terrain) {
        return terrainAttackBonus.get(terrain);
    }

    public Integer getTerrainDefeneceBonus(Character terrain) {
        return terrainAttackBonus.get(terrain);
    }

    /**
     * Calculates the damage done to the opponent and sets the health to it
     * 
     * @param opponent the opponent getting attacked
     * @throws IllegalArgumentException if opponent is null
     */
    public void attack(Unit opponent,Character terrain) throws IllegalArgumentException {
        if (!checkValidParameter(opponent)) {
            throw new IllegalArgumentException("Opponent can't be null");
        }
        int oHealth = opponent.getHealth();
        int oArmorBonus = opponent.getResistBonus();
        int attackBonus = this.getAttackBonus();
        int oHealthAfterAttack = oHealth - (this.getAttack() + attackBonus) + (opponent.getArmor() + oArmorBonus);
        if (oHealthAfterAttack < oHealth) {
            opponent.setHealth(oHealthAfterAttack);
        }
    }

    /**
     * Calculates the damage done to the opponent and sets the health to it
     * 
     * @param opponent the opponent getting attacked
     * @throws IllegalArgumentException if opponent is null
     */
    public void attack(Unit opponent) throws IllegalArgumentException {
        if (!checkValidParameter(opponent)) {
            throw new IllegalArgumentException("Opponent can't be null");
        }
        int oHealth = opponent.getHealth();
        int oArmorBonus = opponent.getResistBonus();
        int attackBonus = this.getAttackBonus();
        int oHealthAfterAttack = oHealth - (this.getAttack() + attackBonus) + (opponent.getArmor() + oArmorBonus);
        if (oHealthAfterAttack < oHealth) {
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
    /**
     * Checks if the parameter is valid
     * 
     * @param parameter the parameter to check
     * @return true if the parameter is valid, false otherwise
     */
    private boolean checkValidParameter(Object object) {
        boolean isValid = true;
        if (object == null) {
            isValid = false;
        } else {
            if (object.getClass() == String.class && ((String) object).isEmpty()) {
                isValid = false;
            }
        }
        return isValid;
    }
    /**
     * Checks if the parameter is valid
     * 
     * @param parameter the parameter to check
     * @return true if the parameter is valid, false otherwise
     */
    private boolean checkValidParameter(int number) {
        return (number > 0);
    }
}