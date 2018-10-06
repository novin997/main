package seedu.address.commons.util;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.TableView;
import javafx.scene.transform.Scale;
import seedu.address.model.person.Person;
import seedu.address.ui.AttendanceStage;

/**
 * Print the Attendance List from the TableView
 * https://stackoverflow.com/questions/31231021/javafx8-print-api-how-to-set-correctly-the-printable-area
 */
public class PrinterUtilAttendanceList {
    private Printer printer;
    private PageLayout pageLayout;
    private PrinterJob printerJob;
    private TableView<Person> tableView;
    private Scale scale;


    public PrinterUtilAttendanceList(AttendanceStage stage){
        this.tableView = stage.getPersonTable();
        this.printer = Printer.getDefaultPrinter();
        this.pageLayout = printer.createPageLayout(
                Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        this.printerJob = PrinterJob.createPrinterJob();
    }

    public void calculateScaleForPrint() {
        double scaleX = pageLayout.getPrintableWidth() / tableView.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / tableView.getBoundsInParent().getHeight();

        scale = new Scale(scaleX,scaleY);
    }

    public void applyScaleToNode() {
        tableView.getTransforms().add(scale);
    }

    public void undoScaleToNode() {
        tableView.getTransforms().remove(scale);
    }

    public void executePrint() {
        if (printerJob != null && printerJob.showPrintDialog(tableView.getScene().getWindow())) {
            boolean success = printerJob.printPage(pageLayout, tableView);
            if (success) {
                printerJob.endJob();
            }
        }
    }
}
