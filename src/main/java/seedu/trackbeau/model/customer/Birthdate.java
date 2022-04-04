package seedu.trackbeau.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a customer's birthday in TrackBeau.
 */
public class Birthdate extends Date {
    public static final String MESSAGE_CONSTRAINTS = "Birthdate should follow dd-MM-yyyy and be valid date."
            + "Birthdate cannot be in the future.";
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
            LocalDate userInputDate = LocalDate.parse(test, formatter);
            if (!isSatisfyDateRequirements(test)) {
                return false;
            }
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
