package data_access;

import com.google.firebase.database.*;
import entity.Conversation;
import entity.Message;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseConversationsRepository implements ConversationRepository {
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

    @Override
    public void addConversation(Conversation conversation) {
        String id = conversationsRef.push().getKey();
        conversation.setId(id);
        conversationsRef.child(id).setValue(serializeConversation(conversation),
                new DatabaseReference.CompletionListener() {
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

    @Override
    public void addMessage(String conversationId, Message message) {
        String id = conversationsRef.child(conversationId).child("messages").push().getKey();
        message.setId(id);
        conversationsRef.child(conversationId).child("messages").child(id).setValue(serializeMessage(message),
                new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.err.println("Failed to add message: " + databaseError.getMessage());
                } else {
                    System.out.println("Message added successfully");
                }
            }
        });
        updateConversationTimestamp(conversationId);
    }

    private void updateConversationTimestamp(String conversationId) {
        conversationsRef.child(conversationId).child("modificationTime").setValue(System.currentTimeMillis(),
                new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.err.println("Failed to add timestamp: " + databaseError.getMessage());
                } else {
                    System.out.println("Timestamp added successfully");
                }
            }
        });
    }

    @Override
    public void getConversationById(String conversationId, ConversationCallback callback) {
        conversationsRef.child(conversationId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Conversation conversation = snapshot.getValue(Conversation.class);
                callback.onCallback(conversation);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                callback.onCallback(null);
            }
        });
    }

    @Override
    public void getConversationsByUserId(String userId, ConversationsCallback callback) {
        conversationsRef.orderByChild("username").equalTo(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Conversation> conversations = new ArrayList<>();
                for (DataSnapshot conversationSnapshot : snapshot.getChildren()) {
                    conversations.add(conversationSnapshot.getValue(Conversation.class));
                }
                callback.onCallback(conversations);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                callback.onCallback(new ArrayList<>());
            }
        });
    }
}
