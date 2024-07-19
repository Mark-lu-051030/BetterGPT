package use_case;

import data_access.SQLiteUserRepository;
import entity.User;

import java.sql.SQLException;

public class SignupService
{
    //this class has some static methods for us to do the signup request.
    public static boolean signUp(String input_username, String input_password) throws SQLException {
        //this is just a beta edition. At the end we have to write our own error to indicate
        //why this user fails its sign up. For example, email address used too many times,
        //id duplicated...


        SQLiteUserRepository sql = new SQLiteUserRepository();

        if(sql.findByUsername(input_username) != null)
        {
            System.out.println("This username has been used! Please change another username!");
            return false;
        }
        else
        {
            sql.addUser(new User(input_username, input_password));
            System.out.println("Successfully signed up!");
            return true;
        }

    }



}
