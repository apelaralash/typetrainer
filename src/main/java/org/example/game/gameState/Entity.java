package org.example.game.gameState;

import java.awt.Graphics;
import java.io.Serializable;

public abstract interface Entity extends Serializable {
    public abstract void update();
    public abstract void draw(Graphics graphics);
}
