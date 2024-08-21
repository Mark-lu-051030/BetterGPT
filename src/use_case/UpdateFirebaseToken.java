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

    public UpdateFirebaseToken(String email, String token) {
        this.email = email;
        this.token = token;

    }
    /*
    public String getUsername(){
        FirebaseInitializer.initialize();
        EmailTokenRepository tokenRepository = new FirebaseTokenRepository();

        String corespondingUsername = tokenRepository.

    }

     */

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

    public String getUsername() {
        FirebaseInitializer.initialize();
        FirebaseTokenRepository tokenRepository = new FirebaseTokenRepository();
        String getUsername = String.valueOf(tokenRepository.FindUserByEmail(email));
        return getUsername;
    }

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


/*
    public static void main(String[] args) {
        String testEmail = "testuser@example.com";
        String testToken = "aw1";

        // Instantiate the UpdateFirebaseToken class with test data
        UpdateFirebaseToken updateFirebaseToken = new UpdateFirebaseToken(testEmail, testToken);

        FirebaseInitializer.initialize();


        javax.swing.SwingUtilities.invokeLater(() -> {
            FirebaseUserRepository userRepository = new FirebaseUserRepository();

            // Call the performUpdate() method to test it
            updateFirebaseToken.findUsernameByEmailandChangePSWD(testEmail, userRepository, "newpass");


        });
    }
}

 */



        /*
        //UpdateFirebaseToken updateFirebaseToken = new UpdateFirebaseToken("12@gmail.com", "testtoken");
        //updateFirebaseToken.changePassword("ZA12", "!!!");
    }
}


    public static void main(String[] args) {

        UpdateFirebaseToken updateTest1 = new UpdateFirebaseToken("user1122223@example.com", "new_token151");
        updateTest1.deleteUser();




        // Create a new user object

        userRepository.updatePassword("123", "qoqo");
        System.out.println("Password changed successfully");

         */

        /*
        FirebaseInitializer.initialize();
        javax.swing.SwingUtilities.invokeLater(() -> {
            FirebaseUserRepository userRepository = new FirebaseUserRepository();
            //userRepository.updatePassword("test1", "fiu");

        User newUser = new User("ZA12", "password11212", "12@gmail.com");
        userRepository.updateUser(newUser);

        userRepository.addUser(newUser);

        System.out.println("User testsubject1 added to Firebase.");


        //});
    //}

         */
