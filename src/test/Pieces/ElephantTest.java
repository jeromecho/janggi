package Pieces;

import model.Gameboard;
import model.Player;
import model.Position;
import model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ElephantTest {
    Elephant movingElephant;
    Elephant stationaryElephant;
    Gameboard gameboard;
    Gameboard anotherGameboard;
    Player player;
    List<Position> expectedPositionsEnemyAndSpacePositions = new ArrayList<Position>();

    @BeforeEach
    public void setUp() {
        movingElephant = new Elephant(4, 2, Piece.PLAYER_1);
        stationaryElephant = new Elephant(9, 2, Piece.PLAYER_1);

        gameboard = new Gameboard();
        anotherGameboard = new Gameboard();
        player = new Player("Bob", Piece.PLAYER_1);

        Piece blockingAlly = new Soldier(7, 5, Piece.PLAYER_1);
        Position blockingAllyPosition = new Position(7, 5);
        Position stationaryElephantPosition = new Position(9, 2);
        player.movePiece(stationaryElephant, stationaryElephantPosition, gameboard);
        player.movePiece(blockingAlly, blockingAllyPosition, gameboard);

        Position newElephantPosition = new Position(4, 2);
        player.movePiece(movingElephant, newElephantPosition, anotherGameboard);
    }

    @BeforeEach
    public void setUpExpectedPositionsEmptySpacesAndEnemy() {
        Position PositionOne = new Position(1, 0);
        Position PositionTwo = new Position(1, 4);
        Position PositionThree = new Position(2, 5);
        Position PositionFour = new Position(6,5);
        Position PositionFive = new Position(7, 0);
        Position PositionSix = new Position(7, 4);
        expectedPositionsEnemyAndSpacePositions.add(PositionOne);
        expectedPositionsEnemyAndSpacePositions.add(PositionTwo);
        expectedPositionsEnemyAndSpacePositions.add(PositionThree);
        expectedPositionsEnemyAndSpacePositions.add(PositionFour);
        expectedPositionsEnemyAndSpacePositions.add(PositionFive);
        expectedPositionsEnemyAndSpacePositions.add(PositionSix);
    }

    @Test
    public void testConstructor() {
        movingElephant = new Elephant(1, 1, Piece.PLAYER_1);
        assertEquals(1, movingElephant.getPosition().getPosY());
        assertEquals(1, movingElephant.getPosition().getPosX());
        assertEquals("elephant", movingElephant.getName());
        assertEquals(Piece.PLAYER_1, movingElephant.getOwner());
    }

    @Test
    public void testConstructorWithDifferentInitialValues() {
        movingElephant = new Elephant(2, 3, Piece.PLAYER_2);
        assertEquals(2, movingElephant.getPosition().getPosY());
        assertEquals(3, movingElephant.getPosition().getPosX());
        assertEquals("elephant", movingElephant.getName());
        assertEquals(Piece.PLAYER_2, movingElephant.getOwner());
    }

    @Test
    public void testGeneratePossiblePositionsNoPossiblePositions() {
        List<Position> possiblePositions = stationaryElephant.generatePossiblePositions(gameboard);
        assertEquals(0, possiblePositions.size());
    }

    @Test
    public void getTestGeneratePossiblePositionsEmptySpacesAndEnemyPositions() {
        List<Position> possiblePositions = movingElephant.generatePossiblePositions(anotherGameboard);

        for (int i = 0; i < expectedPositionsEnemyAndSpacePositions.size(); i++) {
            Position currentExpectedPosition = expectedPositionsEnemyAndSpacePositions.get(i);
            Position currentActualPosition = possiblePositions.get(i);
            assertEquals(currentExpectedPosition.getPosY(), currentActualPosition.getPosY());
            assertEquals(currentExpectedPosition.getPosX(), currentActualPosition.getPosX());
        }
        assertEquals(expectedPositionsEnemyAndSpacePositions.size(), possiblePositions.size());
    }
}
