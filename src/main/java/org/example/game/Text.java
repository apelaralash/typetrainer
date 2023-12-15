package org.example.game;

import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

import javafx.beans.binding.DoubleBinding;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Arrays;

public final class Text implements Entity {
    private String[] words;
    private CharState[] charStates; // this should be [][]!
    private int indexOfCurrentWord, indexOfCurrentLetter, countOfTyped;

    public Text(String text) {
        // TO-DO: read text from file but from another place
        words = text.split(" ");

        charStates = new CharState[words.length];
        Arrays.fill(charStates, CharState.NonTyped);
        
        indexOfCurrentWord = 0;
        indexOfCurrentLetter = 0;
        countOfTyped = 0;
    }
        
    public final void onInput(Character typed_char) {
        if (countOfTyped == charStates.length)
            return;
        
        // charStates[countOfTyped] = (typed_char == words[indexOfCurrentWord].charAt(indexOfCurrentLetter)) ? CharState.Typed : CharState.Error;
        countOfTyped += 1;
    }

    public final boolean compele() { return countOfTyped == words.length; }
        
    
    @Override
    public final void draw(Graphics graphics) {
        graphics.setFont(CommonSettings.font);
        FontMetrics font_metrics = graphics.getFontMetrics();

        int space_char_width = font_metrics.charWidth(' ');
        int offset = 0;
        int number_of_line = 0;
        int summary_len_of_current_string = 0;
        
        for (int index=0; index < words.length; ++index) {
            switch (charStates[index]) {
                case NonTyped: graphics.setColor(Palette.normalText); break;
                case Typed:    graphics.setColor(Palette.typedText); break;
                case Error:    graphics.setColor(Palette.errorText); break;
            }


            // TO-DO: add vertical padding for text
            graphics.drawString(
                words[index],
                CommonSettings.xTextPadding + offset,
                new Double(
                    CommonSettings.fontSize * 1.5 + number_of_line * font_metrics.getHeight()
                ).intValue()
            );
            
            offset += font_metrics.stringWidth(words[index]) + space_char_width;
            summary_len_of_current_string += font_metrics.stringWidth(words[index]);

            if (summary_len_of_current_string >= CommonSettings.lenghtOfLine) {
                summary_len_of_current_string = 0;
                number_of_line += 1;
                offset = 0;
            }
        }

    // // typed text and errors text
    //     graphics.setColor(Palette.typedText);

    //     int number_of_line = 0;
    //     int len_of_text_to_draw = text.length; // lenthg of undrawed text
    //     int len_of_text_line = 0;

    //     for (int index=0; index < text.length; ++index) {
    //         if (index % CommonSettings.lenghtOfLine == 0) {
    //             number_of_line += 1;
    //             len_of_text_to_draw -= len_of_text_line;
    //             len_of_text_line = (len_of_text_to_draw >= CommonSettings.lenghtOfLine) ? CommonSettings.lenghtOfLine : len_of_text_to_draw;
    //         }

    //         switch (charStates[index]) {
    //             case NonTyped: graphics.setColor(Palette.normalText); break;
    //             case Typed:    graphics.setColor(Palette.typedText); break;
    //             case Error:    graphics.setColor(Palette.errorText); break;
    //         }

    //         int char_offset_in_lines = (number_of_line-1)*CommonSettings.lenghtOfLine;
    //         int char_offset_in_chars = index;

    //         graphics.drawChars(
    //             text,
    //             index,
    //             1,
    //             CommonSettings.xTextPadding + font_metrics.charsWidth(
    //                 text,
    //                 char_offset_in_lines,
    //                 char_offset_in_chars - char_offset_in_lines
    //             ),
    //             new Double(
    //                 CommonSettings.fontSize * 1.5 + number_of_line * font_metrics.getHeight()
    //             ).intValue()
    //         );
    //     }
    }

    @Override public final void update() {}
}
