package use_case;
import data_access.SQLiteUserRepository;

import java.sql.SQLException;

public class SignInService
{

    //this class has some static methods for us to do the signin request.

    public static boolean signIn(String input_username, String input_password, SQLiteUserRepository sql) throws SQLException
    {
        //this is just a beta edition. At the end we have to write our own error to indicate
        //why this user fails its sign in. For example, password wrong, short period request too many times

        if(sql.findByUsername(input_username) == null)
        {
            System.out.println("This username does not exist! Please sign in first!");
            return false;
        }
        else if(! sql.findByUsername(input_username).getUserPassword().equals(input_password))
        {
            System.out.println("Wrong password! Please try again!");
            return false;
        }
        else
        {
            System.out.println("Successfully signed in!");
            return true;
        }
    }
}
