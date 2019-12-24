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
        List<TState> closed = new LinkedList<>();

        opened.add(startState);
        startState.setG(0);
        startState.setH(rules.getH(startState));

        while (opened.size() > 0) {
            TState currentState = opened.poll();
            if (rules.isTerminate(currentState)) {
                return currentState.completeSolution();
            }
            closed.add(currentState);
            //System.out.println("closed: " + closed.size());
            List<TState> neighbors = rules.getNeighbors(currentState);
            for (TState neighbor: neighbors) {
                if (neighbor.equals(currentState.getParent()))
                    continue;
                int currentG = currentState.getG() + 1;
                if (rules.isTerminate(neighbor)) {
                    neighbor.setG(currentG);
                    System.out.println(closed.size());
                    return neighbor.completeSolution();
                }
               // System.out.println(neighbor);
                TState stateG = getState(closed, neighbor);
                int previousG = 0; //если вершина уже была в opened, то её вес есть в closed
                if (stateG != null) {
                    previousG = stateG.getG();
                }
                /*if (currentG < previousG) {
                    System.out.println();
                    System.out.println("neighbor: " + neighbor);
                    System.out.println("in closed: " + stateG);
                    System.out.println();
                }*/

                if (currentG < previousG || !opened.contains(neighbor)) {
                    neighbor.setParent(currentState);
                    neighbor.setG(currentG);
                    neighbor.setH(rules.getH(neighbor));
                    if (!opened.contains(neighbor) && currentG  <= 80) {
                        opened.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    private TState getState(List<TState> closed, TState find) {
        for (TState state: closed) {
            if (state.equals(find)) {
                return state;
            }
        }
        return null;
    }
}