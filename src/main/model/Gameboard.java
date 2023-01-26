package model;

import model.pieces.*;

import java.util.ArrayList;

/*
 * Represents a 9 x 10 gameboard with several pieces (Chariot, Knight, Elephant, Guard, King, Cannon, Soldier),
 */
public class Gameboard {
    private ArrayList<ArrayList<Piece>> gameboard;

    // EFFECTS: constructs a new gameboard with pieces in the upper half of the board belonging to
    //          player two, and pieces in the lower half of the board belonging to player two
    public Gameboard() {
        gameboard = initializeGameboard();
    }

    // EFFECTS: returns gameboard
    public ArrayList<ArrayList<Piece>> getBoard() {
        return gameboard;
    }

    // EFFECTS: creates and returns an arraylist with pieces in their appropriate positions
    private ArrayList<ArrayList<Piece>> initializeGameboard() {
        ArrayList<ArrayList<Piece>> gameboard = new ArrayList<>();
        ArrayList<Piece> rowOne = makeRowOne();
        ArrayList<Piece> rowTwo = makeRowTwo();
        ArrayList<Piece> rowThree = makeRowThree();
        ArrayList<Piece> rowFour = makeRowFour();
        ArrayList<Piece> rowFive = makeRowFive();
        ArrayList<Piece> rowSix = makeRowSix();
        ArrayList<Piece> rowSeven = makeRowSeven();
        ArrayList<Piece> rowEight = makeRowEight();
        ArrayList<Piece> rowNine = makeRowNine();
        ArrayList<Piece> rowTen = makeRowTen();
        gameboard.add(rowOne);
        gameboard.add(rowTwo);
        gameboard.add(rowThree);
        gameboard.add(rowFour);
        gameboard.add(rowFive);
        gameboard.add(rowSix);
        gameboard.add(rowSeven);
        gameboard.add(rowEight);
        gameboard.add(rowNine);
        gameboard.add(rowTen);

        return gameboard;
    }

    // EFFECTS: creates the first row of a janggi board, containing the follow pieces
    //          in order (Chariot, Knight, Elephant, Guard, Space, Guard, Elephant, Knight, Chariot)
    //          with all non-Space pieces belonging to player two
    private ArrayList<Piece> makeRowOne() {
        ArrayList<Piece> rowOne = new ArrayList<>();
        Chariot chariotOne = new Chariot(0, 0, Piece.PLAYER_2);
        Knight knightOne = new Knight(0, 1, Piece.PLAYER_2);
        Elephant elephantOne = new Elephant(0, 2, Piece.PLAYER_2);
        Guard guardOne = new Guard(0, 3, Piece.PLAYER_2);
        Space emptySpace = new Space(0, 4);
        Guard guardTwo = new Guard(0, 5, Piece.PLAYER_2);
        Elephant elephantTwo = new Elephant(0, 6, Piece.PLAYER_2);
        Knight knightTwo = new Knight(0, 7, Piece.PLAYER_2);
        Chariot chariotTwo = new Chariot(0, 8, Piece.PLAYER_2);

        rowOne.add(chariotOne);
        rowOne.add(knightOne);
        rowOne.add(elephantOne);
        rowOne.add(guardOne);
        rowOne.add(emptySpace);
        rowOne.add(guardTwo);
        rowOne.add(elephantTwo);
        rowOne.add(knightTwo);
        rowOne.add(chariotTwo);

        return rowOne;
    }

    // EFFECTS: creates the second row of a janggi board, containing the follow pieces
    //          in order (Space, Space, Space, Space, King, Space, Space, Space, Space)
    //          with all non-Space pieces belonging to player two
    private ArrayList<Piece> makeRowTwo() {
        ArrayList<Piece> rowTwo = new ArrayList<>();
        Space spaceOne = new Space(1, 0);
        Space spaceTwo = new Space(1, 1);
        Space spaceThree = new Space(1, 2);
        Space spaceFour = new Space(1, 3);
        King king = new King(1, 4, Piece.PLAYER_2);
        Space spaceFive = new Space(1, 5);
        Space spaceSix = new Space(1, 6);
        Space spaceSeven = new Space(1, 7);
        Space spaceEight = new Space(1, 8);

        rowTwo.add(spaceOne);
        rowTwo.add(spaceTwo);
        rowTwo.add(spaceThree);
        rowTwo.add(spaceFour);
        rowTwo.add(king);
        rowTwo.add(spaceFive);
        rowTwo.add(spaceSix);
        rowTwo.add(spaceSeven);
        rowTwo.add(spaceEight);
        return rowTwo;
    }

