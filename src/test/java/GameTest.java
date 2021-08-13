import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void validFieldInit() {
        System.setIn(new ByteArrayInputStream("5".getBytes(StandardCharsets.UTF_8)));
        assertTrue(new Game().initField());
    }

    @Test
    void invalidFieldInit() {
        System.setIn(new ByteArrayInputStream("d".getBytes(StandardCharsets.UTF_8)));
        assertFalse(new Game().initField());
    }

    @Test
    void createPlayer() throws IOException {
        System.setIn(new ByteArrayInputStream("Bartek".getBytes(StandardCharsets.UTF_8)));
        assertEquals(State.X, new Game().createPlayer(1).getState());
        System.setIn(new ByteArrayInputStream("Bartek".getBytes(StandardCharsets.UTF_8)));
        assertEquals("Bartek", new Game().createPlayer(1).getName());
    }

    @Test
    void getMove() {
        System.setIn(new ByteArrayInputStream("1".getBytes(StandardCharsets.UTF_8)));
        assertEquals(1, new Game().getMove());
    }
}