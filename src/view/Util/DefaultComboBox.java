package view.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

/**
 * A custom JComboBox with specific styling for the UI, renderer, and dimensions.
 */
public class DefaultComboBox extends JComboBox<String> {
    private Font textFont = new Font("Arial", Font.BOLD, 12);
    private Dimension comboBoxSize = new Dimension(80, 20);

    /**
     * Constructs a DefaultComboBox with specified items.
     *
     * @param items The items to be displayed in the combo box.
     */
    public DefaultComboBox(String[] items) {
        super(items);
        setUI(new CustomComboBoxUI());
        setRenderer(new CustomRenderer());
        setBorder(new LineBorder(Color.black, 2));
        setPreferredSize(comboBoxSize);
        setMaximumSize(comboBoxSize);
        setMinimumSize(comboBoxSize);
        setFont(textFont);
    }

    /**
     * A custom ComboBoxUI class to define the appearance and behavior of the combo box.
     */
    private class CustomComboBoxUI extends BasicComboBoxUI {
        /**
         * Creates a custom arrow button for the combo box.
         *
         * @return A JButton representing the arrow button.
         */
        @Override
        protected JButton createArrowButton() {
            JButton arrowButton = new JButton("▼");
            arrowButton.setBorder(new MatteBorder(0, 1, 0, 0, Color.BLACK));
            arrowButton.setBackground(Color.LIGHT_GRAY);
            return arrowButton;
        }

        /**
         * Paints the background of the current value in the combo box.
         *
         * @param g        The Graphics object to protect.
         * @param bounds   The bounding rectangle of the current value.
         * @param hasFocus Whether the combo box has focus.
         */
        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            g.setColor(Color.WHITE);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        /**
         * Creates a custom popup for the combo box.
         *
         * @return A ComboPopup representing the custom popup.
         */
        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup popup = (BasicComboPopup) super.createPopup();
            popup.getList().setSelectionBackground(Color.WHITE);
            popup.getList().setSelectionForeground(Color.BLACK);
            return popup;
        }
    }

    /**
     * A custom renderer class to define the appearance of the combo box items.
     */
    private class CustomRenderer extends BasicComboBoxRenderer {
        /**
         * Returns a component that has been configured to display the specified value.
         *
         * @param list         The JList we're painting.
         * @param value        The value returned by list.getModel().getElementAt(index).
         * @param index        The cells index.
         * @param isSelected   True if the specified cell was selected.
         * @param cellHasFocus True if the specified cell has the focus.
         * @return A component whose paint() method will render the specified value.
         */
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

    /**
     * Sets the font for the combo box text.
     *
     * @param font The font to set for the combo box text.
     */
    public void setTextFont(Font font) {
        this.textFont = font;
    }

    /**
     * Sets the size of the combo box.
     *
     * @param comboBoxSize The dimension to set for the combo box.
     */
    public void setComboBoxSize(Dimension comboBoxSize) {
        this.comboBoxSize = comboBoxSize;
    }
}
