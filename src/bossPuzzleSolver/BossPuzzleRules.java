package bossPuzzleSolver;

import a_star.Rules;
import a_star.State;
import javafx.util.Pair;

import java.util.*;

public class BossPuzzleRules implements Rules<BossPuzzleState> {
    private int[] terminateState;
    private int[] actions;
    private int fieldSide;
    private Map<Integer, Pair<Integer, Integer>> map = new HashMap<>();

    //fieldSize - размер поля, кол-во клеток на одной стороне
    public BossPuzzleRules(int fieldSide, int[] terminateState) {
        if (fieldSide < 2)
            throw new IllegalArgumentException("Invalid field size");
        if (terminateState == null)
            throw new IllegalArgumentException("Terminate state can't be null");
        // размер последовательности
        if (terminateState.length != fieldSide * fieldSide)
            throw new IllegalArgumentException("Size of terminate state is incorrect");
        this.terminateState = terminateState;
        this.fieldSide = fieldSide;
        actions = new int[] {-fieldSide, fieldSide, -1, 1};

        int a = 1;
        for (int i = 0; i < fieldSide; i++) {
            for (int j = 0; j < fieldSide; j++) {
                map.put(a, new Pair<>(i, j));
                a++;
            }
        }
        map.put(0, new Pair<>(fieldSide - 1,fieldSide - 1));
    }

    @Override
    public List<BossPuzzleState> getNeighbors(BossPuzzleState currentState) {
        List<BossPuzzleState> res = new ArrayList<>();
        for (int action : actions) {
            int[] field = doAction(currentState.getField(), action);
            if (field == null)
                continue;
            BossPuzzleState state = new BossPuzzleState(currentState, fieldSide);
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
        if ((action == 1) && ((zero + 1) % fieldSide == 0))
            return null;
        if ((action == -1) && ((zero + 1) % fieldSide == 1))
            return null;

        //создаётся новый эземпляр поля, на к-м меняются местами пустая и перемещаемая клетки
        int[] newField = Arrays.copyOf(field, field.length);
        int a = newField[zero];
        newField[zero] = newField[number];
        newField[number] = a;

        return newField;
    }

    /*public int distanceSum(int[] arr, int n) {
        Arrays.sort(arr);
        int res = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            res += (arr[i] * i - sum);
            sum += arr[i];
        }
        return res;
    }*/

    @Override
    public int getH(BossPuzzleState state) {
        int h = 0;
        for (int a = 0; a < fieldSide; a++) {
            for (int b = 0; b < fieldSide; b++) {
                if (state.getField()[a * fieldSide + b] != terminateState[a * fieldSide + b]) {
                    h += manhattanDistance(state, a, b);
                }
                h += linearConflict(state, a, b);
            }
        }
        return h;
    }

    private int manhattanDistance(BossPuzzleState state, int a, int b) {
        return Math.abs(a - coordinatesCorrect(state.getField()[a * fieldSide + b]).getKey()) +
                Math.abs(b - coordinatesCorrect(state.getField()[a * fieldSide + b]).getValue());
    }
    private int linearConflict(BossPuzzleState state, int a, int b) {
        int res = 0;
        if (state.getField()[a *fieldSide + b] == 0)
            return res;
        Pair<Integer, Integer> coordinatesC = coordinatesCorrect(state.getField()[a * fieldSide + b]);

        if (a == coordinatesC.getKey()) {
            for (int i = b + 1; i < fieldSide; i++) {
                if (a == coordinatesCorrect(state.getField()[a * fieldSide + i]).getKey()) {
                    if (state.getField()[a * fieldSide + b] > state.getField()[a * fieldSide + i]) {
                        if (state.getField()[a * fieldSide + i] == 0)
                            continue;
                        res += 2;
                    }
                }
            }
        } else if (b == coordinatesC.getValue()) {
            for (int i = a + 1; i < fieldSide; i++) {
                if (b == coordinatesCorrect(state.getField()[i * fieldSide + b]).getValue()) {
                    if (state.getField()[a * fieldSide + b] > state.getField()[i * fieldSide + b]) {
                        if (state.getField()[i * fieldSide + b] == 0)
                            continue;
                        res += 2;
                    }
                }
            }
        }
        return res;
    }

    private Pair<Integer, Integer> coordinatesCorrect(int value) {
        return map.get(value);
    }

    public boolean isSolvable(int[] invariants) { //верно при условии, что "0" на последне позиции
        int counter = 0;
        for (int j = 0; j < invariants.length - 1; j++) {
            for (int i = j + 1; i < invariants.length; i++) {
                if (invariants[j] > invariants[i] && invariants[i] != 0 && invariants[j] != 0)
                    counter++;
            }
        }
        return counter % 2 == 0;
    }

    @Override
    public boolean isTerminate(BossPuzzleState state) {
        return Arrays.equals(state.getField(), terminateState);
    }
}
