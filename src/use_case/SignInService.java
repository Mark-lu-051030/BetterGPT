package use_case;

import data_access.SQLiteUserRepository;

import java.sql.SQLException;

/**
 * Service class for handling user sign-in requests.
 */
public class SignInService {

    /**
     * Signs in a user with the given username and password.
     *
     * @param input_username The username of the user attempting to sign in.
     * @param input_password The password of the user attempting to sign in.
     * @param sql            An instance of SQLiteUserRepository to access the database.
     * @return true if the sign-in is successful, false otherwise.
     * @throws SQLException If there is an error accessing the database.
     */
    public static boolean signIn(String input_username, String input_password, SQLiteUserRepository sql) throws SQLException {
        // This is a beta edition. In the final version, we need to provide specific error messages
        // to indicate why the user failed to sign in (e.g., incorrect password, too many requests in a short period).

        if (sql.findByUsername(input_username) == null) {
            System.out.println("This username does not exist! Please sign up first!");
            return false;
        } else if (!sql.findByUsername(input_username).getUserPassword().equals(input_password)) {
            System.out.println("Wrong password! Please try again!");
            return false;
        } else {
            System.out.println("Successfully signed in!");
            return true;
        }
    }
}
