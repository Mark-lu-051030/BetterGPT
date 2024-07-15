package interface_adapter;

import com.theokanning.openai.completion.chat.ChatCompletionResult;
import use_case.ChatService;

public class ChatController
{
    private ChatCompletionResult gpt_message;
    private String user_message;

    /*public ChatController(ChatService chatService)
    {
        this.chatService = chatService;
    }

    public String getResponse(String prompt)
    {
        return chatService.getChatResponse(prompt);
    }

     */


    public ChatController(ChatCompletionResult input_gpt_message)
    {
        this.gpt_message = input_gpt_message;
    }
    public ChatController(String input_user_message)
    {
        this.user_message = input_user_message;
    }

    public String getUserMsg(){return this.user_message;}

    public ChatCompletionResult getGptMsg() {return this.gpt_message;}

}

