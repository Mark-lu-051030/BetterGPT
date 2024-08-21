package data_access;

import com.google.firebase.database.*;
import entity.Conversation;
import entity.Message;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * The FirebaseConversationsRepository class is responsible for managing conversation data in Firebase.
 * It implements the ConversationRepository interface, providing methods to add, retrieve, and update conversations and messages.
 */
public class FirebaseConversationsRepository implements ConversationRepository {
    private final DatabaseReference conversationsRef;

    /**
     * Constructs a FirebaseConversationsRepository and initializes the Firebase database reference for conversations.
     */
    public FirebaseConversationsRepository() {
        conversationsRef = FirebaseDatabase.getInstance().getReference().child("conversations");
    }

    /**
     * Serializes a Conversation object into a Map that can be stored in Firebase.
     *
     * @param conversation the Conversation object to serialize
     * @return a Map containing the serialized conversation data
     */
    private Map<String, Object> serializeConversation(Conversation conversation) {
        Map<String, Object> serializedConversation = new HashMap<>();
        serializedConversation.put("id", conversation.getId());
        serializedConversation.put("username", conversation.getUsername());
        serializedConversation.put("creationTime", conversation.getCreationTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        serializedConversation.put("modificationTime", conversation.getModificationTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        serializedConversation.put("messages", conversation.getMessages());
        serializedConversation.put("name", conversation.getName());
        return serializedConversation;
    }

    /**
     * Serializes a Message object into a Map that can be stored in Firebase.
     *
     * @param message the Message object to serialize
     * @return a Map containing the serialized message data
     */
    private Map<String, Object> serializeMessage(Message message) {
        Map<String, Object> serializedMessage = new HashMap<>();
        serializedMessage.put("id", message.getId());
        serializedMessage.put("conversationId", message.getConversationId());
        serializedMessage.put("content", message.getContent());
        serializedMessage.put("timestamp", message.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        serializedMessage.put("role", message.getRole());
        return serializedMessage;
    }

    /**
     * Adds a new conversation to Firebase. The conversation is serialized and stored under a unique ID.
     *
     * @param conversation the Conversation object to add
     */
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

    /**
     * Adds a new message to an existing conversation in Firebase. The message is serialized and stored under a unique ID.
     *
     * @param conversationId the ID of the conversation to which the message belongs
     * @param message        the Message object to add
     */
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

    /**
     * Updates the modification timestamp of a conversation in Firebase.
     *
     * @param conversationId the ID of the conversation to update
     */
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

    /**
     * Retrieves a conversation by its ID from Firebase and passes it to the provided callback.
     *
     * @param conversationId the ID of the conversation to retrieve
     * @param callback       the callback to handle the retrieved Conversation object
     */
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

    /**
     * Retrieves a list of conversations for a specific user from Firebase.
     *
     * @param userId the ID of the user whose conversations are to be retrieved
     * @return a List of Conversation objects belonging to the user
     */
    @Override
    public List<Conversation> getConversationsByUserId(String userId) {
        List<Conversation> conversations = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        conversationsRef.orderByChild("userName").equalTo(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot conversationSnapshot : snapshot.getChildren()) {
                    conversations.add(conversationSnapshot.getValue(Conversation.class));
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(conversations);
        return conversations;
    }

}
