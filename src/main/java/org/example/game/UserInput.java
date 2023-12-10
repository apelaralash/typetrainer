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

        // Моё сочинение про этот if

        // защита от клавиш по типу Enter, Backspace
        // зачем если у нас метод keyTyped, который, если смотреть доку,
        // вызывается только для клавиш для которых может быть
        // сгенерирован символ? а я откуда знаю \shrug он просто
        // в юникоде странный символ присылает для всего что не символ
        // можешь убрать посмотреть что произойдет

        // проблема в том что это решение не идеально потому что
        // пробел является исключением, да и знаки препинания в принципе

        // есть идея завести множество допустимых символов
        // с которым сверять пришедший символ от event.getKeyChar()
        // но потом же оно все равно не понадобится нигде больше
        // поэтому было принято решение наговнокодить
        if (!Character.isLetter(temp) &&
            temp != ' ' &&
            temp != ',' &&
            temp != '.' &&
            temp != '!' &&
            temp != '?'
        )
            return;

        input = temp;
        inptuWasGot = false;
        System.out.println(input);
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
