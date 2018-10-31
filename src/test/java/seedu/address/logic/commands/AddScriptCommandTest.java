package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains tests for AddScriptCommand.
 */
public class AddScriptCommandTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    private Person novin = new PersonBuilder().withName("Novin Tong Yong Kang").withGender("M").withNationality("SG")
            .withAddress("Pasir Ris street 99,#02-25").withEmail("novin@example.com")
            .withPhone("99999999").withTags("friends").build();

    private Person weiQuan = new PersonBuilder().withName("Tsu Wei Quan").withGender("F").withNationality("SG")
            .withAddress("Tampines street 82,#02-33").withEmail("TsuTheBoss@gmail.com")
            .withPhone("66666666").withTags("owesMoney").build();

    private final String testFilesLocation = "/src/test/data/AddScriptCommandTest/";
    private final String validTextFile = "ValidAddScriptCommand";
    private final String missingTextFile = "MissingTextFile";
    private final String invalidTextFile = "InvalidAddCommand";

    private final String invalidLinesNumbers = "2";

    private String defaultProjectLocation;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Path projectPath = Paths.get("");
        defaultProjectLocation = projectPath.toAbsolutePath().toString();
    }

    @Test
    public void execute_addScriptCommand_successful() {
        String validFileName = validTextFile;
        String expectedMessage = String.format(AddScriptCommand.MESSAGE_SUCCESS,
                validTextFile + AddScriptCommand.TEXT_EXTENSION);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(novin);
        expectedModel.commitAddressBook();

        expectedModel.addPerson(weiQuan);
        expectedModel.commitAddressBook();

        assertCommandSuccess(new AddScriptCommand(validFileName),
                model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_fileMissing_throwsIoException() {
        String expectedMessage = String.format(AddScriptCommand.MESSAGE_FILE_MISSING,
                missingTextFile + AddScriptCommand.TEXT_EXTENSION);
        AddScriptCommand addScriptCommand = new AddScriptCommand(missingTextFile);
        CommandResult commandResult = addScriptCommand.execute(model, commandHistory);
        assertEquals(commandResult.feedbackToUser, expectedMessage);
    }

    @Test
    public void execute_wrongCommand_throwsParseException() {
        String expectedMessage = String.format(AddScriptCommand.MESSAGE_ADD_ERROR,
                invalidLinesNumbers, invalidTextFile + AddScriptCommand.TEXT_EXTENSION);

        AddScriptCommand addScriptCommand = new AddScriptCommand (
                defaultProjectLocation + testFilesLocation, invalidTextFile);

        CommandResult commandResult = addScriptCommand.execute(model, commandHistory);
        assertEquals(commandResult.feedbackToUser, expectedMessage);
    }

}
