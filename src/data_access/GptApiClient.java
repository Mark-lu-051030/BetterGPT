package data_access;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import use_case.ChatClient;

import java.util.List;

/**
 * GptApiClient is responsible for interacting with the OpenAI API to create and send chat completion requests.
 */
public class GptApiClient implements ChatClient {
    private final OpenAiService service;
    private final DataHandler dataHandler;

    /**
     * Constructs a GptApiClient with the specified API key.
     *
     * @param apiKey the API key for accessing the OpenAiService
     */
    public GptApiClient(String apiKey) {
        this.service = new OpenAiService(apiKey.trim());
        this.dataHandler = new DataHandler();
    }

    /**
     * Sends the chat completion request and receives the response from the OpenAI service.
     * @param request the chat completion request to be sent
     * @return the response message content from the chat completion request
     */
    private String sendRequestAndGetResponse(ChatCompletionRequest request) {
        ChatCompletionResult result = service.createChatCompletion(request);
        return result.getChoices().get(0).getMessage().getContent();
    }

    /**
     * Creates and sends a chat completion request based on the specified prompt, returning the response.
     *
     * @param messages the list of chat messages
     * @return the response message content from the chat completion request
     */
    @Override
    public String getChatCompletion(List<ChatMessage> messages) {
        ChatCompletionRequest request = dataHandler.buildChatCompletionRequest("gpt-3.5-turbo", messages);
        return sendRequestAndGetResponse(request);
    }
}
