package com.skewpixel.rltut2019.services;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputService implements KeyListener, FocusListener {

    class Key{
        private int initialThreshold = 150;
        private int continuousThreshold = 50;
        private int pressedThreshold = 2;

        private boolean pressed; // has the key been pressed
        private boolean handled; // has the last press been handled
        private long lastUpdate; // the last time we updated the keypress state
        private int threshold; // the threshold to use for updating the keypress state.
        private int pressedCount; // how many times has the key been pressed

        public Key(boolean pressed, boolean handled) {
            this.pressed = pressed;
            this.handled = handled;
            this.threshold = initialThreshold;
            this.pressedCount = 0;
        }

        // this method is called when the game engine ticks
        public void pressed(long timestamp) {
            // only update the pressed state if the time since the last update has passed the current threshold
            // the idea is that we don't want to pick up too many key presses when the key is first pressed to control
            // the sensitivity. However we will lower the threshold the longer the key is held down to speed up
            // instances of the keypress
            if(timestamp - lastUpdate > threshold) {
                // if the key was pressed since the last tick
                if(pressed) {
                    // increment our pressed count
                    pressedCount++;

                    // if we have moved from the first phase of keypress (initial key down) to the second phase
                    // (key being held down) then we want to lower the threshold to speed up the instances of key
                    // presses
                    if(pressedCount > pressedThreshold) {
                        threshold = continuousThreshold;
                    }
                }

                // we were pressed and the time threshold has passed, so we reset the handled flag if it was set to
                // allow the next press to be handled
                pressed = true;
                handled = false;
                lastUpdate = timestamp;
            }
        }

        public boolean isPressed(boolean setHandled) {
            // if the keypress has not been handled, we will check to see if the key has been pressed, but if the key
            // has already been handled, we just return false
            if(!handled) {
                // if the press hasn't been handled but has been pressed, then set it as handled
                if(pressed) {
                    this.handled = setHandled;
                }

                return pressed;
            }
            return false;
        }

        public void clear() {
            // clean up all state and reset the thresholds
            pressed = false;
            handled = false;
            threshold = initialThreshold;
            pressedCount = 0;
        }
    }

    public Map<Integer, String> keyMappings = new HashMap<>();
    public Map<String, Boolean> keyState = new HashMap<>();
    private Map<String, Key> keys = new HashMap<>();

    // add a mapping to key combination
    public void addKeyMapping(String mappingName, Integer keyCode) {
        keyMappings.put(keyCode, mappingName);
        keyState.put(mappingName, false);
        keys.put(mappingName, new Key(false, false));
    }

    // when the game engine ticks the service, we iterate through our raw key states and update the keys mapped
    // by the key names that have been pressed
    public void tick(long time) {
        for(Map.Entry<String, Boolean> me : keyState.entrySet()) {
            if(me.getValue()) {
                keys.get(me.getKey()).pressed(time);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // update the key state for the key based on the mapping
        if(keyMappings.containsKey(keyCode)) {
            keyState.put(keyMappings.get(keyCode), true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // if the key is released, clear the state and clear the key
        if(keyMappings.containsKey(keyCode)) {
            String mapping = keyMappings.get(keyCode);
            keyState.put(mapping, false);
            keys.get(mapping).clear();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        // we lost focus so clear out all our key states
        for(String mapping : keyMappings.values()) {
            keyState.put(mapping, false);
            keys.get(mapping).clear();
        }
    }

    // if we have a key by the mapped key name, return if it is pressed or not, indicating if we need to set the
    // handled flag or not
    public boolean isKeyPressed(String keyName, boolean setHandled) {
        if(keys.containsKey(keyName)) {
            return keys.get(keyName).isPressed(setHandled);
        }

        return false;
    }

    // return the raw key state for the mapped key name
    public boolean getKeyState(String keyName) {
        if(keyState.containsKey(keyName)) {
            return keyState.get(keyName);
        }

        return false;
    }
}
