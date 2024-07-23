package data_access;

import entity.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

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
        String userTable = "CREATE TABLE IF NOT EXISTS users ("
                + "username TEXT PRIMARY KEY,"
                + "password TEXT NOT NULL,"
                + "email TEXT NOT NULL)";

        String conversationTable = "CREATE TABLE IF NOT EXISTS conversations ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL,"
                + "created_at TEXT NOT NULL,"
                + "updated_at TEXT NOT NULL,"
                + "FOREIGN KEY (username) REFERENCES users(username))";

        String messageTable = "CREATE TABLE IF NOT EXISTS messages ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "conversation_id INTEGER NOT NULL,"
                + "content TEXT NOT NULL,"
                + "timestamp TEXT NOT NULL,"
                + "FOREIGN KEY (conversation_id) REFERENCES conversations(id))";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(conversationTable);
            stmt.execute(messageTable);
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

    public void addConversation(Conversation conversation) {
        String sql = "INSERT INTO conversations (username, created_at, updated_at) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, conversation.getUsername());
            pstmt.setString(2, conversation.getCreationTime().toString());
            pstmt.setString(3, conversation.getModificationTime().toString());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    conversation.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateConversation(Conversation conversation) {
        String sql = "UPDATE conversations SET updated_at = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, conversation.getModificationTime().toString());
            pstmt.setInt(2, conversation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMessage(Message message) {
        String sql = "INSERT INTO messages (conversation_id, content, timestamp) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, message.getCoversationId());
            pstmt.setString(2, message.getContent());
            pstmt.setString(3, message.getTimestamp().toString());
            pstmt.executeUpdate();

            updateConversation(new Conversation(message.getCoversationId(), null, null, LocalDateTime.now()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Conversation> getConversationsByUsername(String username) {
        String sql = "SELECT * FROM conversations WHERE username = ?";
        List<Conversation> conversations = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Conversation conversation = new Conversation(rs.getInt("id"),
                        rs.getString("username"),
                        LocalDateTime.parse(rs.getString("created_at")),
                        LocalDateTime.parse(rs.getString("updated_at")));
                conversation.setMessages((Message) getMessagesByConversationId(rs.getInt("id")));
                conversations.add(conversation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conversations;
    }

    public List<Message> getMessagesByConversationId(int conversationId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE conversation_id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, conversationId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getInt("id"),
                        rs.getInt("conversation_id"),
                        rs.getString("content"),
                        LocalDateTime.parse(rs.getString("timestamp"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
