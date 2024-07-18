package data_access;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;

public class GptApiClient {
    private final OpenAiService service;
    private final DataHandler dataHandler;

    public GptApiClient(String apiKey) {
        this.service = new OpenAiService(apiKey);
        this.dataHandler = new DataHandler();
    }

    public ChatCompletionRequest createChatCompletionRequest(String prompt) {
        return dataHandler.buildChatCompletionRequest("gpt-3.5-turbo", prompt);
    }

    public String sendRequestAndGetResponse(ChatCompletionRequest request) {
        ChatCompletionResult result = service.createChatCompletion(request);
        return result.getChoices().get(0).getMessage().getContent();
    }

    public String getChatCompletion(String prompt) {
        ChatCompletionRequest request = createChatCompletionRequest(prompt);
        return sendRequestAndGetResponse(request);
    }
}