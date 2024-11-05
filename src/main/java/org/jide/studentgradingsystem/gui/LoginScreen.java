package org.jide.studentgradingsystem.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The LoginScreen class creates a user interface for instructor login.
 * It allows instructors to enter their ID and password to authenticate themselves.
 * Logic:
 * ======================================
 * Creates labels and text fields for the instructor ID and password.
 * Creates a login button and sets an event handler to authenticate the instructor using the AuthManager.
 * If authentication is successful, it displays the main application screen.
 * If authentication fails, it shows an error alert.
 * Adds all components to the view layout and sets the scene for the primary stage.
 * Shows the primary stage.
 */

public class LoginScreen {
    private VBox view;

    /**
     * Constructor to initialize the LoginScreen.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    public LoginScreen(Stage primaryStage) {
        view = new VBox(10);

        // Add padding to the Vbox
        view.setPadding(new Insets(20, 20, 20, 20));

        Label idLabel = new Label("Instructor ID:");
        TextField idField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");

        loginButton.setOnAction(event -> {
            String instructorID = idField.getText();
            String password = passwordField.getText();

            // Authenticate the instructor using AuthManager
            if (AuthManager.authenticate(instructorID, password)) {
                // Successful login
                System.out.println("Login successful!");

                // Instantiate the main application screen after successful login
                new MainScreen(primaryStage, instructorID);
            } else {
                // Failed login
                System.out.println("Authentication failed. Please try again.");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid ID or password.");
                alert.showAndWait();
            }
        });

        view.getChildren().addAll(idLabel, idField, passwordLabel, passwordField, loginButton);
        primaryStage.setScene(new Scene(view, 300, 200));
        primaryStage.show();
    }
}
