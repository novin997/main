package seedu.address.storage.scripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.UserPrefs;

/**
 * Load the and add default scripts into in the default directory of the Application.
 */
public class ScriptSetup {
    public static final String ADD_GROUPS_FILE = "AddGroups.txt";
    public static final String ADD_PERSONS_FILE = "AddPersons.txt";
    public static final String DEFAULT_SCRIPT_FOLDER = "/scripts/";
    public static final String DEFAULT_LOGS_FOLDER = "/logs/";

    private static final Logger logger = LogsCenter.getLogger(ScriptSetup.class);

    private String defaultLocation;
    private PathName pathName;

    public ScriptSetup() {
        this.defaultLocation = FileUtil.getRootLocation();
        this.pathName = new PathName();
    }

    /**
     * Creates a script folder and add some sample text files if folder is missing.
     *
     * @param userPrefs is the UserPrefs of the program
     */
    public void execute(UserPrefs userPrefs) {
        Path scriptPath;
        Path logsPath;
        String scriptFolder = userPrefs.getScriptFileDirectory();
        boolean isPathValid = pathName.isValidPath(scriptFolder);
        if (isPathValid) {
            scriptPath = FileUtil.getPath(defaultLocation + scriptFolder);
            logsPath = FileUtil.getPath(defaultLocation + scriptFolder + DEFAULT_LOGS_FOLDER);
        } else {
            scriptPath = FileUtil.getPath(defaultLocation + DEFAULT_SCRIPT_FOLDER);
            logsPath = FileUtil.getPath(defaultLocation + DEFAULT_SCRIPT_FOLDER + DEFAULT_LOGS_FOLDER);
            userPrefs.setScriptFileDirectory(DEFAULT_SCRIPT_FOLDER);
        }
        try {
            FileUtil.createFolder(scriptPath);
            FileUtil.createFolder(logsPath);
            addSampleTextFiles(scriptFolder, defaultLocation);
        } catch (IOException ioe) {
            logger.info(scriptFolder + " is not a valid directory "
                    + "and a default script folder will be automatically generated");
        }
    }

    /**
     * Add some sample text files
     * @param scriptFolder is the folder name for scripts
     * @param defaultLocation root folder of program
     */
    public void addSampleTextFiles(String scriptFolder, String defaultLocation) {
        File file = new File(defaultLocation + scriptFolder + ADD_PERSONS_FILE);
        File file2 = new File(defaultLocation + scriptFolder + ADD_GROUPS_FILE);
        try {
            FileUtil.writeToTextFile(file, ScriptsGenerator.getAddCommand());
            FileUtil.writeToTextFile(file2, ScriptsGenerator.getGroupCommand());
        } catch (IOException ioe) {
            logger.info("Sample Text cannot be created");
        }
    }

    public String getDefaultLocation() {
        return this.defaultLocation;
    }
}
