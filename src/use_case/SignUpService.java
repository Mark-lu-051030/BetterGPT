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
     * @param email The email of the new user
     * @return true if the sign-up is successful, false if the username is already taken.
     */
    public boolean signUp(String input_username, String input_password, String email) {
        userRepository.findByUsername(input_username, new UserRepository.UserCallback() {
            @Override
            public void onCallback(User user) {
                if (user != null) {
                    System.out.println("This username has been used! Please choose another username!");
                } else {
                    userRepository.addUser(new User(input_username, input_password, email));
                    System.out.println("Successfully signed up!");
                }
            }
        });
        return false;
    }
}
