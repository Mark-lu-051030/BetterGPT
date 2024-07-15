package Test;

import com.theokanning.openai.service.*;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import interface_adapter.ConsoleListener;

import java.util.Arrays;
import java.util.Scanner;


public class Test {
    public static void main(String[] args)
    {
        /*String apiKey = "sk-proj-R28ol8hXTZvbitrM96dfT3BlbkFJ1vvGN44cRIZHmhV3ZnEh";
        OpenAiService service = new OpenAiService(apiKey);

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(new ChatMessage("user", "who are you?")))
                .build();

        ChatCompletionResult result = service.createChatCompletion(request);
        System.out.println(result);

         */



        ConsoleListener testlisten = new ConsoleListener(new Scanner(System.in));
        testlisten.listenInNewThread();

        if(!testlisten.checkStatus())
            System.exit(0);



    }
}
