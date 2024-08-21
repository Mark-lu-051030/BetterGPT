package data_access;

import entity.User;

public interface UserRepository {
    void addUser(User user);
    void updatePassword(String username, String newPassword);
    void updateUser(User user);
    void findByUsername(String username, UserCallback callback);
    void findByEmail(String email, UserCallback callback);
    void addConversationIdToUser(String username, String conversationId);

    interface UserCallback {
        void onCallback(User user);
    }
}
