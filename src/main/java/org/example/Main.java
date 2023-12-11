package org.example;

import org.example.game.Game;

// не знаю насколько коректно в java выделять отдельно главный метод
// просто мой сишный опыт
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
