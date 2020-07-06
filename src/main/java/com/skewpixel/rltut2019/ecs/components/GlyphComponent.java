package com.skewpixel.rltut2019.ecs.components;

import java.awt.*;

public class GlyphComponent implements Component {
    public static final String Name = "GlyphComponent";
    public char glyph;
    public Color foregroundColor;
    public Color backgroundColor;

    public GlyphComponent() {}

    public GlyphComponent(char glyph, Color foregroundColor) {
        this.glyph = glyph;
        this.foregroundColor = foregroundColor;
    }

    public GlyphComponent(char glyph, Color foregroundColor, Color backgroundColor) {
        this.glyph = glyph;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void reset() {
        glyph = ' ';
        foregroundColor = null;
        backgroundColor = null;
    }

    @Override
    public String getName() {
        return Name;
    }
}
