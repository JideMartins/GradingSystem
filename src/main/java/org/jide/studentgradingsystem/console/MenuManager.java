package org.jide.studentgradingsystem.console;

import java.util.*;


/**
 * The MenuManager class handles the main menu interactions for the student grading system.
 * It provides options for instructors to manage courses and assign grades.
 */
public class MenuManager {
    private Instructor currentInstructor;
    private Map<String, Instructor> instructors;

    /**
     * Constructor to initialize the MenuManager.
     * It sets up the initial data for the system.
     */
    public MenuManager() {
        initializeData();
    }

    /**
     * Initializes the data for the system, including courses, students, and instructors.
     */
    private void initializeData() {
        Course courseCS101 = new Course("CS101", "Introduction to Programming");
        Course courseCS102 = new Course("CS102", "Data Structures");
        Course courseMath101 = new Course("MATH101", "Calculus I");

        courseCS101.addStudent(new Student("BPTN-06-001", "Alice Adedapo"));
        courseCS101.addStudent(new Student("BPTN-06-002", "Bob Banjo"));
        courseCS101.addStudent(new Student("BPTN-06-003", "Alison Becker"));
        courseCS101.addStudent(new Student("BPTN-06-004", "Reece Joan"));
        courseCS101.addStudent(new Student("BPTN-06-005", "Sandra McDonald"));
        courseCS101.addStudent(new Student("BPTN-06-006", "Ayomide Hussein"));

        courseCS102.addStudent(new Student("BPTN-06-001", "Alice Adedapo"));
        courseCS102.addStudent(new Student("BPTN-06-006", "Ayomide Hussein"));
        courseCS102.addStudent(new Student("BPTN-06-005", "Sandra McDonald"));

        courseMath101.addStudent(new Student("BPTN-06-007", "Diana Crawford"));
        courseMath101.addStudent(new Student("BPTN-06-008", "Edward Beecroft"));

        Instructor hrithik = new Instructor("IN-06-001", "Hrithik Advani");
        Instructor carlos = new Instructor("IN-06-002", "Carlos Zegarra");

        hrithik.addCourse(courseCS101);
        carlos.addCourse(courseCS102);
        carlos.addCourse(courseMath101);

        instructors = new HashMap<>();
        instructors.put(hrithik.getInstructorID(), hrithik);
        instructors.put(carlos.getInstructorID(), carlos);
    }

    /**
     * Displays the main menu for instructors to interact with.
     */
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your instructor ID:");
        String instructorID = scanner.nextLine();
        currentInstructor = instructors.get(instructorID);

        if (currentInstructor != null) {
            System.out.println("Welcome, " + currentInstructor.getName() + "!");
            displayInstructorOptions(scanner);
        } else {
            System.out.println("Invalid instructor ID. Please try again.");
        }
        scanner.close();
    }

    /**
     * Displays the options available to the instructor.
     *
     * @param scanner The Scanner object for reading user input.
     */
    private void displayInstructorOptions(Scanner scanner) {
        while (true) {
            System.out.println("\n1. View Courses\n2. Assign Grade\n3. View Course Report\n4. Export Report\n5. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    currentInstructor.displayCourses();
                    break;
                case 2:
                    handleAssignGrade(scanner);
                    break;
                case 3:
                    handleViewReport(scanner);
                    break;
                case 4:
                    handleExportReport(scanner);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Handles the process of assigning a grade to a student.
     *
     * @param scanner Scanner object for reading user input.
     */
    private void handleAssignGrade(Scanner scanner) {
        System.out.println("Enter course code:");
        String courseCode = scanner.nextLine();
        Course course = currentInstructor.getCourseByCode(courseCode);
        if (course != null) {
            System.out.println("Enter student ID:");
            String studentID = scanner.nextLine();
            Student student = course.getStudentByID(studentID);
            if (student != null) {
                try {
                    System.out.println("Enter test score (max 30):");
                    int testScore = scanner.nextInt();

                    // Checks if the testScore is between 0 and 30.
                    if (testScore < 0 || testScore > 30)
                        throw new IllegalArgumentException("Test score must be between 0 and 30.");

                    System.out.println("Enter project score (max 20):");
                    int projectScore = scanner.nextInt();

                    // Checks if the projectScore is between 0 and 20.
                    if (projectScore < 0 || projectScore > 20)
                        throw new IllegalArgumentException("Project score must be between 0 and 20.");

                    System.out.println("Enter exam score (max 50):");
                    int examScore = scanner.nextInt();

                    //Checks if the projectScore is between 0 and 50.
                    if (examScore < 0 || examScore > 50)
                        throw new IllegalArgumentException("Exam score must be between 0 and 50.");

                    scanner.nextLine();  // Consume newline

                    currentInstructor.assignGrade(student, courseCode, testScore, projectScore, examScore);
                    System.out.println("Grade assigned successfully.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Input error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
            } else {
                System.out.println("Student not found.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }


    /**
     * Handles the process of viewing the course report.
     *
     * @param scanner Scanner object for reading user input.
     */
    private void handleViewReport(Scanner scanner) {
        System.out.println("Enter course code to view report:");
        String courseCode = scanner.nextLine();
        List<StudentReport> reports = currentInstructor.viewCourseReport(courseCode);
        System.out.println(ReportGenerator.formatReport(reports));
    }

    /**
     * Handles the process of exporting the course report to a file.
     *
     * @param scanner Scanner object for reading user input.
     */
    private void handleExportReport(Scanner scanner) {
        System.out.println("Enter course code to export report:");
        String courseCode = scanner.nextLine();
        List<StudentReport> reports = currentInstructor.viewCourseReport(courseCode);
        FileHandler.exportReportToFile(reports, new java.io.File(courseCode + "_report.txt"));
        System.out.println("Report exported successfully.");
    }
}
