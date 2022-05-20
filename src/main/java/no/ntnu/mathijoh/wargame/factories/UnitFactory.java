package no.ntnu.mathijoh.wargame.factories;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.mathijoh.wargame.models.units.Unit;

/**
 * UnitFactory is a class that creates units.
 * It is used by FileController to create units from a file.
 * 
 * 
 * @author Mathias Johansen
 * @version 1.0
 * 
 */
public class UnitFactory {

    private UnitFactory() {
    }

    /**
     * The different unit types that can be created
     */
    public enum UnitType {
        /**
         * Infantry unit
         */
        INFANTRYUNIT("InfantryUnit"),
        /**
         * Ranged unit
         */
        RANGEDUNIT("RangedUnit"),
        /**
         * Cavalry unit
         */
        CAVALRYUNIT("CavalryUnit"),
        /**
         * Commander unit
         */
        COMMANDERUNIT("CommanderUnit");

        private String name;

        /**
         * Constructor for UnitType
         * @param name The name of the unit type
         */
        private UnitType(String name) {
            this.name = name;
        }

        /**
         * Returns the name of the unit type
         * 
         * @return the name of the unit type
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the unit type with the given name
         * 
         * @param name the name of the unit type
         * @return the unit type with the given name or null if no unit type with the
         *         given name exists
         * @throws IllegalArgumentException if the name is null or empty
         */
        public static UnitType getUnitTypeFromName(String name) throws IllegalArgumentException {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            UnitType[] types = UnitType.values();
            UnitType type = null;
            for (int i = 0; i < types.length && type == null; i++) {
                if (types[i].getName().equalsIgnoreCase(name)) {
                    type = types[i];
                }
            }
            return type;
        }
    }

    /**
     * Creates a Unit based on the UnitType
     * 
     * @param unitType   of the unit
     * @param unitName   of the unit
     * @param unitHealth of the unit
     * @return a new unit of the specefied type
     * @throws IllegalArgumentException if any of the parameters is null
     */
    public static Unit createUnit(UnitType unitType, String unitName, Integer unitHealth)
            throws IllegalArgumentException {
        Unit returnUnit = null;
        if (unitType == null || unitName == null || unitHealth == null) {
            throw new IllegalArgumentException("UnitType, unitName and unitHealth can't be null");
        }
        if (unitHealth < 0) {
            throw new IllegalArgumentException("UnitHealth can't be less then 0");
        }
        try {
            returnUnit = (Unit) Class.forName("no.ntnu.mathijoh.wargame.models.units." + unitType.getName())
                    .getConstructor(String.class, int.class).newInstance(unitName, unitHealth);
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't create unit of type " + unitType.getName());
        }
        return returnUnit;

    }

    /**
     * Creates a list of Units based on the UnitType
     * 
     * @param unitType     of the unit
     * @param unitName     of the unit
     * @param unitHealth   of the unit
     * @param amountOfUnit to create
     * @return a list of units
     * @throws IllegalArgumentException if any of the parameters is null
     */
    public static List<Unit> createUnitList(UnitType unitType, String unitName, Integer unitHealth,
            Integer amountOfUnit) throws IllegalArgumentException {
        if (unitType == null) {
            throw new IllegalArgumentException("UnitType can't be null");
        }
        if (amountOfUnit <= 0) {
            throw new IllegalArgumentException("AmountOfUnit can't be less or equal 0");
        }

        ArrayList<Unit> unitList = new ArrayList<>();

        try {
            for (int i = 0; i < amountOfUnit; i++) {
                unitList.add(createUnit(unitType, unitName, unitHealth));
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return unitList;
    }

    /**
     * Copies a unit
     * @param unit the unit to copy
     * @return a copy of the unit
     */
    public static Unit copyUnit(Unit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit can't be null");
        }
        return createUnit(UnitType.getUnitTypeFromName(unit.getClass().getSimpleName()), unit.getName(), unit.getHealth());
    }
}
