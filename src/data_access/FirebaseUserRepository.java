package data_access;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import entity.User;

public class FirebaseUserRepository {
    private final DatabaseReference usersRef;

    public FirebaseUserRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
    }

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
        usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
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

    public void updateUser(User user) {
        usersRef.child(user.getUserName()).setValueAsync(user);
    }

    public interface UserCallback {
        void onCallback(User user);
    }
}
