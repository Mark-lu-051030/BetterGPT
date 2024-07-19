package use_case;
import java.io.File;

public class LoginService
{

    private String username;
    private String password;

    private LoginService(String input_userName, String input_password)
    {
        this.username = input_userName;
        this.password = input_password;

        checkFile();
        //check if userdata.txt exists, if not create it.
    }

    private void checkFile()
    {

    }
}