    // EFFECTS: creates the third row of a janggi board, containing the follow pieces
    //          in order (Space, Cannon, Space, Space, Space, Space, Space, Cannon, Space)
    //          with all non-Space pieces belonging to player two
    private ArrayList<Piece> makeRowThree() {
        ArrayList<Piece> rowThree = new ArrayList<>();
        Space spaceOne = new Space(2, 0);
        Cannon cannonOne = new Cannon(2, 1, Piece.PLAYER_2);
        Space spaceTwo = new Space(2, 2);
        Space spaceThree = new Space(2, 3);
        Space spaceFour = new Space(2, 4);
        Space spaceFive = new Space(2, 5);
        Space spaceSix = new Space(2, 6);
        Cannon cannonTwo = new Cannon(2, 7, Piece.PLAYER_2);
        Space spaceSeven = new Space(2, 8);

        rowThree.add(spaceOne);
        rowThree.add(cannonOne);
        rowThree.add(spaceTwo);
        rowThree.add(spaceThree);
        rowThree.add(spaceFour);
        rowThree.add(spaceFive);
        rowThree.add(spaceSix);
        rowThree.add(cannonTwo);
        rowThree.add(spaceSeven);
        return rowThree;
    }

    // EFFECTS: creates the second row of a janggi board, containing the follow pieces
    //          in order(Soldier, Space, Soldier, Space, Soldier, Space, Soldier, Space, Soldier)
    //          with all non-Space pieces belonging to player two
    private ArrayList<Piece> makeRowFour() {
        ArrayList<Piece> rowFour = new ArrayList<>();
        Soldier soldierOne = new Soldier(3, 0, Piece.PLAYER_2);
        Space spaceOne = new Space(3, 1);
        Soldier soldierTwo = new Soldier(3, 2, Piece.PLAYER_2);
        Space spaceTwo = new Space(3, 3);
        Soldier soldierThree = new Soldier(3, 4, Piece.PLAYER_2);
        Space spaceThree = new Space(3, 5);
        Soldier soldierFour = new Soldier(3, 6, Piece.PLAYER_2);
        Space spaceFour = new Space(3, 7);
        Soldier soldierFive = new Soldier(3, 8, Piece.PLAYER_2);

        rowFour.add(soldierOne);
        rowFour.add(spaceOne);
        rowFour.add(soldierTwo);
        rowFour.add(spaceTwo);
        rowFour.add(soldierThree);
        rowFour.add(spaceThree);
        rowFour.add(soldierFour);
        rowFour.add(spaceFour);
        rowFour.add(soldierFive);
        return rowFour;
    }

    // EFFECTS: creates the first row of a janggi board, containing the follow pieces
    //          in order (Space, Space, Space, Space, Space, Space, Space, Space, Space)
    private ArrayList<Piece> makeRowFive() {
        ArrayList<Piece> rowFive = new ArrayList<>();
        Space spaceOne = new Space(4, 0);
        Space spaceTwo = new Space(4, 1);
        Space spaceThree = new Space(4, 2);
        Space spaceFour = new Space(4, 3);
        Space spaceFive = new Space(4, 4);
        Space spaceSix = new Space(4, 5);
        Space spaceSeven = new Space(4, 6);
        Space spaceEight = new Space(4, 7);
        Space spaceNine = new Space(4, 8);

        rowFive.add(spaceOne);
        rowFive.add(spaceTwo);
        rowFive.add(spaceThree);
        rowFive.add(spaceFour);
        rowFive.add(spaceFive);
        rowFive.add(spaceSix);
        rowFive.add(spaceSeven);
        rowFive.add(spaceEight);
        rowFive.add(spaceNine);
        return rowFive;
    }

    // EFFECTS: creates the first row of a janggi board, containing the follow pieces
    //          in order (Space, Space, Space, Space, Space, Space, Space, Space, Space)
    private ArrayList<Piece> makeRowSix() {
        ArrayList<Piece> rowSix = new ArrayList<>();
        Space spaceOne = new Space(5, 0);
        Space spaceTwo = new Space(5, 1);
        Space spaceThree = new Space(5, 2);
        Space spaceFour = new Space(5, 3);
        Space spaceFive = new Space(5, 4);
        Space spaceSix = new Space(5, 5);
        Space spaceSeven = new Space(5, 6);
        Space spaceEight = new Space(5, 7);
        Space spaceNine = new Space(5, 8);

        rowSix.add(spaceOne);
        rowSix.add(spaceTwo);
        rowSix.add(spaceThree);
        rowSix.add(spaceFour);
        rowSix.add(spaceFive);
        rowSix.add(spaceSix);
        rowSix.add(spaceSeven);
        rowSix.add(spaceEight);
        rowSix.add(spaceNine);
        return rowSix;
    }

