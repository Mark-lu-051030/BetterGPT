package use_case;

import java.util.UUID;

public class CreateToken {

    public String generateResetToken(String email) { // to generate and return verification token
        String token = UUID.randomUUID().toString();

        return token;
    }
}
