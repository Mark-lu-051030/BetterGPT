package use_case;

import data_access.GptApiClient;

public class ChatService {
    private final GptApiClient gptApiClient;

    public ChatService(GptApiClient gptApiClient) {
        this.gptApiClient = gptApiClient;
    }

    public String getChatResponse(String prompt) {
        return gptApiClient.getChatCompletion(prompt);
    }
}