package ui.gui.components;

import javax.swing.*;
import java.awt.*;

/*
 * Class representing a ui component that represents a playable Janggi piece
 */
public class PlayablePiece extends Placeable {

    private int owner;

    // REQUIRES: given positions must be within the board (0 <= posY <= 9,
    //           0 <= posX <= 8)
    // EFFECTS: constructs a playable piece component with an icon, y-position, x-position,
    //          and owner
    public PlayablePiece(ImageIcon icon, int posY, int posX, int owner) {
        super(posY, posX);
        this.setIcon(icon);
        this.owner = owner;
        this.setPreferredSize(new Dimension(Board.CELL_X, Board.CELL_Y));
    }

    public int getOwner() {
        return this.owner;
    }
}
