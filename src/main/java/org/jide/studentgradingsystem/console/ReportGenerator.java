package org.jide.studentgradingsystem.console;

import java.util.List;

/**
 * This class provides methods to generate and format course reports.
 * It retrieves student reports for a specific course and formats them as a string.
 */
public class ReportGenerator {

    /**
     * Generates a course report for a specified course.
     *
     * @param course The course for which the report is generated.
     * @return A list of StudentReport objects for the course.
     */
    public static List<StudentReport> generateCourseReport(String course) {
        return StudentGradingSystemData.getInstance().getReportsForCourse(course);
    }

    /**
     * Formats a list of student reports into a string representation.
     *
     * @param reports The list of StudentReport objects to be formatted.
     * @return A string representation of the student reports.
     */
    public static String formatReport(List<StudentReport> reports) {
        StringBuilder reportBuilder = new StringBuilder("ID, Name, Test, Project, Exam, Total\n");
        for (StudentReport report : reports) {
            reportBuilder.append(report.getStudentID()).append(", ")
                    .append(report.getStudentName()).append(", ")
                    .append(report.getTestScore()).append(", ")
                    .append(report.getProjectScore()).append(", ")
                    .append(report.getExamScore()).append(", ")
                    .append(report.getTotalScore()).append("\n");
        }
        return reportBuilder.toString();
    }
}
