package view.prechat;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The {@code ChatBox} class is a custom JTextArea component for the BetterGPT application.
 * It is used for user input, automatically adjusting its height based on the content.
 */
public class ChatBox extends JTextArea {
    private String message;
    private Font textFont;

    /**
     * Constructs a {@code ChatBox} with the specified initial message and font.
     *
     * @param message  the initial text message
     * @param textFont the font to be used for the text
     */
    public ChatBox(String message, Font textFont) {
        this.message = message;
        this.textFont = textFont;
        setText(message);
        setFont(textFont);
        setPreferredSize(new Dimension(500, 20));
        setLineWrap(true);
        setWrapStyleWord(true);
        adjustHeight();

        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                adjustHeight();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                adjustHeight();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                adjustHeight();
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustHeight();
            }
        });
    }

    /**
     * Adjusts the height of the ChatBox based on the number of lines of text.
     */
    private void adjustHeight() {
        SwingUtilities.invokeLater(() -> {
            int lines = calculateLineCount();
            int newHeight = lines * getFontMetrics(getFont()).getHeight();
            setPreferredSize(new Dimension(getWidth(), newHeight));
            revalidate();
        });
    }

    /**
     * Calculates the number of lines of text in the ChatBox.
     *
     * @return the number of lines of text
     */
    private int calculateLineCount() {
        FontMetrics fm = getFontMetrics(getFont());
        int fontHeight = fm.getHeight();
        int textWidth = getWidth();
        int textHeight = 0;

        String[] lines = getText().split("\n");
        for (String line : lines) {
            textHeight += (int) Math.ceil((double) fm.stringWidth(line) / textWidth) * fontHeight;
        }

        return textHeight / fontHeight;
    }

    /**
     * Returns the current message in the ChatBox.
     *
     * @return the current message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message in the ChatBox.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
        setText(message);
        adjustHeight();
    }
}
