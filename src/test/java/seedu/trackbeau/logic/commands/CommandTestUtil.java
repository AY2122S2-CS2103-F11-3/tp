package seedu.trackbeau.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import static seedu.trackbeau.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.customer.EditCustomerCommand.EditCustomerDescriptor;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;
import seedu.trackbeau.testutil.EditCustomerDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";

    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";

    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";

    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String VALID_SKIN_TYPE_AMY = "Normal";
    public static final String VALID_SKIN_TYPE_BOB = "Oily";

    public static final String VALID_HAIR_TYPE_AMY = "Oily";
    public static final String VALID_HAIR_TYPE_BOB = "Dry";

    public static final String VALID_BIRTHDATE_AMY = "07-12-2001";
    public static final String VALID_BIRTHDATE_BOB = "02-10-1990";

    public static final String VALID_REG_DATE_AMY = "23-02-2022";
    public static final String VALID_REG_DATE_BOB = "02-10-2021";

    public static final String VALID_STAFF_AMY = "Jane";
    public static final String VALID_STAFF_BOB = "John";

    public static final String VALID_SERVICE_AMY = "Chemical Peel";
    public static final String VALID_SERVICE_BOB = "Acne treatment";

    public static final String VALID_ALLERGY_AMY = "Nickel";
    public static final String VALID_ALLERGY_BOB = "Cocoa Butter";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;

    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;

    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;

    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;

    public static final String SKIN_TYPE_DESC_AMY = " " + PREFIX_SKINTYPE + VALID_SKIN_TYPE_AMY;
    public static final String SKIN_TYPE_DESC_BOB = " " + PREFIX_SKINTYPE + VALID_SKIN_TYPE_BOB;

    public static final String HAIR_TYPE_DESC_AMY = " " + PREFIX_HAIRTYPE + VALID_HAIR_TYPE_AMY;
    public static final String HAIR_TYPE_DESC_BOB = " " + PREFIX_HAIRTYPE + VALID_HAIR_TYPE_BOB;

    public static final String BIRTHDATE_DESC_AMY = " " + PREFIX_BIRTHDATE + VALID_BIRTHDATE_AMY;
    public static final String BIRTHDATE_DESC_BOB = " " + PREFIX_BIRTHDATE + VALID_BIRTHDATE_BOB;

    public static final String REG_DATE_DESC_AMY = " " + PREFIX_REGDATE + VALID_REG_DATE_AMY;
    public static final String REG_DATE_DESC_BOB = " " + PREFIX_REGDATE + VALID_REG_DATE_BOB;

    public static final String STAFF_DESC_AMY = " " + PREFIX_STAFFS + VALID_STAFF_AMY;
    public static final String STAFF_DESC_BOB = " " + PREFIX_STAFFS + VALID_STAFF_BOB;

    public static final String SERVICE_DESC_AMY = " " + PREFIX_SERVICES + VALID_SERVICE_AMY;
    public static final String SERVICE_DESC_BOB = " " + PREFIX_SERVICES + VALID_SERVICE_BOB;

    public static final String ALLERGY_DESC_AMY = " " + PREFIX_ALLERGIES + VALID_ALLERGY_AMY;
    public static final String ALLERGY_DESC_BOB = " " + PREFIX_ALLERGIES + VALID_ALLERGY_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_SKIN_TYPE_DESC = " " + PREFIX_SKINTYPE; // empty string not allowed for skin type
    public static final String INVALID_HAIR_TYPE_DESC = " " + PREFIX_HAIRTYPE; // empty string not allowed for hair type
    public static final String INVALID_STAFFS_DESC = " " + PREFIX_STAFFS; //empty string not allowed for tags
    public static final String INVALID_SERVICE_DESC = " " + PREFIX_SERVICES; //empty string not allowed for tags
    public static final String INVALID_ALLERGY_DESC = " " + PREFIX_ALLERGIES; //empty string not allowed for tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCustomerDescriptor DESC_AMY;
    public static final EditCustomerDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withSkinType(VALID_SKIN_TYPE_AMY)
                .withHairType(VALID_HAIR_TYPE_AMY)
                .withStaffs(VALID_STAFF_AMY)
                .withServices(VALID_SERVICE_AMY)
                .withAllergies(VALID_ALLERGY_AMY)
                .build();
        DESC_BOB = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withSkinType(VALID_SKIN_TYPE_BOB)
                .withHairType(VALID_HAIR_TYPE_BOB)
                .withStaffs(VALID_STAFF_BOB)
                .withServices(VALID_SERVICE_BOB)
                .withAllergies(VALID_ALLERGY_BOB)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the trackBeau, filtered customer list and selected customer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TrackBeau expectedTrackbeau = new TrackBeau(actualModel.getTrackBeau());
        List<Customer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCustomerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTrackbeau, actualModel.getTrackBeau());
        assertEquals(expectedFilteredList, actualModel.getFilteredCustomerList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the customer at the given {@code targetIndex} in the
     * {@code model}'s trackBeau.
     */
    public static void showCustomerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCustomerList().size());

        Customer customer = model.getFilteredCustomerList().get(targetIndex.getZeroBased());
        final String[] splitName = customer.getName().fullName.split("\\s+");
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));
        prefixArr.add(0, Arrays.asList(splitName[0]));
        model.updateFilteredCustomerList(new SearchContainsKeywordsPredicate(prefixArr));

        assertEquals(1, model.getFilteredCustomerList().size());
    }

}
