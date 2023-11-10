import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
    // JDBC and database properties.
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver"; //Load the driver
    private static final String DB_URL = "jdbc:mysql://localhost:3306/empDataSet";//Load the URL
    private static final String DB_USERNAME = "root";//Sey the username
    private static final String DB_PASSWORD = "123456789";//set the password

    public static Connection getConnection() {
        Connection con = null;
        try {
            // Register the JDBC driver
            Class.forName(DB_DRIVER);

            // Open the connection
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            if (con != null) {
                System.out.println("Successfully connected.");
            } else {
                System.out.println("Failed to connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    
}

