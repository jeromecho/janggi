package model.pieces;

import model.Gameboard;
import model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Represents a guard piece on the board
 */
public class Guard extends Piece {

    // REQUIRES: requires owner to be either 1 (player 1) or 2 (player 2)
    // EFFECTS: constructs a guard with a y position, x position, and owner
    public Guard(int posY, int posX, int owner) {
        super(posY, posX, "guard", owner);
    }

    // REQUIRES: given gameboard needs to be the same gameboard that the specific
    //           cannon piece is on
    // EFFECTS: returns all possible positions a guard can move to based
    //          on the current position of the guard and the state of the
    //          gameboard
    @Override
    public List<Position> generatePossiblePositions(Gameboard gameboard) {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();
        possiblePositions.add(new Position(getPosition().getPosY() - 1, getPosition().getPosX() - 1));
        possiblePositions.add(new Position(getPosition().getPosY() - 1, getPosition().getPosX()));
        possiblePositions.add(new Position(getPosition().getPosY() - 1, getPosition().getPosX() + 1));
        possiblePositions.add(new Position(getPosition().getPosY(), getPosition().getPosX() - 1));
        possiblePositions.add(new Position(getPosition().getPosY(), getPosition().getPosX() + 1));
        possiblePositions.add(new Position(getPosition().getPosY() + 1, getPosition().getPosX() - 1));
        possiblePositions.add(new Position(getPosition().getPosY() + 1, getPosition().getPosX()));
        possiblePositions.add(new Position(getPosition().getPosY() + 1, getPosition().getPosX() + 1));
        return filterNonValidPositions(possiblePositions, gameboard);
    }

    // EFFECTS: removes positions that are outside the palace or that is occupied
    //          by an ally
    private List<Position> filterNonValidPositions(List<Position> positions, Gameboard gameboard) {
        List<Integer> palaceBoundaries = getPalaceBoundaries();
        int lowerYBound = palaceBoundaries.get(0);
        int higherYBound = palaceBoundaries.get(1);
        int lowerXBound = palaceBoundaries.get(2);
        int higherXBound = palaceBoundaries.get(3);
        return positions.stream()
                .filter(pos -> pos.getPosY() >= lowerYBound && pos.getPosY() <= higherYBound)
                .filter(pos -> pos.getPosX() >= lowerXBound && pos.getPosX() <= higherXBound)
                .filter(pos -> gameboard.getBoard().get(pos.getPosY()).get(pos.getPosX()).getOwner() != getOwner())
                .collect(Collectors.toList());
    }
    /*
     * src: https://www.geeksforgeeks.org/stream-filter-java-examples/
     * src: https://mkyong.com/java8/java-8-convert-a-stream-to-list/
     */

    // EFFECTS: returns 4 values: the y lower-boundary, the y upper-boundary, the x- lower boundary, then the x- upper
    //          boundary of the palace depending on which player the piece belongs
    private List<Integer> getPalaceBoundaries() {
        List<Integer> palaceBoundaries = new ArrayList<>();
        if (getOwner() == Piece.PLAYER_1) {
            palaceBoundaries.add(7);
            palaceBoundaries.add(9);
            palaceBoundaries.add(3);
            palaceBoundaries.add(5);
        } else {
            palaceBoundaries.add(0);
            palaceBoundaries.add(2);
            palaceBoundaries.add(3);
            palaceBoundaries.add(5);
        }
        return palaceBoundaries;
    }
}
