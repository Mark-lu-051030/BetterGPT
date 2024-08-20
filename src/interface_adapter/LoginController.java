package interface_adapter;

import use_case.SignInService;
import data_access.UserRepository;
import use_case.SignUpService;
import view.LoginView;
import view.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private final LoginView view;
    private final UserRepository userRepository;

    public LoginController(LoginView view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;

        view.addLoginListener(new LoginListener());
        view.addSignUpListener(new SignUpListener());
        view.addForgotPasswordListener(new ForgotPasswordListener());
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            // Authenticate user
            SignInService.signIn(username, password, userRepository, new SignInService.SignInCallback() {
                @Override
                public void onSignInResult(boolean success, String message) {
                    if (success) {
                        // Proceed to chat window
                        view.displayMessage("Login successful!");
                        // TODO: Navigate to Chat window
                    } else {
                        // Show error message
                        view.displayMessage(message);
                    }
                }
            });
        }
    }

    private class SignUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            SignUpView signUpView = new SignUpView();
            new SignUpController(signUpView, new SignUpService(userRepository), userRepository);
            signUpView.setVisible(true);
        }
    }

    private class ForgotPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Implement navigation to Forgot Password window
            view.displayMessage("Forgot Password functionality is not implemented yet.");
        }
    }
}
