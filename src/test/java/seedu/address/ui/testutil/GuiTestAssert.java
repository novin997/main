package seedu.address.ui.testutil;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import guitests.guihandles.GroupCardHandle;
import guitests.guihandles.GroupListPanelHandle;
import guitests.guihandles.PersonCardHandle;
import guitests.guihandles.PersonListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(PersonCardHandle expectedCard, PersonCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getAddress(), actualCard.getAddress());
        assertEquals(expectedCard.getEmail(), actualCard.getEmail());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getPhone(), actualCard.getPhone());
        assertEquals(expectedCard.getTags(), actualCard.getTags());
    }

    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertGroupCardEquals(GroupCardHandle expectedCard, GroupCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getGroupLocation(), actualCard.getGroupLocation());
        assertEquals(expectedCard.getGroupName(), actualCard.getGroupName());
        assertEquals(expectedCard.getTags(), actualCard.getTags());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedPerson}.
     */
    public static void assertCardDisplaysPerson(Person expectedPerson, PersonCardHandle actualCard) {
        assertEquals(expectedPerson.getName().fullName, actualCard.getName());
        assertEquals(expectedPerson.getPhone().value, actualCard.getPhone());
        assertEquals(expectedPerson.getEmail().value, actualCard.getEmail());
        assertEquals(expectedPerson.getAddress().value, actualCard.getAddress());
        assertEquals(expectedPerson.getTags().stream().map(tag -> tag.tagName).collect(Collectors.toList()),
                actualCard.getTags());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedGroup}.
     */
    public static void assertCardDisplaysGroup(Group expectedGroup, GroupCardHandle actualCard) {
        assertEquals(expectedGroup.getGroupName().groupName, actualCard.getGroupName());
        assertEquals(expectedGroup.getGroupLocation().value, actualCard.getGroupLocation());
        assertEquals(expectedGroup.getTags().stream().map(tag -> tag.tagName).collect(Collectors.toList()),
                actualCard.getTags());
    }

    /**
     * Asserts that the list in {@code personListPanelHandle} displays the details of {@code persons} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PersonListPanelHandle personListPanelHandle, Person... persons) {
        for (int i = 0; i < persons.length; i++) {
            personListPanelHandle.navigateToCard(i);
            assertCardDisplaysPerson(persons[i], personListPanelHandle.getPersonCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code personListPanelHandle} displays the details of {@code persons} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PersonListPanelHandle personListPanelHandle, List<Person> persons) {
        assertListMatching(personListPanelHandle, persons.toArray(new Person[0]));
    }

    /**
     * Asserts that the list in {@code groupListPanelHandle} displays the details of {@code groups} correctly and
     * in the correct order.
     */
    public static void assertGroupListMatching(GroupListPanelHandle groupListPanelHandle, Group... groups) {
        for (int i = 0; i < groups.length; i++) {
            groupListPanelHandle.navigateToCard(i);
            assertCardDisplaysGroup(groups[i], groupListPanelHandle.getGroupCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code groupListPanelHandle} displays the details of {@code groups} correctly and
     * in the correct order.
     */
    public static void assertGroupListMatching(GroupListPanelHandle groupListPanelHandle, List<Group> groups) {
        assertGroupListMatching(groupListPanelHandle, groups.toArray(new Group[0]));
    }

    /**
     * Asserts the size of the list in {@code personListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(PersonListPanelHandle personListPanelHandle, int size) {
        int numberOfPeople = personListPanelHandle.getListSize();
        assertEquals(size, numberOfPeople);
    }

    /**
     * Asserts the size of the list in {@code groupListPanelHandle} equals to {@code size}.
     */
    public static void assertGroupListSize(GroupListPanelHandle groupListPanelHandle, int size) {
        int numberOfGroup = groupListPanelHandle.getListSize();
        assertEquals(size, numberOfGroup);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}
