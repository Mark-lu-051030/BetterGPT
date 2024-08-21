package data_access;

import com.google.firebase.database.*;
import data_access.EmailTokenRepository;

import java.util.concurrent.CompletableFuture;

/**
 * The FirebaseTokenRepository class provides methods for managing user tokens in Firebase.
 * It implements the EmailTokenRepository interface, providing methods to get, update, and delete tokens associated with user emails.
 */
public class FirebaseTokenRepository implements EmailTokenRepository {
    private final DatabaseReference tokensRef;

    /**
     * Constructs a FirebaseTokenRepository and initializes the Firebase database reference for tokens.
     */
    public FirebaseTokenRepository() {
        tokensRef = FirebaseDatabase.getInstance().getReference().child("tokens");
    }

    /**
     * Retrieves the email associated with a given token from Firebase.
     *
     * @param token the token to search for
     * @return the email associated with the token, or null if not found
     */
    @Override
    public String getEmailByToken(String token) {
        CompletableFuture<String> future = new CompletableFuture<>();
        tokensRef.orderByValue().equalTo(token).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    future.complete(child.getKey().replace(",", "."));
                    return;
                }
                future.complete(null);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(error.toException());
            }
        });
        try {
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves the token associated with a given email from Firebase.
     *
     * @param email the email to search for
     * @return the token associated with the email, or null if not found
     */
    @Override
    public String getTokenByEmail(String email) {
        CompletableFuture<String> future = new CompletableFuture<>();
        tokensRef.child(email.replace(".", ",")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    future.complete(snapshot.getValue(String.class));
                } else {
                    future.complete(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(error.toException());
            }
        });
        try {
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the token associated with a given email in Firebase.
     *
     * @param email the email for which the token should be updated
     * @param token the new token to associate with the email
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateUserToken(String email, String token) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        tokensRef.child(email.replace(".", ",")).setValue(token, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    future.completeExceptionally(error.toException());
                } else {
                    future.complete(true);
                }
            }
        });
        try {
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes the token associated with a given email from Firebase.
     *
     * @param email the email for which the token should be deleted
     * @return true if the deletion was successful, false otherwise
     */
    @Override
    public boolean deleteUser(String email) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        tokensRef.child(email.replace(".", ",")).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    future.completeExceptionally(error.toException());
                } else {
                    future.complete(true);
                }
            }
        });
        try {
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Finds and retrieves the token associated with a given email from Firebase asynchronously.
     *
     * @param email the email to search for
     * @return a CompletableFuture containing the token associated with the email, or null if not found
     */
    public CompletableFuture<String> FindUserByEmail(String email) {
        CompletableFuture<String> future = new CompletableFuture<>();
        tokensRef.child(email.replace(".", ",")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    future.complete(snapshot.getValue(String.class));
                } else {
                    future.complete(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(error.toException());
            }
        });
        return future;
    }
}
