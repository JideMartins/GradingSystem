package org.jide.studentgradingsystem.console;

import java.util.ArrayList;
import java.util.List;

/**
 * The Instructor class represents an instructor in the student grading system.
 * It contains information about the instructor and the courses they handle.
 */
public class Instructor {
    private String instructorID;
    private String name;
    private List<Course> courses;

    /**
     * Constructor to initialize an Instructor object.
     *
     * @param instructorID The unique ID of the instructor.
     * @param name         The name of the instructor.
     */
    public Instructor(String instructorID, String name) {
        this.instructorID = instructorID;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    /**
     * Adds a course to the list of courses handled by the instructor.
     * @param course The course to be added.
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Assigns a grade to a student for a specific course.
     *
     * @param student      The student to whom the grade is assigned.
     * @param course       The course for which the grade is assigned.
     * @param testScore    The test score.
     * @param projectScore The project score.
     * @param examScore    The exam score.
     */
    public void assignGrade(Student student, String course, int testScore, int projectScore, int examScore) {
        StudentReport report = new StudentReport(student.getStudentID(), student.getStudentName(),
                testScore,
                projectScore,
                examScore,
                course);
        StudentGradingSystemData.getInstance().addGrade(report);
    }

    /**
     * Retrieves the course report for a specific course.
     *
     * @param course The course for which the report is generated.
     * @return A list of StudentReport objects for the course.
     */
    public List<StudentReport> viewCourseReport(String course) {
        return ReportGenerator.generateCourseReport(course);
    }

    //Displays the list of courses handled by the instructor.
    public void displayCourses() {
        System.out.println("Courses handled by " + name + ":");
        for (Course course : courses) {
            System.out.println(course.getCourseCode() + " - " + course.getCourseName());
        }
    }

    /**
     * Retrieves a course by its code.
     *
     * @param courseCode The code of the course to be retrieved.
     * @return The course with the specified code, or null if not found.
     */
    public Course getCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        System.out.println("Course not found.");
        return null;
    }

    //Retrieve ID
    public String getInstructorID() {
        return instructorID;
    }


    // Retrieve instructor name
    public String getName() {
        return name;
    }

    //List courses
    public List<Course> getCourses() {
        return courses;
    }
}
