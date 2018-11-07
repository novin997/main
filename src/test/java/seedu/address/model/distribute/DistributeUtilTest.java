package seedu.address.model.distribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_FLAG_WORD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_FEMALE;
import static seedu.address.model.distribute.DistributeUtil.GROUP_LOCATION;
import static seedu.address.model.person.Gender.VALID_GENDER_MALE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonNationalityMap;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.VersionedAddressBook;
import seedu.address.model.group.AddGroup;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupLocation;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.Assert;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class DistributeUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private ObservableList<Person> allPersons = model.getFilteredPersonList();
    private DistributeUtil distUtil = new DistributeUtil();
    private CommandHistory commandHistory = new CommandHistory();

    private ObservableList<Person> setUpObservableListStub() {
        AddressBook stubAddressBook = getTypicalAddressBook();
        UserPrefs stubUserPrefs = new UserPrefs();
        Model model = new ModelManager(stubAddressBook, stubUserPrefs);
        ObservableList<Person> allPersonStub = model.getFilteredPersonList();
        return allPersonStub;
    }

    @Test
    public void shuffleTest() {
        Random stubSeed = new Random(96259561);
        LinkedList<Person> allPersonList = new LinkedList<>(allPersons);
        Collections.shuffle(allPersonList, stubSeed);
        assertTrue(distUtil.shuffle(allPersonList, stubSeed).equals(allPersonList));

        //Test when the seed is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.shuffle(allPersonList, null));

        //Test when LinkList is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.shuffle(null, stubSeed));


        //if the allperson list is empty
        allPersonList.clear();
        Collections.shuffle(allPersonList, stubSeed);
        assertTrue(distUtil.shuffle(allPersonList, stubSeed).equals(allPersonList));

    }

    @Test
    public void findPersonTest() {
        ObservableList<Person> allPersonsStub = setUpObservableListStub();
        LinkedList<Person> allPersonLlStub = new LinkedList<>(allPersonsStub);
        Nationality sgNationality = new Nationality("SG");
        Person actualPerson = distUtil.findPerson(sgNationality, allPersonLlStub);

        // Testing if nationality is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.findPerson(null, allPersonLlStub));

        // Testing if LinkedList is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.findPerson(sgNationality, null));

        // testing if it return the first person it found with SG nationality
        assertEquals(ALICE, actualPerson);

        // testing that it should not be equal since daniel is the 2nd person with SG
        assertNotEquals(DANIEL, actualPerson);

        //remove ALICE and check if it is able to find DANIEL now.
        allPersonLlStub.removeFirst();
        actualPerson = distUtil.findPerson(sgNationality, allPersonLlStub);
        assertEquals(DANIEL, actualPerson);

        // testing if there is no person with SK nationality is not found
        Nationality skNationality = new Nationality("SK");
        actualPerson = distUtil.findPerson(skNationality, allPersonLlStub);
        assertNull(actualPerson);
    }

    @Test
    public void numberOfDifferentNationalityTest() {
        ObservableList<Person> allPersonsStub = setUpObservableListStub();
        LinkedList<Person> allPersonLlStub = new LinkedList<>(allPersonsStub);
        Map<Nationality, Long> actualMap = distUtil.numberOfDifferentNationality(allPersonLlStub);
        Map<Nationality, Long> expectedMap = getTypicalPersonNationalityMap();

        // Testing if LinkList is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.numberOfDifferentNationality(null));

        //Comparing both object are the same.
        assertEquals(expectedMap, actualMap);

        //reduce SG nationality counter by 1.
        Map<Nationality, Long> unexpectedMap = expectedMap;
        unexpectedMap.replace(new Nationality("SG"), Long.parseLong("3"));
        assertNotEquals(unexpectedMap, actualMap);

        //Comparing Map value is different when there is and extra person.
        unexpectedMap.replace(new Nationality("SG"), Long.parseLong("4"));
        unexpectedMap.put(new Nationality("IN"), Long.parseLong("1"));
        assertNotEquals(unexpectedMap, actualMap);

        //empty person list which creates a empty map
        allPersonLlStub.clear();
        actualMap = distUtil.numberOfDifferentNationality(allPersonLlStub);
        expectedMap.clear();
        assertEquals(expectedMap, actualMap);

    }

    @Test
    public void paxPerNationalityTest() {
        ObservableList<Person> allPersonsStub = setUpObservableListStub();
        LinkedList<Person> allPersonLlStub = new LinkedList<>(allPersonsStub);
        Map<Nationality, Long> differentNationalityMap = distUtil.numberOfDifferentNationality(allPersonLlStub);
        Map<Nationality, Long> actualSortedMap = distUtil.paxPerNationality(differentNationalityMap);
        String expectedResult = "{SG=4, US=2, MY=1}";

        // Testing if Map is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.paxPerNationality(null));

        //expects the map to be sorted in value order.
        assertEquals(expectedResult, actualSortedMap.toString());

        //Comparing the 2 map which are sorted by value. actual is sorted by value. unexpected is not sorted.
        Map<Nationality, Long> unexpectedMap = getTypicalPersonNationalityMap();
        assertNotEquals(unexpectedMap.toString(), actualSortedMap.toString());

        //empty person list
        allPersonLlStub.clear();
        differentNationalityMap = distUtil.numberOfDifferentNationality(allPersonLlStub);
        Map<Nationality, Long> actualEmptyMap = distUtil.paxPerNationality(differentNationalityMap);
        expectedResult = "{}";
        assertEquals(expectedResult, actualEmptyMap.toString());
    }

    @Test
    public void filterGenderTest() {
        ObservableList<Person> allStubPerson = setUpObservableListStub();
        LinkedList<Person> allPerson = new LinkedList<>(allStubPerson);
        LinkedList<Person> actualGenderPersons = new LinkedList<>();
        distUtil.filterGender(allPerson, actualGenderPersons, VALID_GENDER_MALE);

        //check if LinkList for allPerson is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.filterGender(null,
                actualGenderPersons, VALID_GENDER_MALE));

        //check if LinkList for filteredGender is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.filterGender(allPerson,
                null, VALID_GENDER_MALE));

        //check if Gender String is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.filterGender(allPerson,
                actualGenderPersons, null));

        //checks object if similar LinkList of all male persons
        assertEquals(TypicalPersons.allMalePerson(), actualGenderPersons);

        //checks object if similar LinkList of all female persons
        actualGenderPersons.clear();
        distUtil.filterGender(allPerson, actualGenderPersons, VALID_GENDER_FEMALE);
        assertEquals(TypicalPersons.allFemalePerson(), actualGenderPersons);

        //Invalid Gender Parameter
        actualGenderPersons.clear();
        distUtil.filterGender(allPerson, actualGenderPersons, INVALID_GENDER_FLAG_WORD);
        LinkedList<Person> expectedOutput = new LinkedList<>();
        assertEquals(expectedOutput, actualGenderPersons);

        //empty person list with male filter
        allPerson.clear();
        distUtil.filterGender(allPerson, actualGenderPersons, VALID_GENDER_MALE);
        assertEquals(expectedOutput, actualGenderPersons);
    }

    @Test
    public void existDuplicateGroupTest() throws CommandException {
        Group stubGroup = new GroupBuilder().withGroupName("TestGroup").build();
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(stubGroup);
        createGroupCommand.execute(model, commandHistory);
        assertTrue(distUtil.doesDuplicateGroupExist("TestGroup", model));

        assertFalse(distUtil.doesDuplicateGroupExist("CS2113Group", model));
    }

    @Test
    public void groupNameConcatenationTest() throws CommandException {
        String expectedGroupName = "T13-04-04";
        String actualGroupName = distUtil.groupNameConcatenation(3, "T13-04-0", model);

        // checks if the actual process will return me the same expectedGN
        assertEquals(expectedGroupName, actualGroupName);

        //check if group is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.groupNameConcatenation(1,
                null, model));

        //check if model is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.groupNameConcatenation(1,
                expectedGroupName, null));

        //check if index is negative
        thrown.expect(CommandException.class);
        thrown.expectMessage(DistributeUtil.MESSAGE_INDEX_NEGATIVE);
        distUtil.groupNameConcatenation(-1, expectedGroupName, model);
    }

    @Test
    public void groupBuilderTest() {
        //check if groupName is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.groupBuilder(null));

        //check if creation of group is same as expected
        String groupName = "CS2113-T13-04";
        Set<Tag> tags = new HashSet<>();
        Group actualGroup = distUtil.groupBuilder(groupName);
        Group expectedGroup = new Group(new GroupName(groupName), new GroupLocation(GROUP_LOCATION), tags);
        assertEquals(expectedGroup, actualGroup);
    }

    @Test
    public void createGroupWithoutCommitTest() throws CommandException {
        //check if Group is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.createGroupWithoutCommit(null, model));

        //check if model is null
        Group expectedGroup = new Group(new GroupName("TestGroup"), new GroupLocation("UNKNOWN"), new HashSet<>());
        Assert.assertThrows(NullPointerException.class, () -> distUtil.createGroupWithoutCommit(expectedGroup, null));

        //check if VersionAddressBook updates if createGroupWithoutCommit is ran
        //it should not update
        VersionedAddressBook currentVersion = ((ModelManager) model).getVersionedAddressBook();
        Group stubGroup = new GroupBuilder().build();
        distUtil.createGroupWithoutCommit(stubGroup, model);
        VersionedAddressBook expectedVersion = ((ModelManager) model).getVersionedAddressBook();
        assertEquals(expectedVersion, currentVersion);

        //add a new group with commit
        //it should update and fail.
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        currentVersion = ((ModelManager) model).getVersionedAddressBook();
        VersionedAddressBook copyOfCurrentVersion = new VersionedAddressBook(currentVersion);
        Group commitGroup = new GroupBuilder().withGroupName("CommitedGroup").build();
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(commitGroup);
        createGroupCommand.execute(model, commandHistory);
        expectedVersion = ((ModelManager) model).getVersionedAddressBook();
        assertNotEquals(expectedVersion, copyOfCurrentVersion);

        //if undo is ran, the version should be the same.
        expectedVersion.undo();
        assertEquals(expectedVersion.toString(), copyOfCurrentVersion.toString());
    }

    @Test
    public void addPersonIntoGroupWithoutCommitTest() throws CommandException {
        //check if addGroup is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.addPersonIntoGroupWithoutCommit(null, model));

        //check if VersionAddressBook updates if addPersonIntoGroupWithoutCommit
        //It should not update.
        //Creating a Group for person to be added into.
        Group stubGroup = new GroupBuilder().build();
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(stubGroup);
        createGroupCommand.execute(model, commandHistory);
        //Creating the Person Object
        Person stubPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(stubPerson);
        addCommand.execute(model, commandHistory);

        VersionedAddressBook currentVersion = ((ModelManager) model).getVersionedAddressBook();
        //copyOfCurrentVersion should store the pointer where only group is created.
        VersionedAddressBook copyOfCurrentVersion = new VersionedAddressBook(currentVersion);
        Index groupIndex = distUtil.returnGroupIndex(stubGroup, model);
        Index personIndex = distUtil.returnPersonIndex(stubPerson, model);
        Set<Index> personIndices = new HashSet<>();
        personIndices.add(personIndex);
        //Creating the AddGroup Object
        AddGroup addGroup = new AddGroup(groupIndex, personIndices);
        //addPersonIntoGroupWithoutCommit will add the person into the group
        distUtil.addPersonIntoGroupWithoutCommit(addGroup, model);
        //Pointer should not update as it does not commit.
        VersionedAddressBook expectedVersion = ((ModelManager) model).getVersionedAddressBook();
        //Check if pointer is still the same.
        assertEquals(expectedVersion.toString(), copyOfCurrentVersion.toString());

        // New Test Case:
        // Test if add using the default way(commit) will result in different results.
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        VersionedAddressBook version = ((ModelManager) model).getVersionedAddressBook();
        VersionedAddressBook updatedVersion = new VersionedAddressBook(version);
        //Create Group
        stubGroup = new GroupBuilder().build();
        createGroupCommand = new CreateGroupCommand(stubGroup);
        createGroupCommand.execute(model, commandHistory);
        groupIndex = distUtil.returnGroupIndex(stubGroup, model);
        Person newPerson = new PersonBuilder().withName("TsuTsuWeiKuan").withNationality("CN").withGender("M")
                .withEmail("tsuweikuan@zoho.com").build();
        addCommand = new AddCommand(newPerson);
        addCommand.execute(model, commandHistory);
        personIndex = distUtil.returnPersonIndex(newPerson, model);
        personIndices = new HashSet<>();
        personIndices.add(personIndex);
        //Creating the AddGroup Object
        addGroup = new AddGroup(groupIndex, personIndices);
        AddGroupCommand addGroupCommand = new AddGroupCommand(addGroup);
        addGroupCommand.execute(model, commandHistory);
        expectedVersion = ((ModelManager) model).getVersionedAddressBook();
        assertNotEquals(expectedVersion, updatedVersion);
    }

    @Test
    public void returnGroupIndexTest() {
        //check if group is null
        Assert.assertThrows(NullPointerException.class, () -> distUtil.returnGroupIndex(null, model));

        //check if model is null
        Group testGroup = distUtil.groupBuilder("testGroup");
        Assert.assertThrows(NullPointerException.class, () -> distUtil.returnGroupIndex(testGroup, null));

        //check is Index match with stub address book
        Index expectedIndex = Index.fromZeroBased(0);
        Group expectedGroup = new GroupBuilder().withGroupName("TUT[1]").withGroupLocation("E1-01-01")
                .withTags("morning").build();
        assertEquals(distUtil.returnGroupIndex(expectedGroup, model), expectedIndex);

        //check if index is 0 if no group is found.
        expectedIndex = Index.fromZeroBased(0);
        Group missingGroup = new GroupBuilder().withGroupName("TUT[1]").withGroupLocation("E1-01-01")
                .withTags("morning").build();
        assertEquals(distUtil.returnGroupIndex(missingGroup, model), expectedIndex);

    }
}
