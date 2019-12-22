package a_star;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClosedSet<TState extends State> {
    private ArrayList<TState> list;
    private Comparator<TState> comparator;

    public ClosedSet(Comparator<TState> comparator) {
        list = new ArrayList<>();
        this.comparator = comparator;
    }

    public boolean contains(TState state) {
        return list.contains(state);
    }

    public void add(TState state) {
        list.add(state);
    }

    public State min() {
        return Collections.min(list, comparator);
    }

    public int size() {
        return list.size();
    }
}
