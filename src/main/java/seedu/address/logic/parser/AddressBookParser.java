package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddScriptCommand;
import seedu.address.logic.commands.AddTestMarksCommand;
import seedu.address.logic.commands.AssignGradeCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.DeleteGroupPersonCommand;
import seedu.address.logic.commands.DeleteTestMarksCommand;
import seedu.address.logic.commands.DistributeCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditTestMarksCommand;
import seedu.address.logic.commands.EmailCommand;
import seedu.address.logic.commands.EmailLoginCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GenListCommand;
import seedu.address.logic.commands.GradeSummaryCommand;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListGroupCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ScriptCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.SelectGroupCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
        case AddCommand.COMMAND_WORD_2:
            return new AddCommandParser().parse(arguments);

        case AddScriptCommand.COMMAND_WORD:
        case AddScriptCommand.COMMAND_WORD_2:
            return new AddScriptCommand(arguments);

        //@@author rajdeepsh

        case CreateGroupCommand.COMMAND_WORD:
        case CreateGroupCommand.COMMAND_WORD_2:
            return new CreateGroupCommandParser().parse(arguments);

        case AddGroupCommand.COMMAND_WORD:
        case AddGroupCommand.COMMAND_WORD_2:
            return new AddGroupCommandParser().parse(arguments);

        case DeleteGroupCommand.COMMAND_WORD:
        case DeleteGroupCommand.COMMAND_WORD_2:
            return new DeleteGroupCommandParser().parse(arguments);

        case DeleteGroupPersonCommand.COMMAND_WORD:
        case DeleteGroupPersonCommand.COMMAND_WORD_2:
            return new DeleteGroupPersonCommandParser().parse(arguments);

        case SelectGroupCommand.COMMAND_WORD:
        case SelectGroupCommand.COMMAND_WORD_2:
            return new SelectGroupCommandParser().parse(arguments);

        //@@author

        case EditCommand.COMMAND_WORD:
        case EditCommand.COMMAND_WORD_2:
            return new EditCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
        case SelectCommand.COMMAND_WORD_2:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD_2:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD_2:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD_2:
            return new FindCommandParser().parse(arguments);

        case GenListCommand.COMMAND_WORD:
        case GenListCommand.COMMAND_WORD_2:
            return new GenListCommand();

        case EmailCommand.COMMAND_WORD:
        case EmailCommand.COMMAND_WORD_2:
            return new EmailCommandParser().parse(arguments);

        case EmailLoginCommand.COMMAND_WORD:
        case EmailLoginCommand.COMMAND_WORD_2:
            return new EmailLoginCommandParser().parse(arguments);

        case DistributeCommand.COMMAND_WORD:
        case DistributeCommand.COMMAND_WORD_2:
            return new DistributeCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD_2:
            return new ListCommand();

        case ListGroupCommand.COMMAND_WORD:
        case ListGroupCommand.COMMAND_WORD_2:
            return new ListGroupCommand();

        case HistoryCommand.COMMAND_WORD:
        case HistoryCommand.COMMAND_WORD_2:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD_2:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD_2:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
        case UndoCommand.COMMAND_WORD_2:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
        case RedoCommand.COMMAND_WORD_2:
            return new RedoCommand();

        case GradeSummaryCommand.COMMAND_WORD:
        case GradeSummaryCommand.COMMAND_WORD_2:
            return new GradeSummaryCommandParser().parse(arguments);

        case AddTestMarksCommand.COMMAND_WORD:
        case AddTestMarksCommand.COMMAND_WORD_2:
            return new AddTestMarksCommandParser().parse(arguments);

        case EditTestMarksCommand.COMMAND_WORD:
        case EditTestMarksCommand.COMMAND_WORD_2:
            return new EditTestMarksCommandParser().parse(arguments);

        case ScriptCommand.COMMAND_WORD:
        case ScriptCommand.COMMAND_WORD_2:
            return new ScriptCommandParser().parse(arguments);

        case AssignGradeCommand.COMMAND_WORD:
        case AssignGradeCommand.COMMAND_WORD_2:
            return new AssignGradeCommandParser().parse(arguments);

        case DeleteTestMarksCommand.COMMAND_WORD:
        case DeleteTestMarksCommand.COMMAND_WORD_2:
            return new DeleteTestMarksCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
