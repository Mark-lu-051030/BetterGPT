package data_access;

import com.google.firebase.database.*;
import entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * The FirebaseUserRepository class manages user data in Firebase.
 * It implements the UserRepository interface, providing methods to add, update, and retrieve users,
 * as well as manage user conversations.
 */
public class FirebaseUserRepository implements UserRepository {
    private final DatabaseReference usersRef;

    /**
     * Constructs a FirebaseUserRepository and initializes the Firebase database reference for users.
     */
    public FirebaseUserRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
    }

    /**
     * Adds a new user to the Firebase database.
     *
     * @param user the User object to add
     */
    @Override
    public void addUser(User user) {
        usersRef.child(user.getUserName()).setValueAsync(user);
    }

    /**
     * Finds a user by their username in Firebase and passes the result to the provided callback.
     *
     * @param username the username to search for
     * @param callback the callback to handle the retrieved User object
     */
    public void findByUsername(String username, UserCallback callback) {
        usersRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                callback.onCallback(user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Log or handle error
                callback.onCallback(null);
            }
        });
    }

    /**
     * Finds a user by their email in Firebase and passes the result to the provided callback.
     *
     * @param email    the email to search for
     * @param callback the callback to handle the retrieved User object
     */
    public void findByEmail(String email, UserCallback callback) {
        usersRef.orderByChild("userEmail").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = null;
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    user = userSnapshot.getValue(User.class);
                    break;
                }
                callback.onCallback(user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Log or handle error
                callback.onCallback(null);
            }
        });
    }

    /**
     * Adds a conversation ID to the specified user's list of conversations in Firebase.
     *
     * @param username       the username of the user
     * @param conversationId the ID of the conversation to add
     */
    @Override
    public void addConversationIdToUser(String username, String conversationId) {
        Map<String, Object> conversationUpdate = new HashMap<>();
        conversationUpdate.put(conversationId, true);

        usersRef.child(username).child("conversations").updateChildrenAsync(conversationUpdate);
    }

    /**
     * Updates an existing user's data in Firebase.
     *
     * @param user the User object with updated information
     */
    @Override
    public void updateUser(User user) {
        usersRef.child(user.getUserName()).setValueAsync(user);
    }

    /**
     * Updates the password for a specified user in Firebase.
     *
     * @param username    the username of the user
     * @param newPassword the new password to set
     */
    @Override
    public void updatePassword(String username, String newPassword) {
        usersRef.child(username).child("userPassword").setValue(newPassword, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.err.println("Failed to update password: " + databaseError.getMessage());
                } else {
                    System.out.println("Password updated successfully");
                }
            }
        });
    }

    /**
     * Retrieves the list of conversation IDs associated with a specific user in Firebase.
     *
     * @param username the username of the user
     * @param listener the ValueEventListener to handle the retrieved data
     */
    public void getConversationsByUser(String username, ValueEventListener listener) {
        usersRef.child(username).child("conversations").addListenerForSingleValueEvent(listener);
    }
}
