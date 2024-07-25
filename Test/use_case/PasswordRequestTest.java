package use_case;

import data_access.PasswordResetService;
import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordRequestTest
{
    private PasswordRequest prtest;
    private User usertester = new User("testUser", "admin");

    private PasswordResetService prstest = new PasswordResetService();

    public void setUp()
    {
        prtest = new PasswordRequest(usertester, prstest);
    }

    @Test
    void getUserTest()
    {
        assertEquals(new User("testUser", "admin"), prtest.getUser());
    }

    @Test
    void getPasswordResetServiceTest()
    {
        assertEquals(new PasswordResetService(), prtest.getPasswordResetService());
    }

    @Test
    void setUserTest()
    {
        User temp = new User("testUser22", "admin22");
        prtest.setUser(new User("testUser22", "admin22"));
        assertEquals(temp, prtest.getUser());
    }

    @Test
    void setPasswordResetServiceTest()
    {
        PasswordResetService temp = new PasswordResetService();
        prtest.setPasswordResetService(new PasswordResetService());
        assertEquals(temp, prtest.getPasswordResetService());
    }
}