package org.example.game;

import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Arrays;

public final class Text implements Entity {
    private String[] words;
    private CharState[] charStates; // this should be [][]!
    // private int indexOfCurrentWord, indexOfCurrentLetter, countOfTyped;

    public Text(String text) {
        // TO-DO: read text from file but from another place
        words = text.split(" ");

        charStates = new CharState[words.length];
        Arrays.fill(charStates, CharState.NonTyped);
        
        // indexOfCurrentWord = 0;
        // indexOfCurrentLetter = 0;
        // countOfTyped = 0;
    }
        
    public final void onInput(Character typed_char) {
        // if (countOfTyped == charStates.length)
        //     return;
        
        // // TO-DO: add input
        // // charStates[countOfTyped] = (typed_char == words[indexOfCurrentWord].charAt(indexOfCurrentLetter)) ? CharState.Typed : CharState.Error;
        // countOfTyped += 1;
    }

    public final boolean compele() { return 0 == words.length; }
    // public final boolean compele() { return countOfTyped == words.length; }
        
    @Override
    public final void draw(Graphics graphics) {
        graphics.setFont(CommonSettings.font);
        FontMetrics font_metrics = graphics.getFontMetrics();

        int space_char_width = font_metrics.charWidth(' ');
        int lineHeight = new Double( // некое подобие интерлиньяжа
            CommonSettings.fontSize * CommonSettings.leading_factor
        ).intValue();

        int offsetX = 0;
        int number_of_line = 0;
        int length_of_drawn_string = 0;
        
        for (int index=0; index < words.length; ++index) {
            switch (charStates[index]) {
                case NonTyped: graphics.setColor(Palette.normalText); break;
                case Typed:    graphics.setColor(Palette.typedText); break;
                case Error:    graphics.setColor(Palette.errorText); break;
            }

            graphics.drawString(
                words[index],
                CommonSettings.xTextPadding + offsetX,
                number_of_line * font_metrics.getHeight() + lineHeight
            );
            
            offsetX += font_metrics.stringWidth(words[index]) + space_char_width;
            length_of_drawn_string += font_metrics.stringWidth(words[index]);

            if (length_of_drawn_string >= CommonSettings.lenghtOfLine) {
                number_of_line += 1;
                length_of_drawn_string = 0;
                offsetX = 0;
            }
        }
    }

    @Override public final void update() {}
}
