package a_star;

//Специфичные правила решения задачи

import java.util.List;

public interface Rules<TState extends State> {

    List<TState> getNeighbors(TState currentState);

    int getH(TState state);

    boolean isTerminate(TState state);
}
