package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EmailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.email.Message;
import seedu.address.model.email.Subject;


/**
 * Parses input arguments and creates a new EmailCommand object
 */
public class EmailCommandParser implements Parser<EmailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EmailCommand
     * and returns an EmailCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EmailCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SUBJECT, PREFIX_MESSAGE);
        if (!arePrefixesPresent(argMultimap, PREFIX_SUBJECT, PREFIX_MESSAGE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailCommand.MESSAGE_USAGE));
        }
        boolean isGroupCommand = isGroup(args);
        boolean isMultipleIndex = isMultipleIndex(argMultimap.getPreamble());
        Subject subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        Message message = ParserUtil.parseMessage(argMultimap.getValue(PREFIX_MESSAGE).get());

        if (!isMultipleIndex && !isGroupCommand) {
            Index index;
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailCommand.MESSAGE_USAGE), pe);
            }
            return new EmailCommand(index, subject, message);
        } else if (isMultipleIndex && !isGroupCommand) {
            List<Index> indexList;
            try {
                indexList = ParserUtil.parseMultipleIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailCommand.MESSAGE_USAGE), pe);
            }
            return new EmailCommand(indexList, subject, message);
        } else {
            Index index;
            try {
                index = ParserUtil.parseGroupIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailCommand.MESSAGE_USAGE), pe);
            }
            return new EmailCommand(index, subject, message, isGroupCommand);
        }

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if index argument contains more than 1 index separated by ",".
     */
    private static boolean isMultipleIndex(String arg) {
        return (arg.contains(","));
    }

    /**
     * Returns true if index argument contains prefix for group.
     */
    private static boolean isGroup(String arg) {
        return (arg.contains(PREFIX_GROUP_INDEX.toString()));
    }
}
