package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

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
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_GRADE_BOB = "100";
    public static final String VALID_GRADE_AMY = "99";

    public static final String VALID_GROUP_NAME_TUT_1 = "TUT[1]";
    public static final String VALID_GROUP_NAME_CS1010 = "CS1010";
    public static final String VALID_GROUP_LOCATION_TUT_1 = "E1-01-01";
    public static final String VALID_GROUP_LOCATION_CS1010 = "VCR";
    public static final String VALID_GROUP_TAG_TUT_1 = "morning";
    public static final String VALID_GROUP_TAG_CS1010 = "night";

    public static final String VALID_GENDER_MALE = "MALE";
    public static final String VALID_GENDER_FEMALE = "FEMALE";
    public static final String VALID_GENDER_ABBREVIATION_MALE = "M";
    public static final String VALID_GENDER_ABBREVIATION_FEMALE = "F";

    public static final String VALID_NATIONALITY_SG = "SG";
    public static final String VALID_NATIONALITY_CN = "CN";

    public static final String VALID_PERSON_INDEX_1 = "1";
    public static final String VALID_PERSON_INDEX_2 = "2";
    public static final String VALID_PERSON_INDEX_3 = "3";
    public static final String VALID_NUMBER_OF_GROUPS = "5";
    public static final String VALID_GENDER_FLAG_TRUE = "true";
    public static final String VALID_GENDER_FLAG_FALSE = "false";
    public static final String VALID_NATIONALITY_FLAG_TRUE = "true";
    public static final String VALID_NATIONALITY_FLAG_FALSE = "false";
    public static final String VALID_FLAG_0 = "0";
    public static final String VALID_FLAG_1 = "1";
    public static final String INVALID_GENDER_FLAG_NUMBER = "4";
    public static final String INVALID_GENDER_FLAG_WORD = "alsfe";
    public static final String INVALID_NATIONALITY_FLAG_NUMBER = "6";
    public static final String INVALID_NATIONALITY_FLAG_WORD = "eurt";

    public static final String VALID_GROUP_INDEX_1 = "1";
    public static final String VALID_GROUP_INDEX_2 = "2";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_FEMALE;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_MALE;
    public static final String GENDER_ABBREVIATION_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_ABBREVIATION_FEMALE;
    public static final String GENDER_ABBREVIATION_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_ABBREVIATION_MALE;
    public static final String NATIONALITY_DESC_AMY = " " + PREFIX_NATIONALITY + VALID_NATIONALITY_SG;
    public static final String NATIONALITY_DESC_BOB = " " + PREFIX_NATIONALITY + VALID_NATIONALITY_CN;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String GRADE_DESC_BOB = " " + PREFIX_GRADE + VALID_GRADE_BOB;
    public static final String GRADE_DESC_AMY = " " + PREFIX_GRADE + VALID_GRADE_AMY;
    public static final String PERSON_INDEX_DESC_1 = " " + PREFIX_PERSON_INDEX + VALID_PERSON_INDEX_1;
    public static final String PERSON_INDEX_DESC_2 = " " + PREFIX_PERSON_INDEX + VALID_PERSON_INDEX_2;
    public static final String PERSON_INDEX_DESC_3 = " " + PREFIX_PERSON_INDEX + VALID_PERSON_INDEX_3;
    public static final String GROUP_INDEX_DESC_1 = " " + PREFIX_GROUP_INDEX + VALID_GROUP_INDEX_1;
    public static final String GROUP_INDEX_DESC_2 = " " + PREFIX_GROUP_INDEX + VALID_GROUP_INDEX_2;

    public static final String GROUP_NAME_DESC_TUT_1 = " " + PREFIX_NAME + VALID_GROUP_NAME_TUT_1;
    public static final String GROUP_NAME_DESC_CS1010 = " " + PREFIX_NAME + VALID_GROUP_NAME_CS1010;
    public static final String GROUP_LOCATION_DESC_TUT_1 = " " + PREFIX_GROUP_LOCATION + VALID_GROUP_LOCATION_TUT_1;
    public static final String GROUP_LOCATION_DESC_CS1010 = " " + PREFIX_GROUP_LOCATION + VALID_GROUP_LOCATION_CS1010;
    public static final String GROUP_TAG_DESC_TUT_1 = " " + PREFIX_TAG + VALID_GROUP_TAG_TUT_1;
    public static final String GROUP_TAG_DESC_CS1010 = " " + PREFIX_TAG + VALID_GROUP_TAG_CS1010;

    public static final String NUMBER_OF_GROUPS = " " + VALID_NUMBER_OF_GROUPS;
    public static final String GENDER_FLAG_FALSE = " " + PREFIX_GENDER + VALID_GENDER_FLAG_FALSE;
    public static final String GENDER_FLAG_TRUE = " " + PREFIX_GENDER + VALID_GENDER_FLAG_TRUE;
    public static final String NATIONALITY_FLAG_FALSE = " " + PREFIX_NATIONALITY + VALID_NATIONALITY_FLAG_FALSE;
    public static final String NATIONALITY_FLAG_TRUE = " " + PREFIX_NATIONALITY + VALID_NATIONALITY_FLAG_TRUE;
    public static final String GENDER_FLAG_BOOLEAN_FALSE = " " + PREFIX_GENDER + VALID_FLAG_0;
    public static final String GENDER_FLAG_BOOLEAN_TRUE = " " + PREFIX_GENDER + VALID_FLAG_1;
    public static final String NATIONALITY_FLAG_BOOLEAN_FALSE = " " + PREFIX_NATIONALITY + VALID_FLAG_0;
    public static final String NATIONALITY_FLAG_BOOLEAN_TRUE = " " + PREFIX_NATIONALITY + VALID_FLAG_1;

    public static final String GENDER_FLAG_INVALID_NUMBER = " " + PREFIX_GENDER + INVALID_GENDER_FLAG_NUMBER;
    public static final String GENDER_FLAG_INVALID_WORD = " " + PREFIX_GENDER + INVALID_GENDER_FLAG_WORD;
    public static final String NATIONALITY_FLAG_INVALID_NUMBER = " " + PREFIX_NATIONALITY
            + INVALID_NATIONALITY_FLAG_NUMBER;
    public static final String NATIONALITY_FLAG_INVALID_WORD = " " + PREFIX_NATIONALITY + INVALID_NATIONALITY_FLAG_WORD;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "Male1"; // '1' not allowed in Gender
    public static final String INVALID_GENDER_ABBREVIATED_DESC = " " + PREFIX_GENDER + "A"; // '1' not allowed in Gender
    public static final String INVALID_NATIONALITY_DESC = " "
            + PREFIX_NATIONALITY + "SG1"; // '1' not allowed in Nationality
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_GRADE_DESC = " " + PREFIX_GRADE + "10*"; // '*' not allowed in tags
    public static final String INVALID_PERSON_INDEX_DESC = " "
            + PREFIX_PERSON_INDEX + "10*"; // '*' not allowed in person index
    public static final String INVALID_GROUP_INDEX_DESC = " "
            + PREFIX_GROUP_INDEX + "10*"; // '*' not allowed in group index

    public static final String INVALID_GROUP_NAME_DESC = " " + PREFIX_NAME + "TUT1*"; // '*' not allowed in group names
    public static final String INVALID_GROUP_LOCATION_DESC = " "
            + PREFIX_GROUP_LOCATION + "E1/01/01"; // '/' not allowed in group names
    public static final String INVALID_GROUP_TAG_DESC = " " + PREFIX_TAG + "morning*"; // '*' not allowed in tags

    public static final String INVALID_GROUP_NUMBER_DESC = " " + "0";
    public static final String INVALID_GROUP_NUMBER_STRING_DESC = " a";
    public static final String INVALID_MAX_GROUP_NUMBER_DESC = " " + "9223372036854775808";

    public static final String PREAMBLE_WHITESPACE = "\t \r \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withGender(VALID_GENDER_FEMALE).withNationality(VALID_NATIONALITY_SG)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withGender(VALID_GENDER_MALE).withNationality(VALID_NATIONALITY_CN)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedMessage, result.feedbackToUser);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book and the filtered person list in the {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the group at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showGroupAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredGroupList().size());

        Group group = model.getFilteredGroupList().get(targetIndex.getZeroBased());
        final String[] splitGroupName = group.getGroupName().groupName.split("\\s+");
        model.updateFilteredGroupList(new GroupNameContainsKeywordsPredicate(Arrays.asList(splitGroupName[0])));

        assertEquals(1, model.getFilteredGroupList().size());
    }

    /**
     * Deletes the first person in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstPerson(Model model) {
        Person firstPerson = model.getFilteredPersonList().get(0);
        model.deletePerson(firstPerson);
        model.commitAddressBook();
    }

}
