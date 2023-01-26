package ui.gui.components;

import model.pieces.Piece;
import ui.gui.views.GameView;

import java.awt.*;

/*
 * Class representing an empty visual component on the board
 */
public class Space extends Placeable {

    private final int owner = Piece.NO_OWNER;

    // REQUIRES: given positions must be within the board (0 <= posY <= 9,
    //           0 <= posX <= 8)
    // EFFECTS: constructs a space component with y-position and x-position
    public Space(int posY, int posX) {
        super(posY, posX);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(Board.CELL_X, Board.CELL_Y));
    }

    public int getOwner() {
        return owner;
    }
}
