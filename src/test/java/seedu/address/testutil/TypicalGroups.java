//@@author rajdeepsh

package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_LOCATION_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_LOCATION_TUT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_TUT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TAG_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TAG_TUT_1;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.JOEL;
import static seedu.address.testutil.TypicalPersons.JOELTAN;
import static seedu.address.testutil.TypicalPersons.NOVIN;
import static seedu.address.testutil.TypicalPersons.WEIQUAN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final Group TUT_2 = new GroupBuilder().withGroupName("TUT[2]")
            .withGroupLocation("E2-02-02").build();
    public static final Group TUT_3 = new GroupBuilder().withGroupName("TUT[3]")
            .withGroupLocation("E3-03-03")
            .withTags("afternoon").build();
    public static final Group TUT_4 = new GroupBuilder().withGroupName("TUT[4]")
            .withGroupLocation("E4-04-04")
            .withTags("morning", "afternoon").build();

    // Manually added - Groups's details found in {@code CommandTestUtil}
    public static final Group TUT_1 = new GroupBuilder()
            .withGroupName(VALID_GROUP_NAME_TUT_1)
            .withGroupLocation(VALID_GROUP_LOCATION_TUT_1)
            .withTags(VALID_GROUP_TAG_TUT_1).build();
    public static final Group CS1010 = new GroupBuilder()
            .withGroupName(VALID_GROUP_NAME_CS1010)
            .withGroupLocation(VALID_GROUP_LOCATION_CS1010)
            .withTags(VALID_GROUP_TAG_CS1010).build();
    public static final Group EMAIL_TEST_GROUP = new GroupBuilder()
            .withGroupName("TestGroupForEmail")
            .withGroupLocation("NUS").build();
    public static final String KEYWORD_MATCHING_CS1010 = "Cs1010"; // A keyword that matches CS1010

    private TypicalGroups() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical groups.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.createGroup(group);
        }
        return ab;
    }

    public static Group getTut1() {
        return new GroupBuilder()
                .withGroupName(VALID_GROUP_NAME_TUT_1)
                .withGroupLocation(VALID_GROUP_LOCATION_TUT_1)
                .withTags(VALID_GROUP_TAG_TUT_1).build();
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(TUT_1, CS1010, TUT_2, TUT_3, TUT_4));
    }

    public static final Group getTypicalGroupsWithPersons() {
        Group group = new GroupBuilder(TUT_1).build();
        group.addPersons(ALICE);
        return group;
    }

    public static final Group getTypicalGroupsValidEmailPersons() {
        Group group = new GroupBuilder(EMAIL_TEST_GROUP).build();
        group.addPersons(JOEL);
        group.addPersons(JOELTAN);
        group.addPersons(NOVIN);
        group.addPersons(WEIQUAN);
        return group;
    }

}
