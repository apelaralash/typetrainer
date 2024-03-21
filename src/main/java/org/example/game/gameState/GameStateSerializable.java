package org.example.game.gameState;

import org.example.game.GameState;

import java.awt.Graphics;
import java.io.*;

public class GameStateSerializable implements Entity {
    @Override public void update() {}
    @Override public void draw(Graphics graphics) {}
    
    public static void dump(GameState state, final String path_to_save_file) {
        // if (state.testCompleted())
        //     return;

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
        File save_file = new File(path_to_save_file);

        if (!save_file.exists())
            return new GameState();

        GameState new_state = null;
        try (ObjectInputStream input_stream =
                new ObjectInputStream(
                    new BufferedInputStream(
                        new FileInputStream(save_file)));
        ) {
            new_state = (GameState) input_stream.readObject();
            input_stream.close();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        if (new_state == null || new_state.testCompleted())
            return new GameState();

        return new_state;
    }

}
