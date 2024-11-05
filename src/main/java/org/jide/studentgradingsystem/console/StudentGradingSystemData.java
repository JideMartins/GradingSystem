package org.jide.studentgradingsystem.console;

import java.util.ArrayList;
import java.util.List;

/**
 * The StudentGradingSystemData class is a singleton that manages student report data.
 * It allows adding grades and retrieving reports for specific courses.
 */
public class StudentGradingSystemData {
    private static StudentGradingSystemData instance;
    private List<StudentReport> gradeBook;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the gradeBook list to store student reports.
     */
    private StudentGradingSystemData() {
        gradeBook = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of StudentGradingSystemData.
     * If the instance does not exist, it creates one.
     *
     * @return The singleton instance of StudentGradingSystemData.
     */
    public static StudentGradingSystemData getInstance() {
        if (instance == null) {
            instance = new StudentGradingSystemData();
        }
        return instance;
    }

    /**
     * Adds a student report to the grade book.
     *
     * @param report The StudentReport to be added.
     */
    public void addGrade(StudentReport report) {
        gradeBook.add(report);
    }

    /**
     * Retrieves the student reports for a specific course.
     *
     * @param course The course for which reports are retrieved.
     * @return A list of StudentReport objects for the specified course.
     */
    public List<StudentReport> getReportsForCourse(String course) {
        List<StudentReport> filteredReports = new ArrayList<>();
        for (StudentReport report : gradeBook) {
            if (report.getCourse().equals(course)) {
                filteredReports.add(report);
            }
        }
        return filteredReports;
    }
}
