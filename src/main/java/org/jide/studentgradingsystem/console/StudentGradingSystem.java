package org.jide.studentgradingsystem.console;

/**
 * Entry point for the console application.
 * Responsible for handling command-line operations where teachers can manage students, assign grades, and generate reports.
 */
public class StudentGradingSystem {
    /**
     * The main method that serves as the entry point for the application.
     * It initializes the MenuManager and displays the menu to the user.
     */
    public static void main(String[] args) {
        MenuManager menuManager = new MenuManager();
        menuManager.showMenu();
    }
}
