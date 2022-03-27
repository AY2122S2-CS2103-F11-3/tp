package seedu.trackbeau.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Birthdate {
    public static final String EMPTY_DATE = "01-01-1000"; //impossible date
    public static final String MESSAGE_CONSTRAINTS = "Birthdate should follow dd-mm-yyyy and be valid date.";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final LocalDate value;


    /**
     * Constructs an {@code Birthdate}.
     *
     * @param birthDate A valid Birthdate.
     */
    public Birthdate(String birthDate) {
        requireNonNull(birthDate);
        checkArgument(isValidBirthdate(birthDate), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(birthDate, formatter);
        requireNonNull(value);
    }

    /**
     * Returns true if a given string is a valid Birthdate.
     */
    public static boolean isValidBirthdate(String test) {
        try {
            LocalDate.parse(test, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return formatter.format(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthdate // instanceof handles nulls
                && value.equals(((Birthdate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
