package data_access;

import entity.*;
import java.sql.*;

/**
 * SQLiteUserRepository handles user-related database operations using SQLite.
 */
public class SQLiteUserRepository {
    private static final String URL = "jdbc:sqlite:users.db";

    /**
     * Constructs a new SQLiteUserRepository and creates the users table if it doesn't exist.
     */
    public SQLiteUserRepository() {
        createTable();
    }

    /**
     * Creates the users table if it doesn't exist.
     */
    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "username TEXT PRIMARY KEY,"
                + "password TEXT NOT NULL,"
                + "email TEXT NOT NULL)";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    /**
     * Add new user to the database
     * @param user User Object need to be added
     * @throws SQLException if a database access error occurs
     */
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserEmail());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.getStackTrace();
        }
    }

    /**
     * Updates the password for an existing user.
     *
     * @param user the User object with the updated password
     */
    public void updateUserPwd(User user) {
        String sql = "UPDATE users SET password = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, user.getUserPassword());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.getStackTrace();
        }
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to be found
     * @return the User object if found, or null if not found
     */
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("email"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
}
