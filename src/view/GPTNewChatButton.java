package view;

import javax.swing.*;
import java.awt.*;

public class GPTNewChatButton {
    private JPanel gptNewChatButton;

    public GPTNewChatButton() {
        gptNewChatButton = new JPanel(new GridBagLayout());
        gptNewChatButton.setBackground(GraphicUtils.HexToColor("#171717"));
        initializePanel();
    }

    private void initializePanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;  // Use NONE to control size directly
        constraints.insets = new Insets(5, 5, 5, 5);  // Margin around components

        //GPT Logo
        JLabel newChatLabel = new JLabel(new ImageIcon(GraphicUtils.loadImage("Image/LogoWhite.png", 30, 30, true)));
        constraints.gridx = 0;
        constraints.gridy = 0;
        gptNewChatButton.add(newChatLabel, constraints);

        JLabel gptText = new JLabel("ChatGPT");
        Font font = new Font("Arial", Font.PLAIN, 15);
        gptText.setForeground(Color.white);
        gptText.setFont(font);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        gptNewChatButton.add(gptText, constraints);
    }



        public JPanel getGPTNewChatButton() {
            return gptNewChatButton;
        }
}
