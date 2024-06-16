package src.app;

import javax.swing.*;
import java.awt.*;
import UI.ViewManager;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewManager();  // Create and show the GUI managed by ViewMasnager.
            }
        });
    }
}
