package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private String id;
    private String conversationId;
    private String content;
    private LocalDateTime timestamp;
    private String role;

    public Message() {

    }

    public Message(String id, String conversationId, String content, LocalDateTime timestamp, String role) {
        this.id = id;
        this.conversationId = conversationId;
        this.content = content;
        this.timestamp = timestamp;
        this.role = role;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
