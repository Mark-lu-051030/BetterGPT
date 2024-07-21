package data_access;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;

/**
 * GptApiClient is responsible for interacting with the OpenAI API to create and send chat completion requests.
 */
public class GptApiClient {
    private final OpenAiService service;
    private final DataHandler dataHandler;

    /**
     * Constructs a GptApiClient with the specified API key.
     *
     * @param apiKey the API key for accessing the OpenAiService
     */
    public GptApiClient(String apiKey) {
        this.service = new OpenAiService(apiKey);
        this.dataHandler = new DataHandler();
    }

    /**
     * Creates a chat completion request using the specified prompt.
     *
     * @param prompt the user's input prompt for the chat completion
     * @return a ChatCompletionRequest object containing the model and prompt
     */
    public ChatCompletionRequest createChatCompletionRequest(String prompt) {
        return dataHandler.buildChatCompletionRequest("gpt-3.5-turbo", prompt);
    }

    /**
     * Sends the chat completion request and receives the response from the OpenAI service.
     * @param request the chat completion request to be sent
     * @return the response message content from the chat completion request
     */
    public String sendRequestAndGetResponse(ChatCompletionRequest request) {
        ChatCompletionResult result = service.createChatCompletion(request);
        return result.getChoices().get(0).getMessage().getContent();
    }

    /**
     * Creates and sends a chat completion request based on the specified prompt, returning the response.
     *
     * @param prompt the user's input prompt for the chat completion
     * @return the response message content from the chat completion request
     */
    public String getChatCompletion(String prompt) {
        ChatCompletionRequest request = createChatCompletionRequest(prompt);
        return sendRequestAndGetResponse(request);
    }
}