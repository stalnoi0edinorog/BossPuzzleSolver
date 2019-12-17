package bossPuzzleSolver;

import a_star.State;

import java.util.Arrays;

public class BossPuzzleState extends State {
    private int[] field;
    private int hash;

    public BossPuzzleState(State parent) {
        super(parent);
    }

    public int[] getField() {
        return field;
    }

    public void setField(int[] field) {
        this.field = field;
        hash = Arrays.hashCode(field);
    }

    @Override
    public String toString() { //поправить
        if (field == null)
            return "" + null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                builder.append(field[j + i * 4]);
                builder.append(" ");
            }
            builder.append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BossPuzzleState))
            return false;
        return hash == o.hashCode();
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
