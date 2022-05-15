package no.ntnu.mathijoh.wargame.fxmodels;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.mathijoh.wargame.models.units.Unit;

public class UnitListDataHolder{
    private List<Unit> units;
    private String unitType;

    public UnitListDataHolder(String unitType){
        this.unitType = unitType;
        units = new ArrayList<>();
    }
    
    public UnitListDataHolder(String unitType, List<Unit> units){
        this.unitType = unitType;
        this.units = units;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public int getUnitCount() {
        return units.size();
    }

    public void removeDeadUnits() {
        units.removeIf(unit -> unit.getHealth() == 0);
    }
}
