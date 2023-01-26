import model.Gameboard;
import model.pieces.Piece;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameboardTest {
    @Test
    public void testGameboardConstructor() {
        Gameboard gameboard = new Gameboard();
        assertEquals(10, gameboard.getBoard().size());
        for (ArrayList<Piece> row: gameboard.getBoard()) {
            assertEquals(9, row.size());
        }
    }
    // COMMENT: Reminder for Bob - mentioned how gameboard would be
    //          very tedious to test one-by-one and that we could see
    //          if the board was working by UI, so I didn't need to
    //          test every single position on board
}
