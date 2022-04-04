package seedu.trackbeau.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a customer's registration date as a member in TrackBeau.
 */
public class RegistrationDate extends Date {
    public static final String MESSAGE_CONSTRAINTS = "RegistrationDate should follow dd-MM-yyyy and be valid date.";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String EMPTY_DATE = "01-01-1000"; //impossible date
    public final LocalDate value;

    /**
     * Constructs an {@code RegistrationDate}.
     *
     * @param regDate A valid RegistrationDate.
     */
    public RegistrationDate(String regDate) {
        requireNonNull(regDate);
        checkArgument(isValidRegistrationDate(regDate), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(regDate, formatter);
        requireNonNull(value);
    }

    /**
     * Returns true if a given string is a valid RegistrationDate.
     */
    public static boolean isValidRegistrationDate(String test) {
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
        if (this.equals(new RegistrationDate(EMPTY_DATE))) {
            return "Registration data not available.";
        } else {
            return formatter.format(value);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RegistrationDate // instanceof handles nulls
                && value.equals(((RegistrationDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
