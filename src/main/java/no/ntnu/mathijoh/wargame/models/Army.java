package no.ntnu.mathijoh.wargame.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Collectors;

import no.ntnu.mathijoh.wargame.factories.UnitFactory;
import no.ntnu.mathijoh.wargame.models.units.CavalryUnit;
import no.ntnu.mathijoh.wargame.models.units.CommanderUnit;
import no.ntnu.mathijoh.wargame.models.units.InfantryUnit;
import no.ntnu.mathijoh.wargame.models.units.RangedUnit;
import no.ntnu.mathijoh.wargame.models.units.Unit;

/**
 * Army is a holder for units to be later used in the battle class
 */
public class Army {

    private String name;
    private List<Unit> units;
    private Random randomGenerator = new Random();

    /**
     * Creates a new army with no units
     * 
     * @param name The name of the army
     * @throws IllegalArgumentException if name is null or empty
     */
    public Army(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        this.name = name;
        this.units = new ArrayList<>();
    }

    /**
     * Creates a new army and puts the units
     * 
     * @param name  The name of the army
     * @param units a list of units you want to include
     * @throws IllegalArgumentException if units or name is null
     */
    public Army(String name, List<Unit> units) throws IllegalArgumentException {
        if (name == null || units == null || units.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("None of the parameter can be null");
        }
        this.name = name;
        this.units = new ArrayList<>(units);
    }

    /**
     * Duplicates the army given into a new army object
     * @param army The army you want to duplicate
     * @throws IllegalArgumentException if army is null
     */
    public Army(Army army)  throws IllegalArgumentException {
        if (army == null) {
            throw new IllegalArgumentException("Army can't be null");
        }
        this.name = army.name;
        this.units = new ArrayList<>();
        army.getAllUnits().forEach(unit -> this.units.add(UnitFactory.copyUnit(unit)));
    }

    private Iterator<Unit> getIterator() {
        return units.iterator();
    }

    /**
     * Adds a unit to the army
     * 
     * @param unit the unit that is being added
     * @throws IllegalArgumentException if unit is null
     */
    public void add(Unit unit) throws IllegalArgumentException {
        if (unit == null) {
            throw new IllegalArgumentException("Unit can't be null");
        }
        units.add(unit);
    }

    /**
     * Adds multiple units in a list
     * 
     * @param units a list of Units that is being added
     * @throws IllegalArgumentException if units is null
     */
    public void addAll(List<Unit> units) throws IllegalArgumentException {
        if (units == null || units.isEmpty()) {
            throw new IllegalArgumentException("Unit list can't be null or empty");
        }
        units.stream().forEach(unit -> this.units.add(unit));
    }

    /**
     * Removes a unit from the army
     * 
     * @param unit the unit that is gonna be removed
     * @throws IllegalArgumentException if unit is null
     */
    public void remove(Unit unit) throws IllegalArgumentException {
        if (unit == null) {
            throw new IllegalArgumentException("Unit can't be null");
        }
        if (!this.units.remove(unit)) {
            throw new IllegalArgumentException("Unit was not in Army");
        }
    }

    /**
     * Returns if it has units or not
     * 
     * @return true if it has units
     */
    public boolean hasUnits() {
        return !(units.isEmpty());
    }

    /**
     * Returns the size of the army
     * 
     * @return int size of the army
     */
    public int getSize() {
        return this.units.size();
    }

    /**
     * Gets the list of every unit in the army
     * 
     * @return List with with every Unit in the army
     */
    public List<Unit> getAllUnits() {
        return units;
    }

    /**
     * Gets a random unit from the army
     * 
     * @return A unit
     * @throws IllegalStateException if the list is empty
     */
    public Unit getRandom() throws IllegalStateException {
        Unit returnUnit;
        if (units.isEmpty()) {
            throw new IllegalStateException("There is no units in the Army");
        }
        int number = randomGenerator.nextInt(units.size());
        returnUnit = units.get(number);
        return returnUnit;
    }

    /**
     * Gets the every cavalry unit the army
     * 
     * @return a list of cavalry units in the army
     */
    public List<Unit> getCavalryUnits() {
        return getUnits(CavalryUnit.class);
    }

    /**
     * Gets the every infantry unit the army
     * 
     * @return a list of infantry units in the army
     */
    public List<Unit> getInfantryUnits() {
        return getUnits(InfantryUnit.class);
    }

    /**
     * Gets the every cavalry unit the army
     * 
     * @return a list of ranged units in the army
     */
    public List<Unit> getRangedUnits() {
        return getUnits(RangedUnit.class);
    }

    /**
     * Gets the every commander unit the army
     * 
     * @return a list of commander units in the army
     */
    public List<Unit> getCommanderUnits() {
        return getUnits(CommanderUnit.class);
    }

    /**
     * A private function meant to filter out some units
     * 
     * @param unitClass the unit class that the person wants to filter
     * @return a list of the specified class of unit
     */
    private List<Unit> getUnits(Class unitClass) {
        return units.stream().filter(unit -> unit.getClass() == unitClass).collect(Collectors.toList());
    }

    /**
     * Gets the name of the Army
     * 
     * @return String name of the army
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the army
     * @param newName the new name of the army
     * @throws IllegalArgumentException if newName is null or empty
     */
    public void setName(String newName) throws IllegalArgumentException {
        if(newName == null || newName.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        this.name = newName;
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