package a_star;

import java.util.*;

public class A_Star<TState extends State, TRules extends Rules<TState>> {

    private TRules rules;

    public A_Star(TRules rules) {
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
    public Collection<State> search(TState startState) {
        Queue<TState> opened = new PriorityQueue<>(new minFComparator());
        List<TState> closed = new LinkedList<>();

        opened.add(startState);
        startState.setG(0);
        startState.setH(rules.getH(startState));

        while (opened.size() > 0) {
            TState currentState = opened.poll();

            if (rules.isTerminate(currentState)) {
                return completeSolution(currentState);
            }

            if (closed.contains(currentState))
                System.out.println("contain");

            closed.add(currentState);


           // System.out.println("opened: " + opened.size());
            System.out.println("closed: " + closed.size());
            //System.out.println();

            List<TState> neighbors = rules.getNeighbors(currentState);
            //System.out.println("Current: " + currentState);
            //System.out.println("************");
            for (TState neighbor: neighbors) {

                if (neighbor.equals(currentState.getParent()))
                    continue;

                if (rules.isTerminate(neighbor)) {
                    //System.out.println(closed);
                    return completeSolution(currentState);
                }

                //System.out.println(currentState.getG());
                int g = currentState.getG() + 1; // путь от начальной вершины

                int neighborG = 0;
                for (TState state: closed) {
                    if (state.equals(neighbor))
                        neighborG = state.getG();
                }
                if (g < neighborG)
                    System.out.println(true);
               // System.out.println(neighbor);
                //System.out.println("g: " + g + ", NeigG: " + neighborG);
               // if (closed.contains(neighbor) /*&& g >= neighborG /*|| neighbor.equals(currentState.getParent())*/) {
                 //   continue;
               // }

                //System.out.println(neighbor);

                if (!closed.contains(neighbor) /*|| g < neighborG*/) {
                    //System.out.println(g < neighborG);
                    neighbor.setParent(currentState);
                    neighbor.setG(g);
                    neighbor.setH(rules.getH(neighbor));
                    if (!opened.contains(neighbor) && g <= 80) {
                        opened.add(neighbor);
                    }
                }
                //System.out.println(g);
            }

           // System.out.println("************");
           // System.out.println();
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
