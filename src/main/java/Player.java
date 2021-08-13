public class Player {
    private final State state;
    private final String name;

    public Player(State state, String name) {
        this.state = state;
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public boolean makeMove(Field field, int index) {
        return field.insertMove(index, state);
    }

    @Override
    public String toString() {
        return "Player " + name;
    }
}
