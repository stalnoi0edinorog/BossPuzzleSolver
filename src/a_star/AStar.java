package a_star;

import java.util.*;

public class AStar<TState extends State, TRules extends Rules<TState>> {

    private TRules rules;

    public AStar(TRules rules) {
        if (rules == null) {
            throw new IllegalArgumentException("Rules can't be null");
        }
        this.rules = rules;
    }

    class minFComparator implements Comparator<TState> {

        @Override
        public int compare(TState state1, TState state2) {
            return state1.getF() - state2.getF();
        }
    }

    //поиск кратчайшего пути от начального состояния до терминального
    public List<State> search(TState startState) {
        Queue<TState> opened = new PriorityQueue<>(new minFComparator());
        Map<Integer, Set<TState>> closed = new HashMap<>();

        opened.add(startState);
        startState.setG(0);
        startState.setH(rules.getH(startState));

        while (opened.size() > 0) {
            TState currentState = opened.poll();
            if (rules.isTerminate(currentState)) {
                return currentState.completeSolution();
            }
            //System.out.println(currentState + " F: " + currentState.getF());
            //System.out.println();
            int currentStateG = currentState.getG();
            closed.computeIfAbsent(currentStateG, k -> new HashSet<>());
            closed.get(currentStateG).add(currentState);
            List<TState> neighbors = rules.getNeighbors(currentState);
            //System.out.println(neighbors);
            for (TState neighbor: neighbors) {
                if (neighbor.equals(currentState.getParent()))
                    continue;
                int currentG = currentState.getG() + 1;

                TState stateG = getState(closed, neighbor);
                int previousG = 0; //если вершина уже была в opened, то её вес есть в closed
                if (stateG != null) {
                    previousG = stateG.getG();
                }
                if (currentG < previousG || stateG == null) {
                    opened.remove(neighbor);
                    neighbor.setParent(currentState);
                    neighbor.setG(currentG); // костыльная фигня для карт
                    neighbor.setH(rules.getH(neighbor));
                    if (currentG <= 80) {
                        opened.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    private TState getState(Map<Integer, Set<TState>>  closed, TState find) {
        for (TState state: closed.get(find.getG())) {
            if (state.equals(find)) {
                return state;
            }
        }
        return null;
    }
}