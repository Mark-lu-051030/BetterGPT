package src.view;  // Ensure this is at the top of your Sidebar.java file

import javax.swing.*;
import java.awt.*;

public class Sidebar {
    private JPanel sidebar;

    public Sidebar() {
        sidebar = new JPanel(new GridBagLayout());
        sidebar.setBackground(GraphicUtils.HexToColor("#171717"));
        initializePanel();
    }

    private void initializePanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;  // Use NONE to control size directly
        constraints.anchor = GridBagConstraints.NORTH;  // Anchor components to the top
        constraints.insets = new Insets(5, 5, 5, 5);  // Margin around components

        // "Close sidebar" button
        JButton innerButton1 = new JButton("Close sidebar");
        constraints.gridx = 0;
        constraints.gridy = 0;
        innerButton1.setPreferredSize(new Dimension(30, 30));  // Ensure the button is square
        sidebar.add(innerButton1, constraints);

        // Blank part in the middle
        JLabel innerLabel1 = new JLabel("");
        constraints.gridx = 1;
        constraints.gridy = 0;
        innerLabel1.setPreferredSize(new Dimension(100, 30));
        sidebar.add(innerLabel1, constraints);

        // "Close sidebar" button
        JButton innerButton2 = new JButton("New Chat");
        constraints.gridx = 2;
        constraints.gridy = 0;
        innerButton2.setPreferredSize(new Dimension(30, 30));  // Ensure the button is square
        sidebar.add(innerButton2, constraints);

        //ChatGPT New Chat Button
        GPTNewChatButton gptNewChatButton = new GPTNewChatButton();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        sidebar.add(gptNewChatButton.getGPTNewChatButton(), constraints);

        //Set above to top
        JLabel bottom = new JLabel("");
        constraints.gridx = 0;
        constraints.gridy = 999;
        constraints.weightx = 1;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        sidebar.add(innerLabel1, constraints);


    }
    public JPanel getSidebar() {
        return sidebar;
    }
}

