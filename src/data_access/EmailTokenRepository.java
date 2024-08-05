package data_access;

public interface EmailTokenRepository {
    String getEmailByToken(String token);
    String getTokenByEmail(String email);
    boolean updateUserToken(String email, String token);
    boolean deleteUser(String email);
}

