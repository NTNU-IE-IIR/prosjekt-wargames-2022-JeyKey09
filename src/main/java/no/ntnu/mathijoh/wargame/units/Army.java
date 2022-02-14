package no.ntnu.mathijoh.wargame.units;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
public class Army {

    private String name;
    private List<Unit> units;
    private Random randomGenerator = new Random();

    public Army(String name) {
        if(name != null){
            this.name = name;
        }
        this.units = new ArrayList<>();
    }

    public Army(String name, List<Unit> units) {
        this.name = name;
        this.units = new ArrayList<>(units);
    }

    public void add(Unit unit) {
        if(unit != null){
            units.add(unit);
        }
    }

    public void addAll(List<Unit> units) {
        if(units != null){
            Iterator<Unit> itUnits = units.iterator();
            while(itUnits.hasNext()){
                Unit currenUnit = itUnits.next();
                this.add(currenUnit);
            }
        }
    }
    
    public void remove(Unit unit) {

    }

    public boolean hasUnits() {
        return !(units.isEmpty());
    }

    public List<Unit> getAllUnits() {
        return units;
    }

    public Unit getRandom() {
        Unit returnUnit = null;
        if(!units.isEmpty()){
            int number = randomGenerator.nextInt(units.size());
            returnUnit = units.get(number);
        }
        return returnUnit;
    }

    public String getName() {
        return name;
    }


    
}
