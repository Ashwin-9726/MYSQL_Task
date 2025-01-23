import java.sql.*;
public class JDBC_Connection {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/EmployeeDB"; //  database URL
        String username = "root"; // database username
        String password = "Cricket@345"; //  database password

        // SQL INSERT query
        String insertQuery = "INSERT INTO employee (empcode, empname, empage, esalary) VALUES (?, ?, ?, ?)";

        // Sample employee data to insert
        Object[][] employees = {
                {101, "Jenny", 25, 10000},
                {102, "Jacky", 30, 20000},
                {103, "Joe", 20, 40000},
                {104, "John", 40, 80000},
                {105, "Shameer", 25, 90000}
        };

        // Connection and statement initialization
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            // Loop through each employee record and insert
            for (Object[] emp : employees) {
                pstmt.setInt(1, (int) emp[0]);        // empcode
                pstmt.setString(2, (String) emp[1]);  // empname
                pstmt.setInt(3, (int) emp[2]);        // empage
                pstmt.setInt(4, (int) emp[3]);  // esalary

                pstmt.addBatch(); // Add this insert to batch
            }

            // Execute batch
            int[] result = pstmt.executeBatch();
            System.out.println("Inserted " + result.length + " records successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


