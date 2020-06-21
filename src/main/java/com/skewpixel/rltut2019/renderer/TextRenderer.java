package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.ui.Terminal;

import java.awt.*;

public class TextRenderer implements Renderer {

    public String getText() {
        return text;
    }

    public interface TextProvider {
        String getText();
    }

    public static final String NAME = "renderer.text_renderer";

    private final int x;
    private final int y;
    private TextProvider textProvider;
    private String text;

    public TextRenderer(int x, int y, String text) {
        this(x, y, (TextProvider)null);
        this.text = text;
        this.textProvider = this::getText;
    }

    public TextRenderer(int x, int y, TextProvider textProvider) {
        this.x = x;
        this.y = y;
        this.text = null;
        this.textProvider = textProvider;
    }

    @Override
    public void render(Terminal terminal) {
        terminal.write(textProvider.getText(), x, y, Color.white);
    }
}
