package Pieces;

import model.Gameboard;
import model.Player;
import model.Position;
import model.pieces.Cannon;
import model.pieces.Piece;
import model.pieces.Soldier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CannonTest {
    Cannon movingCannon;
    Cannon stationaryCannon;
    Gameboard gameboard;
    Gameboard anotherGameboard;
    Player player;
    List<Position> expectedPositionsAnotherGameboardSetup = new ArrayList<Position>();

    @BeforeEach
    public void setUp() {
        movingCannon = new Cannon(9, 0, Piece.PLAYER_1);
        stationaryCannon = new Cannon(7, 1, Piece.PLAYER_1);
        gameboard = new Gameboard();
        anotherGameboard = new Gameboard();
        player = new Player("Bob", Piece.PLAYER_1);

        Position stationaryCannonPosition = new Position(7, 1);
        player.movePiece(stationaryCannon, stationaryCannonPosition, gameboard);

        Piece allyOne = new Soldier(0, 0, Piece.PLAYER_1);
        Piece allyTwo = new Soldier(0, 1, Piece.PLAYER_1) ;
        Position newCannonPosition = new Position(6, 3);
        Position allyPositionInFrontOfCannon = new Position(5, 3);
        Position allyPositionBehindCannon = new Position(7, 3);
        player.movePiece(allyOne, allyPositionInFrontOfCannon, anotherGameboard);
        player.movePiece(movingCannon, newCannonPosition, anotherGameboard);
        player.movePiece(allyTwo, allyPositionBehindCannon, anotherGameboard);
    }

    @BeforeEach
    public void setUpExpectedPositions() {
        Position PositionOne = new Position(4, 3);
        Position PositionTwo = new Position(3, 3);
        Position PositionThree = new Position(2, 3);
        Position PositionFour = new Position(1, 3);
        Position PositionFive = new Position(0,3);
        Position PositionSix = new Position(6, 5);
        Position PositionSeven = new Position(6, 7);
        Position PositionEight = new Position(8 ,3);
        Position PositionNine = new Position(6, 1);
        expectedPositionsAnotherGameboardSetup.add(PositionOne);
        expectedPositionsAnotherGameboardSetup.add(PositionTwo);
        expectedPositionsAnotherGameboardSetup.add(PositionThree);
        expectedPositionsAnotherGameboardSetup.add(PositionFour);
        expectedPositionsAnotherGameboardSetup.add(PositionFive);
        expectedPositionsAnotherGameboardSetup.add(PositionSix);
        expectedPositionsAnotherGameboardSetup.add(PositionSeven);
        expectedPositionsAnotherGameboardSetup.add(PositionEight);
        expectedPositionsAnotherGameboardSetup.add(PositionNine);
    }

    @Test
    public void testConstructor() {
        movingCannon = new Cannon(1, 1, Piece.PLAYER_1);
        assertEquals(1, movingCannon.getPosition().getPosY());
        assertEquals(1, movingCannon.getPosition().getPosX());
        assertEquals("cannon", movingCannon.getName());
        assertEquals(Piece.PLAYER_1, movingCannon.getOwner());
    }

    @Test
    public void testConstructorWithDifferentInitialValues() {
        movingCannon = new Cannon(2, 3, Piece.PLAYER_2);
        assertEquals(2, movingCannon.getPosition().getPosY());
        assertEquals(3, movingCannon.getPosition().getPosX());
        assertEquals("cannon", movingCannon.getName());
        assertEquals(Piece.PLAYER_2, movingCannon.getOwner());
    }

    @Test
    public void testGeneratePossiblePositionsNoPossiblePositions() {
        List<Position> possiblePositions = stationaryCannon.generatePossiblePositions(gameboard);
        assertEquals(0, possiblePositions.size());
    }

    @Test
    public void testGeneratePossiblePositionsPossiblePositionsInAllDirections() {
        List<Position> possiblePositions = movingCannon.generatePossiblePositions(anotherGameboard);
        assertEquals(9, possiblePositions.size());
        for (int i = 0; i < expectedPositionsAnotherGameboardSetup.size(); i++) {
            Position currentExpectedPosition = expectedPositionsAnotherGameboardSetup.get(i);
            Position currentActualPosition = possiblePositions.get(i);
            assertEquals(currentExpectedPosition.getPosY(), currentActualPosition.getPosY());
            assertEquals(currentExpectedPosition.getPosX(), currentActualPosition.getPosX());
        }
    }
}
