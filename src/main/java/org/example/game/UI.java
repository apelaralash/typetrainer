package org.example.game;

import java.awt.Graphics;

import org.example.game.gameState.Entity;
import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

public class UI implements Entity {
    private String time, wpm, accuracy;
    private Boolean restart;

    public UI() {
        time = wpm = accuracy = "";
        restart = false;
    }

    public void setTime(String new_time) {
        time = new_time;
    }
    public void setWpm(String new_wpm) {
        wpm = new_wpm;
    }
    public void setAccuracy(String new_accuracy) {
        accuracy = new_accuracy;
    }

    public void onInput(Character typed_char) {
        if (typed_char == null)
            return;

        if (restart)
            restart = false;

        if (Character.isSpaceChar(typed_char))
            restart = true;
    }

    public Boolean needRestart() {
        return restart;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setFont(CommonSettings.font);
        graphics.setColor(Palette.typedText);

        int offsetX = CommonSettings.windowSize.width / 3;

        graphics.drawString(
            "time: " + time + "s",
            offsetX,
            CommonSettings.fontSize * 2
        );
        graphics.drawString(
            "wpm: " + wpm,
            offsetX,
            CommonSettings.fontSize * 3
        );
        graphics.drawString(
            "accuracy: " + accuracy + "%",
            offsetX,
            CommonSettings.fontSize * 4
        );
    }

    @Override public void update() {}
}
