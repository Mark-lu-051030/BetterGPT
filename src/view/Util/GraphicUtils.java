package view.Util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphicUtils {
    /**
     * Loads an image from the specified path, optionally resizes it to fit within the given dimensions while maintaining its aspect ratio, and returns it as an {@code Image}.
     * This method uses {@code Image.SCALE_SMOOTH} to ensure the quality of the image is maintained during resizing.
     *
     * @param path the file path of the image to be loaded.
     * @param width the maximum width of the image (used if maintainAspectRatio is true).
     * @param height the maximum height of the image (used if maintainAspectRatio is true).
     * @param maintainAspectRatio if true, the image will be resized to maintain its original aspect ratio, otherwise it will be resized exactly to the given dimensions.
     * @return an {@code Image} that fits within the specified dimensions.
     */
    public static Image loadImage(String path, int width, int height, boolean maintainAspectRatio) {
        try {
            BufferedImage loadedImage = ImageIO.read(new File(path));
            if (maintainAspectRatio) {
                double aspectRatio = (double) loadedImage.getWidth() / (double) loadedImage.getHeight();
                int newWidth = width;
                int newHeight = height;
                if (width / aspectRatio <= height) {
                    newHeight = (int) (width / aspectRatio);
                } else {
                    newWidth = (int) (height * aspectRatio);
                }
                return loadedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            } else {
                return loadedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads an image from the specified path, optionally resizes it to fit within the given dimensions while maintaining its aspect ratio, and returns it as an {@code Image}.
     * This method uses {@code Image.SCALE_SMOOTH} to ensure the quality of the image is maintained during resizing.
     *
     * @param path the file path of the image to be loaded.
     * @return an {@code Image} with its original scale.
     */
    public static Image loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a hexadecimal color string to a {@link Color} object.
     *
     * @param hex The hexadecimal string representing the color. This string
     *            should start with a '#' followed by six hexadecimal characters
     *            (e.g., "#FFFFFF"). If the '#' is omitted, the string should still
     *            consist of exactly six hexadecimal characters (e.g., "FFFFFF").
     *            The string should not include any alpha transparency information.
     * @return The {@link Color} object corresponding to the hexadecimal color string.
     * @throws NumberFormatException if the string cannot be parsed as a hexadecimal
     *                               integer, which may occur if the string contains
     *                               invalid hexadecimal characters or does not have
     *                               exactly six hexadecimal characters (excluding '#').
     * @throws StringIndexOutOfBoundsException if the string is too short and does not
     *                                         contain enough characters to represent
     *                                         the red, green, and blue components.
     */
    public static Color HexToColor(String hex) {
        hex = hex.replace("#", "");
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);
        return new Color(r, g, b);
    }
}
