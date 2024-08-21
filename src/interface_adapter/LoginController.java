package interface_adapter;

import app.ApiKeyProvider;
import data_access.ConversationRepository;
import data_access.FirebaseConversationsRepository;
import data_access.GptApiClient;
import entity.Conversation;
import use_case.ChatService;
import use_case.SignInService;
import data_access.UserRepository;
import use_case.SignUpService;
import view.ChatWindow;
import view.LoginView;
import view.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

/**
 * The LoginController class is responsible for handling user interactions with the login view.
 * It manages the login, sign-up, and forgot password actions, and initializes the application
 * upon successful login.
 */

public class LoginController {
    private final LoginView view;
    private final UserRepository userRepository;


    /**
     * Constructs a LoginController with the specified LoginView and UserRepository.
     *
     * @param view           the LoginView instance to control
     * @param userRepository the UserRepository used to authenticate and manage user data
     */
    public LoginController(LoginView view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;

        view.addLoginListener(new LoginListener());
        view.addSignUpListener(new SignUpListener());
        view.addForgotPasswordListener(new ForgotPasswordListener());

    }
    /**
     * Inner class that handles the login action. It listens for login events and authenticates
     * the user using the SignInService.
     */
    private class LoginListener implements ActionListener {
        /**
         * Invoked when an action occurs. Authenticates the user based on the input username
         * and password, and initializes the chat window on successful authentication.
         *
         * @param e the event that occurred
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            // Authenticate user
            SignInService.signIn(username, password, userRepository, new SignInService.SignInCallback() {
                @Override
                public void onSignInResult(boolean success, String message) {
                    if (success) {
                        view.dispose();
                        ChatWindow chatWindow = new ChatWindow();
                        ApplicationInitializer.start(username, chatWindow, userRepository);
                    } else {
                        view.displayMessage(message);
                    }
                }
            });
        }
    }

    /**
     * Inner class that handles the sign-up action. It listens for sign-up events and opens
     * the sign-up view.
     */
    private class SignUpListener implements ActionListener {
        /**
         * Invoked when an action occurs. Opens the sign-up view and hides the login view.
         *
         * @param e the event that occurred
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            SignUpView signUpView = new SignUpView();
            new SignUpController(signUpView, new SignUpService(userRepository), userRepository);
            signUpView.setVisible(true);
        }
    }

    /**
     * Inner class that handles the forgot password action. It listens for forgot password
     * events and displays a message indicating that the functionality is not implemented.
     */
    private class ForgotPasswordListener implements ActionListener {
        /**
         * Invoked when an action occurs. Displays a message that the forgot password
         * functionality is not implemented.
         *
         * @param e the event that occurred
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.displayMessage("Forgot Password functionality is not implemented yet.");
        }
    }

}