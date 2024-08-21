package use_case;

import data_access.FirebaseTokenRepository;
import data_access.EmailTokenRepository;
import app.FirebaseInitializer;
import data_access.UserRepository;
import entity.User;
import data_access.FirebaseUserRepository;

public class UpdateFirebaseToken {
    private String email;
    private String token;

    /**
     * Constructor to initialize email and token.
     *
     * @param email The email associated with the user.
     * @param token The Firebase token to be associated with the user.
     */
    public UpdateFirebaseToken(String email, String token) {
        this.email = email;
        this.token = token;
    }

    /**
     * Updates the Firebase token associated with the user's email.
     */
    public void performUpdate() {
        FirebaseInitializer.initialize();
        EmailTokenRepository tokenRepository = new FirebaseTokenRepository();

        boolean updateSuccess = tokenRepository.updateUserToken(email, token);
        System.out.println("Update success: " + updateSuccess);
    }

    /**
     * Retrieves the Firebase token associated with the user's email.
     *
     * @return The token associated with the email.
     */
    public String retrieveToken() {
        FirebaseInitializer.initialize();
        EmailTokenRepository tokenRepository = new FirebaseTokenRepository();

        String retrievedToken = tokenRepository.getTokenByEmail(email);
        System.out.println("Token associated with email: " + retrievedToken);
        return retrievedToken;
    }

    /**
     * Deletes the user associated with the email from the Firebase database.
     */
    public void deleteUser() {
        FirebaseInitializer.initialize();
        EmailTokenRepository tokenRepository = new FirebaseTokenRepository();

        boolean deleteSuccess = tokenRepository.deleteUser(email);
        System.out.println("Delete success: " + deleteSuccess);
    }

    /**
     * Changes the password of the user identified by username.
     *
     * @param username    The username of the user.
     * @param newPassword The new password to be set.
     */
    public void changePassword(String username, String newPassword) {
        FirebaseInitializer.initialize();
        javax.swing.SwingUtilities.invokeLater(() -> {
            FirebaseUserRepository userRepository = new FirebaseUserRepository();
            userRepository.updatePassword(username, newPassword);
        });
    }

    /**
     * Finds and returns the username associated with the email.
     *
     * @return The username associated with the email.
     */
    public String getUsername() {
        FirebaseInitializer.initialize();
        FirebaseTokenRepository tokenRepository = new FirebaseTokenRepository();
        String getUsername = String.valueOf(tokenRepository.FindUserByEmail(email));
        return getUsername;
    }

    /**
     * Finds the username by email and changes the user's password.
     *
     * @param email       The email of the user.
     * @param userRepository The repository to access user data.
     * @param changePSWD  The new password to be set.
     */
    public void findUsernameByEmailandChangePSWD(String email, UserRepository userRepository, String changePSWD) {
        userRepository.findByEmail(email, new UserRepository.UserCallback() {
            @Override
            public void onCallback(User user) {
                if (user != null) {
                    String username = user.getUserName();
                    userRepository.updatePassword(username, changePSWD);
                    System.out.println("Username found: " + username);
                } else {
                    System.out.println("User not found with email: " + email);
                }
            }
        });
    }
}