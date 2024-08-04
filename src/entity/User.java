package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user with a username, password, email, subscription, and conversations.
 */
public class User {
    private String userName;
    private String password;
    private String email;
    private List<String> conversationIds;

    public User() {
        this.conversationIds = new ArrayList<>();
    }

    /**
     * Constructs a User with the specified username, password, and email.
     *
     * @param userName The username of the user.
     * @param password The password of the user.
     * @param email The email of the user.
     */
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.conversationIds = new ArrayList<>();
    }


    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the user.
     *
     * @param userName The username to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    public String getUserPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to set.
     */
    public void setUserPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the email of the user.
     *
     * @return The email of the user.
     */
    public String getUserEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email to set.
     */
    public void setUserEmail(String email) {
        this.email = email;
    }

    public List<String> getConversationIds() {
        return conversationIds;
    }

    public void addConversationId(String conversationId) {
        this.conversationIds.add(conversationId);
    }

    public void setConversationIds(List<String> conversationIds) {
        this.conversationIds = conversationIds;
    }
}
