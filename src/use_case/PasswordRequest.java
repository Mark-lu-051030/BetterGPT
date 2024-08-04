package use_case;

import data_access.PasswordResetService;
import entity.User;

/**
 * Service class for handling password reset requests.
 */
public class PasswordRequest {
    private User user;
    private PasswordResetService passwordResetService;

    /**
     * Constructs a PasswordRequest with a specified user and PasswordResetService.
     *
     * @param user                 The user associated with the password reset request.
     * @param passwordResetService The service used to handle password reset operations.
     */
    public PasswordRequest(User user, PasswordResetService passwordResetService) {
        // Each PasswordRequest object has a corresponding user and PasswordResetService
        this.user = user;
        this.passwordResetService = passwordResetService;
    }

    /**
     * Resets the user's password if the provided token is valid.
     *
     * @param token       The reset token to validate.
     * @param newPassword The new password to set for the user.
     * @return true if the password has been successfully changed, false otherwise.
     */
    private boolean resetPassword(String token, String newPassword) {
        if (passwordResetService.validateResetToken(token)) {
            user.setUserPassword(newPassword);
            return true;  // returns true if the password has successfully been changed
        }
        return false;
    }

    /**
     * Returns the user associated with this password reset request.
     *
     * @return The user associated with this request.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the PasswordResetService used for this password reset request.
     *
     * @return The PasswordResetService associated with this request.
     */
    public PasswordResetService getPasswordResetService() {
        return passwordResetService;
    }

    /**
     * Sets the user for this password reset request.
     *
     * @param user The user to be set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets the PasswordResetService for this password reset request.
     *
     * @param passwordResetService The PasswordResetService to be set.
     */
    public void setPasswordResetService(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }
}
