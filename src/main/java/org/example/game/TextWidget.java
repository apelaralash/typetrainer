package org.example.game;

import org.example.game.utils.CommonSettings;
import org.example.game.utils.Palette;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Arrays;

public final class TextWidget implements Entity {
    private char[] text;
    private CharState[] characterStates;
    private int counterOfTyped;

    public TextWidget(String text_to_type) {
        counterOfTyped = 0;

        text = text_to_type.toCharArray();


        characterStates = new CharState[text.length];
        Arrays.fill(characterStates, CharState.NonTyped);
    }
        
    public final void onInput(Character typed_char) {
        if (counterOfTyped == characterStates.length)
            return;
        
        characterStates[counterOfTyped] =
            (typed_char == text[counterOfTyped]) ? CharState.Typed : CharState.Error;
        
        counterOfTyped += 1;
    }

    public final boolean compele() { return counterOfTyped == text.length; }
        
    @Override
    public final void draw(Graphics graphics) {
        graphics.setFont(CommonSettings.font);
        FontMetrics font_metrics = graphics.getFontMetrics();

        // int space_char_width = font_metrics.charWidth(' ');
        // int lineHeight = new Double(
        //     CommonSettings.fontSize * CommonSettings.leading_factor
        // ).intValue();

        // int offsetX = 0;
        // int number_of_line = 0;
        // int length_of_drawn_string = 0;
        
        // for (int index=0; index < words.length; ++index) {
        //     switch (charStates[index]) {
        //         case NonTyped: graphics.setColor(Palette.normalText); break;
        //         case Typed:    graphics.setColor(Palette.typedText); break;
        //         case Error:    graphics.setColor(Palette.errorText); break;
        //     }

        //     graphics.drawString(
        //         words[index],
        //         CommonSettings.xTextPadding + offsetX,
        //         number_of_line * font_metrics.getHeight() + lineHeight
        //     );
            
        //     offsetX += font_metrics.stringWidth(words[index]) + space_char_width;
        //     length_of_drawn_string += font_metrics.stringWidth(words[index]);

        //     if (length_of_drawn_string >= CommonSettings.lenghtOfLine) {
        //         number_of_line += 1;
        //         length_of_drawn_string = 0;
        //         offsetX = 0;
        //     }
        // }
    }

    @Override public final void update() {}
}
