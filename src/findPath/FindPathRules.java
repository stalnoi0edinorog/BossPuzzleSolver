package findPath;

import a_star.Rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindPathRules implements Rules<FindPathState> {
    private int[] actions;
    private int fieldSide;
    private int destination;
    private int positionNeighbor;

    public FindPathRules(int fieldSide) {
        if (fieldSide < 0)
            throw new IllegalArgumentException("Incorrect coordinates");
        this.fieldSide = fieldSide;
        actions = new int[] {-fieldSide, fieldSide, -1, 1};
    }

    @Override
    public List<FindPathState> getNeighbors(FindPathState currentState) {
        if (currentState.getField()[currentState.getPosition()] == 0)
            currentState.getField()[currentState.getPosition()] = 1;

        List<FindPathState> res = new ArrayList<>();
        for (int action : actions) {
            int[] field = doAction(currentState.getField(), action, currentState.getPosition(), currentState.getStep() + 1);
            if (field == null)
                continue;
            FindPathState state = new FindPathState(currentState, fieldSide, positionNeighbor);
            state.setField(field);
            state.setStep(currentState.getStep() + 1);
            res.add(state);
        }
        return res;
    }

    //применяет к состоянию правило, возвращает новое состояние
    private int[] doAction(int[] field, int action, int position, int step) {
        int number = position + action;
        positionNeighbor = number;

        if (number < 0 || number >= field.length)
            return null;
        if ((action == 1) && ((position + 1) % fieldSide == 0))
            return null;
        if ((action == -1) && ((position + 1) % fieldSide == 1))
            return null;
        if (field[number] != 0)
            return null;

        int[] newField = Arrays.copyOf(field, field.length);
        newField[number] = step;

        return newField;
    }


    @Override
    public int getH(FindPathState state) {
        return Math.abs(destination - positionNeighbor);
    }

    @Override
    public boolean isTerminate(FindPathState state) {
        return state.getPosition() == destination;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }
}
