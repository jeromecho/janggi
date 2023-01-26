package model.pieces;

import model.Gameboard;
import model.Position;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents an empty space on the board
 */
public class Space extends Piece {
    // REQUIRES: requires owner to be either 1 (player 1) or 2 (player 2)
    // EFFECTS: constructs a piece representing empty space at a y and x position, belonging
    //          to no specific owner
    public Space(int posY, int posX) {
        super(posY, posX, "space", Piece.NO_OWNER);
    }

    @Override
    public List<Position> generatePossiblePositions(Gameboard gameboard) {
        List<Position> emptyPositionsList = new ArrayList<>();
        return emptyPositionsList;
    }
}
