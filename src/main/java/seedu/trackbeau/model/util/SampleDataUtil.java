package seedu.trackbeau.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Birthdate;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.RegistrationDate;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TrackBeau} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new SkinType("oily"), new HairType("dry"),
                    getTagSet("Jason"), getTagSet("Acne", "Balding"), getTagSet("Nickel"),
                    new Birthdate("01-12-2001"), new RegistrationDate("23-03-2022")),
            new Customer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new SkinType("sensitive"), new HairType("oily"),
                getTagSet("Jane", "Lee"), getTagSet("Blackheads"), getTagSet("Steroids"),
                    new Birthdate("01-12-1996"), new RegistrationDate("23-03-2022")),
            new Customer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new SkinType("combination"), new HairType("dry"),
                getTagSet("Lee"),
                    getTagSet("Hair loss"),
                    getTagSet("Salicylic acid", "Aluminium compounds"),
                    new Birthdate("05-10-1960"), new RegistrationDate("23-03-2022")),
            new Customer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new SkinType("oily"), new HairType("dry"),
                    getTagSet("Jason"),
                    getTagSet("Chemical peels"),
                    getTagSet("Cobalt"),
                    new Birthdate("26-05-2000"), new RegistrationDate("23-03-2022")),
            new Customer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new SkinType("sensitive"), new HairType("oily"),
                    getTagSet("Jolene", "Lee"),
                    getTagSet("Laser treatment"),
                    getTagSet("Fragrances"),
                    new Birthdate("23-05-2001"), new RegistrationDate("23-03-2022")),
            new Customer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new SkinType("combination"), new HairType("dry"),
                    getTagSet("Lee"), getTagSet("Acne"), getTagSet("Cocoa butter"),
                    new Birthdate("01-08-2001"), new RegistrationDate("23-03-2022")),
        };
    }

    public static ReadOnlyTrackBeau getSampleTrackBeau() {
        TrackBeau sampleTb = new TrackBeau();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleTb.addCustomer(sampleCustomer);
        }
        return sampleTb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
