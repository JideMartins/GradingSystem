package org.jide.studentgradingsystem.gui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The StudentGradingSystemApp class serves as the entry point for the JavaFX application.
 * It initializes and displays the primary stage and sets the initial screen to the login screen.
 */

public class StudentGradingSystemApp extends Application {

    /**
     * The start method is called after the Application is launched.
     * It sets up the primary stage and displays the login screen.
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Grading System");

        // Show the login screen as the first screen
        new LoginScreen(primaryStage);
    }

    // Launches the JavaFX application by calling the start method
    public static void main(String[] args) {
        launch(args);
    }
}
