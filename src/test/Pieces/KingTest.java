package Pieces;

import model.Gameboard;
import model.Player;
import model.Position;
import model.pieces.Guard;
import model.pieces.King;
import model.pieces.Piece;
import model.pieces.Soldier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {

    King movingKing;
    King stationaryKing;
    King otherOwnerKing;
    Gameboard gameboard;
    Gameboard anotherGameboard;
    Player player;
    List<Position> expectedPositionsEnemyAndSpacePositions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        movingKing = new King(8, 4, Piece.PLAYER_1);
        stationaryKing = new King(9, 3, Piece.PLAYER_1);
        otherOwnerKing = new King(1, 4, Piece.PLAYER_2);
        gameboard = new Gameboard();
        anotherGameboard = new Gameboard();

        player = new Player("Bob", Piece.PLAYER_1);
        Piece blockingAllyOne = new Soldier(8, 3, Piece.PLAYER_1);
        Position blockingAllyPositionOne = new Position(8, 3);
        Piece blockingAllyTwo = new Soldier(9, 4, Piece.PLAYER_1);
        Position blockingAllyPositionTwo = new Position(9, 4);
        Piece blockingAllyThree = new Soldier(8, 4, Piece.PLAYER_1);
        Position blockingAllyPositionThree = new Position(8, 4);

        Position stationaryKingPosition = new Position(9, 3);
        player.movePiece(stationaryKing, stationaryKingPosition, gameboard);
        player.movePiece(blockingAllyOne, blockingAllyPositionOne, gameboard);
        player.movePiece(blockingAllyTwo, blockingAllyPositionTwo, gameboard);
        player.movePiece(blockingAllyThree, blockingAllyPositionThree, gameboard);

        Piece capturableEnemy = new Soldier(8, 5, Piece.PLAYER_2);
        Position capturableEnemyPosition = new Position(8,5);

        Position newKingPosition = new Position(8, 4);
        player.movePiece(movingKing,  newKingPosition, anotherGameboard);
        player.movePiece(capturableEnemy, capturableEnemyPosition, anotherGameboard);

        player.movePiece(otherOwnerKing, new Position(1, 4), gameboard);
    }

    @BeforeEach
    public void setUpExpectedPositionsEmptySpacesAndEnemy() {
        Position PositionOne = new Position(7, 3);
        Position PositionTwo = new Position(7, 4);
        Position PositionThree = new Position(7, 5);
        Position PositionFour = new Position(8, 3);
        Position PositionFive = new Position(8, 5);
        Position PositionSix = new Position(9, 4);
        expectedPositionsEnemyAndSpacePositions.add(PositionOne);
        expectedPositionsEnemyAndSpacePositions.add(PositionTwo);
        expectedPositionsEnemyAndSpacePositions.add(PositionThree);
        expectedPositionsEnemyAndSpacePositions.add(PositionFour);
        expectedPositionsEnemyAndSpacePositions.add(PositionFive);
        expectedPositionsEnemyAndSpacePositions.add(PositionSix);
    }

    @Test
    public void testConstructor() {
        movingKing = new King(1, 1, Piece.PLAYER_1);
        assertEquals(1, movingKing.getPosition().getPosY());
        assertEquals(1, movingKing.getPosition().getPosX());
        assertEquals("king", movingKing.getName());
        assertEquals(Piece.PLAYER_1, movingKing.getOwner());
    }

    @Test
    public void testConstructorWithDifferentInitialValues() {
        movingKing = new King(2, 3, Piece.PLAYER_2);
        assertEquals(2, movingKing.getPosition().getPosY());
        assertEquals(3, movingKing.getPosition().getPosX());
        assertEquals("king", movingKing.getName());
        assertEquals(Piece.PLAYER_2, movingKing.getOwner());
    }

    @Test
    public void testGeneratePossiblePositionsNoPossiblePositions() {
        List<Position> possiblePositions = stationaryKing.generatePossiblePositions(gameboard);
        assertEquals(0, possiblePositions.size());
    }

    @Test
    public void getTestGeneratePossiblePositionsEmptySpacesAndEnemyPositions() {
        List<Position> possiblePositions = movingKing.generatePossiblePositions(anotherGameboard);

        for (int i = 0; i < expectedPositionsEnemyAndSpacePositions.size(); i++) {
            Position currentExpectedPosition = expectedPositionsEnemyAndSpacePositions.get(i);
            Position currentActualPosition = possiblePositions.get(i);
            assertEquals(currentExpectedPosition.getPosY(), currentActualPosition.getPosY());
            assertEquals(currentExpectedPosition.getPosX(), currentActualPosition.getPosX());
        }
        assertEquals(expectedPositionsEnemyAndSpacePositions.size(), possiblePositions.size());
    }

    @Test
    public void getTestGeneratePossiblePositionsOtherPlayerPalace() {
        List<Position> possiblePositions = otherOwnerKing.generatePossiblePositions(gameboard);
        assertEquals(6, possiblePositions.size());
        assertEquals(0, possiblePositions.get(0).getPosY());
        assertEquals(4, possiblePositions.get(0).getPosX());
        assertEquals(1, possiblePositions.get(1).getPosY());
        assertEquals(3, possiblePositions.get(1).getPosX());
        assertEquals(1, possiblePositions.get(2).getPosY());
        assertEquals(5, possiblePositions.get(2).getPosX());
        assertEquals(2, possiblePositions.get(3).getPosY());
        assertEquals(3, possiblePositions.get(3).getPosX());
        assertEquals(2, possiblePositions.get(4).getPosY());
        assertEquals(4, possiblePositions.get(4).getPosX());
        assertEquals(2, possiblePositions.get(5).getPosY());
        assertEquals(5, possiblePositions.get(5).getPosX());
    }
}

