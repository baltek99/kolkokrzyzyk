import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @org.junit.jupiter.api.Test
    void emptyField() {
        assertEquals("1|2|3\n-----\n4|5|6\n-----\n7|8|9\n",
                new Field(3).toString());
        assertEquals("1|2\n---\n3|4\n", new Field(2).toString());
        String str = "1 |2 |3 |4 \n-----------\n5 |6 |7 |8 \n-----------\n9 |10|11|12\n-----------\n13|14|15|16\n";
        assertEquals(str, new Field(4).toString());
    }

    @Test
    void notEmptyField() {
        Field field = new Field(2);
        field.insertMove(1,State.X);
        assertEquals("X|2\n---\n3|4\n", field.toString());
    }

    @Test
    void validMove() {
        Field field = new Field(2);
        assertTrue(field.isValidMove(3));
    }

    @Test
    void invalidMove(){
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
    void winningField(){
        Field field = new Field(2);
        field.insertMove(1, State.X);
        field.insertMove(3, State.X);
        assertTrue(field.isWin());
    }

    @Test
    void notWinningField(){
        Field field = new Field(3);
        field.insertMove(1, State.X);
        field.insertMove(5, State.O);
        field.insertMove(9, State.X);
        assertFalse(field.isWin());
    }
}