package view;

import interface_adapter.ChatController;
import view.Util.GraphicUtils;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code ViewManager} class is responsible for setting up and managing the main JFrame
 * and its components for the BetterGPT application.
 */
public class ViewManager {
    private JFrame frame;

    /**
     * Constructs a {@code ViewManager} and initializes the main JFrame and its components.
     *
     * @param chatController the {@code ChatController} used to manage chat functionalities
     */
    public ViewManager(ChatController chatController) {
        frame = new JFrame("BetterGPT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(true);
        frame.setLayout(new GridBagLayout());
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setIconImage(GraphicUtils.loadImage("Image/LogoBlack.png"));

        frame.setLocationRelativeTo(null);  // Center the window

        // Set up a main content panel with the desired background color
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(GraphicUtils.HexToColor("#212121"));
        frame.setContentPane(contentPanel);  // Apply the content panel to the frame

        initializeComponents(chatController);
        frame.pack();
        frame.setVisible(true);  // Make the frame visible after all modifications
    }

    /**
     * Initializes and adds the main components (sidebar and chat panel) to the frame.
     *
     * @param chatController the {@code ChatController} used to manage chat functionalities
     */
    private void initializeComponents(ChatController chatController) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        Sidebar sideBar = new Sidebar();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 0;
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.gridheight = GridBagConstraints.REMAINDER; // Occupy the remainder of the grid's vertical space
        frame.getContentPane().add(sideBar.getSidebar(), constraints);  // Add to the content pane

        ChatPanel chatPanel = new ChatPanel(chatController);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0;  // This ensures the button doesn't expand vertically
        constraints.gridheight = 1;  // Set back to 1 so it does not affect vertical distribution
        frame.getContentPane().add(chatPanel, constraints);  // Add to the content pane
    }

    /**
     * Returns the main JFrame of the application.
     *
     * @return the main JFrame
     */
    public JFrame getFrame() {
        return frame;
    }
}
