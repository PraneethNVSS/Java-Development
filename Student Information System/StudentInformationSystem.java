import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private String email;
    private Map<String, Integer> grades;

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.grades = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void addGrade(String course, int grade) {
        grades.put(course, grade);
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Email: " + email;
    }
}

class Course {
    private String code;
    private String name;
    private int credits;

    public Course(String code, String name, int credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }
}

public class StudentInformationSystem {
    private List<Student> students;
    private List<Course> courses;

    public StudentInformationSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public void addStudent(int id, String name, String email) {
        Student student = new Student(id, name, email);
        students.add(student);
        System.out.println("Student added successfully:");
        System.out.println(student);
    }

    public void addCourse(String code, String name, int credits) {
        courses.add(new Course(code, name, credits));
    }

    public void enrollStudent(int studentId, String courseCode) {
        Student student = getStudentById(studentId);
        Course course = getCourseByCode(courseCode);

        if (student != null && course != null) {
            // Enroll the student in the course (not implemented in this basic example)
            System.out.println(student.getName() + " enrolled in " + course.getName());
        } else {
            System.out.println("Student or course not found.");
        }
    }

    public void addGrade(int studentId, String courseCode, int grade) {
        Student student = getStudentById(studentId);
        if (student != null) {
            student.addGrade(courseCode, grade);
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private Course getCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentInformationSystem sis = new StudentInformationSystem();
        
        System.out.println("Welcome to the Student Information System.");
        
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a student");
            System.out.println("2. Add a course");
            System.out.println("3. Enroll a student in a course");
            System.out.println("4. Add a grade for a student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter student email: ");
                    String studentEmail = scanner.nextLine();
                    sis.addStudent(sis.students.size() + 1, studentName, studentEmail);
                    break;
                case 2:
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter course credits: ");
                    int credits = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    sis.addCourse(courseCode, courseName, credits);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter course code: ");
                    String enrollCourseCode = scanner.nextLine();
                    sis.enrollStudent(studentId, enrollCourseCode);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    int gradeStudentId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter course code: ");
                    String gradeCourseCode = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    int grade = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    sis.addGrade(gradeStudentId, gradeCourseCode, grade);
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }
}
