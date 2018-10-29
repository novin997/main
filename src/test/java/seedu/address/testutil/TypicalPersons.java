package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_FEMALE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_MALE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NATIONALITY_CN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NATIONALITY_SG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withGender("FEMALE").withNationality("SG")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withGrade("100")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withGender("MALE").withNationality("MY")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withGrade("1")
            .withTags("owesMoney", "friends").build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withGender("MALE").withNationality("US")
            .withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withGrade("22").build();

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withGender("MALE").withNationality("SG")
            .withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withGrade("30").withTags("friends").build();

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withGender("FEMALE").withNationality("SG")
            .withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withGrade("42").build();

    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withGender("FEMALE").withNationality("SG")
            .withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withGrade("99").build();

    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withGender("MALE").withNationality("US")
            .withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withGrade("100").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withGender("MALE")
            .withNationality("SG").withPhone("8482424").withEmail("stefan@example.com")
            .withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withGender("MALE")
            .withNationality("US").withPhone("8482131").withEmail("hans@example.com")
            .withAddress("chicago ave").build();
    public static final Person JOEL = new PersonBuilder().withName("Tan Wei Hao Joel").withGender("MALE")
            .withNationality("SG").withPhone("81385542").withEmail("joeltan98@hotmail.com")
            .withAddress("Lakeside").build();
    public static final Person WEIQUAN = new PersonBuilder().withName("Tsu Wei Quan").withGender("FEMALE")
            .withNationality("SG").withPhone("96259561").withEmail("tsuweiquan@gmail.com")
            .withAddress("Pasiris").build();
    public static final Person NOVIN = new PersonBuilder().withName("Novin Tong").withGender("MALE")
            .withNationality("SG").withPhone("88888888").withEmail("E0176909@u.nus.edu")
            .withAddress("East Side").build();
    public static final Person JOELTAN = new PersonBuilder().withName("Joel Tan").withGender("MALE")
            .withNationality("SG").withPhone("98989898").withEmail("joel.twh@u.nus.edu")
            .withAddress("West Side").build();
    public static final Person TSURAJOVIN = new PersonBuilder().withName("Tsuraj Jovin").withGender("FEMALE")
            .withNationality("SG").withPhone("98765432").withEmail("tsurajovin@gmail.com")
            .withAddress("PSC Building").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withName(VALID_NAME_AMY).withGender(VALID_GENDER_FEMALE).withNationality(VALID_NATIONALITY_SG)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withGrade(VALID_GRADE_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder()
            .withName(VALID_NAME_BOB).withGender(VALID_GENDER_MALE).withNationality(VALID_NATIONALITY_CN)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getSingleValidTypicalPerson() {
        return new ArrayList<>(Arrays.asList(JOEL));
    }

    public static List<Person> getMultipleValidTypicalPerson() {
        return new ArrayList<>(Arrays.asList(JOEL, WEIQUAN, NOVIN, JOELTAN, TSURAJOVIN));
    }

    /**
     * This method creates an LinkListed of Person where it only contains Male Persons from the stub address book.
     * @return : a Linklist of male persons
     */
    public static LinkedList<Person> allMalePerson() {
        LinkedList<Person> maleList = new LinkedList<>();
        maleList.add(BENSON);
        maleList.add(CARL);
        maleList.add(DANIEL);
        maleList.add(GEORGE);
        return maleList;
    }

    /**
     * This method creates an LinkListed of Person where it only contains female Persons from the stub address book.
     * @return : a Linklist of female persons
     */
    public static LinkedList<Person> allFemalePerson() {
        LinkedList<Person> femaleList = new LinkedList<>();
        femaleList.add(ALICE);
        femaleList.add(ELLE);
        femaleList.add(FIONA);
        return femaleList;
    }

    public static Map<Nationality, Long> getTypicalPersonNationalityMap() {
        Map<Nationality, Long> typicalPersonMap = new HashMap<>();
        typicalPersonMap.put(new Nationality("SG"), Long.parseLong("4"));
        typicalPersonMap.put(new Nationality("US"), Long.parseLong("2"));
        typicalPersonMap.put(new Nationality("MY"), Long.parseLong("1"));
        return typicalPersonMap;
    }
}
