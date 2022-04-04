package seedu.trackbeau.model.customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public abstract class Date {
    private static int DATE_LENGTH = 10; //dd-mm-yyyy

    static boolean isSatisfyDateRequirements(String test) {
        return isNotInFuture(test) && isNot29thFebruaryOnNonLeapYear(test);
    }

    static boolean isNotInFuture(String test) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate userInputDate = LocalDate.parse(test, formatter);
        LocalDate now = LocalDate.now();
        if (userInputDate.isAfter(now)) { //date cannot be in the future
            return false;
        }
        return true;
    }

    protected static boolean isNot29thFebruaryOnNonLeapYear(String test) {
        if (test.length() > DATE_LENGTH) {
            test = test.substring(0, DATE_LENGTH);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean isDay29 = test.substring(0, 2).equals("29");
        LocalDate userInputDate = LocalDate.parse(test, formatter);
        //if user inputs 29th Feb on a non leap year, local data automatically changes to 28th feb,
        // so we will throw error as user likely made a typo
        boolean isInput29February = isDay29 && userInputDate.getMonthValue() == 2;
        if (isInput29February && !userInputDate.isLeapYear()) {
            return false;
        }
        return true;
    }

}
