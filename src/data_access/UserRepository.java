package data_access;

import entity.User;

public interface UserRepository {
    void addUser(User user);
    void findByUsername(String username, UserCallback callback);
    void findByEmail(String email, UserCallback callback);
    void updateUser(User user);

    interface UserCallback {
        void onCallback(User user);
    }
}
