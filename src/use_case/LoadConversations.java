package use_case;

import data_access.ConversationRepository;
import entity.Conversation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The LoadConversations class is responsible for retrieving and processing conversation data
 * for a specific user. It interacts with the ConversationRepository to load conversations.
 */
public class LoadConversations {
    private final ConversationRepository conversationRepository;

    /**
     * Constructs a LoadConversations use case with the specified ConversationRepository.
     *
     * @param conversationRepository the repository used to access conversation data
     */
    public LoadConversations(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    /**
     * Loads all conversations for the specified user and returns a list of their names.
     *
     * @param username the username for whom the conversations are to be loaded
     * @return a list of conversation names associated with the user
     */
    public List<String> loadAllConversations(String username) {
        List<Conversation> conversations = conversationRepository.getConversationsByUserId(username);
        List<String> conversationNames = conversations.stream()
                .map(Conversation::getName)
                .collect(Collectors.toList());
        System.out.println(conversationNames);
        return conversationNames;
    }
}
