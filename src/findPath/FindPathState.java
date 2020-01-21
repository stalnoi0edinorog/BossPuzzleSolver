package findPath;

import a_star.State;

import java.util.ArrayList;
import java.util.List;

public class FindPathState extends State {
    private int[] field = new int[100];
    private int position;
    private int step;

    public FindPathState(State parent, int fieldSide, int position) {
        super(parent, fieldSide);
        this.position = position;
        step = 1;
    }

    int[] getField() {
        return field;
    }

    public void setField(int[] field) {
        this.field = field;
    }

    @Override
    public String toString() {
        if (field == null)
            return "" + null;
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (int i = 0; i < this.getFieldSide(); i++) {
            for (int j = 0; j < this.getFieldSide(); j++) {
                builder.append(field[j + i * this.getFieldSide()]);
                builder.append(" ");
            }
            builder.append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public List<State> completeSolution() {
        System.out.println(this);
        List<State> result = new ArrayList<>();
        result.add(this);
        return result;
    }

    public int getPosition() {
        return position;
    }

    void setStep(int step) {
        this.step = step;
    }

    int getStep() {
        return step;
    }
}
