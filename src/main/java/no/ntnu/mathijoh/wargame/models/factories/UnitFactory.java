package no.ntnu.mathijoh.wargame.factories;

import java.util.List;

import no.ntnu.mathijoh.wargame.models.UnitList;
import no.ntnu.mathijoh.wargame.models.units.CavalryUnit;
import no.ntnu.mathijoh.wargame.models.units.CommanderUnit;
import no.ntnu.mathijoh.wargame.models.units.InfantryUnit;
import no.ntnu.mathijoh.wargame.models.units.RangedUnit;
import no.ntnu.mathijoh.wargame.models.units.Unit;

public class UnitFactory {

    private UnitFactory(){}
    
    /**
     * 
     * @param unitType
     * @param unitName
     * @param unitHealth
     * @return
     */
    public Unit createUnit(String unitType, String unitName, Integer unitHealth) {
        Unit unit;
        switch (unitType) {
            case "Infantry":
                unit = new InfantryUnit(unitName, unitHealth);
                break;
            case "Ranged":
                unit = new RangedUnit(unitName, unitHealth);
                break;
            case "Cavalry":
                unit = new CavalryUnit(unitName, unitHealth);
                break;
            case "Commander":
                unit = new CommanderUnit(unitName, unitHealth);
                break;
            default:
                unit = null;
                break;
        }
        return unit;
    }
    /**
     * 
     * @param unitType
     * @param unitName
     * @param unitHealth
     * @param amountOfUnit
     * @return
     */
    public List<Unit> createUnit(String unitType, String unitName, Integer unitHealth, Integer amountOfUnit) {
        UnitList unit;
        switch (unitType) {
            case "Infantry":
                unit = new UnitList("InfantryUnit");
                for(int i = 0; i < amountOfUnit; i++ ) {
                    unit.add(new InfantryUnit(unitName, unitHealth));
                }
                break;
            case "Ranged":
                unit = new UnitList("RangedUnit");
                for(int i = 0; i < amountOfUnit; i++ ) {
                    unit.add(new RangedUnit(unitName, unitHealth));
                }
                break;
            case "Cavalry":
                unit = new UnitList("CavalryUnit");
                for(int i = 0; i < amountOfUnit; i++ ) {
                    unit.add(new CavalryUnit(unitName, unitHealth));
                }
                break;
            case "Commander":
                unit = new UnitList("CommanderUnit");
                for(int i = 0; i < amountOfUnit; i++ ) {
                    unit.add(new CommanderUnit(unitName, unitHealth));
                }
                break;
            default:
                unit = null;
                break;
        }
        return unit;
    }
}
