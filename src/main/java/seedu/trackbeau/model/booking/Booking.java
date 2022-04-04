package seedu.trackbeau.model.booking;

import static seedu.trackbeau.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.service.Service;
import seedu.trackbeau.model.service.ServiceName;
import seedu.trackbeau.model.uniquelist.UniqueListItem;

/**
 * Represents a Booking in trackBeau.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking implements UniqueListItem, Comparable<Booking> {

    private final Customer customer;
    private final Service service;
    private final BookingDateTime bookingDateTime;
    private final Feedback feedback;

    /**
     * Every field must be present and not null.
     */
    public Booking(Customer customer, Service service, BookingDateTime bookingDateTime, Feedback feedback) {
        requireAllNonNull(customer, service, bookingDateTime, feedback);
        this.customer = customer;
        this.service = service;
        this.bookingDateTime = bookingDateTime;
        this.feedback = feedback;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Service getService() {
        return service;
    }

    public Name getCustomerName() {
        return customer.getName();
    }

    public Phone getCustomerPhone() {
        return customer.getPhone();
    }

    public ServiceName getServiceName() {
        return service.getName();
    }

    public BookingDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public LocalDateTime getBookingEndTime() {
        return bookingDateTime.value.plusMinutes(service.getDuration().value);
    }

    public Feedback getFeedback() {
        return feedback;
    }

    /**
     * Returns true if both bookings is the same as define by {@code equals}.
     */
    @Override
    public boolean isSameItem(UniqueListItem otherBooking) {
        return this.equals(otherBooking);
    }

    @Override
    public int compareTo(Booking b) {
        return bookingDateTime.value.compareTo(b.bookingDateTime.value);
    }

    /**
     * Returns true if both bookings have the same fields.
     * This defines a strong notion of equality between two bookings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Booking)) {
            return false;
        }

        Booking otherBooking = (Booking) other;
        return otherBooking.getCustomer().equals(getCustomer())
                && otherBooking.getService().equals(getService())
                && otherBooking.getBookingDateTime().equals(getBookingDateTime());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCustomerName())
                .append("; Phone: ")
                .append(getCustomerPhone())
                .append("; Service: ")
                .append(getServiceName())
                .append("; Appointment DateTime: ")
                .append(getBookingDateTime());

        return builder.toString();
    }
}
