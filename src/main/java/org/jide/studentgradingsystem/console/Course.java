package org.jide.studentgradingsystem.console;

import java.util.ArrayList;
import java.util.List;


/**
 * The Course class represents a course in the student grading system.
 * It contains information about the course and the students enrolled in it.
 */
public class Course {
    private String courseCode;
    private String courseName;
    private List<Student> students;


    /**
     * Constructor to initialize a Course object.
     *
     * @param courseCode unique code of the course.
     * @param courseName name of the course.
     */
    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.students = new ArrayList<>();
    }

    /**
     * Adds a student to the course.
     *
     * @param student student to be added to the course.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Retrieves the list of students enrolled in the course.
     *
     * @return list of students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Retrieves a student from the course by their ID.
     *
     * @param studentID ID of the student to be retrieved.
     * @return The student with the specified ID, or null if not found.
     */
    public Student getStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        System.out.println("Student not found.");
        return null;
    }

    /**
     * Retrieves the course code.
     *
     * @return The course code.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Retrieves the course name
     *
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }

    public void displayStudents() {
        System.out.println("Students enrolled in " + courseName + ":");
        for (Student student : students) {
            System.out.println(student.getStudentID() + " - " + student.getStudentName());
        }
    }
}
