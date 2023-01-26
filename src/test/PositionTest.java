import model.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    @Test
    public void testConstructor() {
        Position position = new Position(0, 0);
        assertEquals(0, position.getPosY());
        assertEquals(0, position.getPosX());
    }

    @Test
    public void testConstructorWithPositionValuesInMiddleOfLowerAndUpperBounds() {
        Position position = new Position(5, 5);
        assertEquals(5, position.getPosY());
        assertEquals(5, position.getPosX());
    }

    @Test
    public void testConstructorWithDifferentPositionValues() {
        Position position = new Position(3, 4);
        assertEquals(3, position.getPosY());
        assertEquals(4, position.getPosX());
    }

    @Test
    public void testConstructorWithUpperLimitValues() {
        Position position = new Position(9, 8);
        assertEquals(9, position.getPosY());
        assertEquals(8, position.getPosX());
    }
}
