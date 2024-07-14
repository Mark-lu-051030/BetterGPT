package data_access;

import com.theokanning.openai.service.*;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.Arrays;

public class GptApiClient {
    private final OpenAiService service;

    public GptApiClient(String apiKey) {
        this.service = new OpenAiService(apiKey);
    }

    public String getChatCompletion(String prompt) {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(new ChatMessage("user", prompt)))
                .build();

        ChatCompletionResult result = service.createChatCompletion(request);
        return result.getChoices().get(0).getMessage().getContent();
    }
}