package org.example.game;

import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

import java.awt.FontMetrics;
import java.awt.Graphics;

import java.util.Arrays;
import java.util.ArrayList;

public final class TextWidget implements Entity {
    private char[][] wrappedText;
    private CharacterState[][] characterStates;
    private int indexOfCurrentLine, indexOfCurrentChar;
    private int numberOfWords;

    public TextWidget(Graphics graphics, String text_to_type) {
        indexOfCurrentChar = 0;
        indexOfCurrentLine = 0;
        numberOfWords = 0;

        graphics.setFont(CommonSettings.font);
        FontMetrics font_metrics = graphics.getFontMetrics();
        wrappedText = getTextToDraw(text_to_type, font_metrics);

        characterStates = new CharacterState[wrappedText.length][];
        for (int index=0; index < characterStates.length; ++index) {
            characterStates[index] = new CharacterState[wrappedText[index].length];
            Arrays.fill(characterStates[index], CharacterState.NonTyped);
        }
    }
        
    public final void onInput(Character typed_char) {   
        if (wrappedText == null)
            return;
        
        if (indexOfCurrentLine >= wrappedText.length)
            return;

        characterStates[indexOfCurrentLine][indexOfCurrentChar] =
            (wrappedText[indexOfCurrentLine][indexOfCurrentChar] == typed_char) ?
                CharacterState.Typed : CharacterState.Error;

        indexOfCurrentChar += 1;

        if (wrappedText[indexOfCurrentLine].length == indexOfCurrentChar) {
            indexOfCurrentChar = 0;
            indexOfCurrentLine += 1;
        }
    }

    public final boolean complete() {
        return (
            indexOfCurrentLine >= wrappedText.length
        );
    }
        
    private final ArrayList<Integer> getIndicesOfTheLastWordsInLines(String[] words, FontMetrics font_metrics) {
        ArrayList<Integer> last_words_indeices = new ArrayList<>();  // indices_of_the_last_words_in_lines

        int space_char_width = font_metrics.charWidth(' ');
        int length_of_line = 0;
        int next_length_of_line = 0;
        int index = 0;

        // looking for indices of the last words in lines
        for (; index < words.length; ++index) {
            length_of_line = 0;
            next_length_of_line = 0;
    
            while (next_length_of_line < CommonSettings.lenghtOfLine) {
                length_of_line += font_metrics.stringWidth(words[index] + space_char_width);

                index += 1;

                if (index + 1 >= words.length)
                    break;

                int next_word_length = font_metrics.stringWidth(words[index + 1]);
                next_length_of_line += length_of_line + next_word_length + space_char_width;

                // that was here but we skip last word
                // need to check on valid when it in other place
                // index += 1;
            }

            last_words_indeices.add(index);
        }

        return last_words_indeices;
    }

    private final char[][] getTextToDraw(String text, FontMetrics font_metrics) {
        String[] words = text.split(" ");
        numberOfWords = words.length;
        ArrayList<Integer> last_words_indeices = getIndicesOfTheLastWordsInLines(words, font_metrics);
        
        char[][] result = new char[last_words_indeices.size()][];
        StringBuilder line = new StringBuilder();
        int last_word_index = 0;
        
        // creating double dimension array for text what need to draw
        for (int line_index=0; line_index < last_words_indeices.size(); ++line_index) {
            for (
                int word_index = last_word_index;
                word_index < last_words_indeices.get(line_index);
                ++word_index
            ) {
                line.append(words[word_index] + " ");
                last_word_index = last_words_indeices.get(line_index);
            }
            line.deleteCharAt(line.length()-1); // delete last space char in line
            
            result[line_index] = line.toString().toCharArray();
            line = new StringBuilder();
        }

        return result;
    }
 
    public final String accuracy() {
        int correct = 0;
        int total_chars = 0;

        for (CharacterState[] state_line : characterStates)
            for (CharacterState state : state_line) {
                total_chars += 1;
                if (state == CharacterState.Typed)
                    correct += 1;
            }

        return String.format("3%s", (double)correct / (double)total_chars);
    }

    public final String wpm(Long totalTime) {
        return String.format("3%s", (double)numberOfWords/totalTime);
    }

    @Override
    public final void draw(Graphics graphics) {
        graphics.setFont(CommonSettings.font);
        FontMetrics font_metrics = graphics.getFontMetrics();

        int offsetX = 0;
        int paddingX = (CommonSettings.windowSize.width - CommonSettings.lenghtOfLine) / 2; // need to fix should be 2!
        int leading = new Double(
            CommonSettings.fontSize * CommonSettings.leading_factor
        ).intValue();

        for (int line_index=0; line_index < wrappedText.length; ++line_index) {
            for (int char_index=0; char_index < wrappedText[line_index].length; ++char_index) {
                switch (characterStates[line_index][char_index]) {
                    case NonTyped: graphics.setColor(Palette.nonTypedText); break;
                    case Typed:    graphics.setColor(Palette.typedText); break;
                    case Error:    graphics.setColor(Palette.errorText); break;
                }
                
                graphics.drawChars(
                    wrappedText[line_index],
                    char_index,
                    1,
                    paddingX + offsetX,
                    line_index * font_metrics.getHeight() + leading
                );

                offsetX += font_metrics.charWidth(wrappedText[line_index][char_index]);
            }

            offsetX = 0;
        }
    }

    @Override public final void update() {}
}
