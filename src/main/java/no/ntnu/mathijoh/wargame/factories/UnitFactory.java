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

    // The different types of units that can be created
    //TODO: Add getFromName() method to UnitType
    public enum UnitType {
        INFANTRYUNIT("InfantryUnit"),
        RANGEDUNIT("RangedUnit"),
        CAVALRYUNIT("CavalryUnit"),
        COMMANDERUNIT("CommanderUnit");

        private String name;

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
}
