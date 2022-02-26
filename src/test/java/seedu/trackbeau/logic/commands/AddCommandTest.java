package seedu.trackbeau.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.trackbeau.commons.core.GuiSettings;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.ReadOnlyUserPrefs;
import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Customer validCustomer = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validCustomer).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validCustomer), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCustomer), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Customer validCustomer = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validCustomer);
        ModelStub modelStub = new ModelStubWithPerson(validCustomer);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Customer alice = new PersonBuilder().withName("Alice").build();
        Customer bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different customer -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTrackBeauFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackBeauFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackBeau(ReadOnlyTrackBeau newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTrackBeau getTrackBeau() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCustomer(Customer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCustomer(Customer target, Customer editedCustomer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Customer> getFilteredCustomerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCustomerList(Predicate<Customer> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single customer.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Customer customer;

        ModelStubWithPerson(Customer customer) {
            requireNonNull(customer);
            this.customer = customer;
        }

        @Override
        public boolean hasCustomer(Customer customer) {
            requireNonNull(customer);
            return this.customer.isSamePerson(customer);
        }
    }

    /**
     * A Model stub that always accept the customer being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Customer> personsAdded = new ArrayList<>();

        @Override
        public boolean hasCustomer(Customer customer) {
            requireNonNull(customer);
            return personsAdded.stream().anyMatch(customer::isSamePerson);
        }

        @Override
        public void addCustomer(Customer customer) {
            requireNonNull(customer);
            personsAdded.add(customer);
        }

        @Override
        public ReadOnlyTrackBeau getTrackBeau() {
            return new TrackBeau();
        }
    }

}
