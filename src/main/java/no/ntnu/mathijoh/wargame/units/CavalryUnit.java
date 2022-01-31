package no.ntnu.mathijoh.wargame.units;

public class CavalryUnit extends Unit{

    private boolean firstAttack = true;

    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }
    
    public CavalryUnit(String name, int health) {
        super(name, health, 20, 12);
    }

    @Override
    public int getAttackBonus() {
        int attackBonus;
        if(firstAttack){
            attackBonus = 6;
            this.firstAttack = false;
        } else {
            attackBonus = 2;
        }
        return attackBonus;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
