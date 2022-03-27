package seedu.trackbeau.ui;

import static seedu.trackbeau.model.customer.Birthdate.EMPTY_DATE;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.trackbeau.model.customer.Customer;

/**
 * An UI component that displays information of a {@code Customer}.
 */
public class CustomerCard extends UiPart<Region> {

    private static final String FXML = "CustomerListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Addressbook level 4</a>
     */

    public final Customer customer;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label skinType;
    @FXML
    private Label hairType;
    @FXML
    private Label birthDate;
    @FXML
    private Label regDate;
    @FXML
    private FlowPane staffs;
    @FXML
    private FlowPane services;
    @FXML
    private FlowPane allergies;

    /**
     * Creates a {@code CustomerCard} with the given {@code Customer} and index to display.
     */
    public CustomerCard(Customer customer, int displayedIndex) {
        super(FXML);
        this.customer = customer;
        id.setText(displayedIndex + ". ");
        name.setText(customer.getName().fullName);
        phone.setText(customer.getPhone().value);
        address.setText(customer.getAddress().value);
        email.setText(customer.getEmail().value);
        regDate.setText("Registration Date: " + customer.getRegDate().toString());
        if (customer.getSkinType().value != "Skin type data not available") {
            skinType.setText("Skin Type: " + customer.getSkinType().value);
        } else {
            skinType.setManaged(false);
        }
        if (customer.getHairType().value != "Hair type data not available") {
            hairType.setText("Hair Type: " + customer.getHairType().value);
        } else {
            hairType.setManaged(false);
        }
        if (!customer.getBirthdate().value.equals(EMPTY_DATE)) {
            birthDate.setText("Birthday: " + customer.getBirthdate().toString());
        } else {
            birthDate.setManaged(false);
        }
        customer.getServices().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> services.getChildren().add(new Label(tag.tagName)));
        customer.getStaffs().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> staffs.getChildren().add(new Label(tag.tagName)));
        customer.getAllergies().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> allergies.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CustomerCard)) {
            return false;
        }

        // state check
        CustomerCard card = (CustomerCard) other;
        return id.getText().equals(card.id.getText())
                && customer.equals(card.customer);
    }
}
