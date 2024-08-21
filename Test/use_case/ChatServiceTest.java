package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.theokanning.openai.completion.chat.ChatMessage;
import data_access.GptApiClient;
import data_access.ConversationRepository;
import entity.Conversation;
import entity.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChatServiceTest {

    private GptApiClient gptApiClient;
    private ConversationRepository conversationRepository;
    private Conversation conversation;
    private ChatService chatService;

    @BeforeEach
    public void setUp() {
        gptApiClient = mock(GptApiClient.class);
        conversationRepository = mock(ConversationRepository.class);
        conversation = new Conversation("testUser");
        chatService = new ChatService(gptApiClient, conversationRepository, conversation);
    }

    @Test
    public void testHandleUserInput() {
        // Arrange
        String userInput = "Hello, how are you?";
        String expectedResponse = "I'm an AI, how can I assist you today?";
        when(gptApiClient.getChatCompletion(anyList())).thenReturn(expectedResponse);

        // Act
        String actualResponse = chatService.handleUserInput(userInput);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Capture the arguments to check if messages were correctly added to the conversation
        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(conversationRepository, times(2)).addMessage(eq(conversation.getId()), messageCaptor.capture());

        List<Message> capturedMessages = messageCaptor.getAllValues();

        // Check that the first message is the user input
        Message userMessage = capturedMessages.get(0);
        assertEquals(userInput, userMessage.getContent());
        assertEquals("user", userMessage.getRole());

        // Check that the second message is the bot response
        Message botMessage = capturedMessages.get(1);
        assertEquals(expectedResponse, botMessage.getContent());
        assertEquals("assistant", botMessage.getRole());
    }

    @Test
    public void testHandleUserInput_ConversationHistory() {
        // Arrange
        String userInput = "What is the weather today?";
        String expectedResponse = "I'm not sure, please check a weather app.";
        when(gptApiClient.getChatCompletion(anyList())).thenReturn(expectedResponse);

        // Add a previous message to the conversation
        Message previousMessage = new Message(null, conversation.getId(), "Hi there!", LocalDateTime.now(), "user");
        conversation.addMessage(previousMessage);

        // Act
        String actualResponse = chatService.handleUserInput(userInput);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify the conversation history was passed correctly to the GPT API
        ArgumentCaptor<List<ChatMessage>> historyCaptor = ArgumentCaptor.forClass(List.class);
        verify(gptApiClient).getChatCompletion(historyCaptor.capture());

        List<ChatMessage> capturedHistory = historyCaptor.getValue();

        // Ensure history contains both the previous and new user message
        assertEquals(2, capturedHistory.size());
        assertEquals("Hi there!", capturedHistory.get(0).getContent());
        assertEquals("user", capturedHistory.get(0).getRole());
        assertEquals(userInput, capturedHistory.get(1).getContent());
        assertEquals("user", capturedHistory.get(1).getRole());
    }
}
