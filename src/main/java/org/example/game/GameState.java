package org.example.game;

import java.awt.Graphics;
import java.io.Serializable;
import java.time.LocalTime;

public final class GameState implements Serializable {
    private TextVew text;

    private LocalTime timeStart;
    // private Double wpm;
    // private Byte accuracy; // in %
    

    public GameState() {
        timeStart = null;
        text = new TextVew();
    }
    
    public void onInput(Character symbol) {
        if (symbol == null)
            return;
        
        if (timeStart == null) {
            timeStart = LocalTime.now();
            System.out.println(timeStart);
        }

        text.onInput(symbol);

        // TO-DO: change wpm, accuracy, etc.
    }

    public void update() {
        //
    }

    public final void draw(Graphics graphics) {
        text.draw(graphics);
    }
}