    // EFFECTS: creates the first row of a janggi board, containing the follow pieces
    //          in order (Soldier, Space, Soldier, Space, Soldier, Space, Soldier, Space, Soldier)
    //          with all non-Space pieces belonging to player one
    private ArrayList<Piece> makeRowSeven() {
        ArrayList<Piece> rowSeven = new ArrayList<>();
        Soldier soldierOne = new Soldier(6, 0, Piece.PLAYER_1);
        Space spaceOne = new Space(6, 1);
        Soldier soldierTwo = new Soldier(6, 2, Piece.PLAYER_1);
        Space spaceTwo = new Space(6, 3);
        Soldier soldierThree = new Soldier(6, 4, Piece.PLAYER_1);
        Space spaceThree = new Space(6, 5);
        Soldier soldierFour = new Soldier(6, 6, Piece.PLAYER_1);
        Space spaceFour = new Space(6, 7);
        Soldier soldierFive = new Soldier(6, 8, Piece.PLAYER_1);

        rowSeven.add(soldierOne);
        rowSeven.add(spaceOne);
        rowSeven.add(soldierTwo);
        rowSeven.add(spaceTwo);
        rowSeven.add(soldierThree);
        rowSeven.add(spaceThree);
        rowSeven.add(soldierFour);
        rowSeven.add(spaceFour);
        rowSeven.add(soldierFive);
        return rowSeven;
    }

    // EFFECTS: creates the first row of a janggi board, containing the follow pieces
    //          in order (Space, Cannon, Space, Space, Space, Space, Space, Cannon, Space)
    //          with all non-Space pieces belonging to player one
    private ArrayList<Piece> makeRowEight() {
        ArrayList<Piece> rowEight = new ArrayList<>();
        Space spaceOne = new Space(7, 0);
        Cannon cannonOne = new Cannon(7, 1, Piece.PLAYER_1);
        Space spaceTwo = new Space(7, 2);
        Space spaceThree = new Space(7, 3);
        Space spaceFour = new Space(7, 4);
        Space spaceFive = new Space(7, 5);
        Space spaceSix = new Space(7, 6);
        Cannon cannonTwo = new Cannon(7, 7, Piece.PLAYER_1);
        Space spaceSeven = new Space(7, 8);

        rowEight.add(spaceOne);
        rowEight.add(cannonOne);
        rowEight.add(spaceTwo);
        rowEight.add(spaceThree);
        rowEight.add(spaceFour);
        rowEight.add(spaceFive);
        rowEight.add(spaceSix);
        rowEight.add(cannonTwo);
        rowEight.add(spaceSeven);
        return rowEight;
    }

    // EFFECTS: creates the first row of a janggi board, containing the follow pieces
    //          in order (Space, Space, Space, Space, King, Space, Space, Space, Space)
    //          with all non-Space pieces belonging to player one
    private ArrayList<Piece> makeRowNine() {
        ArrayList<Piece> rowNine = new ArrayList<>();
        Space spaceOne = new Space(8, 0);
        Space spaceTwo = new Space(8, 1);
        Space spaceThree = new Space(8, 2);
        Space spaceFour = new Space(8, 3);
        King king = new King(8, 4, Piece.PLAYER_1);
        Space spaceFive = new Space(8, 5);
        Space spaceSix = new Space(8, 6);
        Space spaceSeven = new Space(8, 7);
        Space spaceEight = new Space(8, 8);

        rowNine.add(spaceOne);
        rowNine.add(spaceTwo);
        rowNine.add(spaceThree);
        rowNine.add(spaceFour);
        rowNine.add(king);
        rowNine.add(spaceFive);
        rowNine.add(spaceSix);
        rowNine.add(spaceSeven);
        rowNine.add(spaceEight);
        return rowNine;
    }

    // EFFECTS: creates the first row of a janggi board, containing the follow pieces
    //          in order (Chariot, Knight, Elephant, Guard, Space, Guard, Elephant, Knight, Chariot)
    //          with all non-Space pieces belonging to player one
    private ArrayList<Piece> makeRowTen() {
        ArrayList<Piece> rowTen = new ArrayList<>();
        Chariot chariotOne = new Chariot(9, 0, Piece.PLAYER_1);
        Knight knightOne = new Knight(9, 1, Piece.PLAYER_1);
        Elephant elephantOne = new Elephant(9, 2, Piece.PLAYER_1);
        Guard guardOne = new Guard(9, 3, Piece.PLAYER_1);
        Space emptySpace = new Space(9, 4);
        Guard guardTwo = new Guard(9, 5, Piece.PLAYER_1);
        Elephant elephantTwo = new Elephant(9, 6, Piece.PLAYER_1);
        Knight knightTwo = new Knight(9, 7, Piece.PLAYER_1);
        Chariot chariotTwo = new Chariot(9, 8, Piece.PLAYER_1);

        rowTen.add(chariotOne);
        rowTen.add(knightOne);
        rowTen.add(elephantOne);
        rowTen.add(guardOne);
        rowTen.add(emptySpace);
        rowTen.add(guardTwo);
        rowTen.add(elephantTwo);
        rowTen.add(knightTwo);
        rowTen.add(chariotTwo);
        return rowTen;
    }
}
