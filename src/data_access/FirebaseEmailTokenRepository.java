// FirebaseEmailTokenRepository.java
package data_access;

import com.google.firebase.database.*;

public class FirebaseEmailTokenRepository implements EmailTokenRepository {
    private final DatabaseReference tokensRef;

    public FirebaseEmailTokenRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        tokensRef = database.getReference("tokens");
    }

    @Override
    public void mapEmailToToken(String email, String token) {
        tokensRef.child(email.replace(".", ",")).setValue(token, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.err.println("Failed to store token: " + databaseError.getMessage());
                } else {
                    System.out.println("Token stored successfully");
                }
            }
        });
    }

    @Override
    public void retrieveEmailAndToken(String email, TokenCallback callback) {
        tokensRef.child(email.replace(".", ",")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String token = snapshot.getValue(String.class);
                callback.onCallback(token);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                callback.onCallback(null);
            }
        });
    }
}
