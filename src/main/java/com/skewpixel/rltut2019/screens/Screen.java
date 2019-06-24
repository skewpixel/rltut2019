package com.skewpixel.rltut2019.screens;

import com.skewpixel.rltut2019.renderer.Renderable;

import java.awt.event.KeyEvent;

public interface Screen extends Renderable {
    void onKeyPressed(KeyEvent e);
}
