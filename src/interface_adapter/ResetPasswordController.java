package interface_adapter;

import use_case.UpdateFirebaseToken;

public class ResetPasswordController {
    String inputEmail;
    String inputUsername;
    String newPassword;
    String inputToken;
    String realToken;
    public boolean matchingToken;

    UpdateFirebaseToken updateFirebaseToken;


    public ResetPasswordController(String inputEmail, String inputUsername, String inputToken, String realToken) {
        this.inputUsername = inputUsername;
        this.inputEmail = inputEmail;
        this.inputToken = inputToken;
        this.realToken = realToken;

        // Initialize the updateFirebaseToken instance variable
        this.updateFirebaseToken = new UpdateFirebaseToken(inputEmail, "fakeToken");

        if (inputToken.equals(realToken)) {
            matchingToken = true;
            System.out.println("You have input the correct verification code, and your password can be reset: " + matchingToken);
        } else {
            matchingToken = false;
            System.out.println("Incorrect verification code: " + matchingToken);
        }
    }

    public void resetPassword(String newPassword) {
        this.newPassword = newPassword;
        updateFirebaseToken.changePassword(inputUsername, newPassword);

        }

    }


