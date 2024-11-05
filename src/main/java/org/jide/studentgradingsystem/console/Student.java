package org.jide.studentgradingsystem.console;

/**
 * The Student class represents a student in the student grading system.
 * It contains basic information about the student, such as their ID and name.
 */
public class Student {
    private String studentID;
    private String studentName;

    /**
     * Constructor to initialize a Student object.
     *
     * @param studentID The unique ID of the student.
     * @param studentName The name of the student.
     */

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    // Getters
    public String getStudentID() {

        return studentID;
    }

    public String getStudentName() {

        return studentName;
    }

    @Override
    public String toString() {
        return studentName; // Display only the name in the dropdown
    }

}
