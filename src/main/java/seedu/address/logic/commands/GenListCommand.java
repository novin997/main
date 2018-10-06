package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.util.PrinterUtilAttendanceList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.AttendanceStage;

import static java.util.Objects.requireNonNull;

/**
 * Generate a attendance list for all the people in the address book
 */
public class GenListCommand extends Command {

    public static final String COMMAND_WORD = "genlist";
    public static final String COMMAND_WORD_2 = "gl";

    public static final String MESSAGE_GENERATE_ATTENDANCE_LIST = "Attendance list has been generated";
    public static final String MESSAGE_EMPTY_LIST = "The list is currently empty";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generate the attendance list with the currently selected students.\n"
            + "Parameters: PRINT\n"
            + "Example: " + COMMAND_WORD + " print";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        AttendanceStage stage;
        ObservableList<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_LIST);
        }

        stage = new AttendanceStage(lastShownList);
        PrinterUtilAttendanceList printerUtil = new PrinterUtilAttendanceList(stage);
        stage.generateAttendance();

        printerUtil.calculateScaleForPrint();
        printerUtil.applyScaleToNode();
        printerUtil.executePrint();
        printerUtil.undoScaleToNode();

        return new CommandResult(String.format(MESSAGE_GENERATE_ATTENDANCE_LIST));
    }
}
