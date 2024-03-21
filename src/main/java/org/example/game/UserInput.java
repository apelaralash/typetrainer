package org.example.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class UserInput implements KeyListener {
    private Character input;
    private Boolean inputWasGot;

    public UserInput() {
        input = ' ';
        inputWasGot = true;
    }

    @Override
    public final void keyTyped(KeyEvent event) {
        Character temp = event.getKeyChar();

        if (!Character.isLetterOrDigit(temp) &&
            !Character.isSpaceChar(temp) &&
            temp != ',' &&
            temp != '.' &&
            temp != '!' &&
            temp != '?' &&
            temp != ';' &&
            temp != ':' &&
            temp != KeyEvent.VK_BACK_SPACE
        )
            return;

        input = temp;
        inputWasGot = false;
    }

    public final Character lastKey() {
        if (inputWasGot)
            return null;
        
        inputWasGot = true;
        return input;
    }

    @Override final public void keyPressed(KeyEvent event) {}
    @Override final public void keyReleased(KeyEvent event) {}
}
