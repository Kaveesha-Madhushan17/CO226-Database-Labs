import java.sql.*;

public class JdbcSelectTest {
    public static void main(String[] args) {
        try (
                // Step 1: Connect to database
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "root", "Kaveesha#17");

                // Step 2: Create statement
                Statement stmt = conn.createStatement();
        ) {
            // Step 3: SQL query
            String insert = "INSERT INTO books VALUES (1007, 'E/21/407', 'Ipsitha',20,10)";
            stmt.executeUpdate(insert);
            String strSelect = "SELECT * FROM books";
            System.out.println("The SQL statement is: " + strSelect + "\n");

            ResultSet rset = stmt.executeQuery(strSelect);

            // Step 4: Process the ResultSet
            System.out.println("Books are read from the database\n:");
            int rowCount = 0;
            while (rset.next()) {
                System.out.println(rset.getInt("id") + ", "
                        + rset.getString("title") + ", "
                        + rset.getString("author") + ", "
                        + rset.getDouble("price") + ", "
                        + rset.getInt("qty"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
