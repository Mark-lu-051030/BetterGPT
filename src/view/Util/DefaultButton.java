package view.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A custom JButton with specified text, font, border width, text color, hover color, and border color.
 */
public class DefaultButton extends JButton {
    private boolean isHovered = false;
    private Color hoverColor;

    /**
     * Creates a custom button with specified properties.
     *
     * @param text        The text to be displayed on the button.
     * @param font        The font to be used for the button text.
     * @param borderWidth The width of the button border.
     * @param textColor   The color of the button text.
     * @param hoverColor  The color of the button when hovered over.
     * @param borderColor The color of the button border.
     */
    public DefaultButton(String text, Font font, int borderWidth, Color textColor, Color hoverColor, Color borderColor) {
        super(text);
        this.hoverColor = hoverColor;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(font); // Set custom font
        setForeground(textColor); // Set custom text color
        setBorder(new LineBorder(borderColor, borderWidth));

        // Add mouse listeners to change the hover state
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }

    /**
     * Paints the button component with the appropriate background color based on hover state.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (isHovered) {
            g.setColor(hoverColor); // Hover color
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    /**
     * Creates a default button with specified text.
     *
     * @param text The text to be displayed on the button.
     */
    public DefaultButton(String text) {
        this(text, new Font("Courier New", Font.PLAIN, 12), 3, Color.black, Color.LIGHT_GRAY, Color.black);
    }

    /**
     * Creates a default button with specified text and font.
     *
     * @param text The text to be displayed on the button.
     * @param font The font to be used for the button text.
     */
    public DefaultButton(String text, Font font) {
        this(text, font, 3, Color.black, Color.LIGHT_GRAY, Color.black);
    }

    /**
     * Creates a default button with specified text, font, and text color.
     *
     * @param text     The text to be displayed on the button.
     * @param font     The font to be used for the button text.
     * @param textColor The color of the button text.
     */
    public DefaultButton(String text, Font font, Color textColor) {
        this(text, font, 3, textColor, Color.LIGHT_GRAY, Color.black);
    }
}
