package com.skewpixel.rltut2019.ui;

import java.awt.*;

public class TerminalFont {
    public static final TerminalFont DefaultFont = new TerminalFont("/font.png", 9, 16, Colors.Fuchsia);

    private final String fontTextureFile;
    private final int characterWidth;
    private final int characterHeight;
    private final Color transparentColor;

    public TerminalFont(String fontTextureFile, int characterWidth, int characterHeight, Color transparentColor) {
        this.fontTextureFile = fontTextureFile;
        this.characterWidth = characterWidth;
        this.characterHeight = characterHeight;
        this.transparentColor = transparentColor;
    }

    public String getFontTextureFile() {
        return fontTextureFile;
    }

    public int getCharacterWidth() {
        return characterWidth;
    }

    public int getCharacterHeight() {
        return characterHeight;
    }

    public Color getTransparentColor() {
        return transparentColor;
    }
}
