package interface_adapter;

import use_case.LoadConversations;

import java.util.List;

/**
 * The ConversationController class acts as an intermediary between the user interface
 * and the use case that handles loading conversations. It provides methods to load conversations
 * for a specific user and format them for display.
 */
public class ConversationController {
    private final LoadConversations loadConversations;

    /**
     * Constructs a ConversationController with the specified LoadConversations use case.
     *
     * @param loadConversations the use case responsible for loading conversations
     */
    public ConversationController(LoadConversations loadConversations) {
        this.loadConversations = loadConversations;
    }

    /**
     * Loads all conversations for the specified user and returns them as an array of Strings.
     *
     * @param username the username for whom the conversations are to be loaded
     * @return an array of conversation names associated with the user
     */
    public String[] loadUserConversations(String username) {
        List<String> nameList = loadConversations.loadAllConversations(username);
        return nameList.toArray(new String[0]);
    }
}
