package data_access;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PasswordResetService {
    private Map<String, TokenInfo> storedTokens = new HashMap<>();
    private static final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(30); // 30 minutes

    public String generateResetToken(String email) { // to generate and return verification token
        String token = UUID.randomUUID().toString();
        storedTokens.put(token, new TokenInfo(email, new Date())); // store corresponding token to email, and date
        return token;
    }

    public boolean validateResetToken(String token) {  // determine if token is valid
        TokenInfo potentialToken = storedTokens.get(token);
        if (potentialToken == null) {  // check if the token has been stored
            return false;
        }
        long currentTime = System.currentTimeMillis();  // determine if token is expired (past 30 minutes)
        long tokenTime = potentialToken.getCreationDate().getTime();
        if (currentTime - tokenTime > EXPIRATION_TIME) {
            storedTokens.remove(token);  // remove expired token
            return false;
        }
        return true;
    }
    public String getEmailByToken(String token) {
        TokenInfo tokenInfo = storedTokens.get(token);
        if (tokenInfo != null) {
            return tokenInfo.getEmail();  // call getter if the token exists from an email
        }
        return null;  // there is no email corresponding to token
    }
    // note: may store using a database instead, talk to group
    private static class TokenInfo {
        private String email;
        private Date creationDate;

        public TokenInfo(String email, Date creationDate) {
            this.email = email;
            this.creationDate = creationDate;
        }

        public String getEmail() {

            return email;
        }

        public Date getCreationDate() {

            return creationDate;
        }
    }
}

