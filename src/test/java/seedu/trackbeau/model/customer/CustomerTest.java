package seedu.trackbeau.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ALLERGY_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_HAIR_TYPE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_REG_DATE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SERVICE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SERVICE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SKIN_TYPE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_STAFF_BOB;
import static seedu.trackbeau.testutil.Assert.assertThrows;
import static seedu.trackbeau.testutil.TypicalCustomers.ALICE;
import static seedu.trackbeau.testutil.TypicalCustomers.BOB;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.testutil.CustomerBuilder;

public class CustomerTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Customer customer = new CustomerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> customer.getStaffs().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameItem(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameItem(null));

        // same name, same email, same phone, all other attributes different -> returns true
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withStaffs(VALID_STAFF_BOB)
                .withBirthdate(VALID_BIRTHDATE_BOB).withRegistrationDate(VALID_REG_DATE_BOB)
                .withAllergies(VALID_ALLERGY_BOB).withServices(VALID_SERVICE_BOB).build();
        assertTrue(ALICE.isSameItem(editedAlice));

        // different name, different phone, different email, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameItem(editedAlice));

        // name differs in case, different email, different phone,
        // all other attributes same -> returns false
        Customer editedBob = new CustomerBuilder(BOB)
                .withName(VALID_NAME_BOB.toLowerCase())
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .build();
        assertFalse(BOB.isSameItem(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new CustomerBuilder(BOB).withName(nameWithTrailingSpaces)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .build();
        assertFalse(BOB.isSameItem(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Customer aliceCopy = new CustomerBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different customer -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Customer editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new CustomerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different skin type -> returns false
        editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_SKIN_TYPE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different hair type -> returns false
        editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_HAIR_TYPE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different staffs -> returns false
        editedAlice = new CustomerBuilder(ALICE).withStaffs(VALID_STAFF_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different services -> returns false
        editedAlice = new CustomerBuilder(ALICE).withServices(VALID_SERVICE_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different allergies -> returns false
        editedAlice = new CustomerBuilder(ALICE).withAllergies(VALID_ALLERGY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

    }
}
