package org.example.game;

import org.example.game.gameState.GameStateSerializable;
import org.example.game.gameState.TypingTextDrawer;
import org.example.game.utils.CommonSettings;

import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.nio.file.Files;

public final class GameState extends GameStateSerializable {
    private TypingTextDrawer text;
    private Long startTime, endTime;
    // private Double wpm;
    // private Byte accuracy; // in %

    public GameState() {
        startTime = null;
        endTime = null;
        
        text = new TypingTextDrawer(
            loadText()
        );
    }
    
    public void onInput(Character symbol) {
        if (symbol == null)
            return;
        
        if (startTime == null)
            startTime = System.currentTimeMillis(); // nanoTime

        text.onInput(symbol);
    }

    public void update() {
        if (testCompleted()) {
            if (endTime == null) {
                endTime = System.currentTimeMillis();
            }
        }
    }

    public final void draw(Graphics graphics) {
        text.draw(graphics);
    }

    public final String totalTime() {
        return Long.toString(
            TimeUnit.MILLISECONDS.toSeconds((endTime - startTime))
        );
    }

    public final String totalWpm() {
        return text.wpm(TimeUnit.MILLISECONDS.toSeconds((endTime - startTime)));
    }

    public final String totalAccuracy() {
        return text.accuracy();
    }

    public final boolean testCompleted() { return text.complete(); }

    private final String loadText() {
        File text_dir = new File(CommonSettings.pathToTexts);
        ArrayList<File> files = new ArrayList<>();
        Random random = new Random();
        ArrayList<String> result = new ArrayList<>();

        for (File file : text_dir.listFiles())
            if (file.isFile())
                files.add(file);

        if (files.size() == 0)
            throw new RuntimeException("no texts to load!");

        File text = files.get(
            random.nextInt(files.size())
        );

        try (FileInputStream input = new FileInputStream(text);)
        {
            result = new ArrayList<String> (
                Files.readAllLines(text.toPath())
            );
            input.close();
          } catch (IOException exception) {
            exception.printStackTrace();
          }

          return result.get(0);
    }
}
