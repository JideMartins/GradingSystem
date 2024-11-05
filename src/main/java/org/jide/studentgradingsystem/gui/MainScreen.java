package org.jide.studentgradingsystem.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The MainScreen class creates a user interface for the main menu.
 * It provides options for instructors to manage grades and view course reports.
 * Logic:
 * =========================
 * Sets the welcome message with the instructor ID.
 * Creates the "Manage Grades" button, which navigates to the GradeManagementScreen when clicked.
 * Creates the "View Course Report" button, which navigates to the CourseReportScreen when clicked.
 * Adds all components to the view layout and sets the scene for the primary stage.
 */

public class MainScreen {
    private VBox view;

    /**
     * Constructor to initialize the MainScreen.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     * @param instructorID The ID of the instructor to be displayed.
     */
    public MainScreen(Stage primaryStage, String instructorID) {
        view = new VBox(10);

        // Add padding to the Vbox
        view.setPadding(new Insets(20, 20, 20, 20));

        Label welcomeLabel = new Label("Welcome, " + instructorID);

        Button gradeManagementButton = new Button("Manage Grades");
        gradeManagementButton.setOnAction(event -> new GradeManagementScreen(primaryStage));

        Button courseReportButton = new Button("View Course Report");
        courseReportButton.setOnAction(event -> new CourseReportScreen(primaryStage));

        view.getChildren().addAll(welcomeLabel, gradeManagementButton, courseReportButton);
        primaryStage.setScene(new Scene(view, 400, 300));
    }
}
