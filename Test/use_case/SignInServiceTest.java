package use_case;

import data_access.SQLiteUserRepository;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SignInServiceTest {

    @Mock
    private SQLiteUserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignInSuccessful() throws SQLException {
        String username = "existing name";
        String password = "correct password";
        User mockUser = new User(username, password, "existing email");

        when(mockUserRepository.findByUsername(username)).thenReturn(mockUser);
        boolean result = SignInService.signIn(username, password, mockUserRepository);
        assertTrue(result);
    }

    @Test
    void testSignInUserNotFound() throws SQLException {
        String username = "name";
        String password = "password";

        when(mockUserRepository.findByUsername(username)).thenReturn();
        boolean result = SignInService.signIn(username, password, mockUserRepository);
        assertFalse(result);
    }

    @Test
    void testSignInWrongPassword() throws SQLException {
        String username = "existing name";
        String password = "wrong password";
        User mockUser = new User(username, "correct password", "existing email");

        when(mockUserRepository.findByUsername(username)).thenReturn(mockUser);
        boolean result = SignInService.signIn(username, password, mockUserRepository);
        assertFalse(result);
    }
}
