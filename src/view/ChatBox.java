package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChatBox extends JTextArea {
    private String message;
    private Font textFont;

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

    private void adjustHeight() {
        SwingUtilities.invokeLater(() -> {
            int lines = calculateLineCount();
            int newHeight = lines * getFontMetrics(getFont()).getHeight();
            setPreferredSize(new Dimension(getWidth(), newHeight));
            revalidate();
        });
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
