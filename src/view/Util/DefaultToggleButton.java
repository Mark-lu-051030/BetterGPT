package view.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A custom toggle button with customizable text and color for selected and unselected states.
 */
public class DefaultToggleButton extends JToggleButton {
    private String selectedText;
    private String unselectedText;
    private Color selectedColor;
    private Color unselectedColor;
    private Dimension size = new Dimension(80, 20);

    /**
     * Constructs a DefaultToggleButton with specified text, colors, and font.
     *
     * @param selectedText   The text to display when the button is selected.
     * @param unselectedText The text to display when the button is unselected.
     * @param selectedColor  The background color when the button is selected.
     * @param unselectedColor The background color when the button is unselected.
     * @param font           The font to use for the button text.
     */
    public DefaultToggleButton(String selectedText, String unselectedText, Color selectedColor, Color unselectedColor, Font font) {
        super(unselectedText);
        this.selectedText = selectedText;
        this.unselectedText = unselectedText;
        this.selectedColor = selectedColor;
        this.unselectedColor = unselectedColor;

        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);

        setBackground(unselectedColor);
        setFont(font);
        setBorder(new LineBorder(Color.black, 2));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        // Add an ActionListener to repaint when the state changes
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
    }

    /**
     * Constructs a DefaultToggleButton with specified text and default colors and font.
     *
     * @param selectedText   The text to display when the button is selected.
     * @param unselectedText The text to display when the button is unselected.
     */
    public DefaultToggleButton(String selectedText, String unselectedText) {
        this(selectedText, unselectedText, Color.GREEN, Color.LIGHT_GRAY, new Font("Arial", Font.BOLD, 12));
    }

    /**
     * Paints the component with the appropriate background color and text based on the button's state.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (isSelected()) {
            setBackground(selectedColor); // Change to desired color
            setText(selectedText);
        } else {
            setBackground(unselectedColor); // Change to another color if needed
            setText(unselectedText);
        }
        super.paintComponent(g);
    }
}
