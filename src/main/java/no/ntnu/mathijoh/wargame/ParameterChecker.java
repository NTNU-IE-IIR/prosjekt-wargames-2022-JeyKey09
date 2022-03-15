package no.ntnu.mathijoh.wargame;

public class ParameterChecker {
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

    private boolean checkValidParameter(int number) {
        return (number > 0);
    }
}
