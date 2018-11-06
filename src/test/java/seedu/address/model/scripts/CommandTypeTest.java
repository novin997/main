package seedu.address.model.scripts;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.script.CommandType;
import seedu.address.testutil.Assert;

public class CommandTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new CommandType(null));
    }

    @Test
    public void constructor_invalidCommandType_throwsIllegalArgumentException() {
        String invalidCommandType = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new CommandType(invalidCommandType));
    }

    @Test
    public void isValidCommand() {
        // null command
        Assert.assertThrows(NullPointerException.class, () -> CommandType.isValidCommand(null));

        // blank command
        assertFalse(CommandType.isValidCommand("")); // empty string
        assertFalse(CommandType.isValidCommand(" ")); // spaces only

        // invalid commands
        assertFalse(CommandType.isValidCommand("abc"));
        assertFalse(CommandType.isValidCommand("efg"));
        assertFalse(CommandType.isValidCommand("@2123"));
        assertFalse(CommandType.isValidCommand("123"));
        assertFalse(CommandType.isValidCommand("novin"));

        // valid commands
        assertTrue(CommandType.isValidCommand("add"));
        assertTrue(CommandType.isValidCommand("a"));
        assertTrue(CommandType.isValidCommand("addgroup"));
        assertTrue(CommandType.isValidCommand("ag"));
        assertTrue(CommandType.isValidCommand("add_testmarks"));
        assertTrue(CommandType.isValidCommand("adt"));
        assertTrue(CommandType.isValidCommand("clear"));
        assertTrue(CommandType.isValidCommand("c"));
        assertTrue(CommandType.isValidCommand("creategroup"));
        assertTrue(CommandType.isValidCommand("cg"));
        assertTrue(CommandType.isValidCommand("delete"));
        assertTrue(CommandType.isValidCommand("d"));
        assertTrue(CommandType.isValidCommand("deletegroup"));
        assertTrue(CommandType.isValidCommand("dg"));
        assertTrue(CommandType.isValidCommand("deletegroupstudent"));
        assertTrue(CommandType.isValidCommand("dgs"));
        assertTrue(CommandType.isValidCommand("distinto"));
        assertTrue(CommandType.isValidCommand("di"));
        assertTrue(CommandType.isValidCommand("edit"));
        assertTrue(CommandType.isValidCommand("e"));
        assertTrue(CommandType.isValidCommand("edt"));
        assertTrue(CommandType.isValidCommand("edit_test"));
        assertTrue(CommandType.isValidCommand("sendmail"));
        assertTrue(CommandType.isValidCommand("sm"));
        assertTrue(CommandType.isValidCommand("exit"));
        assertTrue(CommandType.isValidCommand("ex"));
        assertTrue(CommandType.isValidCommand("find"));
        assertTrue(CommandType.isValidCommand("f"));
        assertTrue(CommandType.isValidCommand("genlist"));
        assertTrue(CommandType.isValidCommand("gl"));
        assertTrue(CommandType.isValidCommand("display"));
        assertTrue(CommandType.isValidCommand("disp"));
        assertTrue(CommandType.isValidCommand("help"));
        assertTrue(CommandType.isValidCommand("h"));
        assertTrue(CommandType.isValidCommand("history"));
        assertTrue(CommandType.isValidCommand("his"));
        assertTrue(CommandType.isValidCommand("list"));
        assertTrue(CommandType.isValidCommand("l"));
        assertTrue(CommandType.isValidCommand("listgroup"));
        assertTrue(CommandType.isValidCommand("lg"));
        assertTrue(CommandType.isValidCommand("redo"));
        assertTrue(CommandType.isValidCommand("r"));
        assertTrue(CommandType.isValidCommand("select"));
        assertTrue(CommandType.isValidCommand("s"));
        assertTrue(CommandType.isValidCommand("selectgroup"));
        assertTrue(CommandType.isValidCommand("sg"));
        assertTrue(CommandType.isValidCommand("undo"));
        assertTrue(CommandType.isValidCommand("u"));
    }

    @Test
    public void test_hashcode() {
        CommandType commandType1 = new CommandType("add");
        CommandType commandType2 = new CommandType("undo");
        assertFalse(commandType1.hashCode() == commandType2.hashCode());
    }
}
