package org.example.game;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.lang.management.PlatformLoggingMXBean;
import java.util.Arrays;

import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

public final class Text implements Entity {
    private char[] text;
    // private String[] text;
    // private int indexOfCurrentWord, indexOfCurrentLetter;
    private CharState[] charStates;
    int countOfTyped;

    public Text() {
        // TO-DO: read text from file
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.".toCharArray();
        // text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.".split(" ");

        // indexOfCurrentWord = 0;
        // indexOfCurrentLetter = 0;
        // countOfTyped = 0;

        charStates = new CharState[text.length];
        Arrays.fill(charStates, CharState.NonTyped);

    }
        
    public final void onInput(Character typed_char) {
        if (countOfTyped == charStates.length)
            return;
        
        charStates[countOfTyped] = (typed_char == text[countOfTyped]) ? CharState.Typed : CharState.Error;
        // charStates[countOfTyped] = (typed_char == text[indexOfCurrentWord].charAt(indexOfCurrentLetter)) ? CharState.Typed : CharState.Error;
        countOfTyped += 1;
    }

    public final boolean compele() { return countOfTyped == text.length; }
        
    @Override
    public final void update() {}
    
    @Override
    public final void draw(Graphics graphics) {
        graphics.setFont(CommonSettings.font);
        FontMetrics font_metrics = graphics.getFontMetrics();
        
    // typed text and errors text
        graphics.setColor(Palette.typedText);

        int number_of_line = 0;
        int len_of_text_to_draw = text.length; // lenthg of undrawed text
        int len_of_text_line = 0;

        for (int index=0; index < text.length; ++index) {
            if (index % CommonSettings.lenghtOfLine == 0) {
                number_of_line += 1;
                len_of_text_to_draw -= len_of_text_line;
                len_of_text_line = (len_of_text_to_draw >= CommonSettings.lenghtOfLine) ? CommonSettings.lenghtOfLine : len_of_text_to_draw;
            }

            switch (charStates[index]) {
                case NonTyped: graphics.setColor(Palette.normalText); break;
                case Typed:    graphics.setColor(Palette.typedText); break;
                case Error:    graphics.setColor(Palette.errorText); break;
            }

            int char_offset_in_lines = (number_of_line-1)*CommonSettings.lenghtOfLine;
            int char_offset_in_chars = index;

            graphics.drawChars(
                text,
                index,
                1,
                CommonSettings.xTextPadding + font_metrics.charsWidth(
                    text,
                    char_offset_in_lines,
                    char_offset_in_chars - char_offset_in_lines
                ),
                new Double(
                    CommonSettings.fontSize * 1.5 + number_of_line * font_metrics.getHeight()
                ).intValue()
            );
        }
    }
}
