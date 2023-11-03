import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) {
        Connection con = null;

        try {
            // GET CONNECTION
            con = JDBCUtil.getConnection();

            // CREATE TABLE (check if the table exists before creating it)
            if (!EmployeeDatabase.tableExists(con, "empData")) {
                EmployeeDatabase.createTable(con);
                System.out.println("Table empRecords created successfully.");
            } else {
                System.out.println("Table empData already exists.");
            }

            // INSERT RECORDS
            EmployeeDatabase.insertRecords(con);
            System.out.println("Records inserted successfully.");

            // UPDATE RECORDS
            EmployeeDatabase.updateRecord(con);
            System.out.println("Records updated successfully.");

            // SELECT RECORDS
            EmployeeDatabase.selectRecords(con);

            // DELETE RECORD
            EmployeeDatabase.deleteRecord(con);
  

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
