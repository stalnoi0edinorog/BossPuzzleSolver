package a_star;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class OpenSet<TState extends State> extends PriorityQueue{
    private PriorityQueue<TState> queue;

    public OpenSet(Comparator<TState> comparator) {
        queue = new PriorityQueue<>(comparator);
    }
}
