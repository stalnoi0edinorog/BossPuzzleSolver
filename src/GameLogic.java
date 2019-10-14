import java.util.Random;

public class GameLogic {
    private int[][] win = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    public int[][] numbers = new int[4][4];

    public void newGame() {
        numbers = new int[4][4];
        Random random = new Random();
        int[] invariants = new int[16];

        for (int j = 1; j < 16; j++) {
            int a, b;
            do {
                a = random.nextInt(4);
                b = random.nextInt(4);
            } while (numbers[a][b] != 0 || (a == 3 && b == 3));
            numbers[a][b] = j;
            invariants[a * 4 + b] = j;
        }

        if (!isSolvable(invariants) || isWin())
            newGame();
    }

    private boolean isSolvable(int[] invariants) {
        int counter = 0;
        for (int j = 0; j < 14; j++) {
            for (int i = j + 1; i < 15; i++) {
                if (invariants[j] > invariants[i])
                    counter++;
            }
        }
        return counter % 2 == 0;
    }

    void change(int number) {
        int i = 0,j = 0;
        for (int a = 0; a < 4; a++)
            for (int b = 0; b < 4; b++)
                if (numbers[a][b] == number) {
                    i = a;
                    j = b;
                }
        if (i > 0)
            if (numbers[i - 1][j] == 0) {
                numbers[i - 1][j] = number;
                numbers[i][j] = 0;
            }

        if (i < 3)
            if (numbers[i + 1][j] == 0) {
                numbers[i + 1][j] = number;
                numbers[i][j] = 0;
            }

        if (j > 0)
            if (numbers[i][j - 1] == 0) {
                numbers[i][j - 1] = number;
                numbers[i][j] = 0;
            }

        if (j < 3)
            if (numbers[i][j + 1] == 0) {
                numbers[i][j + 1] = number;
                numbers[i][j] = 0;
            }
    }

    boolean isWin() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (numbers[i][j] != win[i][j])
                    return false;
        return true;
    }
}