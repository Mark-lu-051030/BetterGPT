package data_access;

public interface EmailTokenRepository {
    void mapEmailToToken(String email, String token);
    void retrieveEmailAndToken(String email, TokenCallback callback);

    public interface TokenCallback {
        void onCallback(String token);
    }
}
