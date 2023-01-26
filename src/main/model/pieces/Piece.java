package model.pieces;

import model.Gameboard;
import model.Position;

import java.util.ArrayList;
import java.util.List;


/*
 * Represents a piece on the board with a position, owner, and name
 */
public abstract class Piece {
    public static final int NO_OWNER = 0;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final String TOP = "top";
    public static final String RIGHT = "right";
    public static final String BOTTOM = "bottom";
    public static final String LEFT = "left";

    protected Position piecePosition;
    private int pieceOwner;
    private String pieceName;

    public Piece(int posY, int posX, String name, int owner) {
        Position newPosition = new Position(posY, posX);
        piecePosition = newPosition;
        pieceOwner = owner;
        pieceName = name;
    }

    // EFFECTS: generates all possible positions a piece can move to based
    //          on the current position of a piece and the state of the
    //          gameboard
    public abstract List<Position> generatePossiblePositions(Gameboard gameboard);

    public String getName() {
        return pieceName;
    }

    public int getOwner() {
        return pieceOwner;
    }

    public Position getPosition() {
        return piecePosition;
    }
}
