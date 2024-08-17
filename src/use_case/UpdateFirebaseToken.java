package use_case;

import data_access.FirebaseTokenRepository;
import data_access.EmailTokenRepository;
import app.FirebaseInitializer;


import data_access.FirebaseUserRepository;

public class UpdateFirebaseToken {
    private String email;
    private String token;

    public UpdateFirebaseToken(String email, String token) {
        this.email = email;
        this.token = token;

    }

    public void performUpdate() {
        FirebaseInitializer.initialize();
        EmailTokenRepository tokenRepository = new FirebaseTokenRepository();

        // Update the database with a user and their corresponding token
        boolean updateSuccess = tokenRepository.updateUserToken(email, token);
        System.out.println("Update success: " + updateSuccess);


    }

    public String retrieveToken() {
        FirebaseInitializer.initialize();
        EmailTokenRepository tokenRepository = new FirebaseTokenRepository();

        String retrievedToken = tokenRepository.getTokenByEmail(email);
        System.out.println("Token associated with email: " + retrievedToken);
        return retrievedToken;

    }

    public void deleteUser() {
        FirebaseInitializer.initialize();
        EmailTokenRepository tokenRepository = new FirebaseTokenRepository();

        boolean deleteSuccess = tokenRepository.deleteUser(email);
        System.out.println("Delete success: " + deleteSuccess);
    }

    public void changePassword(String username, String newPassword) {
        FirebaseInitializer.initialize();
        javax.swing.SwingUtilities.invokeLater(() -> {
            FirebaseUserRepository userRepository = new FirebaseUserRepository();
            userRepository.updatePassword(username, newPassword);
        });
    }
}
/* for testing delete later
    public static void main(String[] args) {
        UpdateFirebaseToken updateFirebaseToken = new UpdateFirebaseToken("12@gmail.com", "testtoken");
        updateFirebaseToken.changePassword("ZA12", "!!!");
    }
}

    /*
    public static void main(String[] args) {
        /*
        UpdateFirebaseToken updateTest1 = new UpdateFirebaseToken("user1122223@example.com", "new_token151");
        updateTest1.deleteUser();

         */
        // Create a new user object
        /*
        userRepository.updatePassword("123", "qoqo");
        System.out.println("Password changed successfully");

        */
        //FirebaseInitializer.initialize();
        //javax.swing.SwingUtilities.invokeLater(() -> {
            //FirebaseUserRepository userRepository = new FirebaseUserRepository();
            //userRepository.updatePassword("test1", "fiu");


        /*
        User newUser = new User("ZA12", "password11212", "12@gmail.com");
        userRepository.updateUser(newUser);

        userRepository.addUser(newUser);

        System.out.println("User testsubject1 added to Firebase.");

         */






        //});
    //}
//}