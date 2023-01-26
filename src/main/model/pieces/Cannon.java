package model.pieces;

import model.Gameboard;
import model.Position;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a cannon piece on the board
 */
public class Cannon extends Piece {

    // REQUIRES: requires owner to be either 1 (player 1) or 2 (player 2)
    // EFFECTS: constructs a cannon with a y position, x position, and an owner
    public Cannon(int posY, int posX, int owner) {
        super(posY, posX, "cannon", owner);
    }

    // REQUIRES: given gameboard needs to be the same gameboard that the specific
    //           cannon piece is on
    // EFFECTS: returns all possible positions a cannon can move to based
    //          on the current position of the cannon and the state of the
    //          gameboard
    @Override
    public List<Position> generatePossiblePositions(Gameboard gameboard) {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        possiblePositions.addAll(searchUp(gameboard));
        possiblePositions.addAll(searchRight(gameboard));
        possiblePositions.addAll(searchDown(gameboard));
        possiblePositions.addAll(searchLeft(gameboard));
        return possiblePositions;
    }

    // EFFECTS: searches and returns all possible positions the cannon can move up the board
    private List<Position> searchUp(Gameboard gameboard) {
        ArrayList<Position> upPositions = new ArrayList<>();
        int posX = piecePosition.getPosX();
        boolean nonCannonExistsInPath = false;

        for (int i = piecePosition.getPosY(); i >= 0; i--) {
            Piece currentPiece = gameboard.getBoard().get(i).get(posX);
            String otherPieceName = currentPiece.getName();
            if (!nonCannonExistsInPath && !otherPieceName.equals("cannon") && !otherPieceName.equals("space")) {
                nonCannonExistsInPath = true;
                continue;
            } else if (nonCannonExistsInPath
                    && currentPiece.getOwner() != getOwner()
                    && !currentPiece.getName().equals("cannon")) {
                Position newValidPosition = new Position(i, posX);
                upPositions.add(newValidPosition);
            }
        }

        return upPositions;
    }

    // EFFECTS: searches and returns all possible positions the cannon can move to the right on the board
    private List<Position> searchRight(Gameboard gameboard) {
        ArrayList<Position> rightPositions = new ArrayList<>();
        int posY = piecePosition.getPosY();
        boolean nonCannonExistsInPath = false;

        for (int i = piecePosition.getPosX(); i < 9; i++) {
            Piece currentPiece = gameboard.getBoard().get(posY).get(i);
            String otherPieceName = currentPiece.getName();
            if (!nonCannonExistsInPath && !otherPieceName.equals("cannon") && !otherPieceName.equals("space")) {
                nonCannonExistsInPath = true;
                continue;
            } else if (nonCannonExistsInPath && currentPiece.getOwner() != getOwner()
                        && !currentPiece.getName().equals("cannon")) {
                Position newValidPosition = new Position(posY, i);
                rightPositions.add(newValidPosition);
            }
        }

        return rightPositions;
    }

    // EFFECTS: searches and returns all possible positions the cannon can move down the board
    private List<Position> searchDown(Gameboard gameboard) {
        ArrayList<Position> downPositions = new ArrayList<>();
        int posX = piecePosition.getPosX();
        boolean nonCannonExistsInPath = false;

        for (int i = piecePosition.getPosY(); i < 10; i++) {
            Piece currentPiece = gameboard.getBoard().get(i).get(posX);
            String otherPieceName = currentPiece.getName();
            if (!nonCannonExistsInPath && !otherPieceName.equals("cannon") && !otherPieceName.equals("space")) {
                nonCannonExistsInPath = true;
                continue;
            } else if (nonCannonExistsInPath &&
                    currentPiece.getOwner() != getOwner() &&
                    !currentPiece.getName().equals("cannon")
            ) {
                Position newValidPosition = new Position(i, posX);
                downPositions.add(newValidPosition);
            }
        }

        return downPositions;
    }

    // EFFECTS: searches and returns all possible positions the cannon can move to the left on the board
    private List<Position> searchLeft(Gameboard gameboard) {
        ArrayList<Position> leftPositions = new ArrayList<>();
        int posY = piecePosition.getPosY();
        boolean nonCannonExistsInPath = false;

        for (int i = piecePosition.getPosX(); i >= 0; i--) {
            Piece currentPiece = gameboard.getBoard().get(posY).get(i);
            String otherPieceName = currentPiece.getName();
            if (!nonCannonExistsInPath && !otherPieceName.equals("cannon") && !otherPieceName.equals("space")) {
                nonCannonExistsInPath = true;
                continue;
            } else if (nonCannonExistsInPath &&
                    currentPiece.getOwner() != getOwner() &&
                    !currentPiece.getName().equals("cannon")
            ) {
                Position newValidPosition = new Position(posY, i);
                leftPositions.add(newValidPosition);
            }
        }

        return leftPositions;
    }
}
