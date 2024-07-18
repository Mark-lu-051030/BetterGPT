package app;

import view.ViewManager;
import data_access.GptApiClient;
import interface_adapter.ChatController;
import use_case.ChatService;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String apiKey = "sk-proj-R28ol8hXTZvbitrM96dfT3BlbkFJ1vvGN44cRIZHmhV3ZnEh";
                GptApiClient gptApiClient = new GptApiClient(apiKey);
                ChatService chatService = new ChatService(gptApiClient);
                ChatController chatController = new ChatController(chatService);

                new ViewManager(chatController);
            }
        });
    }
}

