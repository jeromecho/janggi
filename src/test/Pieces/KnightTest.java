package Pieces;

import model.Gameboard;
import model.Player;
import model.Position;
import model.pieces.Elephant;
import model.pieces.Knight;
import model.pieces.Piece;
import model.pieces.Soldier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {
    Knight movingKnight;
    Knight stationaryKnight;
    Gameboard gameboard;
    Gameboard anotherGameboard;
    Player player;
    List<Position> expectedPositionsEnemyAndSpacePositions = new ArrayList<Position>();

    @BeforeEach
    public void setUp() {
        movingKnight = new Knight(5, 1, Piece.PLAYER_1);
        stationaryKnight = new Knight(9, 1, Piece.PLAYER_1);

        gameboard = new Gameboard();
        anotherGameboard = new Gameboard();
        player = new Player("Bob", Piece.PLAYER_1);

        Piece blockingAllyOne = new Soldier(7, 0, Piece.PLAYER_1);
        Position blockingAllyPositionOne = new Position(7, 0);
        Piece blockingAllyTwo = new Soldier(7, 2, Piece.PLAYER_1);
        Position blockingAllyPositionTwo = new Position(7, 2);
        Piece blockingAllyThree = new Soldier(8, 3, Piece.PLAYER_1);
        Position blockingAllyPositionThree = new Position(8, 3);

        Position stationaryKnightPosition = new Position(9, 1);
        player.movePiece(stationaryKnight, stationaryKnightPosition, gameboard);
        player.movePiece(blockingAllyOne, blockingAllyPositionOne, gameboard);
        player.movePiece(blockingAllyTwo, blockingAllyPositionTwo, gameboard);
        player.movePiece(blockingAllyThree, blockingAllyPositionThree, gameboard);

        Position newKnightPosition = new Position(5, 1);
        player.movePiece(movingKnight, newKnightPosition, anotherGameboard);
    }

    @BeforeEach
    public void setUpExpectedPositionsEmptySpacesAndEnemy() {
        Position PositionOne = new Position(3, 0);
        Position PositionTwo = new Position(3, 2);
        Position PositionThree = new Position(4, 3);
        Position PositionFour = new Position(6,3);
        Position PositionFive = new Position(7, 0);
        Position PositionSix = new Position(7, 2);
        expectedPositionsEnemyAndSpacePositions.add(PositionOne);
        expectedPositionsEnemyAndSpacePositions.add(PositionTwo);
        expectedPositionsEnemyAndSpacePositions.add(PositionThree);
        expectedPositionsEnemyAndSpacePositions.add(PositionFour);
        expectedPositionsEnemyAndSpacePositions.add(PositionFive);
        expectedPositionsEnemyAndSpacePositions.add(PositionSix);
    }

    @Test
    public void testConstructor() {
        movingKnight = new Knight(1, 1, Piece.PLAYER_1);
        assertEquals(1, movingKnight.getPosition().getPosY());
        assertEquals(1, movingKnight.getPosition().getPosX());
        assertEquals("knight", movingKnight.getName());
        assertEquals(Piece.PLAYER_1, movingKnight.getOwner());
    }

    @Test
    public void testConstructorWithDifferentInitialValues() {
        movingKnight = new Knight(2, 3, Piece.PLAYER_2);
        assertEquals(2, movingKnight.getPosition().getPosY());
        assertEquals(3, movingKnight.getPosition().getPosX());
        assertEquals("knight", movingKnight.getName());
        assertEquals(Piece.PLAYER_2, movingKnight.getOwner());
    }

    @Test
    public void testGeneratePossiblePositionsNoPossiblePositions() {
        List<Position> possiblePositions = stationaryKnight.generatePossiblePositions(gameboard);
        assertEquals(0, possiblePositions.size());
    }

    @Test
    public void getTestGeneratePossiblePositionsEmptySpacesAndEnemyPositions() {
        List<Position> possiblePositions = movingKnight.generatePossiblePositions(anotherGameboard);

        for (int i = 0; i < expectedPositionsEnemyAndSpacePositions.size(); i++) {
            Position currentExpectedPosition = expectedPositionsEnemyAndSpacePositions.get(i);
            Position currentActualPosition = possiblePositions.get(i);
            assertEquals(currentExpectedPosition.getPosY(), currentActualPosition.getPosY());
            assertEquals(currentExpectedPosition.getPosX(), currentActualPosition.getPosX());
        }
        assertEquals(expectedPositionsEnemyAndSpacePositions.size(), possiblePositions.size());
    }
}
