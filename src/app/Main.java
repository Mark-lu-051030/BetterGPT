package app;

import data_access.*;
import entity.Conversation;
import interface_adapter.ChatController;
import interface_adapter.LoginController;
import use_case.ChatService;
import use_case.UpdateFirebaseToken;
import view.LoginView;
/**
 * The Main class serves as the entry point for the application.
 * It initializes necessary services and components, such as Firebase,
 * and starts the login process by displaying the login view.
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     * It initializes Firebase, creates the user repository, and starts
     * the login process by displaying the login view.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        FirebaseInitializer.initialize();
        FirebaseUserRepository userRepository = new FirebaseUserRepository();

        LoginView loginView = new LoginView();
        new LoginController(loginView, userRepository);

        loginView.setVisible(true);

    }

}
