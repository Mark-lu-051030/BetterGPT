package view;

import entity.Conversation;
import interface_adapter.ChatController;
import view.Util.DefaultButton;
import view.Util.DefaultScrollPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The {@code ChatPanel} class is responsible for creating and managing the chat panel
 * component of the BetterGPT application. This includes the chat area, user input field,
 * and send button.
 */
public class ChatPanel extends JPanel {
    private ChatArea chatArea;
    private DefaultScrollPane chatScrollPane;
    private ChatBox userInput;
    private DefaultButton sendButton;
    private GridBagConstraints constraints;
    private Font textFont = new Font("Arial", Font.PLAIN, 20);
    private Color backgroundColor = Color.darkGray;
    private ChatController chatController;
    private Conversation conversation;

    /**
     * Constructs a {@code ChatPanel} and initializes its components.
     *
     * @param chatController the {@code ChatController} used to manage chat functionalities
     */
    public ChatPanel(ChatController chatController) {
        this.chatController = chatController;
        setLayout(new BorderLayout()); // Use BorderLayout for main panel

        chatArea = new ChatArea(this, textFont, backgroundColor);

        chatScrollPane = new DefaultScrollPane(chatArea);
        userInput = new ChatBox("", textFont);

        sendButton = new DefaultButton("↑");
        sendButton.setPreferredSize(new Dimension(30, 30));
        sendButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        sendButton.setFont(textFont);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(backgroundColor);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        inputPanel.add(userInput);
        inputPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        inputPanel.add(sendButton);
        inputPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        add(chatScrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        ActionListener actionListener = e -> {
            String input = userInput.getText();
            if (!input.isEmpty()) {
                chatArea.addMessage(true, "You: " + input);
                String response = chatController.getResponse(input);
                chatArea.addMessage(false, "GPT: " + response);
                userInput.setText("");
            }
        };

        userInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionListener.actionPerformed(null);
                }
            }
        });
        sendButton.addActionListener(actionListener);
    }
}
