package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ChatServiceTest {
    private ChatService chatService;
    private ChatClient mockChatClient;

    @BeforeEach
    void setUp() {
        mockChatClient = mock(ChatClient.class);
        chatService = new ChatService(mockChatClient);
    }

    @Test
    void generateResponse() {
        String prompt = "Hello, World!";
        String expectedResponse = "Hello!";

        when(mockChatClient.getChatCompletion(prompt)).thenReturn(expectedResponse);
        String response = chatService.generateResponse(prompt);
        verify(mockChatClient).getChatCompletion(prompt);
        assertEquals(expectedResponse, response);
    }
}
