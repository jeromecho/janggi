package model.pieces;

import model.Gameboard;
import model.Position;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a chariot piece on the board
 */
public class Chariot extends Piece {

    // REQUIRES: requires owner to be either 1 (player 1) or 2 (player 2)
    // EFFECTS: constructs a new chariot with a y position, x position, and owner
    public Chariot(int posY, int posX, int owner) {
        super(posY, posX, "chariot", owner);
    }

    // REQUIRES: given gameboard needs to be the same gameboard that the specific
    //           cannon piece is on
    // EFFECTS: returns all possible positions a chariot can move to based
    //          on the current position of the chariot and the state of the
    //          gameboard
    @Override
    public List<Position> generatePossiblePositions(Gameboard gameboard) {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();
        possiblePositions.addAll(searchUp(gameboard));
        possiblePositions.addAll(searchRight(gameboard));
        possiblePositions.addAll(searchDown(gameboard));
        possiblePositions.addAll(searchLeft(gameboard));
        return possiblePositions;
    }

    // EFFECTS: searches and returns all possible positions the chariot can move up the board
    private List<Position> searchUp(Gameboard gameboard) {
        ArrayList<Position> upPositions = new ArrayList<>();
        int posX = piecePosition.getPosX();
        int otherPlayerPlayingOrder = getOtherPlayerPlayingOrder();

        for (int i = piecePosition.getPosY() - 1; i >= 0; i--) {

            Piece currentPiece = gameboard.getBoard().get(i).get(posX);
            int otherPieceOwner = currentPiece.getOwner();
            if (otherPieceOwner == getOwner()) {
                return upPositions;
            } else if (otherPieceOwner == otherPlayerPlayingOrder) {
                Position newAllowablePosition = new Position(i, posX);
                upPositions.add(newAllowablePosition);
                return upPositions;
            } else if (otherPieceOwner == Piece.NO_OWNER) {
                Position newAllowablePosition = new Position(i, posX);
                upPositions.add(newAllowablePosition);
            }
        }

        return upPositions;
    }

    // EFFECTS: searches and returns all possible positions the chariot can move to the right on the board
    private List<Position> searchRight(Gameboard gameboard) {
        ArrayList<Position> rightPositions = new ArrayList<>();
        int posY = piecePosition.getPosY();
        int otherPlayerPlayingOrder = getOtherPlayerPlayingOrder();

        for (int i = piecePosition.getPosX() + 1; i < 9; i++) {
            Piece currentPiece = gameboard.getBoard().get(posY).get(i);
            int otherPieceOwner = currentPiece.getOwner();
            if (otherPieceOwner == getOwner()) {
                return rightPositions;
            } else if (otherPieceOwner == otherPlayerPlayingOrder) {
                Position newAllowablePosition = new Position(posY, i);
                rightPositions.add(newAllowablePosition);
                return rightPositions;
            } else if (otherPieceOwner == Piece.NO_OWNER) {
                Position newAllowablePosition = new Position(posY, i);
                rightPositions.add(newAllowablePosition);
            }
        }

        return rightPositions;
    }

    // EFFECTS: searches and returns all possible positions the chariot can move down the board
    private List<Position> searchDown(Gameboard gameboard) {
        ArrayList<Position> downPositions = new ArrayList<>();
        int posX = piecePosition.getPosX();
        int otherPlayerPlayingOrder = getOtherPlayerPlayingOrder();

        for (int i = piecePosition.getPosY() + 1; i < 10; i++) {
            Piece currentPiece = gameboard.getBoard().get(i).get(posX);
            int otherPieceOwner = currentPiece.getOwner();

            if (otherPieceOwner == getOwner()) {
                return downPositions;
            } else if (otherPieceOwner == otherPlayerPlayingOrder) {
                Position newAllowablePosition = new Position(i, posX);
                downPositions.add(newAllowablePosition);
                return downPositions;
            } else if (otherPieceOwner == Piece.NO_OWNER) {
                Position newValidPosition = new Position(i, posX);
                downPositions.add(newValidPosition);
            }
        }

        return downPositions;
    }

    // EFFECTS: searches and returns all possible positions the chariot can move to the left on the board
    private List<Position> searchLeft(Gameboard gameboard) {
        ArrayList<Position> leftPositions = new ArrayList<>();
        int posY = piecePosition.getPosY();
        int otherPlayerPlayingOrder = getOtherPlayerPlayingOrder();

        for (int i = piecePosition.getPosX() - 1; i >= 0; i--) {
            Piece currentPiece = gameboard.getBoard().get(posY).get(i);
            int otherPieceOwner = currentPiece.getOwner();

            if (otherPieceOwner == getOwner()) {
                return leftPositions;
            } else if (otherPieceOwner == otherPlayerPlayingOrder) {
                Position newAllowablePosition = new Position(posY, i);
                leftPositions.add(newAllowablePosition);
                return leftPositions;
            } else if (otherPieceOwner == Piece.NO_OWNER) {
                Position newValidPosition = new Position(posY, i);
                leftPositions.add(newValidPosition);
            }
        }

        return leftPositions;
    }

    // EFFECTS: returns the player order of the other player
    private int getOtherPlayerPlayingOrder() {
        if (getOwner() == Piece.PLAYER_1) {
            return Piece.PLAYER_2;
        } else {
            return Piece.PLAYER_1;
        }
    }
}
