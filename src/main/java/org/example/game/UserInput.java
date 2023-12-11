package org.example.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class UserInput implements KeyListener {
    private Character input;
    private Boolean inptuWasGot;

    public UserInput() {
        input = ' ';
        inptuWasGot = true;
    }

    @Override
    public final void keyTyped(KeyEvent event) {
        Character temp = event.getKeyChar();
        if (!Character.isLetterOrDigit(temp) &&
            !Character.isSpaceChar(temp) && // temp != ' ' &&
            temp != ',' &&
            temp != '.' &&
            temp != '!' &&
            temp != '?'
        )
            return;

        input = temp;
        inptuWasGot = false;
    }

    public final Character lastKey() {
        if (inptuWasGot)
            return null;
        
        inptuWasGot = true;
        return input;
    }

    @Override final public void keyPressed(KeyEvent event) {}
    @Override final public void keyReleased(KeyEvent event) {}
}
