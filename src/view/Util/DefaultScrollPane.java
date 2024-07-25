package view.Util;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * A custom JScrollPane with a customized scrollbar UI and specific corner settings.
 */
public class DefaultScrollPane extends JScrollPane {

    /**
     * Constructs a DefaultScrollPane with a specified view component.
     *
     * @param view The panel to be displayed in the scroll pane.
     */
    public DefaultScrollPane(JPanel view) {
        super(view);

        // Apply custom scroll bar UI
        getVerticalScrollBar().setUI(new CustomScrollBarUI());
        getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        // Remove border to prevent any white line
        setBorder(BorderFactory.createEmptyBorder());

        // Setting corners to null or empty components
        setCorner(JScrollPane.UPPER_LEFT_CORNER, null);
        setCorner(JScrollPane.UPPER_RIGHT_CORNER, null);
        setCorner(JScrollPane.LOWER_LEFT_CORNER, null);
        setCorner(JScrollPane.LOWER_RIGHT_CORNER, null);

        setWheelScrollingEnabled(true);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Revalidate and repaint to ensure proper rendering
        revalidate();
        repaint();
    }

    /**
     * A custom ScrollBar UI class to define the appearance and behavior of the scroll bars.
     */
    private static class CustomScrollBarUI extends BasicScrollBarUI {

        private static final Color SCROLL_BAR_COLOR = Color.LIGHT_GRAY;
        private static final int SCROLL_BAR_WIDTH = 10;

        /**
         * Creates a button with zero size to be used as the decrease button of the scrollbar.
         *
         * @param orientation The orientation of the button.
         * @return A JButton with zero size.
         */
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        /**
         * Creates a button with zero size to be used as the increase button of the scrollbar.
         *
         * @param orientation The orientation of the button.
         * @return A JButton with zero size.
         */
        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        /**
         * Creates a JButton with zero size.
         *
         * @return A JButton with zero size.
         */
        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }

        /**
         * Paints the thumb of the scrollbar with the specified color.
         *
         * @param g           The Graphics object to protect.
         * @param c           The component to paint.
         * @param thumbBounds The bounding rectangle of the thumb.
         */
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            g.setColor(SCROLL_BAR_COLOR);
            g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        }

        /**
         * Returns the preferred size of the scrollbar component.
         *
         * @param c The component whose preferred size is being queried.
         * @return The preferred size of the component.
         */
        @Override
        public Dimension getPreferredSize(JComponent c) {
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                return new Dimension(SCROLL_BAR_WIDTH, super.getPreferredSize(c).height);
            } else {
                return new Dimension(super.getPreferredSize(c).width, SCROLL_BAR_WIDTH);
            }
        }

        /**
         * Configures the scrollbar colors.
         */
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = SCROLL_BAR_COLOR;
        }
    }
}
