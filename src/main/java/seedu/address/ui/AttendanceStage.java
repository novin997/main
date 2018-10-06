package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import seedu.address.model.person.Person;

/**
 * The UI Component to the Attendance List.
 */
public class AttendanceStage extends UiPart<Stage> {

    private static final String FXML = "AttendanceStage.fxml";

    //private Stage secondaryStage;

    private ObservableList<Person> persons;

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, String> phoneColumn;

    @FXML
    private TableColumn<Person, String> addressColumn;

    @FXML
    private TableColumn<Person, String> emailColumn;


    /**
     * Create new Stage for AttendanceList.
     */
    public AttendanceStage(ObservableList<Person> persons) {
        this(new Stage());
        this.persons = persons;
    }


    /**
     * Setup the Stage for AttendanceList.
     */
    public AttendanceStage(Stage newStage) {
        super(FXML, newStage);
        //secondaryStage = newStage;
        newStage.setMaximized(true);
        newStage.show();
        registerAsAnEventHandler(this);
    }

    /**
     * Using ObservableList to generate the Attendance List.
     */
    @FXML
    public void generateAttendance() {
        personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Initialize the person table with all the data
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        personTable.setItems(persons);
    }

    public TableView<Person> getPersonTable() {
        return personTable;
    }

}
