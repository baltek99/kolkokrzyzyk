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
}