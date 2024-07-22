package Entity;

import entity.User;
import junit.framework.TestCase;


public class UserTest extends TestCase {

    private User user;
    private User userWithEmail;


    public void setUp() {
        user = new User("testUser", "passwordXX12", "test@example.com");

    }


    public void testGetUserName() {
        assertEquals("testUser", user.getUserName());
    }


    public void testSetUserName() {
        user.setUserName("newUser");
        assertEquals("newUser", user.getUserName());
    }

    public void testGetUserPassword() {
        assertEquals("passwordXX12", user.getUserPassword());
        }

    public void testSetUserPassword() {
        user.setUserPassword("newPass");
        assertEquals("newPass", user.getUserPassword());

    }
    public void testGetUserEmail() {
        user.setUserEmail("new@example.com");
        assertEquals("new@example.com", user.getUserEmail());
    }
    public void testSetUserEmail() {
        assertEquals("test@example.com", user.getUserEmail());
        user.setUserEmail("updated@example.com");
        assertEquals("updated@example.com", user.getUserEmail());
    }
    public void testConversations() {
        assertTrue(user.getConversations().isEmpty());

    }
}