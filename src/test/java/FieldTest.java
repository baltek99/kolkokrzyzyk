import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void emptyField() {
        assertEquals("1|2|3\n-----\n4|5|6\n-----\n7|8|9\n", new Field(3).toString());
        assertEquals("1|2\n---\n3|4\n", new Field(2).toString());
        String str = "1 |2 |3 |4 \n-----------\n5 |6 |7 |8 \n-----------\n9 |10|11|12\n-----------\n13|14|15|16\n";
        assertEquals(str, new Field(4).toString());
    }

    @Test
    void notEmptyField() {
        Field field = new Field(2);
        field.insertMove(1, State.X);
        assertEquals("X|2\n---\n3|4\n", field.toString());
    }

    @Test
    void validMove() {
        Field field = new Field(2);
        assertTrue(field.isValidMove(3));
    }

    @Test
    void invalidMove() {
        Field field = new Field(2);
        field.insertMove(3, State.X);
        assertFalse(field.isValidMove(3));
        assertFalse(field.isValidMove(5));
    }

    @Test
    void insertMove() {

        Field field = new Field(2);
        field.insertMove(1, State.X);
        assertEquals(State.X, field.getField()[0][0]);
    }

    @Test
    void winningField() {
        Field field = new Field(2);
        field.insertMove(1, State.X);
        field.insertMove(3, State.X);
        assertTrue(field.isWin());
    }

    @Test
    void notWinningField() {
        Field field = new Field(3);
        field.insertMove(1, State.X);
        field.insertMove(5, State.O);
        field.insertMove(9, State.X);
        assertFalse(field.isWin());
    }

    @Test
    void winningBigFieldByDiag() {
        Field field = new Field(7);
        field.insertMove(1, State.X);
        field.insertMove(9, State.X);
        field.insertMove(17, State.X);
        field.insertMove(25, State.X);
        field.insertMove(33, State.X);
        assertTrue(field.isWin());

        field = new Field(7);
        field.insertMove(8, State.X);
        field.insertMove(16, State.X);
        field.insertMove(24, State.X);
        field.insertMove(32, State.X);
        field.insertMove(40, State.X);
        assertTrue(field.isWin());

        field = new Field(7);
        field.insertMove(21, State.X);
        field.insertMove(27, State.X);
        field.insertMove(33, State.X);
        field.insertMove(39, State.X);
        field.insertMove(45, State.X);
        assertTrue(field.isWin());
    }

    @Test
    void winningBigFieldByRows() {
        Field field = new Field(7);
        field.insertMove(2, State.X);
        field.insertMove(3, State.X);
        field.insertMove(4, State.X);
        field.insertMove(5, State.X);
        field.insertMove(6, State.X);
        assertTrue(field.isWin());
    }

    @Test
    void winningBigFieldByColumns() {
        Field field = new Field(7);
        field.insertMove(11, State.X);
        field.insertMove(18, State.X);
        field.insertMove(25, State.X);
        field.insertMove(32, State.X);
        field.insertMove(39, State.X);
        assertTrue(field.isWin());
    }

    @Test
    void isFull() {
        Field field = new Field(3);
        assertFalse(field.isFull());
        for (int i = 0; i < 9; i++) {
            field.insertMove(i + 1, State.X);
        }
        assertTrue(field.isFull());
    }
}