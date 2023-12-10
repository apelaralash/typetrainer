package org.example;

import org.example.colors.DesignColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.image.BufferedImage;

public class MenuScreen extends JFrame {
    private static final String str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

    private ComparableTimer timer = new ComparableTimer();

    private MyKeyListener keyListener = new MyKeyListener();
    private MyTextView textView =  new MyTextView(str);

    public MenuScreen() {
        setTitle("MenuScreen");
        setPreferredSize(new Dimension(1280, 1024));
        setBackground(DesignColors.GRAY);
        setResizable(false);




//        JTextArea text = new JTextArea();
//        text.setText(str);
//        text.setPreferredSize(new Dimension(800, 600));
//        text.setLineWrap(true);
//        text.setWrapStyleWord(true);
//        text.setFont(font);
//        text.setBackground(DesignColors.GRAY);
//        text.setForeground(DesignColors.LIGHT_GRAY);
//        text.setEditable(false);
//
//        JTextField textField = new JTextField();
//        textField.setFont(font);
//        textField.setBackground(DesignColors.GRAY);
//        textField.setForeground(DesignColors.SUPER_LIGHT_GRAY);
//        textField.setCaretColor(DesignColors.YELLOW);
//        textField.setPreferredSize(new Dimension(800, 600));
//        textField.setFocusable(true);

//        JButton buttonStart = new JButton("Start");
//        buttonStart.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        timer.startTimer();
//                    }
//                }
//        );
//        JButton buttonStop = new JButton("Stop");
//        buttonStop.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        timer.stopTimerAndReturnResult();
//                    }
//                }
//        );

//        add(text, BorderLayout.NORTH);
//        add(textField, BorderLayout.CENTER);
//        add(buttonStart, BorderLayout.AFTER_LAST_LINE);
//        add(buttonStop, BorderLayout.AFTER_LINE_ENDS);
        addKeyListener(keyListener);

        pack();

        getGraphics().drawString("ALLALLALA", 100, 100);
    }

    public void run() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics graphics = image.getGraphics();
        while (true) {
            Graphics mainGraphics = getGraphics();
            graphics.setColor(DesignColors.GRAY);
            graphics.drawRect(0, 0, getWidth(), getHeight());
            textView.onKeyInput(keyListener.getLastSymbol());
            textView.update();
            textView.draw(mainGraphics);

            mainGraphics.drawImage(image, 0, 0, null);
        }
    }
}
