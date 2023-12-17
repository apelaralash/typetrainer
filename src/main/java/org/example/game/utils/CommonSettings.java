package org.example.game.utils;

import java.awt.Dimension;
import java.awt.Font;

public class CommonSettings {
// window
    public static final Dimension windowSize = new Dimension(1280, 960);

// font
    public static final Integer fontSize = 48; // in px
    public static final Font font = new Font("Arial", Font.BOLD, fontSize);
    public static final Integer lengthOfLine = 40; // in chars

// serizalization
    public static final String pathToSaveFile = "save.bin";

// texts
    // TO-DO: add folder for text 
    public static final String pathToTexts = "text/";
}
