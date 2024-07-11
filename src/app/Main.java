package app;

import view.ViewManager;
import view.TempUI;

import javax.swing.*;

public class Main{
    /*public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewManager();  // Create and show the GUI managed by ViewMasnager.
            }
        });
    }*/
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TempUI window = new TempUI();
            window.setVisible(true);
        });
    }
}

