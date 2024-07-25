package use_case;

import data_access.SQLiteUserRepository;
import entity.User;

import java.sql.SQLException;

/**
 * Service class for handling user sign-up requests.
 */
public class SignUpService {
    /**
     * Signs up a new user with the given username and password.
     *
     * @param input_username The username of the new user.
     * @param input_password The password of the new user.
     * @return true if the sign-up is successful, false if the username is already taken.
     * @throws SQLException If there is an error accessing the database.
     */
    public static boolean signUp(String input_username, String input_password) throws SQLException {
        // This is a beta edition. In the final version, we need to provide specific error messages
        // to indicate why the user failed to sign up (e.g., email address used too many times, ID duplicated).

        SQLiteUserRepository sql = new SQLiteUserRepository();

        if (sql.findByUsername(input_username) != null) {
            System.out.println("This username has been used! Please choose another username!");
            return false;
        } else {
            sql.addUser(new User(input_username, input_password));
            System.out.println("Successfully signed up!");
            return true;
        }
    }
}
