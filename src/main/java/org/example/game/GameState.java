package org.example.game;

import java.awt.Graphics;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.time.*;

// Serialization )))
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class GameState implements Serializable {
    private TypingTest text;

    private Long startTime, endTime;
    // private Double wpm;
    // private Byte accuracy; // in %

    public GameState() {
        startTime = null;
        endTime = null;
        text = new TypingTest();
    }
    
    public void onInput(Character symbol) {
        if (symbol == null)
            return;
        
        // if (startTime == null)
        //     startTime = System.currentTimeMillis(); // nanoTime

        // TO-DO: change wpm, accuracy, etc.
        
        text.onInput(symbol);
    }

    public void update() {
        // if (text.compele()) {
        //     if (endTime == null) {
        //         endTime = System.currentTimeMillis();
        //         System.out.println(TimeUnit.MILLISECONDS.toSeconds((endTime - startTime)));
        //     }
        // }
    }

    public final void draw(Graphics graphics) {
        text.draw(graphics);
    }

    public static void dump(GameState state, final String path_to_save_file) {
        try (ObjectOutputStream output_stream =
            new ObjectOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(
                        new File(path_to_save_file), false)));
        ) {
            output_stream.writeObject(state);
            output_stream.flush();
            output_stream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static GameState load(final String path_to_save_file) {
        return new GameState();

        // TODO: delete first return and add logic to load from completed game
        // File save_file = new File(path_to_save_file);

        // if (!save_file.exists())
        //     return new GameState();

        // GameState new_state = null;
        // try (ObjectInputStream input_stream =
        //         new ObjectInputStream(
        //             new BufferedInputStream(
        //                 new FileInputStream(save_file)));
        // ) {
        //     new_state = (GameState) input_stream.readObject();
        //     input_stream.close();
        // } catch (IOException | ClassNotFoundException exception) {
        //     exception.printStackTrace();
        // }

        // return new_state;
    }
}
