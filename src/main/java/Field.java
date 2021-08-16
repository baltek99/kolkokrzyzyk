public class Field {
    private State[][] field;
    private final int howManyToWin;

    public Field(int n) {
        field = new State[n][n];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = State.EMPTY;
            }
        }
        howManyToWin = n <= 5 ? n : 5;
        //howManyToWin = n;
    }

    public boolean isFull() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == State.EMPTY) return false;
            }
        }
        return true;
    }

    public State[][] getField() {
        return field;
    }

    public void setField(State[][] field) {
        this.field = field;
    }

    private int numberOfDigits(int x) {
        return String.valueOf(x).length();
    }

    private void addSpaces(StringBuilder sb, int number) {
        for (int i = 0; i < number; i++) {
            sb.append(' ');
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int max = field.length * field.length;
        int number = numberOfDigits(max);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == State.EMPTY) {
                    int numberOfField = i * field[i].length + j + 1;
                    sb.append(numberOfField);
                    addSpaces(sb, number - numberOfDigits(numberOfField));
                } else {
                    if (field[i][j] == State.X) {
                        sb.append("X");
                    } else {
                        sb.append("O");
                    }
                    addSpaces(sb, number - 1);
                }

                if (j < field[i].length - 1) {
                    sb.append("|");
                }
            }
            sb.append("\n");

            if (i + 1 < field.length) {
                for (int j = 0; j < (number + 1) * field.length - 1; j++)
                    sb.append("-");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public boolean isValidMove(int index) {
        index--;
        int n = field.length;
        if (index < 0 || index >= n * n) return false;
        if (field[index / n][index % n] == State.EMPTY) return true;
        else return false;
    }

    public boolean insertMove(int index, State state) {
        int n = field.length;
        if (isValidMove(index)) {
            index--;
            field[index / n][index % n] = state;
            return true;
        } else {
            System.out.println("Nieprawidłowy ruch\n");
            return false;
        }
    }

    public boolean isWin() {
        return checkColumns() || checkRows() || checkDiagonals();
    }

    private boolean checkDiagonals() {
        for (int p = 0; p < 2 * field.length; p++) {
            if (Math.min(p + 1, field.length) - Math.max(0, p - field.length + 1) >= howManyToWin) {
                int counter = 1;
                for (int q = Math.max(0, p - field.length + 1); q < Math.min(p + 1, field.length) - 1; q++) {
                    int x = q, y = p - q;
                    if (field[x][y] == field[x + 1][y - 1] && field[x][y] != State.EMPTY) {
                        counter++;
                    } else
                        counter = 1;
                    if (counter == howManyToWin)
                        return true;
                }
            }
        }

        //backward diagonal
        for (int p = 0; p < 2 * field.length; p++) {
            if (Math.min(p + 1, field.length) - Math.max(0, p - field.length + 1) >= howManyToWin) {
                int counter = 1;
                for (int q = Math.max(0, p - field.length + 1); q < Math.min(p + 1, field.length) - 1; q++) {
                    int x = field.length - 1 - q, y = p - q;
                    if (field[x][y] == field[x - 1][y - 1] && field[x][y] != State.EMPTY) {
                        counter++;
                    } else
                        counter = 1;
                    if (counter == howManyToWin) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean checkColumns() {
        int counter = 1;
        for (int i = 0; i < field.length; i++) {
            State prev = field[0][i];
            for (int j = 1; j < field[i].length; j++) {
                if (field[j][i] == prev && field[j][i] != State.EMPTY) {
                    counter++;
                } else
                    counter = 1;

                if (counter == howManyToWin) return true;
                prev = field[j][i];
            }
        }
        return false;
    }

    private boolean checkRows() {
        int counter = 1;
        for (int i = 0; i < field.length; i++) {
            State prev = field[i][0];
            for (int j = 1; j < field[i].length; j++) {
                if (field[i][j] == prev && field[i][j] != State.EMPTY) {
                    counter++;
                } else
                    counter = 1;

                if (counter == howManyToWin) return true;
                prev = field[i][j];
            }
        }
        return false;
    }
}
