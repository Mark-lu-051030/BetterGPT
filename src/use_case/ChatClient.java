package use_case;

import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.List;

public interface ChatClient {
    String getChatCompletion(List<ChatMessage> messages);
}
