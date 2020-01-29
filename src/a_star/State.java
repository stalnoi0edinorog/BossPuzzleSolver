package a_star;

// Вершина графа решений

import java.util.List;

public abstract class State {
    private int g;
    private int h;
    private State parent;
    private int fieldSide;


    public State(State parent, int fieldSide) {
        this.fieldSide = fieldSide;
        this.parent = parent;
    }

    int getF(){
        return g + h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    void setH(int h) {
        this.h = h;
    }

    public State getParent() {
        return parent;
    }

    void setParent(State parent) {
        this.parent = parent;
    }

    public abstract List<State> completeSolution();

    protected int getFieldSide() {
        return fieldSide;
    }
}
