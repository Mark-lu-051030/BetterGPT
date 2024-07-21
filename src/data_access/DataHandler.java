package data_access;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.Collections;

/**
 * DataHandler is responsible for creating chat completion requests.
 */
public class DataHandler {
    /**
     * Builds a chat completion request for the given model and prompt.
     *
     * @param model  the model to use for the chat completion request
     * @param prompt the user's input prompt for the chat completion
     * @return a ChatCompletionRequest object containing the model and prompt
     */
    public ChatCompletionRequest buildChatCompletionRequest(String model, String prompt) {
        return ChatCompletionRequest.builder()
                .model(model)
                .messages(Collections.singletonList(new ChatMessage("user", prompt)))
                .build();
    }
}