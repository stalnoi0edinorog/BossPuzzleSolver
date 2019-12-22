package bossPuzzleSolver;

import a_star.Rules;
import a_star.State;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BossPuzzleRules implements Rules<BossPuzzleState> {
    private int[] terminateState;
    private int[] actions = new int[] {-4, 4, -1, 1};

    //fieldSize - размер поля, кол-во клеток на одной стороне
    public BossPuzzleRules(int fieldSize, int[] terminateState) {
        if (fieldSize < 2)
            throw new IllegalArgumentException("Invalid field size");
        if (terminateState == null)
            throw new IllegalArgumentException("Terminate state can't be null");
        // размер последовательности
        if (terminateState.length != 16)
            throw new IllegalArgumentException("Size of terminate state is incorrect");
        this.terminateState = terminateState;
    }

    //Список состояний, в к-е м/б осуществлён переход из указанного
    @Override
    public List<BossPuzzleState> getNeighbors(BossPuzzleState currentState) {
        List<BossPuzzleState> res = new ArrayList<>();
        for (int action : actions) {
            int[] field = doAction(currentState.getField(), action);
            if (field == null)
                continue;
            BossPuzzleState state = new BossPuzzleState(currentState);
            state.setField(field);
            res.add(state);
        }
        return res;
    }

    //применяет к состоянию правило, возвращает новое состояние
    private int[] doAction(int[] field, int action) {
        int zero = 0;
        for (; zero < field.length; zero++)  {
            if (field[zero] == 0)
                break;
        }
        //индекс перемещаемой клетки
        int number = zero + action;

        //проверка допустимости хода
        if (number < 0 || number >= field.length)
            return null;
        if ((action == 1) && ((zero + 1) % 4 == 0))
            return null;
        if ((action == -1) && ((zero + 1) % 4 == 1))
            return null;

        //создаётся новый эземпляр поля, на к-м меняются местами пустая и перемещаемая клетки
        int[] newField = Arrays.copyOf(field, field.length);
        int a = newField[zero];
        newField[zero] = newField[number];
        newField[number] = a;

        return newField;
    }

    // расстояние от a до b
    @Override
    public int getDistance(BossPuzzleState a, BossPuzzleState b) {
        State c = b;
        int distance = 0;
        while ((c != null) && (!c.equals(a))) {
            c = c.getParent();
            distance++;
        }
        return distance;
    }

    //эвристика
    @Override
    public int getH(BossPuzzleState state) {
        int h = 0;
        int m = 0;
        int l = 0;
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                if (state.getField()[a * 4 + b] != terminateState[a * 4 + b]) {
                   /* m += Math.abs(a - coordinatesCorrect(state.getField()[a * 4 + b]).getKey()) +
                            Math.abs(b - coordinatesCorrect(state.getField()[a * 4 + b]).getValue());*/
                    h += Math.abs(a - coordinatesCorrect(state.getField()[a * 4 + b]).getKey()) +
                            Math.abs(b - coordinatesCorrect(state.getField()[a * 4 + b]).getValue());
                }
                    int conflict;
                    if ((conflict = linearConflict(state, a, b)) > 0) {
                        //l += conflict;
                        h += conflict;
                    }
                }
            }
        //System.out.println("Manh: " + m + ", Linear: " + l);
        return h;
    }

    private int linearConflict(BossPuzzleState state, int a, int b) {
        int res = 0;
        if (state.getField()[a *4 + b] == 0)
            return res;
        Pair<Integer, Integer> coordinatesC = coordinatesCorrect(state.getField()[a * 4 + b]);

        if (a == coordinatesC.getKey()) {
            for (int i = b + 1; i < 4; i++) {
                if (a == coordinatesCorrect(state.getField()[a * 4 + i]).getKey()) {
                    if (state.getField()[a * 4 + b] > state.getField()[a * 4 + i]) {
                        if (state.getField()[a*4 + i] == 0)
                            continue;
                       // System.out.println(state.getField()[a * 4 + b] + " " + state.getField()[a * 4 + i]);
                        res += 2;
                    }
                }
            }
        } else if (b == coordinatesC.getValue()) {
            for (int i = a + 1; i < 4; i++) {
                if (b == coordinatesCorrect(state.getField()[i * 4 + b]).getValue()) {
                    if (state.getField()[a * 4 + b] > state.getField()[i * 4 + b]) {
                        if (state.getField()[i * 4 + b] == 0)
                            continue;
                        res += 2;
                    }
                }
            }
        }
        return res;
    }

    private int cornerTiles(BossPuzzleState state) { // ----> думать
        if (state.getField()[1] == 2 && state.getField()[4] == 5 && state.getField()[0] != 1) {
            return 2;
        }

        if (state.getField()[2] == 3 && state.getField()[7] == 8 && state.getField()[3] != 4) {
            return 2;
        }

        if (state.getField()[8] == 9 && state.getField()[13] == 14 && state.getField()[12] != 13) {
            return 2;
        }
        return 0;
    }

    private Pair<Integer, Integer> coordinatesCorrect(int value) {
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                if (terminateState[a * 4 + b] == value)
                    return new Pair<>(a, b);
            }
        }
        return null;
    }

    @Override
    public boolean isTerminate(BossPuzzleState state) {
        return Arrays.equals(state.getField(), terminateState);
    }
}
