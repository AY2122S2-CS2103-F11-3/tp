package seedu.trackbeau.logic.commands.customer;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_HAIRTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_REGDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SKINTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STAFFS;
import static seedu.trackbeau.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.commons.util.CollectionUtil;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
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
 * Edits the details of an existing customer in trackBeau.
 */
public class EditCustomerCommand extends Command {

    public static final String COMMAND_WORD = "editc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the customer identified "
            + "by the index number used in the displayed customer list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_REGDATE + "REGISTRATION DATE] "
            + "[" + PREFIX_SKINTYPE + "SKIN TYPE] "
            + "[" + PREFIX_HAIRTYPE + "HAIR TYPE] "
            + "[" + PREFIX_BIRTHDATE + "BIRTHDAY] "
            + "[" + PREFIX_STAFFS + "STAFFS]... "
            + "[" + PREFIX_SERVICES + "SERVICES]... "
            + "[" + PREFIX_ALLERGIES + "ALLERGIES]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CUSTOMER_SUCCESS = "Edited Customer: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CUSTOMER = "This customer already exists in trackBeau.";

    private final Index index;
    private final EditCustomerDescriptor editCustomerDescriptor;

    /**
     * @param index of the customer in the filtered customer list to edit
     * @param editCustomerDescriptor details to edit the customer with
     */
    public EditCustomerCommand(Index index, EditCustomerDescriptor editCustomerDescriptor) {
        requireNonNull(index);
        requireNonNull(editCustomerDescriptor);

        this.index = index;
        this.editCustomerDescriptor = new EditCustomerDescriptor(editCustomerDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        Customer customerToEdit = lastShownList.get(index.getZeroBased());
        Customer editedCustomer = createEditedCustomer(customerToEdit, editCustomerDescriptor);
        if (customerToEdit.isDifferentItemFromOriginal(editedCustomer) && model.hasCustomer(editedCustomer)) {
            throw new CommandException(MESSAGE_DUPLICATE_CUSTOMER);
        }

        /*if (customerToEdit.isSameItem(editedCustomer) || model.hasCustomer(editedCustomer) {
            throw new CommandException(MESSAGE_DUPLICATE_CUSTOMER
                    + " "  + hasSameName + " " + hasSameEmail + " " + hasSamePhoneNumber);
        }*/
        //if have diff email, same phone, they are still considered to be the same person
        //if model has this customer with same p or e, than there would be a clash
        //problem: they should be considered a different person so if email or phone is different, return different

        model.setCustomer(customerToEdit, editedCustomer);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(String.format(MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer));
    }

    /**
     * Creates and returns a {@code Customer} with the details of {@code customerToEdit}
     * edited with {@code editCustomerDescriptor}.
     */
    private static Customer createEditedCustomer(Customer customerToEdit,
                                                 EditCustomerDescriptor editCustomerDescriptor) {
        assert customerToEdit != null;

        Name updatedName = editCustomerDescriptor.getName().orElse(customerToEdit.getName());
        Phone updatedPhone = editCustomerDescriptor.getPhone().orElse(customerToEdit.getPhone());
        Email updatedEmail = editCustomerDescriptor.getEmail().orElse(customerToEdit.getEmail());
        Address updatedAddress = editCustomerDescriptor.getAddress().orElse(customerToEdit.getAddress());
        SkinType updatedSkinType = editCustomerDescriptor.getSkinType().orElse(customerToEdit.getSkinType());
        HairType updatedHairType = editCustomerDescriptor.getHairType().orElse(customerToEdit.getHairType());
        Set<Tag> updatedStaffs = editCustomerDescriptor.getStaffs().orElse(customerToEdit.getStaffs());
        Set<Tag> updatedServices = editCustomerDescriptor.getServices().orElse(customerToEdit.getServices());
        Set<Tag> updatedAllergies = editCustomerDescriptor.getAllergies().orElse(customerToEdit.getAllergies());
        Birthdate updatedBirthdate = editCustomerDescriptor.getBirthdate().orElse(customerToEdit.getBirthdate());
        RegistrationDate updatedRegistrationDate = editCustomerDescriptor
                .getRegistrationDate().orElse(customerToEdit.getRegDate());
        return new Customer(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedSkinType, updatedHairType,
                updatedStaffs, updatedServices, updatedAllergies, updatedBirthdate, updatedRegistrationDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCustomerCommand)) {
            return false;
        }

        // state check
        EditCustomerCommand e = (EditCustomerCommand) other;
        return index.equals(e.index)
                && editCustomerDescriptor.equals(e.editCustomerDescriptor);
    }

    /**
     * Stores the details to edit the customer with. Each non-empty field value will replace the
     * corresponding field value of the customer.
     */
    public static class EditCustomerDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private SkinType skinType;
        private HairType hairType;
        private Birthdate birthdate;
        private RegistrationDate regDate;
        private Set<Tag> staffs;
        private Set<Tag> services;
        private Set<Tag> allergies;

        public EditCustomerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCustomerDescriptor(EditCustomerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setSkinType(toCopy.skinType);
            setHairType(toCopy.hairType);
            setStaffs(toCopy.staffs);
            setServices(toCopy.services);
            setAllergies(toCopy.allergies);
            setBirthdate(toCopy.birthdate);
            setRegistrationDate(toCopy.regDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address,
                    skinType, hairType, staffs, services, allergies, birthdate, regDate);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setSkinType(SkinType skinType) {
            this.skinType = skinType;
        }

        public Optional<SkinType> getSkinType() {
            return Optional.ofNullable(skinType);
        }

        public void setHairType(HairType hairType) {
            this.hairType = hairType;
        }

        public Optional<HairType> getHairType() {
            return Optional.ofNullable(hairType);
        }

        public void setBirthdate(Birthdate birthdate) {
            this.birthdate = birthdate;
        }

        public Optional<Birthdate> getBirthdate() {
            return Optional.ofNullable(birthdate);
        }

        public void setRegistrationDate(RegistrationDate regDate) {
            this.regDate = regDate;
        }

        public Optional<RegistrationDate> getRegistrationDate() {
            return Optional.ofNullable(regDate);
        }

        /**
         * Sets {@code staffs} to this object's {@code staffs}.
         * A defensive copy of {@code staffs} is used internally.
         */
        public void setStaffs(Set<Tag> staffs) {
            this.staffs = (staffs != null) ? new HashSet<>(staffs) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code staffs} is null.
         */
        public Optional<Set<Tag>> getStaffs() {
            return (staffs != null) ? Optional.of(Collections.unmodifiableSet(staffs)) : Optional.empty();
        }

        /**
         * Sets {@code services} to this object's {@code services}.
         * A defensive copy of {@code services} is used internally.
         */
        public void setServices(Set<Tag> services) {
            this.services = (services != null) ? new HashSet<>(services) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code services} is null.
         */
        public Optional<Set<Tag>> getServices() {
            return (services != null) ? Optional.of(Collections.unmodifiableSet(services)) : Optional.empty();
        }

        /**
         * Sets {@code allergies} to this object's {@code allergies}.
         * A defensive copy of {@code services} is used internally.
         */
        public void setAllergies(Set<Tag> allergies) {
            this.allergies = (allergies != null) ? new HashSet<>(allergies) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code allergies} is null.
         */
        public Optional<Set<Tag>> getAllergies() {
            return (allergies != null) ? Optional.of(Collections.unmodifiableSet(allergies)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCustomerDescriptor)) {
                return false;
            }

            // state check
            EditCustomerDescriptor e = (EditCustomerDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getSkinType().equals(e.getSkinType())
                    && getHairType().equals(e.getHairType())
                    && getBirthdate().equals(e.getBirthdate())
                    && getRegistrationDate().equals(e.getRegistrationDate())
                    && getStaffs().equals(e.getStaffs())
                    && getServices().equals(e.getServices())
                    && getAllergies().equals(e.getAllergies());
        }
    }
}
