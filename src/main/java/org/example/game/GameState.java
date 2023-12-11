package org.example.game;

import java.awt.Graphics;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.time.*;

public final class GameState implements Serializable {
    private TypingTest text;

    private Long startTime, endTime;
    // private Double wpm;
    // private Byte accuracy; // in %

    public GameState() {
        startTime = null;
        endTime = null;
        text = new TypingTest();
    }
    
    public void onInput(Character symbol) {
        if (symbol == null)
            return;
        
        if (startTime == null)
            startTime = System.currentTimeMillis(); // nanoTime

        text.onInput(symbol);

        // TO-DO: change wpm, accuracy, etc.
    }

    public void update() {
        if (text.compele()) {
            if (endTime == null) {
                endTime = System.currentTimeMillis();
                System.out.println(TimeUnit.MILLISECONDS.toSeconds((endTime - startTime)));
            }
        }
    }

    public final void draw(Graphics graphics) {
        text.draw(graphics);
    }
}
