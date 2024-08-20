package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChatWindow extends JFrame {

    // Panels for the UI layout
    private JPanel conversationHistoryPanel;
    private JPanel chatAreaPanel;
    private JButton profileButton;
    private JButton settingsButton;
    private JButton newConversationButton;
    private JButton renameConversationButton;
    private JButton deleteConversationButton;
    private JList<String> conversationList;
    private DefaultListModel<String> listModel;

    public ChatWindow() {
        // Set up the main frame
        setTitle("BetterGPT Chat");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and set up the conversation history panel
        conversationHistoryPanel = new JPanel();
        conversationHistoryPanel.setLayout(new BorderLayout());
        conversationHistoryPanel.setBackground(new Color(240, 240, 240));
        conversationHistoryPanel.setPreferredSize(new Dimension(300, getHeight()));

        // Create and set up the chat area panel
        chatAreaPanel = new JPanel();
        chatAreaPanel.setLayout(new BorderLayout());
        chatAreaPanel.setBackground(Color.WHITE);

        // Create and set up the profile and settings buttons
        profileButton = new JButton("Profile");
        settingsButton = new JButton("Settings");

        // Add the profile and settings buttons to the conversation history panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.add(profileButton);
        bottomPanel.add(settingsButton);

        // Create and set up the conversation list
        listModel = new DefaultListModel<>();
        conversationList = new JList<>(listModel);
        conversationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane conversationScrollPane = new JScrollPane(conversationList);

        // Create and set up the buttons for managing conversations
        newConversationButton = new JButton("New Conversation");
        renameConversationButton = new JButton("Rename");
        deleteConversationButton = new JButton("Delete");

        // Add the buttons to a panel
        JPanel managePanel = new JPanel();
        managePanel.setLayout(new GridLayout(1, 3));
        managePanel.add(newConversationButton);
        managePanel.add(renameConversationButton);
        managePanel.add(deleteConversationButton);

        // Add components to the conversation history panel
        conversationHistoryPanel.add(managePanel, BorderLayout.NORTH);
        conversationHistoryPanel.add(conversationScrollPane, BorderLayout.CENTER);
        conversationHistoryPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the panels to the frame
        add(conversationHistoryPanel, BorderLayout.WEST);
        add(chatAreaPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    // Method to add conversation to the list
    public void addConversation(String conversationName) {
        listModel.addElement(conversationName);
    }

    // Method to remove the selected conversation
    public void removeSelectedConversation() {
        int selectedIndex = conversationList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        }
    }

    // Method to rename the selected conversation
    public void renameSelectedConversation(String newName) {
        int selectedIndex = conversationList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.set(selectedIndex, newName);
        }
    }

    // Listener methods for the buttons
    public void addNewConversationListener(ActionListener listener) {
        newConversationButton.addActionListener(listener);
    }

    public void addRenameConversationListener(ActionListener listener) {
        renameConversationButton.addActionListener(listener);
    }

    public void addDeleteConversationListener(ActionListener listener) {
        deleteConversationButton.addActionListener(listener);
    }

    public String getSelectedConversation() {
        return conversationList.getSelectedValue();
    }

    public int getSelectedConversationIndex() {
        return conversationList.getSelectedIndex();
    }

    public static void main(String[] args) {
        new ChatWindow();
    }
}
