package com.skewpixel.rltut2019.services;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputService implements KeyListener, FocusListener {

    public Map<Integer, String> keyMappings = new HashMap<>();
    public Map<String, Boolean> keyState = new HashMap<>();

    public void addKeyMapping(String mappingName, Integer keyCode) {
        keyMappings.put(keyCode, mappingName);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyMappings.containsKey(keyCode)) {
            keyState.put(keyMappings.get(keyCode), true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyMappings.containsKey(keyCode)) {
            keyState.put(keyMappings.get(keyCode), false);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        for(Map.Entry<String, Boolean> keyEntry : keyState.entrySet()) {
            keyEntry.setValue(false);
        }
    }

    public boolean isKeyPressed(String keyName) {
        if(keyState.containsKey(keyName)) {
            return keyState.get(keyName);
        }

        return false;
    }
}
