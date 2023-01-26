package model.pieces;

import model.Gameboard;
import model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Represents a soldier piece on the board
 */
public class Soldier extends Piece {

    // REQUIRES: requires owner to be either 1 (player 1) or 2 (player 2)
    // EFFECTS: constructs a soldier with a y position, x position, and owner
    public Soldier(int posY, int posX, int owner) {
        super(posY, posX, "soldier", owner);
    }

    // REQUIRES: given gameboard needs to be the same gameboard that the specific
    //           cannon piece is on
    // EFFECTS: returns all possible positions a soldier can move to based
    //          on the current position of the soldier and the state of the
    //          gameboard
    @Override
    public List<Position> generatePossiblePositions(Gameboard gameboard) {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();
        if (Piece.PLAYER_1 == getOwner()) {
            possiblePositions.add(new Position(getPosition().getPosY() - 1, getPosition().getPosX()));
        } else {
            possiblePositions.add(new Position(getPosition().getPosY() + 1, getPosition().getPosX()));
        }
        possiblePositions.add(new Position(getPosition().getPosY(), getPosition().getPosX() - 1));
        possiblePositions.add(new Position(getPosition().getPosY(), getPosition().getPosX() + 1));
        return filterNonValidPositions(possiblePositions, gameboard);
    }

    // EFFECTS: removes positions that are outside the palace or that is occupied
    //          by an ally
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
