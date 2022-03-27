package seedu.trackbeau.model.customer;

import static seedu.trackbeau.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.trackbeau.model.tag.Tag;
import seedu.trackbeau.model.uniquelist.UniqueListItem;

/**
 * Represents a Customer trackBeau.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Customer implements UniqueListItem {
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final SkinType skinType;
    private final HairType hairType;
    private final Set<Tag> staffs = new HashSet<>();
    private final Set<Tag> services = new HashSet<>();
    private final Set<Tag> allergies = new HashSet<>();
    private final Birthdate birthdate;
    private final RegistrationDate regDate;

    /**
     * Every field must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address,
                    SkinType skinType, HairType hairType,
                    Set<Tag> staffs, Set<Tag> services, Set<Tag> allergies,
                    Birthdate birthdate, RegistrationDate regDate) {
        requireAllNonNull(name, phone, email, address);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.skinType = skinType;
        this.hairType = hairType;
        this.staffs.addAll(staffs);
        this.services.addAll(services);
        this.allergies.addAll(allergies);
        this.birthdate = birthdate;
        this.regDate = regDate;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    public HairType getHairType() {
        return hairType;
    }

    public Birthdate getBirthdate() {
        return birthdate;
    }

    public RegistrationDate getRegDate() {
        return regDate;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getStaffs() {
        return Collections.unmodifiableSet(staffs);
    }

    public Set<Tag> getServices() {
        return Collections.unmodifiableSet(services);
    }

    public Set<Tag> getAllergies() {
        return Collections.unmodifiableSet(allergies);
    }

    /**
     * Returns true if both customers have the same phone number and email.
     * This defines a weaker notion of equality between two customers.
     */
    public boolean isSameItem(UniqueListItem other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        return hasSameIdentity(otherCustomer);
    }

    /**
     * Returns true if edited customer has changed phone or email fields.
     */
    public boolean isDifferentItemFromOriginal(UniqueListItem other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        boolean hasSamePhoneNumber = otherCustomer.getPhone().equals(getPhone());
        boolean hasSameEmail = otherCustomer.getEmail().equals(getEmail());
        return !(hasSamePhoneNumber && hasSameEmail);
    }

    /**
     * Returns true if two customers have same name or same phone number or same email
     * @param otherCustomer
     */
    boolean hasSameIdentity(Customer otherCustomer) {
        boolean hasSamePhoneNumber = otherCustomer.getPhone().equals(getPhone());
        boolean hasSameEmail = otherCustomer.getEmail().equals(getEmail());
        if (hasSameEmail || hasSamePhoneNumber) {
            //if person has diff email, same phone, they are considered to be same person
            //if person has diff phone, same email, they are considered to be the same person
            return true;
        }
        return false;
    }

    /**
     * Returns true if both customers have the same identity and data fields.
     * This defines a stronger notion of equality between two customers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        return otherCustomer.getName().equals(getName())
            && otherCustomer.getPhone().equals(getPhone())
            && otherCustomer.getEmail().equals(getEmail())
            && otherCustomer.getAddress().equals(getAddress())
            && otherCustomer.getStaffs().equals(getStaffs())
            && otherCustomer.getServices().equals(getServices())
            && otherCustomer.getAllergies().equals(getAllergies())
            && otherCustomer.getBirthdate().equals(getBirthdate())
            && otherCustomer.getRegDate().equals(getRegDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, skinType, hairType, staffs, services, allergies);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Phone: ")
            .append(getPhone())
            .append("; Email: ")
            .append(getEmail())
            .append("; Address: ")
            .append(getAddress())
            .append("; Skin Type: ")
            .append(getSkinType())
            .append("; Hair Type: ")
            .append(getHairType())
            .append("; Birthday: ")
            .append(getBirthdate())
            .append("; Registration Date: ")
            .append(getRegDate());

        Set<Tag> staffs = getStaffs();
        if (!staffs.isEmpty()) {
            builder.append("; Favourite staffs: ");
            staffs.forEach(builder::append);
        }

        Set<Tag> services = getServices();
        if (!services.isEmpty()) {
            builder.append("; Favourite Services: ");
            services.forEach(builder::append);
        }

        Set<Tag> allergies = getAllergies();
        if (!allergies.isEmpty()) {
            builder.append("; allergies: ");
            allergies.forEach(builder::append);
        }

        return builder.toString();
    }

}
