package seedu.trackbeau.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.trackbeau.model.customer.Date;

public class BookingDateTime extends Date {
    public static final String MESSAGE_CONSTRAINTS =
            "BookingDateTime should follow dd-MM-yyyy HH:mm, and it should not be blank";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public final LocalDateTime value;

    /**
     * Constructs an {@code BookingDateTime}.
     *
     * @param bookingDateTime A valid BookingDateTime.
     */
    public BookingDateTime(String bookingDateTime) {
        requireNonNull(bookingDateTime);
        checkArgument(isValidBookingDateTime(bookingDateTime), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(bookingDateTime, formatter);
        requireNonNull(value);
    }

    /**
     * Returns true if a given string is a valid BookingDateTime.
     */
    public static boolean isValidBookingDateTime(String test) {
        try {
            LocalDateTime.parse(test, formatter);
            if (!isNot29thFebruaryOnNonLeapYear(test)) {
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
                || (other instanceof BookingDateTime // instanceof handles nulls
                && value.equals(((BookingDateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
