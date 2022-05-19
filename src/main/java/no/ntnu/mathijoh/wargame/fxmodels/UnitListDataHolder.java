package no.ntnu.mathijoh.wargame.fxmodels;

import java.util.List;

import no.ntnu.mathijoh.wargame.models.units.Unit;
/**
 * This class is used to hold the data for the ArmyTableView
 * @author Mathias J. Kirkeby
 * @version 1.0
 */
public class UnitListDataHolder{
    private List<Unit> units;
    private String unitType;

    /**
     * Constructor of the UnitListDataHolder
     * @param unitType the type of unit
     * @param units  the units of the type
     * @throws IllegalArgumentException if any arguments are null
     */
    public UnitListDataHolder(String unitType, List<Unit> units)throws IllegalArgumentException{
        setUnitType(unitType);
        if(units == null){
            throw new IllegalArgumentException("Units cannot be null");
        }        
        this.units = units;
    }
    /**
     * Gets the unitType
     * @return the unitType
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * Sets the unitType
     * @param unitType the unitType to set
     * @throws IllegalArgumentException if unitType is null
     */
    public void setUnitType(String unitType) throws IllegalArgumentException {
        if(unitType == null || unitType.isEmpty()){
            throw new IllegalArgumentException("Unit type cannot be null");
        }
        this.unitType = unitType;
    }

    /**
     * Gets the units count
     * @return the units count
     */
    public int getUnitCount() {
        return units.size();
    }

    /**
     * Removes the dead units from the list
     */
    public void removeDeadUnits() {
        units.removeIf(unit -> unit.getHealth() == 0);
    }
}
