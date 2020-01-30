package bossPuzzle;

import java.util.Arrays;
import java.util.Random;
class GameLogic {
    private int[] win = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    int[] numbers = new int[16];


    void newGame() {
        Random random = new Random();
        numbers = new int[16];

        for (int j = 1; j < 16; j++) {
            int a, b;
            do {
                a = random.nextInt(4);
                b = random.nextInt(4);
            } while (numbers[a * 4 + b] != 0 || (a == 3 && b == 3));
            numbers[a * 4 + b] = j;
        }
        System.out.println(Arrays.toString(numbers));
        if (!isSolvable(numbers) || isWin()) {
            newGame();
        }
    }

    private boolean isSolvable(int[] invariants) {
        int counter = 0;
        for (int j = 0; j < 15; j++) {
            for (int i = j + 1; i < 16; i++) {
                if (invariants[j] > invariants[i])
                    counter++;
            }
        }
        return counter % 2 == 0;
    }

    boolean change(int number) {
        int i = 0,j = 0;
        for (int a = 0; a < 4; a++)
            for (int b = 0; b < 4; b++)
                if (numbers[a * 4 + b] == number) {
                    i = a;
                    j = b;
                }
        if (i > 0)
            if (numbers[(i - 1) * 4 + j] == 0) {
                numbers[(i - 1) * 4 + j] = number;
                numbers[i * 4 + j] = 0;
                return true;
            }

        if (i < 3)
            if (numbers[(i + 1) * 4 + j] == 0) {
                numbers[(i + 1) * 4 + j] = number;
                numbers[i * 4 + j] = 0;
                return true;
            }

        if (j > 0)
            if (numbers[i * 4 + j - 1] == 0) {
                numbers[i * 4 + j - 1] = number;
                numbers[i* 4 + j] = 0;
                return true;
            }

        if (j < 3)
            if (numbers[i * 4 + j + 1] == 0) {
                numbers[i* 4 + j + 1] = number;
                numbers[i * 4 + j] = 0;
                return true;
            }
        return false;
    }

    boolean isWin() {
        return Arrays.equals(numbers, win);
    }

    int[] getWin() {
        return win;
    }
}