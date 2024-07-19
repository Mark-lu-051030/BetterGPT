package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChatArea extends JPanel {
    private ChatPanel chatPanel;
    private GridBagConstraints constraints;
    ArrayList<JPanel> messages;
    JLabel pusher = new JLabel("");
    private Font messageFont;

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
