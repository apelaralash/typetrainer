package org.example.game;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.example.game.utils.IOnWindowClosing;

import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class Window {
    private final JFrame frame;
    private JPanel canvas;

    public Window(final String title, final int width, final int height) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setLayout(
            new BoxLayout(
                frame.getContentPane(),
                BoxLayout.PAGE_AXIS
            )
        );

        canvas = new JPanel();
        canvas.setPreferredSize(new Dimension(width, height));
        frame.add(canvas);

        frame.pack();
    }

    public final void addWindowListener(IOnWindowClosing action) {
        frame.addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent event) {
                    action.windowClosing();
                }
            }
        );
    }
    public final void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
    }

    public final void present() {
        frame.setVisible(true);
    }

    public final Graphics graphics() { return canvas.getGraphics(); }
    public final Dimension size() { return frame.getSize(); }
}
