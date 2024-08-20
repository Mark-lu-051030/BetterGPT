package view.prechat;

import view.Util.GraphicUtils;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code Sidebar} class is responsible for creating and managing the sidebar
 * component of the BetterGPT application.
 */
public class Sidebar {
    private JPanel sidebar;

    /**
     * Constructs a {@code Sidebar} and initializes the sidebar panel and its components.
     */
    public Sidebar() {
        sidebar = new JPanel(new GridBagLayout());
        sidebar.setBackground(GraphicUtils.HexToColor("#171717"));
        initializePanel();
    }

    /**
     * Initializes the sidebar panel and adds the necessary components such as buttons and labels.
     */
    private void initializePanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;  // Use NONE to control size directly
        constraints.anchor = GridBagConstraints.NORTH;  // Anchor components to the top
        constraints.insets = new Insets(5, 5, 5, 5);  // Margin around components

        // "Close sidebar" button
        JButton innerButton1 = new JButton("Close sidebar");
        constraints.gridx = 0;
        constraints.gridy = 0;
        innerButton1.setPreferredSize(new Dimension(30, 30));  // Adjusted the button size for better visibility
        sidebar.add(innerButton1, constraints);

        // Blank part in the middle
        JLabel innerLabel1 = new JLabel("");
        constraints.gridx = 1;
        constraints.gridy = 0;
        innerLabel1.setPreferredSize(new Dimension(100, 30));
        sidebar.add(innerLabel1, constraints);  // Fixed reference to use a new label

        // "New Chat" button
        JButton innerButton2 = new JButton("New Chat");
        constraints.gridx = 2;
        constraints.gridy = 0;
        innerButton2.setPreferredSize(new Dimension(30, 30));  // Adjusted the button size
        sidebar.add(innerButton2, constraints);

        // ChatGPT New Chat Button
        GPTNewChatButton gptNewChatButton = new GPTNewChatButton();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;  // Span across all columns
        sidebar.add(gptNewChatButton.getGPTNewChatButton(), constraints);

        // Filler label at the bottom to push everything up
        JLabel bottomFiller = new JLabel("");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weighty = 1;  // Assign a weight to push other components up
        constraints.gridheight = GridBagConstraints.REMAINDER;  // Span to the end
        sidebar.add(bottomFiller, constraints);
    }

    /**
     * Returns the sidebar panel.
     *
     * @return the sidebar panel
     */
    public JPanel getSidebar() {
        return sidebar;
    }
}
