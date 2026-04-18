import java.sql.*;
import java.util.Scanner;

public class JdbcUpdateTest {
    static final String URL = "jdbc:mysql://localhost:3306/ebookshop";
    static final String USER = "root";
    static final String PASS = "Kaveesha#17";
    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(URL , USER, PASS);
                Scanner sc = new Scanner(System.in);
        ) {

            while(true){
                System.out.println("1.see the books ");
                System.out.println("2.add a new book ");
                System.out.println("3.delete a new book ");
                System.out.println("4.update a new book ");
                System.out.println("5.exit");
                System.out.println("Enter your choice: ");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> allBooks(conn);
                    case 2 -> addBook(conn, sc);
                    case 3 -> deleteBook(conn);
                    case 4 -> updateBook(conn,sc);
                    case 5 -> exit();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void exit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private static void updateBook(Connection conn, Scanner sc) throws SQLException {

    }

    private static void deleteBook(Connection conn) throws SQLException {

    }

    private static void addBook(Connection conn, Scanner sc) throws SQLException {
    }

    private static void allBooks(Connection conn) throws SQLException {
        String sql = "select * from book";
        try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf("%s | %s | %s | %s | %.2f\n",
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getDate("dob"),
                    rs.getString("email"),
                    rs.getFloat("gpa"));
        }


        }
    }

