package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;
    private JLabel forgotPasswordLabel;

    public LoginView() {
        setTitle("BetterGPT Login");
        setSize(1429, 928);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main Panel to Hold Everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Left Panel with Code Block
        JPanel leftPanel = createLeftPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 2;
        gbc.weighty = 1;
        mainPanel.add(leftPanel, gbc);

        // Right Panel with Login Form
        JPanel rightPanel = createRightPanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        mainPanel.add(rightPanel, gbc);

        // Add Main Panel to the Frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the Frame Visible
        setVisible(true);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new GridBagLayout());

        JLabel codeLabel = new JLabel("<html><pre>while (alive) {<br>    Eat();<br>    Chat-and-Learn();<br>    Code();<br>    Sleep();<br>}</pre></html>");
        codeLabel.setFont(new Font("Courier New", Font.PLAIN, 36));
        codeLabel.setForeground(new Color(51, 102, 255));
        leftPanel.add(codeLabel);

        return leftPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome Back...");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 60));
        welcomeLabel.setForeground(new Color(51, 102, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.3;
        gbc.insets = new Insets(0, 0, 20, 0);
        rightPanel.add(welcomeLabel, gbc);

        // Username Field
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(400, 60));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        rightPanel.add(usernameField, gbc);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(400, 60));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 0, 20, 0);
        rightPanel.add(passwordField, gbc);

        // Log In Button
        loginButton = new JButton("Log In");
        loginButton.setBackground(new Color(33, 99, 243));
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(400, 60));
        loginButton.setFocusPainted(false);
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        rightPanel.add(loginButton, gbc);

        // Forgot Password Label
        forgotPasswordLabel = new JLabel("<html><a href=''>Forgot password?</a></html>");
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 4;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 0, 20, 0);
        rightPanel.add(forgotPasswordLabel, gbc);

        // Sign Up Button
        signUpButton = new JButton("Sign up with email address");
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setForeground(new Color(33, 99, 243));
        signUpButton.setPreferredSize(new Dimension(400, 60));
        signUpButton.setFocusPainted(false);
        signUpButton.setBorder(BorderFactory.createLineBorder(new Color(33, 99, 243)));
        gbc.gridy = 5;
        gbc.weighty = 0.3;
        rightPanel.add(signUpButton, gbc);

        return rightPanel;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addSignUpListener(ActionListener listener) {
        signUpButton.addActionListener(listener);
    }

    public void addForgotPasswordListener(ActionListener listener) {
        forgotPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listener.actionPerformed(null);
            }
        });
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
