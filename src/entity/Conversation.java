package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Conversation implements Serializable {
    private String id;
    private String username;
    private List<Message> messages;
    private LocalDateTime creationTime;
    private LocalDateTime modificationTime;
    private String name;

    public Conversation() {
        this.messages = new ArrayList<>();
        this.creationTime = LocalDateTime.now();
        this.modificationTime = LocalDateTime.now();
        this.name = "New Chat";

    }

    public Conversation(String username) {
        this.username = username;
        this.messages = new ArrayList<>();
        this.creationTime = LocalDateTime.now();
        this.modificationTime = LocalDateTime.now();
        this.name = "New Chat";

    }

    public Conversation(String id, String username, LocalDateTime creationTime, LocalDateTime modificationTime) {
        this.id = id;
        this.username = username;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
        this.messages = new ArrayList<>();
        this.name = "New Chat";
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
        this.modificationTime = LocalDateTime.now();
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(LocalDateTime modificationTime) {
        this.modificationTime = modificationTime;
    }
}
