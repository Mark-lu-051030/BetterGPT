package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The {@code ChatArea} class is a custom JPanel used to display chat messages in the BetterGPT application.
 * It manages the layout and addition of user and GPT messages.
 */
public class ChatArea extends JPanel {
    private ChatPanel chatPanel;
    private GridBagConstraints constraints;
    private ArrayList<JPanel> messages;
    private JLabel pusher = new JLabel("");
    private Font messageFont;

    /**
     * Constructs a {@code ChatArea} with the specified parent ChatPanel, font, and background color.
     *
     * @param chatPanel the parent {@code ChatPanel} containing this ChatArea
     * @param messageFont the font used for the messages
     * @param backgroundColor the background color of the chat area
     */
    public ChatArea(ChatPanel chatPanel, Font messageFont, Color backgroundColor) {
        this.chatPanel = chatPanel;
        this.messages = new ArrayList<>();
        this.messageFont = messageFont;

        setLayout(new GridBagLayout());
        setBackground(backgroundColor);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(pusher, constraints);
    }

    /**
     * Adds a message to the chat area.
     *
     * @param isUser  {@code true} if the message is from the user, {@code false} if it is from GPT
     * @param message the message text to be added
     */
    public void addMessage(boolean isUser, String message) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        JTextArea messageText = new ChatBox(message, messageFont);
        if (isUser) {
            rowPanel.add(Box.createRigidArea(new Dimension(50, 0)));
            rowPanel.add(messageText);
        } else {
            rowPanel.add(messageText);
            rowPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        }
        rowPanel.setBackground(Color.DARK_GRAY);
        remove(pusher);
        constraints.gridy++;
        add(rowPanel, constraints);
        messages.add(rowPanel);

        constraints.gridy++;
        constraints.weighty = 1;
        add(pusher, constraints);
        constraints.weighty = 0;

        revalidate();
        repaint();
    }
}
