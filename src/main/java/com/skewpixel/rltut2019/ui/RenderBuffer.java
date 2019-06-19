package com.skewpixel.rltut2019.ui;

public class RenderBuffer {
    private final int width;
    private final int height;
    private final int[] pixels;

    public RenderBuffer(int width, int height) {
        this.width = width;
        this.height = height;

        this.pixels = new int[width * height];

        for(int i = 0; i < width*height; i++) {
            pixels[i] = 0;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void draw(RenderBuffer src, int xOfs, int yOfs) {
        draw(src, xOfs, yOfs, 0, 0xFFFFFFFF);
    }

    public void draw(RenderBuffer src, int xOfs, int yOfs, int transparentCol, int col) {
        draw(src, 0, 0, src.getWidth(), src.getHeight(), xOfs, yOfs, transparentCol, col, -1);
    }

    public void draw(RenderBuffer src, int startX, int startY, int drawWidth, int drawHeight, int xOffset,
                     int yOffset, int transparentCol, int color, int bgColor) {

        for(int y = 0; y< drawHeight; y++) {
            int yp = y + yOffset;
            if(yp < 0 || yp >= height)
                continue;

            for(int x = 0; x < drawWidth; x++) {
                int xp = x + xOffset;
                if(xp < 0 || xp >= width)
                    continue;

                int srcPxl = src.pixels[(x + startX) + (y + startY) * src.getWidth()];
                int dest = pixels[xp + yp * width];

                // remove the alpha channel and check it against the transparent colour
                if((srcPxl & 0x00FFFFFF) != transparentCol)
                {
                    pixels[xp + yp * width] = alpha_blend(srcPxl & color, dest);
                }
                else if(bgColor != -1) {
                    pixels[xp + yp * width] = alpha_blend(bgColor, dest);
                }
            }
        }
    }


    public static int alpha_blend(int c1, int c2) {
        int a1 = (c1 & 0xff000000) >>> 24;
        int a2 = (c2 & 0xff000000) >>> 24; // Do not need for traditional alpha blending

        int r1 = (c1 & 0x00ff0000) >> 16;
        int r2 = (c2 & 0x00ff0000) >> 16;

        int g1 = (c1 & 0x0000ff00) >> 8;
        int g2 = (c2 & 0x0000ff00) >> 8;

        int b1 = (c1 & 0x000000ff);
        int b2 = (c2 & 0x000000ff);

        float src_alpha = ((float)a1) / 255.0f;

        int red   = (int)((r1 * src_alpha) + (r2 * (1.0f - src_alpha)));
        int green = (int)((g1 * src_alpha) + (g2 * (1.0f - src_alpha)));
        int blue  = (int)((b1 * src_alpha) + (b2 * (1.0f - src_alpha)));

        return (a2 << 24) | (red << 16) | (green << 8) | blue;
    }
}
