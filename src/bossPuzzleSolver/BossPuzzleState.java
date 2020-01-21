package bossPuzzleSolver;

import a_star.State;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BossPuzzleState extends State {
    private int[] field;

    public BossPuzzleState(State parent, int fieldSide) {
        super(parent, fieldSide);
        //this.setParent(parent);
    }

    int[] getField() {
        return field;
    }

    public void setField(int[] field) {
        this.field = field;
    }

    @Override
    public String toString() {
        //System.out.println(fieldSide);
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
        System.out.println("Victory in " + this.getG() + " moves");

        LinkedList<State> path = new LinkedList<>();
        State c = this;
        while (c != null) {
            path.addFirst(c);
            c = c.getParent();
        }
        return path;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        return Arrays.equals(field, ((BossPuzzleState) obj).field);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(field);
    }
}