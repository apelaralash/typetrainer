package org.example.game;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Arrays;

import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

public final class TextVew implements Entity {
    private char[] text;
    private boolean[] correctChars;
    int countOfTyped;

    public TextVew() {
        // TO-DO: read text from file
        // TO-DO: вместо пробелов можно какой-то спец символ выводить
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.".toCharArray();

        correctChars = new boolean[text.length];
        Arrays.fill(correctChars, false);

        countOfTyped = 0;
    }
        
    public final void onInput(Character typed_char) {
        if (countOfTyped == correctChars.length)
            return;
        
        correctChars[countOfTyped] = (typed_char == text[countOfTyped]) ? true : false;
        countOfTyped += 1;
    }
        
    @Override
    public final void update() {}
    
    @Override
    public final void draw(Graphics graphics) {
        graphics.setFont(CommonSettings.font);
        FontMetrics font_metrics = graphics.getFontMetrics();
        
    // typed text and errors text
        graphics.setColor(Palette.typedText);

        // TO-DO: refactor this into 1 function

        int number_of_line = 0;
        int len_of_text_to_draw = text.length; // lenthg of undrawed text
        int len_of_text_line = 0;

        for (int index=0; index < countOfTyped; ++index) {
            if (index % CommonSettings.lenghtOfLine == 0) {
                number_of_line += 1;
                len_of_text_to_draw -= len_of_text_line;
                len_of_text_line = (len_of_text_to_draw >= CommonSettings.lenghtOfLine) ? CommonSettings.lenghtOfLine : len_of_text_to_draw;
            }

            graphics.setColor((correctChars[index]) ? Palette.typedText : Palette.errorText);

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

        
    // draw a normal text
        graphics.setColor(Palette.normalText);

        for (int index=countOfTyped; index < text.length; ++index) {
            if (index % CommonSettings.lenghtOfLine == 0) {
                number_of_line += 1;
                len_of_text_to_draw -= len_of_text_line;
                len_of_text_line = (len_of_text_to_draw >= CommonSettings.lenghtOfLine) ? CommonSettings.lenghtOfLine : len_of_text_to_draw;
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
