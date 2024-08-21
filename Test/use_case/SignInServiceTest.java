package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import data_access.UserRepository;
import entity.User;
import use_case.SignInService;
import use_case.SignInService.SignInCallback;

public class SignInServiceTest {

    private UserRepository userRepository;
    private SignInService.SignInCallback signInCallback;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        signInCallback = mock(SignInCallback.class);
    }

    @Test
    public void testSignIn_UserDoesNotExist() {
        // Arrange
        String username = "nonExistentUser";
        String password = "password123";

        // The repository returns null, indicating the user does not exist
        doAnswer(invocation -> {
            UserRepository.UserCallback callback = invocation.getArgument(1);
            callback.onCallback(null);
            return null;
        }).when(userRepository).findByUsername(eq(username), any());

        // Act
        SignInService.signIn(username, password, userRepository, signInCallback);

        // Assert
        verify(signInCallback).onSignInResult(false, "This username does not exist! Please sign up first!");
    }

    @Test
    public void testSignIn_WrongPassword() {
        // Arrange
        String username = "existingUser";
        String correctPassword = "correctPassword";
        String wrongPassword = "wrongPassword";

        User user = new User(username, correctPassword, "email@example.com");

        // The repository returns a user, but with a different password
        doAnswer(invocation -> {
            UserRepository.UserCallback callback = invocation.getArgument(1);
            callback.onCallback(user);
            return null;
        }).when(userRepository).findByUsername(eq(username), any());

        // Act
        SignInService.signIn(username, wrongPassword, userRepository, signInCallback);

        // Assert
        verify(signInCallback).onSignInResult(false, "Wrong password! Please try again!");
    }

    @Test
    public void testSignIn_Successful() {
        // Arrange
        String username = "existingUser";
        String password = "correctPassword";

        User user = new User(username, password, "email@example.com");

        // The repository returns a user with the correct password
        doAnswer(invocation -> {
            UserRepository.UserCallback callback = invocation.getArgument(1);
            callback.onCallback(user);
            return null;
        }).when(userRepository).findByUsername(eq(username), any());

        // Act
        SignInService.signIn(username, password, userRepository, signInCallback);

        // Assert
        verify(signInCallback).onSignInResult(true, "Successfully signed in!");
    }
}
