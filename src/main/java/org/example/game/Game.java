package org.example.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Game {
    private GameState state;
    private Window window;
    private UserInput input;

    public Game() {
        // при изменении размера окна придется править расположение виджетов)))
        window = new Window("TypeTrainer", 1280, 1080);
        state = GameStateSerialization.load("save.bin");
        input = new UserInput();

        window.addKeyListener(input);
        window.addWindowListener(
            () -> GameStateSerialization.dump(state, "save.bin")
        );
        window.present();
    }

    public final void run() {
        final Dimension window_size = window.size();
        BufferedImage frame = new BufferedImage(
            window_size.width,
            window_size.height,
            BufferedImage.TYPE_4BYTE_ABGR
        );
        Graphics frames_graphics = frame.getGraphics();

        for (;;) {
            state.update(input.lastKey());
            state.draw(frames_graphics);
            window.graphics().drawImage(frame, 0, 0, null);
        }

    }
    
}
