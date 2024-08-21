package data_access;

import entity.Conversation;
import entity.Message;

import java.util.List;

public interface ConversationRepository {
    void addConversation(Conversation conversation);
    void addMessage(String conversationId, Message message);
    void getConversationById(String conversationId, ConversationCallback callback);
    List<Conversation> getConversationsByUserId(String userId);

    interface ConversationCallback {
        void onCallback(Conversation conversation);
    }

    interface ConversationsCallback {
        void onCallback(List<Conversation> conversations);
    }

}
