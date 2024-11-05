package org.jide.studentgradingsystem.console;
/**
 * This cass represents a report of a student's performance in a course.
 * Contains information about the student's ID, name, scores, and the course they are enrolled in.
 */


public class StudentReport {
    private String studentID;
    private String studentName;
    private int testScore;
    private int projectScore;
    private int examScore;
    private String course;

    /**
     * Constructor to initialize a StudentReport object.
     *
     * @param studentID    The ID of the student.
     * @param studentName  The name of the student.
     * @param testScore    The student's test score.
     * @param projectScore The student's project score.
     * @param examScore    The student's exam score.
     * @param course       The course the student is enrolled in.
     */
    public StudentReport(String studentID, String studentName, int testScore, int projectScore, int examScore, String course) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.testScore = testScore;
        this.projectScore = projectScore;
        this.examScore = examScore;
        this.course = course;
    }

    //getters to return studentID, studentName, testScore, projectScore, examScore, course
    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getTestScore() {
        return testScore;
    }

    public int getProjectScore() {
        return projectScore;
    }

    public int getExamScore() {
        return examScore;
    }

    public String getCourse() {
        return course;
    }

    /**
     * Calculates and retrieves the total score of the student.
     *
     * @return the total score
     */

    public int getTotalScore() {
        return testScore + projectScore + examScore;
    }
}
