package no.ntnu.mathijoh.wargame.models;

/**
 * Used for checking if a parameter is valid between classes
 * It should be removed from the project and instead use specific exceptions
 * within methods for better handling of errors or parameters
 * @deprecated
 * @author Mathias J. Kirkeby
 * @version 1.0
 */
@Deprecated
public class ParameterChecker {

    private ParameterChecker() {
    }

    /**
     * Checks if a parameter is valid
     * 
     * @param object the object to be checked
     * @return true if the parameter is valid
     */
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

    /**
     * Checks if a parameter is valid
     * 
     * @param number the number to be checked
     * @return true if the parameter is valid
     */
    public static boolean checkValidParameter(int number) {
        return (number > 0);
    }
}
