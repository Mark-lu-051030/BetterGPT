package view;

import data_access.DataHandler;
import entity.ChatGPTService;
import interface_adapter.ChatController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChatPanel extends JPanel {
    private ArrayList<JTextArea> currentMessages;
    private JPanel chatArea;
    private ChatBox userInput;
    private JButton sendButton;
    private JScrollPane scrollPane;
    private GridBagConstraints gbc;

    public ChatPanel() {
        setLayout(new BorderLayout()); // Use BorderLayout for main panel

        currentMessages = new ArrayList<>();
        chatArea = new JPanel();
        chatArea.setLayout(new GridBagLayout()); // Use GridBagLayout for chatArea
        chatArea.setBackground(Color.darkGray);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        scrollPane = new JScrollPane(chatArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Prevent horizontal scrolling

        userInput = new ChatBox("");
        Font font = new Font("Monospaced", Font.PLAIN, 20);

        sendButton = new JButton("Send");
        sendButton.setFont(font);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(userInput, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(userInput.getText());
                String input = userInput.getText();



                if (!input.isEmpty())
                {
                    addUserMessage("You: " + input);
                    String out = DataHandler.getGptMsg(ChatGPTService.service, DataHandler.sendUserMsg(input));

                    String response = out;
                    addGPTMessage("GPT: " + out);
                    userInput.setText("");



                }
            }
        });
    }

    private void addUserMessage(String message)
    {
        ChatBox newChat = new ChatBox(message); // Create a new ChatBox with the same text as userInput
        newChat.setEditable(false);
        newChat.setBackground(Color.LIGHT_GRAY);
        currentMessages.add(newChat);
        chatArea.add(newChat, gbc);
        // Only revalidate and repaint after all messages are added
        if (!scrollPane.getVerticalScrollBar().isVisible()) {
            revalidateAndRepaint();
        } else {
            SwingUtilities.invokeLater(() -> {
                chatArea.revalidate();
                chatArea.repaint();
                JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
                verticalBar.setValue(verticalBar.getMaximum());
            });
        }
    }

    private void addGPTMessage(String message) {
        ChatBox newChat = new ChatBox(message); // Create a new ChatBox with the GPT message
        newChat.setEditable(false);
        newChat.setBackground(Color.LIGHT_GRAY);
        currentMessages.add(newChat);
        chatArea.add(newChat, gbc);
        // Only revalidate and repaint after all messages are added
        if (!scrollPane.getVerticalScrollBar().isVisible()) {
            revalidateAndRepaint();
        } else {
            SwingUtilities.invokeLater(() -> {
                chatArea.revalidate();
                chatArea.repaint();
                JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
                verticalBar.setValue(verticalBar.getMaximum());
            });
        }
    }

    private void revalidateAndRepaint() {
        chatArea.revalidate();
        chatArea.repaint();
    }
}
