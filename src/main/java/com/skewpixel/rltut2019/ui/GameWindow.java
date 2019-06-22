package com.skewpixel.rltut2019.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {

    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    private boolean fullscreen = false;

    public GameWindow(String windowTitle, int width, int height) {
        super();
        this.setTitle(windowTitle);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension dimension = new Dimension(width, height);

        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setResizable(false);
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setFullscreen(boolean fullscreen) {
        if(fullscreen == this.fullscreen) {
            return;
        }

        setUndecorated(fullscreen);
        device.setFullScreenWindow(fullscreen ? this : null);

        this.fullscreen = fullscreen;
    }
}
