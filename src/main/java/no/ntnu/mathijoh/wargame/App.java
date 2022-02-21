package no.ntnu.mathijoh.wargame;

import no.ntnu.mathijoh.wargame.units.Army;
import no.ntnu.mathijoh.wargame.units.InfantryUnit;
import no.ntnu.mathijoh.wargame.units.Unit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      Army testArmy = new Army("Test");
      Army testArmy2 = new Army("Test");
      if (testArmy.equals(testArmy2)) {
        System.out.println("It works");
      }
      Unit testUnit = new InfantryUnit("name", 10);
      testArmy.add(testUnit);
      testArmy2.add(testUnit);
      if (testArmy.equals(testArmy2)) {
        System.out.println("This also works");
      }

      Unit testUnit01 = new InfantryUnit("name", 10);
      Unit testUnit02 = new InfantryUnit("name", 10);
      testArmy.add(testUnit01);
      testArmy2.add(testUnit02);
      if (testArmy.equals(testArmy2)) {
        System.out.println("This also also works");
      }
      if (testUnit01.equals(testUnit02)){
        System.out.println("These are the same");
      }
    }
}
