package use_case;

import data_access.UserRepository;
import entity.User;

/**
 * Service class for handling user sign-up requests.
 */
public class SignUpService {

    private final UserRepository userRepository;

    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Signs up a new user with the given username and password.
     *
     * @param input_username The username of the new user.
     * @param input_password The password of the new user.
     * @param email The email of the new user.
     * @param callback A callback to handle success or failure of sign-up.
     */
    public void signUp(String input_username, String input_password, String email, SignUpCallback callback) {
        userRepository.findByUsername(input_username, new UserRepository.UserCallback() {
            @Override
            public void onCallback(User userByUsername) {
                if (userByUsername != null) {
                    callback.onSignUpResult(false, "This username is already taken! Please choose another username.");
                } else {
                    checkEmail();
                }
            }

            private void checkEmail() {
                userRepository.findByEmail(email, new UserRepository.UserCallback() {
                    @Override
                    public void onCallback(User user) {
                        if (user != null) {
                            callback.onSignUpResult(false, "Email already exists!");
                        } else {
                            createUser();
                        }
                    }
                });
            }

            private void createUser() {
                userRepository.addUser(new User(input_username, input_password, email));
                callback.onSignUpResult(true, "Successfully signed up!");
            }
        });
    }

    public interface SignUpCallback {
        void onSignUpResult(boolean success, String message);
    }
}
