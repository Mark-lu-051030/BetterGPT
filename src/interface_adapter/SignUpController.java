package interface_adapter;

import data_access.UserRepository;
import use_case.SignUpService;
import view.SignUpView;
import view.LoginView;

public class SignUpController {
    private final SignUpView view;
    private final SignUpService signUpService;
    private final UserRepository userRepository;

    public SignUpController(SignUpView signUpView, SignUpService signUpService, UserRepository userRepository) {
        this.view = signUpView;
        this.signUpService = signUpService;
        this.userRepository = userRepository;

        // Add listeners to the view
        view.addSignUpListener(e -> signUp());
        view.addLogInListener(e -> returnToLogin());
    }

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

    private void returnToLogin() {
        view.dispose();
        LoginView loginView = new LoginView();
        new LoginController(loginView, userRepository);
        loginView.setVisible(true);
    }
}
