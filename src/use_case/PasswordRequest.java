package use_case;

import entity.User;


public class PasswordRequest {
    private User user;
    private PasswordResetService passwordResetService;

    public PasswordRequest(User user, PasswordResetService passwordResetService) {
        // Each PasswordRequest object has a corresponding user and PasswordResetService
        this.user = user;
        this.passwordResetService = passwordResetService;
    }
    // Note that we have just a basic constructor for a user for phase 1, so this code may change drastically

    private boolean resetPassword(String token, String newPassword) {
        if (passwordResetService.validateResetToken(token)) {
            user.setUserPassword(newPassword);
            return true;  // returns true of password has successfully been changed

        }
        return false;
    }

    // Getter methods
    public User getUser() {
        return user;
    }

    public PasswordResetService getPasswordResetService() {
        return passwordResetService;
    }

    // Setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setPasswordResetService(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }
}
