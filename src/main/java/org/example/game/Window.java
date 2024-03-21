package org.example.game;

import org.example.game.utils.LambdaHolder;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class Window {
    private final JFrame frame;
    private JPanel canvas;

    public Window(final String title, final Dimension window_size, KeyListener listener) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(window_size.width, window_size.height);
        frame.setLocationRelativeTo(null);
        frame.setLayout(
            new BoxLayout(
                frame.getContentPane(),
                BoxLayout.PAGE_AXIS
            )
        );

        frame.addKeyListener(listener);

        // mb change it to contantPane? idk
        canvas = new JPanel();
        canvas.setPreferredSize(window_size);
        frame.add(canvas);

        frame.pack();
    }

    public final void addWindowListener(LambdaHolder lambda) {
        frame.addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent event) {
                    lambda.action();
                }
            }
        );
    }

    public final void present() {
        frame.setVisible(true);
    }

    public final Graphics graphics() { return canvas.getGraphics(); }
    public final Dimension size() { return frame.getSize(); }
}
