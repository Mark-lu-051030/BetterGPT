package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The SignUpView class represents the user interface for the sign-up screen of the application.
 * It provides fields for the user to enter their email, username, and password, as well as buttons
 * for signing up and logging in.
 */
public class SignUpView extends JFrame {
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JLabel logInLabel;

    /**
     * Constructs a SignUpView and initializes the user interface components.
     */
    public SignUpView() {
        setTitle("BetterGPT Sign Up");
        setSize(1429, 928);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main Panel to Hold Everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Left Panel with Blue Background and Image
        JPanel leftPanel = createLeftPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainPanel.add(leftPanel, gbc);

        // Right Panel with Sign Up Form
        JPanel rightPanel = createRightPanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 3;
        mainPanel.add(rightPanel, gbc);

        // Add Main Panel to the Frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the Frame Visible
        setVisible(true);
    }

    /**
     * Creates and configures the left panel containing a welcome message and an image.
     *
     * @return the configured JPanel representing the left panel
     */
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0, 80, 255));
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Welcome Text
        JLabel welcomeLabel = new JLabel("Welcome aboard my friend");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(welcomeLabel, gbc);

        JLabel subtitleLabel = new JLabel("just a couple of clicks and we start");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 2;
        leftPanel.add(subtitleLabel, gbc);

        // Astronaut Image
        JLabel astronautLabel = new JLabel(new ImageIcon("src/resources/imgs/SignUp/AST.png"));
        gbc.gridy = 3;
        gbc.weighty = 0.0;
        leftPanel.add(astronautLabel, gbc);

        return leftPanel;
    }

    /**
     * Creates and configures the right panel containing the sign-up form.
     *
     * @return the configured JPanel representing the right panel
     */
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Spacer
        gbc.gridy = 0;
        gbc.weighty = 0.25;
        rightPanel.add(Box.createVerticalGlue(), gbc);

        // Create Account Label
        JLabel createAccountLabel = new JLabel("Create Account");
        createAccountLabel.setFont(new Font("Arial", Font.BOLD, 60));
        createAccountLabel.setForeground(new Color(51, 102, 255));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 0, 20, 0);
        rightPanel.add(createAccountLabel, gbc);

        // Email Field
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(400, 60));
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        rightPanel.add(emailField, gbc);

        // Username Field
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(400, 60));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        rightPanel.add(usernameField, gbc);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(400, 60));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        gbc.gridy = 4;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 0, 20, 0);
        rightPanel.add(passwordField, gbc);

        // Sign Up Button
        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(33, 99, 243));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setPreferredSize(new Dimension(400, 60));
        signUpButton.setFocusPainted(false);
        gbc.gridy = 5;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 0, 10, 0);
        rightPanel.add(signUpButton, gbc);

        // Terms and Conditions Label
        JLabel termsLabel = new JLabel("By clicking Sign Up, you agree to our terms and conditions.");
        termsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        termsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 6;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 0, 30, 0);
        rightPanel.add(termsLabel, gbc);

        // Log In Label
        logInLabel = new JLabel("<html>Already have an account? <a href=''>Log In</a></html>");
        logInLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logInLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logInLabel.setForeground(new Color(33, 99, 243));
        gbc.gridy = 7;
        gbc.weighty = 0.2;
        gbc.insets = new Insets(0, 0, 20, 0);
        rightPanel.add(logInLabel, gbc);

        return rightPanel;
    }

    /**
     * Gets the email entered by the user.
     *
     * @return the entered email as a String
     */
    public String getEmail() {
        return emailField.getText();
    }

    /**
     * Gets the username entered by the user.
     *
     * @return the entered username as a String
     */
    public String getUsername() {
        return usernameField.getText();
    }

    /**
     * Gets the password entered by the user.
     *
     * @return the entered password as a String
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Adds an ActionListener to the sign-up button.
     *
     * @param listener the ActionListener to be added
     */
    public void addSignUpListener(ActionListener listener) {
        signUpButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the log-in label.
     *
     * @param listener the ActionListener to be added
     */
    public void addLogInListener(ActionListener listener) {
        logInLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listener.actionPerformed(null);
            }
        });
    }

    /**
     * Displays a message to the user using a dialog box.
     *
     * @param message the message to be displayed
     */
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
