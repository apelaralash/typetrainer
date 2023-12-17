package org.example.game;

import org.example.game.utils.CommonSettings;

import java.awt.Graphics;
// import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class GameState implements Serializable {
    private TextWidget text;

    private Long startTime, endTime;
    // private Double wpm;
    // private Byte accuracy; // in %

    public GameState(Graphics graphics) {
        startTime = null;
        endTime = null;
        
        // TO-DO: read text from file but from another place
        text = new TextWidget(
            loadText()
        );
        // text = new TextWidget(
        //     "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        // );
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
                // System.out.println(TimeUnit.MILLISECONDS.toSeconds((endTime - startTime)));
                // System.out.println(text.accuracy());
                // System.out.println(text.wpm(TimeUnit.MILLISECONDS.toSeconds((endTime - startTime))));
            }
        }
    }

    public final void draw(Graphics graphics) {
        text.draw(graphics);
    }

    public final boolean testCompleted() { return text.complete(); }

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

    public static GameState load(final String path_to_save_file, Graphics graphics) {
        File save_file = new File(path_to_save_file);

        if (!save_file.exists())
            return new GameState(graphics);

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
            return new GameState(graphics);

        return new_state;
    }

    private final String loadText() {
        File text_dir = new File(CommonSettings.pathToTexts);
        ArrayList<File> files = new ArrayList<>();
        Random random = new Random();
        ArrayList<String> result = new ArrayList<>();

        for (File file : text_dir.listFiles())
            if (file.isFile())
                files.add(file);

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
        //   } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
          }

          return result.get(0);
        // return " ";
    }
}
