package com.skewpixel.rltut2019.ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Textures {

    public static RenderBuffer font = loadBitmap("/font.png");


    public static RenderBuffer loadBitmap(String path) {
        try {
            BufferedImage img = ImageIO.read(RenderBuffer.class.getResource(path));

            int width = img.getWidth();
            int height = img.getHeight();

            RenderBuffer bitmap = new RenderBuffer(width, height);
            img.getRGB(0, 0, width, height, bitmap.getPixels(), 0, width);

            return bitmap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
