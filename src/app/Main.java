package app;

import view.ViewManager;
import data_access.GptApiClient;
import interface_adapter.ChatController;
import use_case.ChatService;
import data_access.SQLiteUserRepository;
import entity.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SQLiteUserRepository userRepository = new SQLiteUserRepository();

                String apiKey = "sk-proj-R28ol8hXTZvbitrM96dfT3BlbkFJ1vvGN44cRIZHmhV3ZnEh";
                GptApiClient gptApiClient = new GptApiClient(apiKey);
                ChatService chatService = new ChatService(gptApiClient);
                ChatController chatController = new ChatController(chatService);

                User newUser = new User("john_doe", "password123", "john@example.com");
                try {
                    userRepository.addUser(newUser);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("User added: " + newUser.getUserName());

                new ViewManager(chatController);
            }
        });
    }
}

