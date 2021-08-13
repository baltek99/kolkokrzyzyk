import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private Player player1;
    private Player player2;
    private Field field;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Game game = new Game();
        game.initGame();
    }

    public void initGame() {
        hello();
        while (!initField()) ;
        initPlayer(1);
        initPlayer(2);
        game();
    }

    public void game() {
        boolean finished = false;

        System.out.println(field);

        while (!finished) {

            makeValidMove(player1);

            System.out.println(field);

            if (checkFinished(player1)) {
                break;
            } else {
                makeValidMove(player2);
            }

            System.out.println(field);

            finished = checkFinished(player2);
        }
    }

    public void makeValidMove(Player player) {
        boolean flag = true;
        while (flag) {
            int index = getMove();
            flag = !player.makeMove(field, index);
        }
    }

    public boolean checkFinished(Player player) {
        boolean isFull = field.isFull();
        boolean isWin = field.isWin();
        boolean finished = isFull || isWin;

        if (finished) {
            if (isFull) {
                System.out.println("Mamy remis pomiędzy graczami");
            } else {
                System.out.println("Wygral " + player.getName());
            }
        }
        return finished;
    }

    public int getMove() {
        boolean flag = true;
        int index = 0;
        while (flag) {
            System.out.println("Podaj numer pozycji:");
            try {
                String line = reader.readLine();
                try {
                    index = Integer.parseInt(line);
                    flag = false;
                } catch (NumberFormatException e) {
                    System.out.println("Nieprawidłowo wpisana liczba.");
                }

            } catch (IOException e) {
                System.out.println("Nieprawidłowe dane wejściowe.");
            }
        }
        return index;
    }

    public void initPlayer(int i) {
        boolean flag = true;
        while (flag) {
            try {
                if (i == 1)
                    player1 = createPlayer(i);
                else
                    player2 = createPlayer(i);
                flag = false;
            } catch (IOException e) {
                System.out.println("Nieprawidłowe dane dla gracza " + i);
            }
        }
    }

    public Player createPlayer(int i) throws IOException {
        System.out.println("Podaj imie " + (i == 1 ? "pierwszego" : "drugiego") + " gracza:");
        String line = reader.readLine();
        return new Player(i == 1 ? State.X : State.O, line);
    }

    public void hello() {
        System.out.println("Gra w kółko i krzyżyk");
        System.out.println("Aby wykonać ruch należy wpisać numer wolnego pola planszy.");
        System.out.println("Jeśli wybrany rozmiar jest większy od pięciu, aby wygrać wystarczy zająć 5 pól w linii.");
    }

    public boolean initField() {
        System.out.println("Podaj rozmiar planszy:");

        try {
            String line = reader.readLine();
            try {
                int n = Integer.parseInt(line);
                if (n > 0) {
                    field = new Field(n);
                    return true;
                } else {
                    System.out.println("Liczba powinna być dodatnia!");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowo wpisana liczba.");
            }

        } catch (IOException e) {
            System.out.println("Nieprawidłowe dane wejściowe.");
        }
        return false;
    }
}
