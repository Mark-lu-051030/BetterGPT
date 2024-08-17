package interface_adapter;

//will reduce dependency with UI, this is just to test

public class Store {

    // VerificationStore logic
    private static String verificationCode;

    public static void setVerificationCode(String verificationCode) {
        Store.verificationCode = verificationCode;
    }

    public static String getVerificationCode() {
        return verificationCode;
    }

    // EmailStore logic
    private static String email;

    public static void setEmail(String email) {
        Store.email = email;
    }

    public static String getEmail() {
        return email;
    }

    // PasswordStore logic
    private static String newPassword;

    public static void setNewPassword(String newPassword) {
        Store.newPassword = newPassword;
    }

    public static String getNewPassword() {
        return newPassword;
    }
}
