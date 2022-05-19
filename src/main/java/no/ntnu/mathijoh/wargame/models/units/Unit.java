package no.ntnu.mathijoh.wargame.models.units;

import java.util.HashMap;

import no.ntnu.mathijoh.wargame.models.map.Terrain;

/**
 * Base class for every Unit class.
 * 
 * @author Mathias Johansen
 * @version 2.0
 */
public abstract class Unit {
    
    //TODO: Implement range and movement cost

    private String name;
    private int health;
    private int attack;
    private int armor;
    private HashMap<Terrain, Integer> terrainAttackBonus;
    private HashMap<Terrain, Integer> terrainDefenseBonus;

    /**
     * Constructor of the Unit Class
     * Takes in the name, health, attack and armor of the unit
     * Also 2 HashMaps with the terrain and the bonus for attack and defense
     * 
     * @param name                of the unit
     * @param health              to the unit
     * @param attack              power of the unit
     * @param armor               of the unit
     * @param unitTerrainAttackBonus  of the unit
     * @param unitTerrainDefenceBonus of the unit
     * @throws IllegalArgumentException if any of the parameters is null
     */
    protected Unit(String name, int health, int attack, int armor, HashMap<Terrain, Integer> unitTerrainAttackBonus,
            HashMap<Terrain, Integer> unitTerrainDefenceBonus) throws IllegalArgumentException {
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
        if (!checkValidParameter(unitTerrainAttackBonus)) {
            throw new IllegalArgumentException("TerrainAttackBonus can't be null");
        }
        if (!checkValidParameter(unitTerrainDefenceBonus)) {
            throw new IllegalArgumentException("TerrainDefenseBonus can't be null");
        }
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.health = health;
        this.terrainAttackBonus = new HashMap<>(unitTerrainAttackBonus);
        this.terrainDefenseBonus = new HashMap<>(unitTerrainDefenceBonus);
    }

    /**
     * Constructor of the Unit Class
     * Takes in the name, health, attack and armor of the unit
     * Creates 2 blank hashmap for the terrain attack and defense bonus
     * 
     * @param name   of the unit
     * @param health to the unit
     * @param attack power of the unit
     * @param armor  of the unit
     * @throws IllegalArgumentException if any of the parameters is null
     */
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

    /**
     * Returns the Defensebonus to the unit
     * 
     * @return defenseBonus to the unit
     */
    public abstract int getResistBonus();

    /**
     * Puts the terrain and the bonus in the hashmap
     * 
     * @param terrain the terrain
     * @param bonus   the bonus
     * @throws IllegalArgumentException if any of the parameters is null
     */
    public void putTerrainAttackBonus(Terrain terrain, int bonus) throws IllegalArgumentException {
        if (terrain == null) {
            throw new IllegalArgumentException("Terrain can't be null");
        }
        this.terrainAttackBonus.put(terrain, bonus);
    }

    /**
     * Puts the terrain and the defencebonus in the hashmap
     * 
     * @param terrain the terrain
     * @param bonus   the bonus
     * @throws IllegalArgumentException if any of the parameters is null
     */
    public void putTerrainDefenceBonus(Terrain terrain, int bonus) {
        if (terrain == null) {
            throw new IllegalArgumentException("Terrain can't be null");
        }
        this.terrainDefenseBonus.put(terrain, bonus);
    }

    /**
     * Returns the terrainAttackBonus of the terrain
     * 
     * @param terrain the terrain to fetch the bonus from
     * @return the bonus to the terrain or 0 if the terrain is not specified
     */
    public Integer getTerrainAttackBonus(Terrain terrain) {
        Integer bonus = this.terrainAttackBonus.get(terrain);
        if (bonus == null) {
            bonus = 0;
        }
        return bonus;
    }

    /**
     * Returns the terrain Defense Bonus of the terrain
     * 
     * @param terrain the terrain to fetch the bonus from
     * @return the bonus to the terrain or 0 if the terrain is not specified
     */
    public Integer getTerrainDefenseBonus(Terrain terrain) {
        Integer bonus = this.terrainDefenseBonus.get(terrain);
        if (bonus == null) {
            bonus = 0;
        }
        return bonus;
    }
    

    /**
     * Calculates the damage done to the opponent and sets the health to it
     * 
     * @param opponent the opponent getting attacked
     * @param unitsTerrain  the terrain the unit is on
     * @param opponentsTerrains the terrain the opponent is on
     * @throws IllegalArgumentException if opponent is null or the terrain is null
     */
    public void attack(Unit opponent, Terrain unitsTerrain, Terrain opponentsTerrains) throws IllegalArgumentException {
        if (!checkValidParameter(opponent)) {
            throw new IllegalArgumentException("Opponent can't be null");
        }
        if (unitsTerrain == null || opponentsTerrains == null) {
            throw new IllegalArgumentException("Terrain can't be null");
        }
        int oHealth = opponent.getHealth();
        int oArmorBonus = opponent.getResistBonus();
        int attackBonus = this.getAttackBonus();
        int oHealthAfterAttack = oHealth - (this.getAttack() + attackBonus + this.getTerrainAttackBonus(unitsTerrain))
                + (opponent.getArmor() + oArmorBonus + opponent.getTerrainDefenseBonus(opponentsTerrains));
        if (oHealthAfterAttack < oHealth) {
            opponent.setHealth(oHealthAfterAttack);
        }
    }

        /**
     * Calculates the damage done to the opponent and sets the health to it
     * 
     * @param opponent the opponent getting attacked
     * @param terrain  the terrain the unit is on
     * @throws IllegalArgumentException if opponent is null or the terrain is null
     */
    public void attack(Unit opponent, Terrain terrain) throws IllegalArgumentException {
        attack(opponent, terrain, terrain);
    }

    /**
     * Calculates the damage done to the opponent and sets the health to it
     * 
     * @param opponent the opponent getting attacked
     * @throws IllegalArgumentException if opponent is null
     */
    public void attack(Unit opponent) throws IllegalArgumentException {
        attack(opponent, Terrain.NONE, Terrain.NONE);
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