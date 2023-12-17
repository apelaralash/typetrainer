package org.example.game.gameState;

import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.ArrayList;

public final class TypingTextDrawer implements Entity {
    private ArrayList<char[]> wrappedText;
    private CharacterState[][] characterStates;
    private int indexOfCurrentLine, indexOfCurrentChar;
    private int numberOfWords;

    public TypingTextDrawer(String text_to_type) {
        indexOfCurrentChar = 0;
        indexOfCurrentLine = 0;
        numberOfWords = 0;

        wrappedText = wrapText(text_to_type.split(" "));

        characterStates = new CharacterState[wrappedText.size()][];
        for (int index=0; index < characterStates.length; ++index) {
            characterStates[index] = new CharacterState[wrappedText.get(index).length];
            Arrays.fill(characterStates[index], CharacterState.NonTyped);
        }
    }
        
    public final void onInput(Character typed_char) {   
        if (wrappedText == null)
            return;
        
        if (indexOfCurrentLine >= wrappedText.size())
            return;

        // backspace
        if (typed_char == '\b') {
            if (indexOfCurrentChar > 0)
                indexOfCurrentChar -= 1;
            else if (indexOfCurrentChar == 0 && indexOfCurrentLine > 0) {
                indexOfCurrentLine -= 1;
                indexOfCurrentChar = wrappedText.get(indexOfCurrentLine).length-1;
            }
            
            characterStates[indexOfCurrentLine][indexOfCurrentChar] = CharacterState.NonTyped;
            
            return;
        }

        characterStates[indexOfCurrentLine][indexOfCurrentChar] =
            (wrappedText.get(indexOfCurrentLine)[indexOfCurrentChar] == typed_char) ?
                CharacterState.Typed : CharacterState.Error;

        indexOfCurrentChar += 1;

        if (wrappedText.get(indexOfCurrentLine).length == indexOfCurrentChar) {
            indexOfCurrentChar = 0;
            indexOfCurrentLine += 1;
        }
    }

    private final ArrayList<char[]> wrapText(String[] words) {
        ArrayList<char[]> result = new ArrayList<>();
        StringBuilder line = new StringBuilder();

        for (int index=0; index < words.length; ++index) {
            if (index + 1 == words.length) {
                line.append(words[index] + " ");
                result.add(line.toString().toCharArray());
                break;
            }

            if (line.length() + words[index].length() + 1 >= CommonSettings.lengthOfLine) {
                result.add(line.toString().toCharArray());
                line = new StringBuilder();
            }
            
            line.append(words[index] + " ");
        }

        return result;
    }
  
    @Override
    public final void draw(Graphics graphics) {
        graphics.setFont(CommonSettings.font);
        FontMetrics font_metrics = graphics.getFontMetrics();

        int paddingY = (CommonSettings.windowSize.height - (font_metrics.getHeight() * wrappedText.size()))/2;
        int offsetX = 0;

        for (int line_index=0; line_index < wrappedText.size(); ++line_index) {
            for (int char_index=0; char_index < wrappedText.get(line_index).length; ++char_index) {
                switch (characterStates[line_index][char_index]) {
                    case NonTyped: graphics.setColor(Palette.nonTypedText); break;
                    case Typed:    graphics.setColor(Palette.typedText); break;
                    case Error:    graphics.setColor(Palette.errorText); break;
                }
                
                graphics.drawChars(
                    wrappedText.get(line_index),
                    char_index,
                    1,
                    CommonSettings.paddingX + offsetX,
                    paddingY + line_index * font_metrics.getHeight()
                );

                offsetX += font_metrics.charWidth(wrappedText.get(line_index)[char_index]);
            }

            offsetX = 0;
        }
    }

    @Override public final void update() {}
     
    public final String accuracy() {
        int correct = 0;
        int total_chars = 0;

        for (CharacterState[] state_line : characterStates)
            for (CharacterState state : state_line) {
                total_chars += 1;
                if (state == CharacterState.Typed)
                    correct += 1;
            }
        
        return String.format("%.2f", (((double)correct) / total_chars) * 100 );
    }

    public final String wpm(Long totalTime) {
        int total_chars = 0;
        for (CharacterState[] state_line : characterStates)
            total_chars += state_line.length;

        return String.format("%.2f", (((double)total_chars)/5) / ((double)totalTime / 60));
    }

    public final boolean complete() { return indexOfCurrentLine >= wrappedText.size(); }
    public final boolean empty() { return indexOfCurrentChar == 0 && indexOfCurrentLine == 0; }
}
