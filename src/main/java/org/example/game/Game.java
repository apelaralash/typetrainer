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
        state = GameStateSerialization.load(CommonSettings.pathToSaveFile);
        input = new UserInput();

        window.addKeyListener(input);
        window.addWindowListener(
            () -> GameStateSerialization.dump(state, CommonSettings.pathToSaveFile)
        );

        window.present();
    }

    public final void run() {
        BufferedImage frame = new BufferedImage(
            CommonSettings.windowSize.width,
            CommonSettings.windowSize.height,
            BufferedImage.TYPE_4BYTE_ABGR
        );
        Graphics frames_graphics = frame.getGraphics();

        while (true) {
            frames_graphics.setColor(Palette.background);
            frames_graphics.fillRect(
                0, 0,
                CommonSettings.windowSize.width, CommonSettings.windowSize.height
            );

            state.onInput(input.lastKey());
            state.update();
            state.draw(frames_graphics);

            window.graphics().drawImage(frame, 0, 0, null);
        }
    }
}
