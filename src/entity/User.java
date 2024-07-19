package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String password;
    private String email;
    private List<Conversation> conversations;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.conversations = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void addConversation(Conversation conversation) {
        this.conversations.add(conversation);
    }

}
