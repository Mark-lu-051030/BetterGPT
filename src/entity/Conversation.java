package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Conversation implements Serializable {
    private int id;
    private String username;
    private List<Message> messages;
    private final LocalDateTime creationTime;
    private LocalDateTime modificationTime;

    public Conversation(int id, String username, LocalDateTime creationTime, LocalDateTime now) {
        this.id = id;
        this.username = username;
        this.creationTime = creationTime;
        this.modificationTime = LocalDateTime.now();
        this.messages = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(Message message) {
        this.messages.add(message);
        this.modificationTime = LocalDateTime.now();
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(LocalDateTime modificationTime) {
        this.modificationTime = modificationTime;
    }
}
