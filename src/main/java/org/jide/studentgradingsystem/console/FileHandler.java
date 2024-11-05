package org.jide.studentgradingsystem.console;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * This class provides methods to export student report data to a file.
 * Logic:
 * Uses a PrintWriter wrapped in a try-with-resources statement to handle file writing.
 * Writes the report header to the file.
 * Iterates over the reports list to write each student’s data to the file.
 */
public class FileHandler {

    /**
     * Exports a list of student reports to a specified file.
     * @param reports List of StudentReport objects to be exported.
     * @param file File object representing the file to write the report to.
     */
    public static void exportReportToFile(List<StudentReport> reports, File file) {

        try (PrintWriter writer = new PrintWriter(file)) {
            // Loop through each StudentReport and write its data to the file
            writer.println("ID, Name, Test, Project, Exam, Total");
            for (StudentReport report : reports) {
                writer.printf("%s, %s, %d, %d, %d, %d%n",
                        report.getStudentID(), report.getStudentName(),
                        report.getTestScore(), report.getProjectScore(),
                        report.getExamScore(), report.getTotalScore());
            }
            // Alternatively
/*            writer.println("===============================================");
            System.out.println();
            for (StudentReport report : reports) {
                writer.println("Student Name: " + report.getStudentName());
                writer.println("Student ID: " + report.getStudentID());
                writer.println("Test Score: " + report.getTestScore());
                writer.println("Exam Score: " + report.getExamScore());
                writer.println("Project Score: " + report.getProjectScore());
                writer.println("Total Score: " + report.getTotalScore());
                writer.println("-----------------------------------------------");
            }*/
        } catch (Exception e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }

}
