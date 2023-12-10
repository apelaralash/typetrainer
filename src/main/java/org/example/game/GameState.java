package org.example.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.io.Serializable;

public final class GameState implements Serializable {
    private final String text;
    private StringBuilder userTyped;
    private StringBuilder errors; // mb better userErrors?

    
    public GameState() {
        StringBuilder temp_text = new StringBuilder("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");

        if (temp_text.length() > 40)
            for (int index=0; index <= temp_text.length(); ++index)
                if (index % 40 == 0)
                    temp_text.insert(index, '\n');

        text = temp_text.toString();

        userTyped = new StringBuilder();
        errors = new StringBuilder();
    }

    public void update(Character new_input) {
        if (new_input == null)
            return;

        userTyped.append(new_input);
    }

    public final void draw(Graphics graphics) {
        graphics.setFont(
            new Font("Arial", Font.BOLD, 48)
        );
        graphics.setColor(Color.BLACK);
        graphics.drawString(text, 10, 100);

        graphics.setColor(Color.cyan);
        graphics.drawString(userTyped.toString(), 10, 100);

        graphics.setColor(Color.red);
        graphics.drawString(errors.toString(), 10, 100);
    }
}
