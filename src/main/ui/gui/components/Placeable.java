package ui.gui.components;

import javax.swing.*;
import java.awt.*;

/*
 * Class representing all components placeable on a board
 */
public abstract class Placeable extends JLabel {

    private int posY;
    private int posX;

    // REQUIRES: given positions must be within the board (0 <= posY <= 9,
    //           0 <= posX <= 8)
    // EFFECTS: constructs a board component with y-position and x-position
    public Placeable(int posY, int posX) {
        this.posY = posY;
        this.posX = posX;
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public abstract int getOwner();
}
