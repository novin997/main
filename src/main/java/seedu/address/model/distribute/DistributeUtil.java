package seedu.address.model.distribute;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.AddGroup;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupLocation;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 *
 */
public class DistributeUtil {

    public static final String MESSAGE_DUPLICATE_GROUP = "There exist another group with the same name.";
    public static final String MESSAGE_INDEX_NEGATIVE = "Index should be positive.";
    public static final String GROUP_LOCATION = "UNKNOWN";
    private static final Logger logger = LogsCenter.getLogger(DistributeUtil.class);
    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    public void setModel(Model model) {
        this.model = model;
    }

    /** This method in an integration of numberOfDifferentNationality method and paxPerNationality method.
     *  The primary function for this method is to create a Map of Nationality as key and Integer as value.
     *
     * @param allPerson : Takes in the allPerson list in order to count the number of different nationalities.
     * @return a Map for data processing in the nationality Distribution method.
     */
    public Map<Nationality, Integer> createNationalityMap(LinkedList<Person> allPerson) {
        Map<Nationality, Long> counts = numberOfDifferentNationality(allPerson);
        counts = paxPerNationality(counts);
        Map<Nationality, Integer> nationalityIntegerMap = new HashMap<>();
        for (int i = 0; i < counts.size(); i++) {
            String[] parseValue = counts.entrySet().toArray()[i].toString().split("=");
            Nationality nationalityCode = new Nationality(parseValue[0]);
            int numOfSameNationality = Integer.parseInt(parseValue[1]);
            nationalityIntegerMap.put(nationalityCode, numOfSameNationality);
        }
        return nationalityIntegerMap;
    }

    /**
     * This method does an exhaustive search in the all person list to find any person with the same nationality.
     *
     * @param key : The nationality field to search for in all personlist.
     * @param personLinkedList : Holds all person data.
     * @return return the Person object when nationality matches.
     */
    public Person findPerson(Nationality key, LinkedList<Person> personLinkedList) {
        requireAllNonNull(key, personLinkedList);
        for (Person p : personLinkedList) {
            if (p.getNationality().equals(key)) {
                Person tempPerson = p;
                personLinkedList.remove(p);
                return tempPerson;
            }
        }
        return null;
    }

    /**
     * This function shuffles all the person inside the LinkedList, with a specific seed.
     *
     * @param allPerson : Contain all Person list that is not shuffled.
     * @param seed : The seed to perform shuffle. Reason for this is to conduct unit testing.
     * @return return a shuffled Person List.
     */
    public LinkedList<Person> shuffle(LinkedList<Person> allPerson, Random seed) {
        requireAllNonNull(allPerson, seed);
        Collections.shuffle(allPerson, seed);
        return allPerson;
    }

    //Method implemented below was adapted from https://stackoverflow.com/a/22132422/3818748
    /**
     * This function takes in the list of all person and calculate the total number of different nationalities.
     * Returns a integer value which represent the number of different nationalities
     *
     * @param allPerson : Contain all Person List.
     * @return return a Map which contains the Nationality field as Key are the number of person belonging
     *         to that nationality as value.
     */
    public Map<Nationality, Long> numberOfDifferentNationality(LinkedList<Person> allPerson) {
        requireNonNull(allPerson);
        return allPerson.stream().collect(Collectors.groupingBy(e -> e.getNationality(), Collectors.counting()));
    }

