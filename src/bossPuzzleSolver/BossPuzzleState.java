package bossPuzzleSolver;

import a_star.State;

import java.util.Arrays;

public class BossPuzzleState extends State {
    private int[] field;

    public BossPuzzleState(State parent) {
        super(parent);
    }

    public int[] getField() {
        return field;
    }

    public void setField(int[] field) {
        this.field = field;
    }

    @Override
    public String toString() { //поправить
        if (field == null)
            return "" + null;
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
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
    public boolean equals(Object obj){
        if (obj == null || getClass() != obj.getClass())
            return false;
        for (int i = 0; i < field.length; i++) {
            if (field[i] != ((BossPuzzleState) obj).getField()[i])
                return false;
        }
        return true;
    }
}
