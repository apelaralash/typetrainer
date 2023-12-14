package org.example.game;

import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.lang.management.PlatformLoggingMXBean;
import java.util.Arrays;

public final class TypingTest implements Entity {
    private String[] words;
    private CharState[] charStates;
    int countOfTyped;

    public TypingTest() {
        // TO-DO: read text from file
        String _text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        words = _text.split(' ');

        charStates = new CharState[text.length];
        Arrays.fill(charStates, CharState.NonTyped);

        countOfTyped = 0;
    }
        
    public final void onInput(Character typed_char) {
        if (countOfTyped == correctChars.length)
            return;
        
        correctChars[countOfTyped] = (typed_char == text[countOfTyped]) ? true : false;
        countOfTyped += 1;
    }

    public final boolean compele() { return countOfTyped == text.length; }
        
    @Override public final void update() {}
    
    @Override
    public final void draw(Graphics graphics) {
        graphics.setFont(CommonSettings.font);
        FontMetrics font_metrics = graphics.getFontMetrics();

        int current_line_drawed_len = 0;
        
        for (int index=0; index <= words.length; ++index) {
            if (current_line_drawed_len >= CommonSettings.lenghtOfLine) break;

            switch (charStates[index]) {
                // Pallete.text_nonTyped etc..
                case NonTyped:  graphics.setColor(Pallete.nonTypedText); break;
                case Typed:     graphics.setColor(Pallete.typedText); break;
                case Error:     graphics.setColor(Pallete.errorText); break;
            }

            graphics.drawString(
                words[index],
                font_metrics.drawString(
                    
                ),
                100
            );
        }

    // typed text and errors text
    
        // graphics.setColor(Palette.typedText);

        // int number_of_line = 0;
        // int len_of_text_to_draw = text.length; // lenthg of undrawed text
        // int len_of_text_line = 0;

        // for (int index=0; index < text.length; ++index) {
        //     if (index % CommonSettings.lenghtOfLine == 0) {
        //         number_of_line += 1;
        //         len_of_text_to_draw -= len_of_text_line;
        //         len_of_text_line = (len_of_text_to_draw >= CommonSettings.lenghtOfLine) ? CommonSettings.lenghtOfLine : len_of_text_to_draw;
        //     }

        //     switch (charStates[index]) {
        //         case NonTyped: graphics.setColor(Pallete.nonTypedText); break;
        //         case Typed: graphics.setColor(Pallete.typedText); break;
        //         case Error: graphics.setColor(Pallete.errorText); break;
        //     }

        //     int char_offset_in_lines = (number_of_line-1)*CommonSettings.lenghtOfLine;
        //     int char_offset_in_chars = index;

        //     graphics.drawChars(
        //         text,
        //         index,
        //         1,
        //         CommonSettings.xTextPadding + font_metrics.charsWidth(
        //             text,
        //             char_offset_in_lines,
        //             char_offset_in_chars - char_offset_in_lines
        //         ),
        //         new Double(
        //             CommonSettings.fontSize * 1.5 + number_of_line * font_metrics.getHeight()
        //         ).intValue()
        //     );
        // }
    }
}
