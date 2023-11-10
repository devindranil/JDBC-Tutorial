import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {
    private static final String UPDATE_BALANCE = "UPDATE accounts SET balance = ? WHERE account_number = ?";
    
    public static void transferMoney(Connection connection, String fromAccountNumber, String toAccountNumber, double amount)
            throws SQLException {
        try (PreparedStatement updateFromAccount = connection.prepareStatement(UPDATE_BALANCE);
             PreparedStatement updateToAccount = connection.prepareStatement(UPDATE_BALANCE)) {

            // Check if there is sufficient balance in the source account
            double fromAccountBalance = getAccountBalance(connection, fromAccountNumber);
            if (fromAccountBalance < amount) {
                throw new SQLException("Insufficient balance in account " + fromAccountNumber);
            }

            // Deduct amount from the source account
            updateFromAccount.setDouble(1, fromAccountBalance - amount);
            updateFromAccount.setString(2, fromAccountNumber);
            updateFromAccount.executeUpdate();

            // Add amount to the destination account
            double toAccountBalance = getAccountBalance(connection, toAccountNumber);
            updateToAccount.setDouble(1, toAccountBalance + amount);
            updateToAccount.setString(2, toAccountNumber);
            updateToAccount.executeUpdate();

            System.out.println("Transfer successful: " + amount + " from account " + fromAccountNumber +
                    " to account " + toAccountNumber);
        }
    }

    private static double getAccountBalance(Connection connection, String accountNumber) throws SQLException {
        // Retrieve the current balance of the account
        // Assuming you have a table 'accounts' with columns 'account_number' and 'balance'
        String selectBalance = "SELECT balance FROM accounts WHERE account_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectBalance)) {
            preparedStatement.setString(1, accountNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("balance");
                } else {
                    throw new SQLException("Account not found: " + accountNumber);
                }
            }
        }
    }
}
