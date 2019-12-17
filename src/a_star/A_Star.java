package a_star;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class A_Star<TState extends State, TRules extends Rules<TState>> {

    private TRules rules;

    public A_Star(TRules rules) {
        if (rules == null) {
            throw new IllegalArgumentException("Rules can't be null");
        }
        this.rules = rules;
    }

    //поиск кратчайшего пути от начального состояния до терминального
    public Collection<State> search(TState startState) {
        List<Integer> closed = new LinkedList<>();
        List<TState> opened = new LinkedList<>();

        opened.add(startState);
        startState.setG(0);
        startState.setH(rules.getH(startState));

        while (!opened.isEmpty()) {
            TState currentState = getStateWithMinF(opened);
            if (rules.isTerminate(currentState)) {
                return completeSolution(currentState);
            }

            opened.remove(currentState);
            closed.add(currentState.hashCode());

            List<TState> nextStates = rules.getNeighbors(currentState);
            for (TState state: nextStates) {
                if (closed.contains((state.hashCode()))) {
                    continue;
                }
                int g = currentState.getG() + rules.getDistance(currentState, state); //расстояние от начального до state
                boolean isGBetter;
                if (!opened.contains(state)) {
                    state.setH(rules.getH(state));
                    opened.add(state);
                    isGBetter = true;
                } else {
                    isGBetter = g < state.getG();
                }
                if (isGBetter) {
                    state.setParent(currentState);
                    state.setG(g);
                }
            }
        }
        return null;
    }

    // Вершина в open с наименьшим весом
    private TState getStateWithMinF(Collection<TState> open) {
        TState minF = null;
        int min = Integer.MAX_VALUE;
        for (TState state : open) {
            if (state.getF() < min) {
                min = state.getF();
                minF = state;
            }
        }
        return minF;
    }

    //Последовательность состояний от начального до конечного
    private Collection<State> completeSolution(TState terminate) {

        System.out.println("Win!!!");

        LinkedList<State> path = new LinkedList<>();
        State c = terminate;
        while (c != null) {
            path.addFirst(c);
            c = c.getParent();
        }
        return path;
    }
}
