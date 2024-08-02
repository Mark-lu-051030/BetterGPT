package use_case;

import data_access.UserRepository;
import entity.User;

/**
 * Service class for handling user sign-in requests.
 */
public class SignInService {
    /**
     * Signs in a user with the given username and password.
     *
     * @param input_username The username of the user attempting to sign in.
     * @param input_password The password of the user attempting to sign in.
     * @param userRepository  An instance of UserRepository to access the database.
     * */
    public static void signIn(String input_username, String input_password,
                              UserRepository userRepository, SignInCallback callback) {
        userRepository.findByUsername(input_username, new UserRepository.UserCallback() {
            @Override
            public void onCallback(User user) {
                if (user == null) {
                    callback.onSignInResult(false,
                            "This username does not exist! Please sign up first!");
                } else if (!user.getUserPassword().equals(input_password)) {
                    callback.onSignInResult(false, "Wrong password! Please try again!");
                } else {
                    callback.onSignInResult(true, "Successfully signed in!");
                }
            }
        });
    }

    public interface SignInCallback {
        void onSignInResult(boolean success, String message);
    }
}
