package interface_adapter;

import use_case.ActuallySendEmail;
import use_case.CreateToken;

import use_case.UpdateFirebaseToken;

public class SendEmailController {
    private String to;
    private String subject;
    private String body;
    private CreateToken tokenGenerator;
    private String token; // Variable to store the generated token

    // Constructor with 3 parameters
    public SendEmailController(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.tokenGenerator = new CreateToken(); // Initialize the token generator

        // Generate the token and store it in the instance variable
        this.token = tokenGenerator.generateResetToken(to);

        // Optionally append or include the generated token in the body
        this.body += "\nYour verification token is: " + token;

        // Call the sendEmail method
        ActuallySendEmail.sendEmail(this.to, this.subject, this.body);

        // tell the firebase a new user has been created

    }

    // Getter method for the token
    public String getToken() {
        return this.token;
    }
}

