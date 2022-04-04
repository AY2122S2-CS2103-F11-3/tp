package seedu.trackbeau.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.commons.util.StringUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.booking.BookingDateTime;
import seedu.trackbeau.model.booking.Feedback;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Birthdate;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.RegistrationDate;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.service.Duration;
import seedu.trackbeau.model.service.Price;
import seedu.trackbeau.model.service.ServiceName;
import seedu.trackbeau.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String skinType } into an {@code SkinType}.
     * Leading and trailing whitespaces will be trimmed.
     * Skin type input must fit existing categories.
     *
     * @throws ParseException if the given {@code SkinType} is invalid.
     */
    public static SkinType parseSkinType(String skinType) throws ParseException {
        requireNonNull(skinType);
        String trimmedSkinType = skinType.trim();
        if (!SkinType.isValidSkinType(trimmedSkinType)) {
            throw new ParseException(SkinType.MESSAGE_CONSTRAINTS);
        }
        return new SkinType(trimmedSkinType);
    }
    /**
     * Parses a {@code String hairType} into an {@code HairType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code HairType} is invalid.
     */
    public static HairType parseHairType(String hairType) throws ParseException {
        requireNonNull(hairType);
        String trimmedHairType = hairType.trim();
        if (!HairType.isValidHairType(trimmedHairType)) {
            throw new ParseException(HairType.MESSAGE_CONSTRAINTS);
        }
        return new HairType(trimmedHairType);
    }

    /**
     * Parses a {@code String birthdate} into an {@code Birthdate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Birthdate} is invalid.
     */
    public static Birthdate parseBirthdate(String birthDate) throws ParseException {
        requireNonNull(birthDate);
        String trimmedBirthDate = birthDate.trim();
        if (!Birthdate.isValidBirthdate(trimmedBirthDate)) {
            throw new ParseException(Birthdate.MESSAGE_CONSTRAINTS);
        }
        return new Birthdate(birthDate);
    }

    /**
     * Parses a {@code String regDate} into an {@code RegistrationDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code RegistrationDate} is invalid.
     */
    public static RegistrationDate parseRegistrationDate(String regDate) throws ParseException {
        requireNonNull(regDate);
        String trimmedRegDate = regDate.trim();
        if (!RegistrationDate.isValidRegistrationDate(trimmedRegDate)) {
            throw new ParseException(RegistrationDate.MESSAGE_CONSTRAINTS);
        }
        return new RegistrationDate(regDate);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String startTime} into an {@code BookingDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startTime} is invalid.
     */
    public static BookingDateTime parseStartTime(String startTime) throws ParseException {
        requireNonNull(startTime);
        String trimmedStartTime = startTime.trim();
        if (!BookingDateTime.isValidBookingDateTime(trimmedStartTime)) {
            throw new ParseException(BookingDateTime.MESSAGE_CONSTRAINTS);
        }
        return new BookingDateTime(trimmedStartTime);
    }

    /**
     * Parses a {@code String serviceName} into a {@code ServiceName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code serviceName} is invalid.
     */
    public static ServiceName parseServiceName(String serviceName) throws ParseException {
        requireNonNull(serviceName);
        String trimmedName = serviceName.trim();
        if (!ServiceName.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ServiceName(trimmedName);
    }

    /**
     * Parses a {@code String price} into a {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code price} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        String trimmedPrice = price.trim();
        if (!Price.isValidPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
        return new Price(trimmedPrice);
    }

    /**
     * Parses a {@code String duration} into a {@code Duration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        String trimmedDuration = duration.trim();
        if (!Duration.isValidDuration(trimmedDuration)) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }
        return new Duration(trimmedDuration);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Splits {@code args} into a list of String where each String can be parse as an Index and returns it.
     *
     * @throws ParseException if parseIndex() throws a ParseException.
     */
    public static ArrayList<Index> parseIndexes(String args) throws ParseException {
        String[] split = args.split(",");
        ArrayList<Index> indexes = new ArrayList<>();
        for (String s : split) {
            indexes.add(parseIndex(s));
        }
        return indexes;
    }

    /**
     * Parses a {@code String feedback} into an {@code feedback}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Feedback parseFeedback(String feedback) throws ParseException {
        requireNonNull(feedback);
        String trimmedFeedback = feedback.trim();
        if (!Feedback.isValidFeedback(trimmedFeedback)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Feedback(trimmedFeedback);
    }
}
