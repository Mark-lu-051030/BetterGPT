package view.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.*;
import java.awt.*;

public class DefaultComboBox extends JComboBox<String> {
    private Font textFont = new Font("Arial", Font.BOLD, 12);
    private Dimension comboBoxSize = new Dimension(80,20);

    public DefaultComboBox(String[] items) {
        super(items);
        setUI(new CustomComboBoxUI());
        setRenderer(new CustomRenderer());
        setBorder(new LineBorder(Color.black,2));
        setPreferredSize(comboBoxSize);
        setMaximumSize(comboBoxSize);
        setMinimumSize(comboBoxSize);
        setFont(textFont);
    }

    private class CustomComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton arrowButton = new JButton("▼");
            arrowButton.setBorder(new MatteBorder(0, 1, 0, 0, Color.BLACK));
            arrowButton.setBackground(Color.LIGHT_GRAY);
            return arrowButton;
        }

        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            g.setColor(Color.WHITE);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup popup = (BasicComboPopup) super.createPopup();
            popup.getList().setSelectionBackground(Color.WHITE);
            popup.getList().setSelectionForeground(Color.BLACK);
            return popup;
        }
    }

    private class CustomRenderer extends BasicComboBoxRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                label.setBackground(Color.LIGHT_GRAY);
                label.setForeground(Color.BLACK);
            } else {
                label.setBackground(Color.WHITE);
                label.setForeground(Color.BLACK);
            }
            label.setFont(textFont);
            return label;
        }
    }
    public void setTextFont(Font font){
        this.textFont = font;
    }

    public void setComboBoxSize(Dimension comboBoxSize) {
        this.comboBoxSize = comboBoxSize;
    }
}
