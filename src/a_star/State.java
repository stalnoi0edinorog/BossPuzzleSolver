package a_star;

// Вершина графа решений

public abstract class State {
    private int g;
    private int h;
    private State parent;

    public State(State parent) {
        this.parent = parent;
    }

    //Возвращает вес состояния = расстояние от нач. до тек. + эвристическая оценка
    public int getF(){
        return g + h;
    }

    //Расстояние от начального до текущего
    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    //Эвристическая оценка расстояния от текущего до термиального
    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }
}
