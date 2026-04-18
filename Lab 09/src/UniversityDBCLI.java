import java.sql.*;
import java.util.Scanner;

public class UniversityDBCLI {
    static final String DB_URL = "jdbc:mysql://localhost:3306/university";
    static final String USER = "root";
    static final String PASS = "Kaveesha00";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n---University Database CLI---");
                System.out.println("1. View All Students");
                System.out.println("2. Add a New Student");
                System.out.println("3. Update Student GPA");
                System.out.println("4. Delete a Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        viewAllStudents(conn);
                        break;
                    case 2:
                        addStudent(conn, scanner);
                        break;
                    case 3:
                        updateGPA(conn, scanner);
                        break;
                    case 4:
                        deleteStudent(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void viewAllStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nStudent List:");
            while (rs.next()) {
                System.out.printf("%s | %s | %s | %s | %.2f\n",
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getDate("dob"),
                        rs.getString("email"),
                        rs.getFloat("gpa"));
            }
        }
    }

    static void addStudent(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        // If student already exists, delete it
        String deleteSQL = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement delStmt = conn.prepareStatement(deleteSQL)) {
            delStmt.setString(1, id);
            delStmt.executeUpdate();
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter DOB (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter GPA: ");
        float gpa = scanner.nextFloat();
        scanner.nextLine(); // consume newline

        String sql = "INSERT INTO students (student_id, name, dob, email, gpa) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setDate(3, Date.valueOf(dob));
            stmt.setString(4, email);
            stmt.setFloat(5, gpa);
            stmt.executeUpdate();
            System.out.println("Student added successfully.");
        }
    }

    static void updateGPA(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Student ID to update GPA: ");
        String id = scanner.nextLine();
        System.out.print("Enter New GPA: ");
        float gpa = scanner.nextFloat();
        scanner.nextLine();

        String sql = "UPDATE students SET gpa = ? WHERE student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setFloat(1, gpa);
            stmt.setString(2, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("GPA updated successfully.");
            else
                System.out.println("Student not found.");
        }
    }

    static void deleteStudent(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();

        String sql = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Student deleted successfully.");
            else
                System.out.println("Student not found.");
        }
    }
}
