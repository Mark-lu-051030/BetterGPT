package interface_adapter;

import data_access.UserRepository;
import use_case.SignUpService;
import view.SignUpView;
import view.LoginView;

/**
 * The SignUpController class manages the interactions between the SignUpView and the SignUpService.
 * It handles user input for signing up and transitioning back to the login view.
 */
public class SignUpController {
    private final SignUpView view;
    private final SignUpService signUpService;
    private final UserRepository userRepository;

    /**
     * Constructs a SignUpController with the specified SignUpView, SignUpService, and UserRepository.
     *
     * @param signUpView      the view for user registration
     * @param signUpService   the service responsible for handling the sign-up process
     * @param userRepository  the repository for managing user data
     */
    public SignUpController(SignUpView signUpView, SignUpService signUpService, UserRepository userRepository) {
        this.view = signUpView;
        this.signUpService = signUpService;
        this.userRepository = userRepository;

        // Add listeners to the view
        view.addSignUpListener(e -> signUp());
        view.addLogInListener(e -> returnToLogin());
    }

    /**
     * Handles the sign-up process by validating user input and calling the sign-up service.
     * If the sign-up is successful, the user is redirected to the login view.
     */
    private void signUp() {
        String email = view.getEmail();
        String username = view.getUsername();
        String password = view.getPassword();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            view.displayMessage("Username, email, and password must not be empty!");
            return;
        }

        signUpService.signUp(username, password, email, (success, message) -> {
            view.displayMessage(message);
            if (success) {
                returnToLogin();
            }
        });
    }

    /**
     * Closes the sign-up view and opens the login view.
     */
    private void returnToLogin() {
        view.dispose();
        LoginView loginView = new LoginView();
        new LoginController(loginView, userRepository);
        loginView.setVisible(true);
    }
}
