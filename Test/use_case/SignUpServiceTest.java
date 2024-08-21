package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import data_access.UserRepository;
import entity.User;
import use_case.SignUpService;
import use_case.SignUpService.SignUpCallback;

public class SignUpServiceTest {

    private UserRepository userRepository;
    private SignUpService signUpService;
    private SignUpCallback signUpCallback;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        signUpCallback = mock(SignUpCallback.class);
        signUpService = new SignUpService(userRepository);
    }

    @Test
    public void testSignUp_UsernameAlreadyTaken() {
        // Arrange
        String username = "existingUser";
        String password = "password123";
        String email = "email@example.com";

        // Simulate that the username already exists
        doAnswer(invocation -> {
            UserRepository.UserCallback callback = invocation.getArgument(1);
            callback.onCallback(new User(username, password, email));
            return null;
        }).when(userRepository).findByUsername(eq(username), any());

        // Act
        signUpService.signUp(username, password, email, signUpCallback);

        // Assert
        verify(signUpCallback).onSignUpResult(false, "This username is already taken! Please choose another username.");
    }

    @Test
    public void testSignUp_EmailAlreadyExists() {
        // Arrange
        String username = "newUser";
        String password = "password123";
        String email = "existingEmail@example.com";

        // Simulate that the username does not exist
        doAnswer(invocation -> {
            UserRepository.UserCallback callback = invocation.getArgument(1);
            callback.onCallback(null);
            return null;
        }).when(userRepository).findByUsername(eq(username), any());

        // Simulate that the email already exists
        doAnswer(invocation -> {
            UserRepository.UserCallback callback = invocation.getArgument(1);
            callback.onCallback(new User("anotherUser", password, email));
            return null;
        }).when(userRepository).findByEmail(eq(email), any());

        // Act
        signUpService.signUp(username, password, email, signUpCallback);

        // Assert
        verify(signUpCallback).onSignUpResult(false, "Email already exists!");
    }

    @Test
    public void testSignUp_Successful() {
        // Arrange
        String username = "newUser";
        String password = "password123";
        String email = "newEmail@example.com";

        // Simulate that the username does not exist
        doAnswer(invocation -> {
            UserRepository.UserCallback callback = invocation.getArgument(1);
            callback.onCallback(null);
            return null;
        }).when(userRepository).findByUsername(eq(username), any());

        // Simulate that the email does not exist
        doAnswer(invocation -> {
            UserRepository.UserCallback callback = invocation.getArgument(1);
            callback.onCallback(null);
            return null;
        }).when(userRepository).findByEmail(eq(email), any());

        // Act
        signUpService.signUp(username, password, email, signUpCallback);

        // Assert
        verify(signUpCallback).onSignUpResult(true, "Successfully signed up!");

        // Verify that addUser was called with the correct arguments
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).addUser(userCaptor.capture());
        User addedUser = userCaptor.getValue();
        assertEquals(username, addedUser.getUserName());
        assertEquals(password, addedUser.getUserPassword());
        assertEquals(email, addedUser.getUserEmail());
    }
}
