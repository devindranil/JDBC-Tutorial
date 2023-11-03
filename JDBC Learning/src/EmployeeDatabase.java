
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDatabase {

    public static void createTable(Connection con) throws SQLException {
        String createTableQuery = "CREATE TABLE empData(empID int, empName varchar(255), empDes varchar(55), empSalary int)";
        Statement stmt = con.createStatement();
        stmt.execute(createTableQuery);
    }

    public static void insertRecords(Connection con) throws SQLException {
        String insertRecordQuery = "INSERT INTO empData (empID, empName, empDes, empSalary) VALUES " +
            "(1, 'John Doe', 'Manager', 50000), " +
            "(2, 'Jane Smith', 'Engineer', 60000), " +
            "(3, 'Bob Johnson', 'Designer', 55000), " +
            "(4, 'Rony Mathew', 'Developer', 15000)";
        Statement stmt = con.createStatement();
        stmt.execute(insertRecordQuery);
    }

    public static void updateRecord(Connection con) throws SQLException {
        String updateRecordQuery = "UPDATE empData SET empSalary = 65000 WHERE empName = 'John Doe'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(updateRecordQuery);
    }

    public static void selectRecords(Connection con) throws SQLException {
        String selectRecordsQuery = "SELECT * FROM empData";
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(selectRecordsQuery);
        while (resultSet.next()) {
            // Process and print the retrieved records
            while (resultSet.next()) {
                int empID = resultSet.getInt("empID");
                String empName = resultSet.getString("empName");
                String empDes = resultSet.getString("empDes");
                int empSalary = resultSet.getInt("empSalary");
                
                System.out.println("Employee ID: " + empID);
                System.out.println("Employee Name: " + empName);
                System.out.println("Employee Description: " + empDes);
                System.out.println("Employee Salary: " + empSalary);
                System.out.println();
            }
        }
    }

    public static void deleteRecord(Connection con) throws SQLException {
        String deleteRecordQuery = "DELETE FROM empData WHERE empName = 'Bob Johnson'";
        Statement stmt = con.createStatement();
        int rowsAffected = stmt.executeUpdate(deleteRecordQuery);
        System.out.println(rowsAffected + " records deleted successfully.");
    }

    public static boolean tableExists(Connection con, String tableName) throws SQLException {
        DatabaseMetaData meta = (DatabaseMetaData) con.getMetaData();
        ResultSet tables = meta.getTables(null, null, tableName, null);
        return tables.next();
    }
    
}
