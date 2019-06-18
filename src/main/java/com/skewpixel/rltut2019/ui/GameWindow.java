package com.skewpixel.rltut2019.ui;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

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
}
