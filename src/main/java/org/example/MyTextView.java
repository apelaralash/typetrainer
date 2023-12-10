package org.example;

import org.example.colors.DesignColors;

import java.awt.*;
import java.io.File;

public class MyTextView implements Widget {

    private String requiredText;

    private StringBuilder currentText;

    private StringBuilder errorText;

    public MyTextView(String text) {
        super();
        setRequiredText(text);
    }

    @Override
    public void draw(Graphics graphics) {
        Font font = createFont();
        graphics.setFont(font);

        graphics.setColor(DesignColors.LIGHT_GRAY);
        graphics.drawString(requiredText, 10, 10);

        graphics.setColor(DesignColors.SUPER_LIGHT_GRAY);
        graphics.drawString(currentText.toString(), 10, 10);

        graphics.setColor(Color.RED);
        graphics.drawString(errorText.toString(), 10, 10);
    }

    @Override
    public void update() {
        for (int i = 0; i <= currentText.length(); ++i) {
            if (requiredText.charAt(i) == currentText.charAt(i)) {
                errorText.append(" ");
            } else {
                errorText.append(requiredText.charAt(i));
            }
        }
    }

    public void onKeyInput(char letter) {
        currentText.append(letter);
        update();
    }

    public void setRequiredText(String requiredText) {
        this.requiredText = requiredText;
    }

    private Font createFont() {
        try {
            File is = new File("dina10pxbold.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f);
            return font;
        } catch (Exception e) {
            return Font.getFont("Arial");
        }
    }
}
