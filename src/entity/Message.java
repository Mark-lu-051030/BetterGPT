package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private int id;
    private int conversationId;
    private String content;
    private final LocalDateTime timestamp;


    public Message(int id, int conversationId, String content, LocalDateTime timestamp) {
        this.id = id;
        this.conversationId = conversationId;
        this.timestamp = timestamp;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoversationId() {
        return conversationId;
    }

    public void setCoversationId(int coversationId) {
        this.conversationId = coversationId;
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
}
