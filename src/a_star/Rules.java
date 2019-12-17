package a_star;

//Специфичные правила решения задачи

import java.util.List;

public interface Rules<TState extends State> {

    //Список состояний, в к-е м/б осуществлён переход из текущего
    List<TState> getNeighbors(TState currentState);

    //Расстояние между указанными состояниями
    int getDistance(TState a, TState b);

    //Эвристическая оценка расстояния от указанного состояния до конечного
    int getH(TState state);

    boolean isTerminate(TState state);
}
