package org.jide.studentgradingsystem.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jide.studentgradingsystem.console.FileHandler;
import org.jide.studentgradingsystem.console.StudentGradingSystemData;
import org.jide.studentgradingsystem.console.StudentReport;

import java.io.File;
import java.util.List;


/**
 * The CourseReportScreen class creates a user interface for viewing and exporting course reports.
 * It displays student reports in a table format and provides options for exporting the report.
 */

public class CourseReportScreen {
    private VBox view;
    private StudentGradingSystemData data = StudentGradingSystemData.getInstance();

    /**
     * Constructor to initialize the CourseReportScreen.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    public CourseReportScreen(Stage primaryStage) {
        view = new VBox(10);

        // Add padding to the Vbox
        view.setPadding(new Insets(20, 20, 20, 20));

        Label courseLabel = new Label("Select Course:");
        ComboBox<String> courseDropdown = new ComboBox<>();
        courseDropdown.getItems().addAll("CS101 - Introduction to Programming", "CS102 - Data Structures", "MATH101 - Calculus I");

        TableView<StudentReport> table = new TableView<>();
        TableColumn<StudentReport, String> idColumn = new TableColumn<>("Student ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        TableColumn<StudentReport, String> nameColumn = new TableColumn<>("Student Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        TableColumn<StudentReport, Integer> testColumn = new TableColumn<>("Test Score");
        testColumn.setCellValueFactory(new PropertyValueFactory<>("testScore"));

        TableColumn<StudentReport, Integer> projectColumn = new TableColumn<>("Project Score");
        projectColumn.setCellValueFactory(new PropertyValueFactory<>("projectScore"));

        TableColumn<StudentReport, Integer> examColumn = new TableColumn<>("Exam Score");
        examColumn.setCellValueFactory(new PropertyValueFactory<>("examScore"));

        TableColumn<StudentReport, Integer> totalColumn = new TableColumn<>("Total Score");
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalScore"));

        table.getColumns().addAll(idColumn, nameColumn, testColumn, projectColumn, examColumn, totalColumn);

        // Refresh report when a course is selected
        courseDropdown.setOnAction(event -> {
            String selectedCourse = courseDropdown.getValue();
            if (selectedCourse != null) {
                List<StudentReport> reportData = data.getReportsForCourse(selectedCourse);
                table.getItems().clear();
                table.getItems().addAll(reportData); // Load updated report data
            }
        });

        Button exportButton = new Button("Export Report");
        exportButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                FileHandler.exportReportToFile(table.getItems(), file);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Report exported to " + file.getPath());
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> new MainScreen(primaryStage, "Instructor ID")); // Replace with actual ID if needed

        view.getChildren().addAll(courseLabel, courseDropdown, table, exportButton, backButton);
        primaryStage.setScene(new Scene(view, 800, 600));
    }
}
