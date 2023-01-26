package model.pieces;

import model.Game;
import model.Gameboard;
import model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Represents an elephant piece on the board
 */
public class Elephant extends Piece {

    // REQUIRES: requires owner to be either 1 (player 1) or 2 (player 2)
    // EFFECTS: constructs a new elephant with a y position, x position, and owner
    public Elephant(int posY, int posX, int owner) {
        super(posY, posX, "elephant", owner);
    }

    // REQUIRES: given gameboard needs to be the same gameboard that the specific
    //           cannon piece is on
    // EFFECTS: returns all possible positions an elephant can move to based
    //          on the current position of the elephant and the state of the
    //          gameboard
    @Override
    public List<Position> generatePossiblePositions(Gameboard gameboard) {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();
        possiblePositions.addAll(searchDirection(Piece.TOP, gameboard));
        possiblePositions.addAll(searchDirection(Piece.RIGHT, gameboard));
        possiblePositions.addAll(searchDirection(Piece.BOTTOM, gameboard));
        possiblePositions.addAll(searchDirection(Piece.LEFT, gameboard));
        return possiblePositions;
    }

    // REQUIRES: given string must be one of: "top", "right", "bottom", or "left"
    // EFFECTS: returns all possible positions in the given direction for the elephant
    //          piece
    private List<Position> searchDirection(String direction, Gameboard gameboard) {
        List<Position> positions = new ArrayList<>();
        if (direction.equals(Piece.TOP)) {
            positions.add(new Position(piecePosition.getPosY() - 3, piecePosition.getPosX() - 2));
            positions.add(new Position(piecePosition.getPosY() - 3, piecePosition.getPosX() + 2));
            return filterNonValidPositions(positions, gameboard);
        } else if (direction.equals(Piece.RIGHT)) {
            positions.add(new Position(piecePosition.getPosY() - 2, piecePosition.getPosX() + 3));
            positions.add(new Position(piecePosition.getPosY() + 2, piecePosition.getPosX() + 3));
            return filterNonValidPositions(positions, gameboard);
        } else if (direction.equals(Piece.BOTTOM)) {
            positions.add(new Position(piecePosition.getPosY() + 3, piecePosition.getPosX() - 2));
            positions.add(new Position(piecePosition.getPosY() + 3, piecePosition.getPosX() + 2));
            return filterNonValidPositions(positions, gameboard);
        } else {
            positions.add(new Position(piecePosition.getPosY() - 2, piecePosition.getPosX() - 3));
            positions.add(new Position(piecePosition.getPosY() + 2, piecePosition.getPosX() - 3));
            return filterNonValidPositions(positions, gameboard);
        }
    }

    // EFFECTS: filters the given list of positions off the board or that are occupied by an ally
    private List<Position> filterNonValidPositions(List<Position> positions, Gameboard gameboard) {
        return positions.stream()
                .filter(pos -> pos.getPosY() >= 0 && pos.getPosY() <= 9)
                .filter(pos -> pos.getPosX() >= 0 && pos.getPosX() <= 8)
                .filter(pos -> gameboard.getBoard().get(pos.getPosY()).get(pos.getPosX()).getOwner() != getOwner())
                .collect(Collectors.toList());
    }
    /*
     * src: https://www.geeksforgeeks.org/stream-filter-java-examples/
     * src: https://mkyong.com/java8/java-8-convert-a-stream-to-list/
     */
}
