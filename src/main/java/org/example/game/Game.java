package org.example.game;

import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Game {
    private Window window;
    private GameState state;
    private UserInput input;

    public Game() {
        window = new Window(
            "TypeTrainer",
            CommonSettings.windowSize.width,
            CommonSettings.windowSize.height
        );
        state = GameState.load(CommonSettings.pathToSaveFile);
        input = new UserInput();

        window.addKeyListener(input);
        window.addWindowListener(
            // TO-DO: make it more simple if it using only one time
            () -> GameState.dump(state, CommonSettings.pathToSaveFile)
        );

        window.present();
    }

    public final void run() {
        // TO-DO: search anolog of it what work on MAC
        BufferedImage frame = new BufferedImage(
            CommonSettings.windowSize.width,
            CommonSettings.windowSize.height,
            BufferedImage.TYPE_INT_ARGB
        );
        Graphics frame_graphics = frame.getGraphics();

        while (true) {
            frame_graphics.setColor(Palette.background);
            frame_graphics.fillRect(
                0, 0,
                CommonSettings.windowSize.width, CommonSettings.windowSize.height
            );

            state.onInput(input.lastKey());
            state.update();
            state.draw(frame_graphics);

            window.graphics().drawImage(frame, 0, 0, null);
        }
    }
}
