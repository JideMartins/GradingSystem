package org.jide.studentgradingsystem.gui;
import org.jide.studentgradingsystem.console.Student;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jide.studentgradingsystem.console.StudentGradingSystemData;
import org.jide.studentgradingsystem.console.StudentReport;

/**
 * The GradeManagementScreen class creates a user interface for managing student grades.
 * It allows instructors to select a course, select a student, enter scores, and add grades.
 */

public class GradeManagementScreen {
    private VBox view;
    private StudentGradingSystemData data = StudentGradingSystemData.getInstance(); // Singleton instance for storing reports


    /**
     * Constructor to initialize the GradeManagementScreen.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    public GradeManagementScreen(Stage primaryStage) {
        view = new VBox(10);

        // Add padding to the Vbox
        view.setPadding(new Insets(20, 20, 20, 20));

        Label courseLabel = new Label("Select Course:");
        ComboBox<String> courseDropdown = new ComboBox<>();
        courseDropdown.getItems().addAll("CS101 - Introduction to Programming", "CS102 - Data Structures", "MATH101 - Calculus I");

        Label studentLabel = new Label("Select Student:");
        ComboBox<Student> studentDropdown = new ComboBox<>();

        // Load students based on course selection
        courseDropdown.setOnAction(event -> {
            String selectedCourse = courseDropdown.getValue();
            studentDropdown.getItems().clear();

            if (selectedCourse.contains("CS101")) {
                studentDropdown.getItems().addAll(
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-001", "Alice Adedapo"),
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-002", "Bob Banjo"),
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-003", "Alison Becker"),
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-004", "Reece Joan"),
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-005", "Sandra McDonald"),
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-006", "Ayomide Hussein")
                );
            } else if (selectedCourse.contains("CS102")) {
                studentDropdown.getItems().addAll(
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-001", "Alice Adedapo"),
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-005", "Sandra McDonald"),
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-006", "Ayomide Hussein")
                );
            } else if (selectedCourse.contains("MATH101")) {
                studentDropdown.getItems().addAll(
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-007", "Diana Crawford"),
                        new org.jide.studentgradingsystem.console.Student("BPTN-06-008", "Edward Beecroft")
                );
            }
        });


        // Labels and text fields for entering scores
        Label testLabel = new Label("Test Score (0-30):");
        TextField testField = new TextField();
        Label projectLabel = new Label("Project Score (0-20):");
        TextField projectField = new TextField();
        Label examLabel = new Label("Exam Score (0-50):");
        TextField examField = new TextField();

        // Total score label
        Label totalLabel = new Label("Total Score:");
        Label totalScore = new Label("0");

        // Update total score dynamically as fields are populated
        testField.textProperty().addListener((obs, oldVal, newVal) -> updateTotal(testField, projectField, examField, totalScore));
        projectField.textProperty().addListener((obs, oldVal, newVal) -> updateTotal(testField, projectField, examField, totalScore));
        examField.textProperty().addListener((obs, oldVal, newVal) -> updateTotal(testField, projectField, examField, totalScore));

        // Add grade button
        Button addGradeButton = new Button("Add Grade");
        addGradeButton.setOnAction(event -> {
            String course = courseDropdown.getValue();
            Student student = studentDropdown.getValue();

            // Parse scores and validate
            if (validateInputs(testField, projectField, examField)){
                try {
                    int test = Integer.parseInt(testField.getText());
                    int project = Integer.parseInt(projectField.getText());
                    int exam = Integer.parseInt(examField.getText());
                    int total = test + project + exam;

                    if (course != null && student != null) {
                        String studentID = student.getStudentID();
                        String studentName = student.getStudentName();
                        // Store the grade in the system data
                        data.addGrade(new StudentReport(studentID, studentName, test, project, exam, course));

                        // Update total score display and show confirmation alert
                        totalScore.setText(String.valueOf(total));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Grade added for " + student + " in " + course);
                        alert.showAndWait();

                        // Clear the fields after adding
                        testField.clear();
                        projectField.clear();
                        examField.clear();
                        totalScore.setText("0");
                    } else {
                        showAlert("Please select a course and a student.");
                    }
                } catch (NumberFormatException e) {
                    showAlert("Invalid input. Please enter valid scores.");
                }
            }
        });

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> new MainScreen(primaryStage, "Instructor ID")); // Replace with actual ID if needed

        view.getChildren().addAll(courseLabel, courseDropdown, studentLabel, studentDropdown, testLabel, testField,
                projectLabel, projectField, examLabel, examField, totalLabel, totalScore, addGradeButton, backButton);

        primaryStage.setScene(new Scene(view, 800, 800));
    }

    /**
     * Updates the total score based on the values entered in the score fields.
     *
     * @param testField    The text field for the test score.
     * @param projectField The text field for the project score.
     * @param examField    The text field for the exam score.
     * @param totalLabel   The label displaying the total score.
     */

    private void updateTotal(TextField testField, TextField projectField, TextField examField, Label totalLabel) {
        try {
            int test = Integer.parseInt(testField.getText());
            int project = Integer.parseInt(projectField.getText());
            int exam = Integer.parseInt(examField.getText());
            int total = test + project + exam;
            totalLabel.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            totalLabel.setText("Invalid input.");
        }
    }

    /**
     * Validates the input fields for the specified rules.
     * @param testField text field to validate
     * @param projectField
     * @param examField
     * @return true if all inputs are valid, false otherwise.
     */
    private boolean validateInputs(TextField testField, TextField projectField, TextField examField) {
        boolean valid = true;
        // Validate test score
        if (!validateField(testField, 0, 30)) { valid = false; }
        // Validate project score
        if (!validateField(projectField, 0, 20)) { valid = false; }
        // Validate exam score
        if (!validateField(examField, 0, 50)) { valid = false; } return valid;
    }


    /**
     * Validates a text field to ensure it contains an integer within the specified range.
     * @param field
     * @param min minimum valid value
     * @param max maximum valid value
     * @return true if the field contains a valid value, false otherwise
     */
    private boolean validateField(TextField field, int min, int max) {
        try {
            int value = Integer.parseInt(field.getText());
            if (value < min || value > max) {
                field.setStyle("-fx-border-color: red;");
                showAlert("Value out of range. Please enter a value between " + min + " and " + max + ".");
                return false;
            } else {
                field.setStyle(null); // Reset the style if valid
                }
        } catch (NumberFormatException e) {
            field.setStyle("-fx-border-color: red;");
            showAlert("Invalid input. Please enter an integer.");
            return false;
        }
        return true;
    }

    /**
     * Displays an alert with the specified message.
     *
     * @param message The message to be displayed in the alert.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
}
