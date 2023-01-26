package model;

import java.util.Objects;

/*
 * Represents a position on a board, containing the vertical position from the top of the board
   and the horizontal position from left side of board
 */
public class Position {
    private int piecePosY;
    private int piecePosX;

    // REQUIRES: posY and posX must exist within board - 0 <= posY <= 9, 0 <= posX <= 8
    // EFFECTS: constructs a new position with a y position and an x position
    public Position(int posY, int posX) {
        piecePosY = posY;
        piecePosX = posX;
    }

    // EFFECTS: returns the piece's y position
    public int getPosY() {
        return piecePosY;
    }

    // EFFECTS: returns the piece's x position
    public int getPosX() {
        return piecePosX;
    }

    // EFFECTS: sets the piece's y position
    public void setPosY(int y) {
        piecePosY = y;
    }

    // EFFECTS: sets the piece's x position
    public void setPosX(int x) {
        piecePosX = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return piecePosY == position.piecePosY && piecePosX == position.piecePosX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piecePosY, piecePosX);
    }
}