    /**
     * This function will sort the map that holds the number of people with different nationalities.
     *
     * @param numberOfNationality : Contain all Person List.
     * @return return a Map which contains the Nationality field as Key are the number of person belonging
     *         to that nationality as value.
     */
    public Map<Nationality, Long> paxPerNationality(Map<Nationality, Long> numberOfNationality) {
        requireNonNull(numberOfNationality);
        return numberOfNationality.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                        LinkedHashMap::new));
    }


    /**
     * This method does the selective distribution of all person to the group they shall be in.
     * This method does the distribution for Male gender to every group first before repeating the process for female.
     *
     * @param index : The number of group. Acts as a pointer to which group should we add the person into.
     * @param groupArrayList : Acts as a group with each elements are the group. The sub array are the persons.
     * @param genderLinkList : The list of single-gender person.
     * @param loopCounter : A counter to ensure Person are added incrementally to every group.
     * @param personArrayList : A pointer that points to the group element.
     * @param num : A pointer that points to the last index of the sub-arrayList
     */
    public void selectiveDistributionByGender(int index, ArrayList<ArrayList<Person>> groupArrayList,
                                              LinkedList<Person> genderLinkList,
                                              int loopCounter, int num, ArrayList<Person> personArrayList) {
        if (loopCounter < index) {
            personArrayList.add(genderLinkList.getLast());
            groupArrayList.add(num, personArrayList);
        } else {
            int z = loopCounter % index;
            personArrayList = groupArrayList.get(num);
            groupArrayList.get(z).add(personArrayList.size() - 1, genderLinkList.getLast());
        }
    }

    /**
     * This method does the distribution of person into a group.
     *
     * @param index : The number of group. Acts as a pointer to which group should we add the person into.
     * @param groupArrayList : Acts as a group with each elements are the group. The sub array are the persons.
     * @param loopCounter : A counter to ensure Person are added incrementally to every group.
     * @param personArrayList : A pointer that points to the group element.
     * @param person : The person object to add into the sub-array.
     */
    public void selectiveDistributionByNationality(int index, ArrayList<ArrayList<Person>> groupArrayList,
                                                   ArrayList<Person> personArrayList,
                                                   int loopCounter, Person person) {
        if (loopCounter < index) {
            personArrayList.add(person);
            groupArrayList.add(personArrayList);
        } else {
            int z = loopCounter % index;
            ArrayList temp = groupArrayList.get(z);
            groupArrayList.get(z).add(temp.size() - 1, person);
        }

    }
    /**
     * This function runs through the allPerson list and add the specific gender required into an LinkedList.
     *
     * @param allPerson : The list of allPerson in the addressbook.
     */
    public void filterGender(LinkedList<Person> allPerson,
                             LinkedList<Person> filteredGender, String gender) {
        requireAllNonNull(allPerson, filteredGender, gender);
        for (Person p : allPerson) {
            if (p.getGender().toString().equals(gender)) {
                filteredGender.add(p);
            }
        }
    }

    /**
     * This function concatenates the group index count behind the given group name.
     * Index shown to user will start from 1.
     *
     * @return the groupName that has been concatenated with an index behind.
     */
    public String groupNameConcatenation (int index, String groupName, Model model) throws CommandException {
        requireAllNonNull(index, groupName, model);
        if (index < 0) {
            logger.log(Level.WARNING, "Received a Negative value for Index.");
            throw new CommandException(MESSAGE_INDEX_NEGATIVE);
        }
        index = index + 1;
        groupName = groupName + String.valueOf(index);
        if (doesDuplicateGroupExist(groupName, model)) {
            logger.log(Level.WARNING, "Another Group Exist in the Management System.");
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }
        return groupName;
    }

    /**
     * This function checks if there is any other groups that have the same name.
     *
     * @param groupName : The string to be check if another groupname string exist.
     * @return false if there is no existing group.
     */
    public boolean doesDuplicateGroupExist(String groupName, Model model) {
        ObservableList<Group> allGroups = model.getFilteredGroupList();
        requireNonNull(allGroups);
        for (Group gN : allGroups) {
            if (gN.getGroupName().toString().equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function will check all n number of groupName with the existing address book for existing groups.
     *
     * @param index : Number of groups the user desire.
     * @param groupName : The name of the group the user desire.
     * @throws CommandException if there exist a duplicate groupName.
     */
    public void doesGroupNameExist(int index, String groupName, Model model) throws CommandException {
        requireAllNonNull(index, groupName, model);
        for (int i = index; i > 0; i--) {
            groupNameConcatenation(i, groupName, model);
        }
    }

    /**
     * This method will creates a new group with a given groupname.
     *
     * @param toCreateGroupName : The groupName that has been concatenated with index
     * @return returns the group object that has been created.
     */
    public Group groupBuilder(String toCreateGroupName) {
        requireNonNull(toCreateGroupName);
        GroupName parseGroupName = new GroupName(toCreateGroupName);
        GroupLocation parseGroupLocation = new GroupLocation(GROUP_LOCATION);
        Set<Tag> tags = new HashSet<>();
        return new Group(parseGroupName, parseGroupLocation, tags);
    }

    /** This method creates a group. However it does not do a commit after addition of group.
     *
     * @param group : Group object to be created.
     * @throws CommandException : if there exist another group with the same object.
     */
    public void createGroupWithoutCommit(Group group, Model model) throws CommandException {
        requireAllNonNull(group, model);
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(group);
        createGroupCommand.setShouldCommit(false);
        createGroupCommand.execute(model, commandHistory);
        createGroupCommand.setShouldCommit(true);
    }

    /** This method add a Person into a group. However it does not do a commit after addition into group.
     *
     * @param addGroup : AddGroup Object to be executed.
     * @throws CommandException : When there is an Invalid Group or Person Index found.
     */
    public void addPersonIntoGroupWithoutCommit(AddGroup addGroup, Model model) throws CommandException {
        requireAllNonNull(addGroup, model);
        AddGroupCommand personToAdd = new AddGroupCommand(addGroup);
        personToAdd.setShouldCommit(false);
        personToAdd.execute(model, commandHistory);
        personToAdd.setShouldCommit(true);
    }

    /**
     * This method returns the index of the particular group in the address book.
     *
     * @param group : The group to search for.
     * @return Return the Index value of the group.
     */
    public Index returnGroupIndex(Group group, Model model) {
        requireAllNonNull(group, model);
        ObservableList<Group> allGroups = model.getFilteredGroupList();
        for (int i = 0; i < allGroups.size(); i++) {
            if (group.isSameGroup(allGroups.get(i))) {
                return Index.fromZeroBased(i);
            }
        }
        return Index.fromZeroBased(0);
    }

    /**
     * This method returns the index of the particular person in the address book.
     *
     * @param person : The person to search for.
     * @return Return the Index value of the person.
     */
    public Index returnPersonIndex(Person person, Model model) {
        requireAllNonNull(person, model);
        ObservableList<Person> allPerson = model.getFilteredPersonList();
        for (int i = 0; i < allPerson.size(); i++) {
            if (person.isSamePerson(allPerson.get(i))) {
                return Index.fromZeroBased(i);
            }
        }
        return null;
    }


    /**
     * This Method Create the require group by the user and add the specific person into the group.
     * Works for multiple groups.
     *
     * @param groupArrayList : Total number of groups
     * @param groupName : Groupname set by the User
     * @throws CommandException if Index of a group was return as 0.
     */
    public void distributeProcess(ArrayList<ArrayList<Person>> groupArrayList,
                                   String groupName) throws CommandException {
        ObservableList<Person> allPerson = model.getFilteredPersonList();
        for (int i = 0; i < groupArrayList.size(); i++) {
            String toCreateGroupName = groupNameConcatenation(i, groupName, model);
            Group newGroup = groupBuilder(toCreateGroupName);
            createGroupWithoutCommit(newGroup, model);
            Index groupIndex = returnGroupIndex(newGroup, model);
            requireNonNull(groupIndex);
            logger.log(Level.INFO, "Preparing distribution of Students into Group " + toCreateGroupName
                    + " with the Index of " + groupIndex.getOneBased());
            logger.log(Level.INFO, toCreateGroupName + " will contain " + groupArrayList.get(i).size()
                    + " person.");
            for (int j = 0; j < groupArrayList.get(i).size(); j++) {
                logger.log(Level.INFO, "Adding Person " + groupArrayList.get(i).get(j) + " into Group "
                        + toCreateGroupName);
                for (int k = 0; k < allPerson.size(); k++) {
                    if (allPerson.get(k).equals(groupArrayList.get(i).get(j))) {
                        Set<Index> personIndices = new HashSet<>();
                        personIndices.add(Index.fromZeroBased(k));
                        AddGroup addSinglePersonIntoGroup = new AddGroup(groupIndex, personIndices);
                        addPersonIntoGroupWithoutCommit(addSinglePersonIntoGroup, model);
                    }
                }
            }
        }
    }

}
