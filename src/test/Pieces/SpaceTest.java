package Pieces;

import model.Gameboard;
import model.Player;
import model.Position;
import model.pieces.Piece;
import model.pieces.Soldier;
import model.pieces.Space;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceTest {
    Space space;
    Space anotherSpace;
    Gameboard gameboard;
    Gameboard anotherGameboard;
    Player player;

    @BeforeEach
    public void setUp() {
        space = new Space(4, 2);
        anotherSpace = new Space(6, 0);

        gameboard = new Gameboard();
        anotherGameboard = new Gameboard();
        player = new Player("Bob", Piece.PLAYER_1);

        Piece blockingAllyOne = new Soldier(5, 0, Piece.PLAYER_1);
        Position blockingAllyPositionOne = new Position(5, 0);
        Piece blockingAllyTwo = new Soldier(7, 0, Piece.PLAYER_1);
        Position blockingAllyPositionTwo = new Position(7, 0);
        Piece blockingAllyThree = new Soldier(6, 1, Piece.PLAYER_1);
        Position blockingAllyPositionThree = new Position(6, 1);

        Position spacePosition = new Position(6, 0);
        player.movePiece(space, spacePosition, gameboard);
        player.movePiece(blockingAllyOne, blockingAllyPositionOne, gameboard);
        player.movePiece(blockingAllyTwo, blockingAllyPositionTwo, gameboard);
        player.movePiece(blockingAllyThree, blockingAllyPositionThree, gameboard);

        Position anotherSpacePosition = new Position(4, 2);
        player.movePiece(anotherSpace,  anotherSpacePosition, anotherGameboard);
    }

    @Test
    public void testConstructor() {
        space = new Space(1, 1);
        assertEquals(1, space.getPosition().getPosY());
        assertEquals(1, space.getPosition().getPosX());
        assertEquals("space", space.getName());
        assertEquals(Piece.NO_OWNER, space.getOwner());
    }

    @Test
    public void testConstructorWithDifferentInitialValues() {
        space = new Space(2, 3);
        assertEquals(2, space.getPosition().getPosY());
        assertEquals(3, space.getPosition().getPosX());
        assertEquals("space", space.getName());
        assertEquals(Piece.NO_OWNER, space.getOwner());
    }

    @Test
    public void testGeneratePossiblePositionsOnePosition() {
        List<Position> possiblePositions = space.generatePossiblePositions(gameboard);
        assertEquals(0, possiblePositions.size());
    }

    @Test
    public void testGeneratePossiblePositionsAnotherPosition() {
        List<Position> possiblePositions = anotherSpace.generatePossiblePositions(anotherGameboard);
        assertEquals(0, possiblePositions.size());
    }
}
