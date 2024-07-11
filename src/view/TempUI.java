package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempUI extends JFrame {
    private final JTextArea chatArea;
    private final JTextArea userInput;

    public TempUI() {
        setTitle("Chat with GPT");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        userInput = new JTextArea();
        userInput.setPreferredSize(new Dimension(500, 200));
        Font font = new Font("Monospaced", Font.PLAIN, 20);
        userInput.setFont(font);

        JButton sendButton = new JButton("Send");
        sendButton.setFont(font);

        chatArea.setFont(font);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(userInput, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        Container container = getContentPane();
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = userInput.getText();
                if (!input.isEmpty()) {
                    chatArea.append("You: " + input + "\n");
                    String response = "GPT response here";
                    chatArea.append("GPT: " + response + "\n");
                    userInput.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TempUI window = new TempUI();
            window.setVisible(true);
        });
    }
}