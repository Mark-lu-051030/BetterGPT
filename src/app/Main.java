package app;

import data_access.*;
import entity.Conversation;
import interface_adapter.ChatController;
import interface_adapter.LoginController;
import use_case.ChatService;
import use_case.UpdateFirebaseToken;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        FirebaseInitializer.initialize();
        FirebaseUserRepository userRepository = new FirebaseUserRepository();

        LoginView loginView = new LoginView();
        new LoginController(loginView, userRepository);

        loginView.setVisible(true);

    }

}
