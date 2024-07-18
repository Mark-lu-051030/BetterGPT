package data_access;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.Collections;

public class DataHandler {
    public ChatCompletionRequest buildChatCompletionRequest(String model, String prompt) {
        return ChatCompletionRequest.builder()
                .model(model)
                .messages(Collections.singletonList(new ChatMessage("user", prompt)))
                .build();
    }
}