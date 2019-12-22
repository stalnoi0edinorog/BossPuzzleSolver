package a_star;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OpenSet<TState extends State> {
    private PriorityQueue<TState> queue;

    public OpenSet(Comparator<TState> comparator) {
        queue = new PriorityQueue<>(comparator);
    }

    public void add(TState state) {
        queue.add(state);
    }

    public void remove(TState state) {
        queue.remove(state);
    }

    public TState poll() {
        return queue.poll();
    }

    public TState getNode(TState state) {
        for (TState nextState : queue) {
            if (nextState.equals(state)) {
                return nextState;
            }
        }
        return null;
    }

    public int size() {
        return queue.size();
    }
}
