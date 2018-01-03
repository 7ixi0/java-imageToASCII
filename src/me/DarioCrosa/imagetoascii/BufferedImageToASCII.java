package me.DarioCrosa.imagetoascii;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BufferedImageToASCII {

    private StringBuilder output = new StringBuilder();
    private String pixels = "%&#MHGw*+-. ";

    public BufferedImageToASCII(BufferedImage img, int width, int height, String pixels) {
        if (!(pixels.equals(""))) {
            this.pixels = pixels;
        }

        if (width != img.getWidth() || height != img.getHeight()) {
            img = resize(img, width, height);
        }

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int clr = img.getRGB(x, y);
                double brightness = getBrightness(clr);
                char pixel = brightnessToChar(brightness);
                output.append(pixel);
            }

            output.append('\n');
        }
    }

    public String getOutput() {
        return output.toString();
    }

    private Character brightnessToChar(double brightness) {
        int index = (int) (brightness / 255 * (pixels.length() - 1));
        return pixels.charAt(index);
    }

    private static double getBrightness(int color) {
        int red = (color & 0x00ff0000) >> 16;
        int green = (color & 0x0000ff00) >> 8;
        int blue = color & 0x000000ff;

        return  0.2126*red + 0.7152*green + 0.0722*blue;
    }

    private static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage newImg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return newImg;
    }

}
