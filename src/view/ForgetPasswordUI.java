package view;

//import inter_adapter.GetInputEmail;
//import inter_adapter.SendEmail2;
import interface_adapter.SendEmailController;
import interface_adapter.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgetPasswordUI {

    public static void main(String[] args) {
        // Create a new JFrame container
        JFrame frame = new JFrame("Forget Password UI");

        // Specify GridBagLayout for the layout manager
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Give the frame an initial size
        frame.setSize(800, 800);

        // Terminate the program when the user closes the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        // Create a label for the email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(new Color(0, 102, 204));
        emailLabel.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(emailLabel, gbc);

        // Create a text field for the user to enter their email
        JTextField emailText = new JTextField(40);
        emailText.setBackground(Color.WHITE);
        emailText.setForeground(new Color(0, 102, 204));
        emailText.setFont(new Font("Arial", Font.PLAIN, 28)); // Double the font size
        emailText.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(emailText, gbc);

        // Create a button for "Forget Password"
        JButton forgetPasswordButton = new JButton("Forget Password");
        forgetPasswordButton.setBackground(new Color(0, 102, 204));
        forgetPasswordButton.setForeground(Color.WHITE);
        forgetPasswordButton.setFont(new Font("Arial", Font.BOLD, 24));
        forgetPasswordButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        gbc.gridx = 1;
        gbc.gridy = 1;

        // Add an action listener for the button
        forgetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the email input
                String email = emailText.getText();
                if (email.isEmpty()) {
                    // Show an error message if the email field is empty
                    JOptionPane.showMessageDialog(frame, "Please enter your email.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Store the email address in Store
                    Store.setEmail(email);
                    // Show a dialog box when the button is clicked
                    JOptionPane.showMessageDialog(frame, "Password recovery instructions sent to " + email + ".");
                }
                String storedEmail = Store.getEmail();
                // Send email with verification code
                String recipient = storedEmail;
                String subject = "Verification Code";
                String body = "Here is your verification code";

                // Call the sendEmail method
                //SendEmail2.sendEmail(recipient, subject, body);
                SendEmailController sendEmailController = new SendEmailController(storedEmail, subject, body);

            }
        });

        // Add the button to the panel
        panel.add(forgetPasswordButton, gbc);

        // Create a button for "Show Stored Email"
        JButton showEmailButton = new JButton("Show Stored Email");
        showEmailButton.setBackground(new Color(0, 102, 204));
        showEmailButton.setForeground(Color.WHITE);
        showEmailButton.setFont(new Font("Arial", Font.BOLD, 24));
        showEmailButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        gbc.gridx = 1;
        gbc.gridy = 2;

        // Add an action listener for the button
        showEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the stored email from Store
                String storedEmail = Store.getEmail();
                // Show a dialog box with the stored email
                JOptionPane.showMessageDialog(frame, "Stored Email: " + storedEmail);


                /* marked as duplicate
                // Send email with verification code
                String recipient = storedEmail;
                String subject = "Verification Code";
                String body = "Here is your verification code";

                // Call the sendEmail method
                //SendEmail2.sendEmail(recipient, subject, body);
                SendEmailController sendEmailController = new SendEmailController(storedEmail, subject, body);

                 */
            }
        });

        // Add the button to the panel
        panel.add(showEmailButton, gbc);

        // Create a button for "Enter Verification Code"
        JButton enterCodeButton = new JButton("Enter Verification Code");
        enterCodeButton.setBackground(new Color(0, 102, 204));
        enterCodeButton.setForeground(Color.WHITE);
        enterCodeButton.setFont(new Font("Arial", Font.BOLD, 24));
        enterCodeButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        gbc.gridx = 1;
        gbc.gridy = 3;

        // Add an action listener for the button
        enterCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter the verification code
                String verificationCode = JOptionPane.showInputDialog(frame, "Enter your verification code:");
                // Store the verification code in Store
                Store.setVerificationCode(verificationCode);
            }
        });

        // Add the button to the panel
        panel.add(enterCodeButton, gbc);

        // Create a button for "Verify"
        JButton verifyButton = new JButton("Verify");
        verifyButton.setBackground(new Color(0, 102, 204));
        verifyButton.setForeground(Color.WHITE);
        verifyButton.setFont(new Font("Arial", Font.BOLD, 24));
        verifyButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        gbc.gridx = 1;
        gbc.gridy = 4;

        // Add an action listener for the button
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered verification code
                String enteredCode = Store.getVerificationCode();
                // Show a dialog box to prompt for the entered code
                String codeInput = JOptionPane.showInputDialog(frame, "Enter the verification code to verify:");
                if (enteredCode != null && enteredCode.equals(codeInput)) {
                    // Show a success message if the verification code matches
                    JOptionPane.showMessageDialog(frame, "Verification successful.");
                } else {
                    // Show an error message if the verification code does not match
                    JOptionPane.showMessageDialog(frame, "Verification failed. Incorrect code.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add the button to the panel
        panel.add(verifyButton, gbc);

        // Create a button for "Update Password"
        JButton updatePasswordButton = new JButton("Update Password");
        updatePasswordButton.setBackground(new Color(0, 102, 204));
        updatePasswordButton.setForeground(Color.WHITE);
        updatePasswordButton.setFont(new Font("Arial", Font.BOLD, 24));
        updatePasswordButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        gbc.gridx = 1;
        gbc.gridy = 5;

        // Add an action listener for the button
        updatePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter the new password
                String newPassword = JOptionPane.showInputDialog(frame, "Enter your new password:");
                // Store the new password in Store
                Store.setNewPassword(newPassword);
                // Show a success message
                JOptionPane.showMessageDialog(frame, "Password updated successfully.");
            }
        });

        // Add the button to the panel
        panel.add(updatePasswordButton, gbc);

        // Display the frame
        frame.setVisible(true);
    }
}