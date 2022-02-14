package no.ntnu.mathijoh.wargame.units;

public class RangedUnit extends Unit{
    
    private int amountOfAttacks = 0;

    public RangedUnit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        super(name, health, attack, armor);
    }

    public RangedUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 15, 8);
    }
    
    @Override
    public int getAttackBonus() {
        return 3;
    }

    @Override
    public int getResistBonus() {
        int resist;
        switch(amountOfAttacks){
            case 0:
                resist = 6;
                break;
            case 1:
                resist = 4;
                break;
            default:
                resist = 2;
        }
        amountOfAttacks += 1;
        return resist;
    }
}
