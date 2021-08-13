import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getState() {
        Player player = new Player(State.X, "Koala");
        assertEquals(State.X, player.getState());
        assertNotEquals(State.O, player.getState());
    }

    @Test
    void getName() {
        Player player = new Player(State.X, "Koala");
        assertEquals("Koala", player.getName());
        assertNotEquals("K", player.getName());
    }


    @Test
    void testToString() {
        Player player = new Player(State.X, "Koala");
        assertEquals("Player Koala", player.toString());
        assertNotEquals("Player K", player.toString());
    }

    @Test
    void makeMove() {
        Field field = new Field(3);
        Player p1 = new Player(State.X, "Gracz");
        p1.makeMove(field,1);
        assertEquals(State.X, field.getField()[0][0]);
    }
}