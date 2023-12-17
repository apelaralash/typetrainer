package org.example;

import org.example.game.Game;

// TO-DO:
// - text from file
// - refactoring of TextWidget
// - UI and restart

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
