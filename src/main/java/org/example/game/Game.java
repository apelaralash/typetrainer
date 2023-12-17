package org.example.game;

import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Game {
    private GameState state;
    private UserInput input;
    private Window window;
    // private UI ui;

    public Game() {
        input = new UserInput();
        window = new Window("TypeTrainer", CommonSettings.windowSize, input);

        state = GameState.load(CommonSettings.pathToSaveFile);

        window.addWindowListener(
            () -> GameState.dump(state, CommonSettings.pathToSaveFile)
        );

        window.present();
    }

    public final void run() {
        BufferedImage frame = new BufferedImage(
            CommonSettings.windowSize.width,
            CommonSettings.windowSize.height,
            BufferedImage.TYPE_4BYTE_ABGR_PRE
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
