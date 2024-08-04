package data_access;

import entity.User;

public interface UserRepository {
    void addUser(User user);
    void updatePassword(String username, String newPassword);
    void updateUser(User user);
    void findByUsername(String username, UserCallback callback);
    void findByEmail(String email, UserCallback callback);

    interface UserCallback {
        void onCallback(User user);
    }
}
