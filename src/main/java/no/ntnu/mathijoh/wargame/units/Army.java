package no.ntnu.mathijoh.wargame.units;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/** 
 * Army is a holder for units to be later used in the battle class
 */
public class Army {

    private String name;
    private List<Unit> units;
    private Random randomGenerator = new Random();
    
    /**
     * Creates a new army with no units
     * @param name The name of the army
     * @throws IllegalArgumentException if name is null
     */
    public Army(String name) throws IllegalArgumentException {
        if(name == null){
            throw new IllegalArgumentException("Name can't be null");
        }
        this.name = name;
        this.units = new ArrayList<>();
    }

    /**
     * Creates a new army and puts the units 
     * @param name The name of the army
     * @param units a list of units you want to include
     * @throws IllegalArgumentException if units or name i null
     */
    public Army(String name, List<Unit> units) throws IllegalArgumentException {
        if(name == null || units == null){
            throw new IllegalArgumentException("None of the parameter can be null");
        }
        this.name = name;
        this.units = new ArrayList<>(units);
    }

    /**
     * Adds a unit to the army
     * @param unit the unit that is being added
     * @exception IllegalArgumentException if unit is null
     */
    public void add(Unit unit) throws IllegalArgumentException{
        if(unit == null){
            throw new IllegalArgumentException("Unit can't be null");
        }
        units.add(unit);
    }

    /**
     * Adds multiple units in a list 
     * @param units a list of Units that is being added
     * @exception IllegalArgumentException if units is null
     */
    public void addAll(List<Unit> units) throws IllegalArgumentException {
        if(units == null){
            throw new IllegalArgumentException("Unit list can't be null");
            }
        Iterator<Unit> itUnits = units.iterator();
        while(itUnits.hasNext()){
            Unit currenUnit = itUnits.next();
            this.add(currenUnit);
        }
    }

    /**
     * Removes a unit from the army
     * @param unit the unit that is gonna be removed
     * @exception IllegalArgumentException if unit is null
     */
    public void remove(Unit unit) throws IllegalArgumentException {
        if (unit == null){
            throw new IllegalArgumentException("Unit can't be null");
        }
        this.units.remove(unit);
    }

    /**
     * @return true if it has units
     */
    public boolean hasUnits() {
        return !(units.isEmpty());
    }

    /**
     * Gets the list of every unit in the army
     * @return List<Unit> with every Unit in the army
     */
    public List<Unit> getAllUnits() {
        return units;
    }

    /**
     * Gets a random from the army
     * @return A unit
     * @exception IllegalStateException if the list is empty
     */
    public Unit getRandom() throws IllegalStateException {
        Unit returnUnit = null;
        if(units.isEmpty()){
            throw new IllegalStateException("There is no units in the Army");
        }
        int number = randomGenerator.nextInt(units.size());
        returnUnit = units.get(number);
        return returnUnit;
    }

    /**
     * Gets the name of the Army
     * @return String
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((units == null) ? 0 : units.hashCode());
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
        Army other = (Army) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (units == null) {
            if (other.units != null)
                return false;
        } else if (!units.equals(other.units))
            return false;
        return true;
    }
}
