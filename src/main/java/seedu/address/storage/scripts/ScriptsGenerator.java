package seedu.address.storage.scripts;

/**
 * This class will help the program to generate scripts automatically by BruteForce.
 */
public class ScriptsGenerator {
    private static final String NEXT_LINE = System.lineSeparator();

    private static final String ADD_COMMAND = "n/Novin Tong Yong Kang g/M nat/SG p/99999999 "
            + "e/E0176909@u.nus.edu a/Pasir Ris street 99,#02-25 t/friends" + NEXT_LINE
            + "n/Tsu Wei Quan g/F nat/SG p/66666666 e/tsuweiquan@gmail.com "
            + "a/Tampines street 82,#02-33 t/owesMoney" + NEXT_LINE
            + "n/TsuraJovin g/M nat/SG p/98743546 e/tsurajovin@gmail.com "
            + "a/Bedok street 32 t/rich" + NEXT_LINE
            + "n/Joel g/M nat/SG p/9784230 e/joeltan98@hotmail.com "
            + "a/Jurong West Street 52 t/pro" + NEXT_LINE
            + "n/JoelTan g/M nat/SG p/9784230 e/joel.twh@u.nus.edu "
            + "a/Jurong West Street 52 t/pro" + NEXT_LINE;

    private static final String GROUP_COMMAND = "n/tut[01] l/e1-11-20 t/ma1301" + NEXT_LINE
            + "n/tut[02] l/e3-11-20 t/ma1301" + NEXT_LINE
            + "n/tut[03] l/e5-11-20 t/ma1301" + NEXT_LINE
            + "n/tut[04] l/e11-11-20 t/ma1301" + NEXT_LINE
            + "n/tut[05] l/s22-11-20 t/ma1301" + NEXT_LINE
            + "n/tut[06] l/as4-11-20 t/ma1301" + NEXT_LINE
            + "n/tut[07] l/e9-3-2 t/ma1301" + NEXT_LINE
            + "n/tut[08] l/e5-1-2 t/ma1301" + NEXT_LINE;

    public static String getAddCommand() {
        return ADD_COMMAND;
    }

    public static String getGroupCommand() {
        return GROUP_COMMAND;
    }
}
