package seedu.trackbeau.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SERVICE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_STAFF_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackbeau.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.trackbeau.testutil.TypicalCustomers.getTypicalTrackBeau;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.customer.EditCustomerCommand;
import seedu.trackbeau.logic.commands.customer.EditCustomerCommand.EditCustomerDescriptor;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ModelManager;
import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.UserPrefs;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.testutil.CustomerBuilder;
import seedu.trackbeau.testutil.EditCustomerDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCustomerCommand.
 */
public class EditCustomerCommandTest {

    private Model model = new ModelManager(getTypicalTrackBeau(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Customer editedCustomer = new CustomerBuilder().build();
        EditCustomerCommand.EditCustomerDescriptor descriptor =
            new EditCustomerDescriptorBuilder(editedCustomer).build();
        EditCustomerCommand editCustomerCommand = new EditCustomerCommand(INDEX_FIRST_CUSTOMER, descriptor);

        String expectedMessage = String.format(EditCustomerCommand.MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new TrackBeau(model.getTrackBeau()), new UserPrefs());
        expectedModel.setCustomer(model.getFilteredCustomerList().get(0), editedCustomer);

        assertCommandSuccess(editCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastCustomer = Index.fromOneBased(model.getFilteredCustomerList().size());
        Customer lastCustomer = model.getFilteredCustomerList().get(indexLastCustomer.getZeroBased());

        //amy cannot clash phone number or email with bob but they can have the same name

        CustomerBuilder customerInList = new CustomerBuilder(lastCustomer);
        Customer editedCustomer = customerInList.withName(VALID_NAME_BOB)
                .withStaffs(VALID_STAFF_AMY).withServices(VALID_SERVICE_BOB).build();

        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withStaffs(VALID_STAFF_AMY).withServices(VALID_SERVICE_BOB).build();
        EditCustomerCommand editCustomerCommand = new EditCustomerCommand(indexLastCustomer, descriptor);

        String expectedMessage = String.format(EditCustomerCommand.MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new TrackBeau(model.getTrackBeau()), new UserPrefs());
        expectedModel.setCustomer(lastCustomer, editedCustomer);

        assertCommandSuccess(editCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedRemovedUnfilteredList_success() {
        Customer secondCustomer = model.getFilteredCustomerList().get(INDEX_SECOND_CUSTOMER.getZeroBased());

        CustomerBuilder customerInList = new CustomerBuilder(secondCustomer);
        Customer editedCustomer = customerInList.withServices().withStaffs().build();

        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder().withServices().withStaffs().build();
        EditCustomerCommand editCustomerCommand = new EditCustomerCommand(INDEX_SECOND_CUSTOMER, descriptor);

        String expectedMessage = String.format(EditCustomerCommand.MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new TrackBeau(model.getTrackBeau()), new UserPrefs());
        expectedModel.setCustomer(secondCustomer, editedCustomer);

        assertCommandSuccess(editCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCustomerCommand editCustomerCommand =
            new EditCustomerCommand(INDEX_FIRST_CUSTOMER, new EditCustomerCommand.EditCustomerDescriptor());
        System.out.println(editCustomerCommand.toString());

        Customer editedCustomer = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());

        System.out.println(editedCustomer.toString());

        String expectedMessage = String.format(EditCustomerCommand.MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new TrackBeau(model.getTrackBeau()), new UserPrefs());

        assertCommandSuccess(editCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        Customer customerInFilteredList = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        Customer editedCustomer = new CustomerBuilder(customerInFilteredList).withName(VALID_NAME_BOB).build();
        EditCustomerCommand editCustomerCommand = new EditCustomerCommand(INDEX_FIRST_CUSTOMER,
            new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCustomerCommand.MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new TrackBeau(model.getTrackBeau()), new UserPrefs());
        expectedModel.setCustomer(model.getFilteredCustomerList().get(0), editedCustomer);

        assertCommandSuccess(editCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCustomerUnfilteredList_failure() {
        Customer firstCustomer = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        EditCustomerCommand.EditCustomerDescriptor descriptor =
            new EditCustomerDescriptorBuilder(firstCustomer).build();
        EditCustomerCommand editCustomerCommand = new EditCustomerCommand(INDEX_SECOND_CUSTOMER, descriptor);

        assertCommandFailure(editCustomerCommand, model, EditCustomerCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

    @Test
    public void execute_duplicateCustomerFilteredList_failure() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        // edit customer in filtered list into a duplicate trackBeau
        Customer customerInList = model.getTrackBeau().getCustomerList().get(INDEX_SECOND_CUSTOMER.getZeroBased());
        EditCustomerCommand editCustomerCommand = new EditCustomerCommand(INDEX_FIRST_CUSTOMER,
            new EditCustomerDescriptorBuilder(customerInList).build());

        assertCommandFailure(editCustomerCommand, model, EditCustomerCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

    @Test
    public void execute_invalidCustomerIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCustomerList().size() + 1);
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCustomerCommand editCustomerCommand = new EditCustomerCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCustomerCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of trackBeau
     */
    @Test
    public void execute_invalidCustomerIndexFilteredList_failure() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);
        Index outOfBoundIndex = INDEX_SECOND_CUSTOMER;
        // ensures that outOfBoundIndex is still in bounds of trackBeau
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrackBeau().getCustomerList().size());

        EditCustomerCommand editCustomerCommand = new EditCustomerCommand(outOfBoundIndex,
            new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCustomerCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCustomerCommand standardCommand = new EditCustomerCommand(INDEX_FIRST_CUSTOMER, DESC_AMY);

        // same values -> returns true
        EditCustomerCommand.EditCustomerDescriptor copyDescriptor = new EditCustomerDescriptor(DESC_AMY);
        EditCustomerCommand commandWithSameValues = new EditCustomerCommand(INDEX_FIRST_CUSTOMER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCustomerCommand(INDEX_SECOND_CUSTOMER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCustomerCommand(INDEX_FIRST_CUSTOMER, DESC_BOB)));
    }

}
