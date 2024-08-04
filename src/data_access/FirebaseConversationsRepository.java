package data_access;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import entity.Conversation;
import entity.Message;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class FirebaseConversationsRepository {
    private final DatabaseReference conversationsRef;

    public FirebaseConversationsRepository() {
        conversationsRef = FirebaseDatabase.getInstance().getReference().child("conversations");
    }

    private Map<String, Object> serializeConversation(Conversation conversation) {
        Map<String, Object> serializedConversation = new HashMap<>();
        serializedConversation.put("id", conversation.getId());
        serializedConversation.put("username", conversation.getUsername());
        serializedConversation.put("creationTime", conversation.getCreationTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        serializedConversation.put("modificationTime", conversation.getModificationTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        serializedConversation.put("messages", conversation.getMessages());
        return serializedConversation;
    }

    private Map<String, Object> serializeMessage(Message message) {
        Map<String, Object> serializedMessage = new HashMap<>();
        serializedMessage.put("id", message.getId());
        serializedMessage.put("conversationId", message.getConversationId());
        serializedMessage.put("content", message.getContent());
        serializedMessage.put("timestamp", message.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        serializedMessage.put("role", message.getRole());
        return serializedMessage;
    }

    public void addConversationWithListener(Conversation conversation) {
        String id = conversationsRef.push().getKey();
        conversation.setId(id);
        Map<String, Object> serializedConversation = serializeConversation(conversation);
        conversationsRef.child(id).setValue(serializedConversation, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.err.println("Failed to add conversation: " + databaseError.getMessage());
                } else {
                    System.out.println("Conversation added successfully");
                }
            }
        });
    }

    public void addMessage(String conversationId, Message message) {
        String id = conversationsRef.child(conversationId).child("messages").push().getKey();
        message.setId(id);
        Map<String, Object> serializedMessage = serializeMessage(message);
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("messages/" + id, serializedMessage);
        updateMap.put("modificationTime", System.currentTimeMillis());

        conversationsRef.child(conversationId).updateChildren(updateMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.err.println("Failed to add message: " + databaseError.getMessage());
                } else {
                    System.out.println("Message added successfully");
                }
            }
        });
    }

    public void getConversationById(String conversationId, ValueEventListener listener) {
        conversationsRef.child(conversationId).addListenerForSingleValueEvent(listener);
    }

    public void getConversationsByUserId(String userId, ValueEventListener listener) {
        conversationsRef.orderByChild("username").equalTo(userId).addListenerForSingleValueEvent(listener);
    }
}