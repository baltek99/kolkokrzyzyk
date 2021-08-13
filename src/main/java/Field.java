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
        howManyToWin = n;
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
        if (index < 0 || index >= n*n) return false;
        if (field[index/n][index%n] == State.EMPTY) return true;
        else return false;
    }

    public void insertMove(int index, State state) {
        int n = field.length;
        if (isValidMove(index)) {
            index--;
            field[index/n][index%n] = state;
        }
        else {
            System.out.println("Nieprawidłowy ruch\n");
        }
    }
    public boolean isWin() {
        return checkColumns() || checkRows() || checkDiagonals();
    }

    private boolean checkDiagonals() {
        int counter = 1;
        int counter2 = 1;
        State prev1 = field[0][0];
        State prev2 = field[0][field.length-1];
        for (int i = 1; i < field.length; i++) {
            if (field[i][i] == prev1 && field[i][i] != State.EMPTY) {
                counter++;
            }
            if (field[i][field.length-i-1] == prev2 && field[i][field.length-i-1] != State.EMPTY) {
                counter2++;
            }
        }
        if (counter == howManyToWin || counter2 == howManyToWin) return true;
        return false;
    }

    private boolean checkColumns() {
        int counter =1;
        for (int i = 0; i < field.length; i++) {
            State prev = field[0][i];
            for (int j = 1; j < field[i].length; j++) {
                if (field[j][i] == prev && field[j][i] != State.EMPTY) {
                    counter++;
                }
            }
            if (counter == howManyToWin) return true;
            else counter = 1;
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
                }
            }
            if (counter == howManyToWin) return true;
            else counter = 1;
        }
        return false;
    }
}
