package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.group.AddGroup;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson and .isSameGroup comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueGroupList groups;

     /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */ {
        persons = new UniquePersonList();
        groups = new UniqueGroupList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons and Groups in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the group list with {@code groups}.
     * {@code groups} must not contain duplicate groups.
     *
     * @param groups Groups to replace list with.
     */
    public void setGroups(List<Group> groups) {
        this.groups.setGroups(groups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setGroups(newData.getGroupList());
    }

    // person - level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void updatePerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    // group - level operations

    /**
     * Returns true if a group with the same identity fields
     * as {@code group} exists in the address book.
     *
     * @param group Group to check for.
     * @return Check result.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    /**
     * Adds a group to the address book.
     * The group must not already exist in the address book.
     *
     * @param g Group to add.
     */
    public void createGroup(Group g) {
        groups.createGroup(g);
    }

    /**
     * Adds persons to a group in the address book.
     * The persons must not already exist in the group.
     * The group must exist in the address book.
     *
     * @param aG Contains group and persons to add to group.
     */
    public void addGroup(AddGroup aG) {
        groups.addGroup(aG);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     * @param key Group to remove.
     */
    public void removeGroup(Group key) {
        groups.remove(key);
    }

    /**
     * Return true if a person is already in the specified group.
     *
     * @param aG Contains group and person to check with.
     * @return Check result.
     */
    public boolean hasPersonInGroup(AddGroup aG) {
        return groups.contains(aG);
    }

    /**
     * Removes person from a group.
     *
     * @param g Group to remove person from.
     * @param p Person to be removed.
     */
    public void removeGroupPerson(Group g, Person p) {
        groups.removeGroupPerson(g, p);
    }

    //// util methods

    /**
     * Returns the size of persons and groups.
     *
     * @return String containing size of persons and group.
     */
    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons "
                + groups.asUnmodifiableObservableList().size() + " groups";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    /**
     * Returns the group list as {@code UnmodifiableObservableList}.
     *
     * @return Unmodifiable list.
     */
    @Override
    public ObservableList<Group> getGroupList() {
        return groups.asUnmodifiableObservableList();
    }

    /**
     * Returns true if both objects have the same fields.
     *
     * @param other Object to compare with.
     * @return Comparison result.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons) && groups.equals(((AddressBook) other).groups));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
