package data_access;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.json.*;

import java.util.Arrays;


public class DataHandler
{
    public static ChatCompletionRequest sendUserMsg(String input_user_message)
    {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(new ChatMessage("user", input_user_message)))
                .build();

        return request;
    }

    public static String getGptMsg(OpenAiService service, ChatCompletionRequest request)
    {
        return service.createChatCompletion(request).getChoices().get(0).getMessage().getContent();
    }






}
