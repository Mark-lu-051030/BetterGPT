package data_access;

import com.google.firebase.database.*;
import data_access.EmailTokenRepository;

import java.util.concurrent.CompletableFuture;

public class FirebaseTokenRepository implements EmailTokenRepository {
    private final DatabaseReference tokensRef;

    public FirebaseTokenRepository() {
        tokensRef = FirebaseDatabase.getInstance().getReference().child("tokens");
    }

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
