package no.ntnu.mathijoh.wargame.models;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.mathijoh.wargame.models.units.Unit;

public class UnitList extends ArrayList<Unit>{
    
    private String unitType;

    public UnitList(String unitType){
        super();
        this.unitType = unitType;
    }
    
    public UnitList(String unitType, List<Unit> units){
        super(units);
        this.unitType = unitType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

}
