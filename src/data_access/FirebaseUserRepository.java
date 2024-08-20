package data_access;

import com.google.firebase.database.*;
import entity.User;

import java.util.HashMap;
import java.util.Map;

public class FirebaseUserRepository implements UserRepository {
    private final DatabaseReference usersRef;

    public FirebaseUserRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
    }

    @Override
    public void addUser(User user) {
        usersRef.child(user.getUserName()).setValueAsync(user);
    }

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

    public void addConversationIdToUser(String username, String conversationId) {
        Map<String, Object> conversationUpdate = new HashMap<>();
        conversationUpdate.put(conversationId, true);

        usersRef.child(username).child("conversations").updateChildrenAsync(conversationUpdate);
    }

    @Override
    public void updateUser(User user) {
        usersRef.child(user.getUserName()).setValueAsync(user);
    }

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

    public void getConversationsByUser(String username, ValueEventListener listener) {
        usersRef.child(username).child("conversations").addListenerForSingleValueEvent(listener);
    }
}
