package com.skewpixel.rltut2019.ui;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RenderCanvas extends Canvas {

    private int width;
    private int height;
    private int scale;

    private BufferStrategy strategy;
    private BufferedImage img;
    private int[] pixels;

    public RenderCanvas(int width, int height) {
        this(width, height, 1);
    }

    public RenderCanvas(int width, int height, int scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;

        img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
    }

    public void render(RenderBuffer srcBuf) {
        if(strategy == null) {
            strategy = getBufferStrategy();

            if (strategy == null) {
                createBufferStrategy(3);
                strategy = getBufferStrategy();
            }
        }

        int[] srcPixels = srcBuf.getPixels();
        System.arraycopy(srcPixels, 0, pixels, 0, srcPixels.length);

        Graphics g = strategy.getDrawGraphics();
        g.drawImage(img, 0, 0, width * scale, height * scale, null);
        g.dispose();
        strategy.show();
    }
}
