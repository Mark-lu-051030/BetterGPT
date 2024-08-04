package data_access;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.List;

/**
 * DataHandler is responsible for creating chat completion requests.
 */
public class DataHandler {
    /**
     * Builds a chat completion request for the given model and prompt.
     *
     * @param model    the model to use for the chat completion request
     * @param messages the list of messages for the chat completion
     * @return a ChatCompletionRequest object containing the model and messages
     */
    public ChatCompletionRequest buildChatCompletionRequest(String model, List<ChatMessage> messages) {
        return ChatCompletionRequest.builder()
                .model(model)
                .messages(messages)
                .build();
    }
}
