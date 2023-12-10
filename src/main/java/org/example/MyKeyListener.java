package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    public char lastSymbol;
    private boolean wasGot = false;

    public MyKeyListener() {
//        setPreferredSize(new Dimension(0, 0));
//        setVisible(false);
//        setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        lastSymbol = e.getKeyChar();
        System.out.println(lastSymbol);
        wasGot = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Character getLastSymbol() {
        if (wasGot) return null;
        wasGot = true;
        return lastSymbol;
    }
}
