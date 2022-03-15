package no.ntnu.mathijoh.wargame.models;

public class ParameterChecker {
    
    private ParameterChecker() {
    }

    public static boolean checkValidParameter(Object object) {
        boolean isValid = true;
        if (object == null) {
            isValid = false;
        } else {
            if (object.getClass() == String.class && ((String) object).isEmpty()) {
                isValid = false;
            }
        }
        return isValid;
    }

    public static boolean checkValidParameter(int number) {
        return (number > 0);
    }
}
