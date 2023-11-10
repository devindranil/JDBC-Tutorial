import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) {
        Connection con = null;

        try {
            // GET CONNECTION
            con = JDBCUtil.getConnection();

            // Start the transaction
            con.setAutoCommit(false);

            // Simulate transfer from account1 to account2
            Transaction.transferMoney(con, "account123", "account456", 200); // Transfer 500 from account1 to account2

            // Commit the transaction if everything is successful
            con.commit();
            System.out.println("Transaction committed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();

            // Rollback the transaction in case of an exception
            System.err.println("Rolling back the transaction.");
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
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
