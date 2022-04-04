package seedu.trackbeau.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.exceptions.IllegalValueException;
import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.booking.BookingDateTime;
import seedu.trackbeau.model.booking.Feedback;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.service.Service;

/**
 * Jackson-friendly version of {@link Booking}.
 */
class JsonAdaptedBooking {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private final Integer customerIndex;
    private final Integer serviceIndex;
    private final String bookingDateTime;
    private final String feedback;

    /**
     * Constructs a {@code JsonAdaptedBooking} with the given Booking details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("nameIndex") Integer customerIndex,
                              @JsonProperty("serviceIndex") Integer serviceIndex,
                              @JsonProperty("bookingDateTime") String bookingDateTime,
                              @JsonProperty("feedback") String feedback) {
        this.customerIndex = customerIndex;
        this.serviceIndex = serviceIndex;
        this.bookingDateTime = bookingDateTime;
        this.feedback = feedback;
    }

    /**
     * Converts a given {@code Booking} into this class for Jackson use.
     */
    public JsonAdaptedBooking(Booking source, Integer customerIndex, Integer serviceIndex) {
        this.customerIndex = customerIndex;
        this.serviceIndex = serviceIndex;
        this.bookingDateTime = source.getBookingDateTime().toString();
        this.feedback = source.getFeedback().toString();
    }

    Integer getModelCustomerID() throws IllegalValueException {
        if (customerIndex == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Integer.class.getSimpleName()));
        }
        if (customerIndex < 0) {
            throw new IllegalValueException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }
        return customerIndex;
    }

    Integer getModelServiceID() throws IllegalValueException {
        if (serviceIndex == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Integer.class.getSimpleName()));
        }
        if (serviceIndex < 0) {
            throw new IllegalValueException(Messages.MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX);
        }
        return serviceIndex;
    }

    BookingDateTime getModelBookingDateTime() throws IllegalValueException {
        if (bookingDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BookingDateTime.class.getSimpleName()));
        }
        if (!BookingDateTime.isValidBookingDateTime(bookingDateTime)) {
            throw new IllegalValueException(BookingDateTime.MESSAGE_CONSTRAINTS);
        }

        return new BookingDateTime(bookingDateTime);
    }

    Feedback getModelFeedback() throws IllegalValueException {
        if (feedback == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Feedback.class.getSimpleName()));
        }
        if (!Feedback.isValidFeedback(feedback)) {
            throw new IllegalValueException(Feedback.MESSAGE_CONSTRAINTS);
        }
        return new Feedback(feedback);
    }

    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code Booking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Booking.
     */
    public Booking toModelType(TrackBeau trackBeau) throws IllegalValueException {
        List<Customer> customerList = trackBeau.getCustomerList();
        List<Service> serviceList = trackBeau.getServiceList();
        Customer customer = customerList.get(getModelCustomerID());
        Service service = serviceList.get(getModelServiceID());

        return new Booking(customer, service, getModelBookingDateTime(), getModelFeedback());
    }
}
