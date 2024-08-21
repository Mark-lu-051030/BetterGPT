package view;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The ChatWindow class represents the main user interface for the chat application.
 * It includes a list of conversations, a chat display area, and input controls for the user.
 */
public class ChatWindow extends JFrame {
    private JList<String> conversationList;
    private JTextArea chatDisplayArea;
    private JTextField inputField;
    private JButton createNewConversationButton;
    private JButton profileButton;
    private JButton settingsButton;
    private JButton sendButton;

    /**
     * Constructs a ChatWindow and initializes the user interface components.
     */
    public ChatWindow() {
        setTitle("BetterGPT Chat");
        setSize(1429, 928);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left Panel - Conversation History
        JPanel leftPanel = createLeftPanel();

        // Right Panel - Chat Display Area with Input
        JPanel rightPanel = createRightPanel();

        // Add Panels to Frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Make the Frame Visible
        setVisible(true);
    }

    /**
     * Creates and configures the left panel containing the conversation list and control buttons.
     *
     * @return the configured JPanel representing the left panel
     */
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0, 80, 255)); // Blue background
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create New Conversation Button
        createNewConversationButton = new JButton("New Conversation");
        createNewConversationButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 10, 10, 10);
        leftPanel.add(createNewConversationButton, gbc);

        // Scrollable Conversation List
        conversationList = new JList<>();
        conversationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(conversationList);
        gbc.gridy = 1;
        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        leftPanel.add(scrollPane, gbc);

        // Profile and Settings Buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1, 10, 10));
        bottomPanel.setBackground(new Color(33, 99, 243));
        profileButton = new JButton("Profile");
        settingsButton = new JButton("Settings");
        bottomPanel.add(profileButton);
        bottomPanel.add(settingsButton);
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(bottomPanel, gbc);

        return leftPanel;
    }

    /**
     * Creates and configures the right panel containing the chat display area and input controls.
     *
     * @return the configured JPanel representing the right panel
     */
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        // Chat Display Area
        chatDisplayArea = new JTextArea();
        chatDisplayArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatDisplayArea);
        rightPanel.add(chatScrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);

        sendButton = new JButton("↑");
        sendButton.setPreferredSize(new Dimension(50, 50)); // Adjust the size as needed
        inputPanel.add(sendButton, BorderLayout.EAST);

        rightPanel.add(inputPanel, BorderLayout.SOUTH);

        return rightPanel;
    }

    /**
     * Sets the conversation list data in the JList component.
     *
     * @param conversations an array of conversation names to be displayed in the list
     */
    public void setConversationListData(String[] conversations) {
        conversationList.setListData(conversations);
    }

    /**
     * Adds a listener for selection events in the conversation list.
     *
     * @param listener the ListSelectionListener to be added
     */
    public void addConversationSelectionListener(ListSelectionListener listener) {
        conversationList.addListSelectionListener(listener);
    }

    /**
     * Adds a listener for actions on the input field and send button.
     *
     * @param listener the ActionListener to be added
     */
    public void addInputFieldListener(ActionListener listener) {
        inputField.addActionListener(listener);
        sendButton.addActionListener(listener);
    }

    /**
     * Adds a listener for the "New Conversation" button.
     *
     * @param listener the ActionListener to be added
     */
    public void addCreateNewConversationListener(ActionListener listener) {
        createNewConversationButton.addActionListener(listener);
    }

    /**
     * Adds a listener for the "Profile" button.
     *
     * @param listener the ActionListener to be added
     */
    public void addProfileButtonListener(ActionListener listener) {
        profileButton.addActionListener(listener);
    }

    /**
     * Adds a listener for the "Settings" button.
     *
     * @param listener the ActionListener to be added
     */
    public void addSettingsButtonListener(ActionListener listener) {
        settingsButton.addActionListener(listener);
    }

    /**
     * Appends a chat message to the chat display area.
     *
     * @param message the message to append
     */
    public void appendChatMessage(String message) {
        chatDisplayArea.setFont(new Font("Arial", Font.PLAIN, 24));
        chatDisplayArea.append(message + "\n\n");
        chatDisplayArea.setCaretPosition(chatDisplayArea.getDocument().getLength());
    }

    /**
     * Clears the input field text.
     */
    public void clearInputField() {
        inputField.setText("");
    }

    /**
     * Clears all text in the chat display area.
     */
    public void clearChatDisplay() {
        chatDisplayArea.setText("");
    }

    /**
     * Gets the selected conversation from the conversation list.
     *
     * @return the name of the selected conversation, or null if none is selected
     */
    public String getSelectedConversation() {
        return conversationList.getSelectedValue();
    }

    /**
     * Gets the text currently entered in the input field.
     *
     * @return the text entered by the user
     */
    public String getInputText() {
        return inputField.getText();
    }
}
