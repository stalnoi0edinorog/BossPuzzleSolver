package a_star;

// Вершина графа решений

import bossPuzzleSolver.BossPuzzleState;

import java.util.Collection;
import java.util.List;

public abstract class State {
    private int g;
    private int h;
    private State parent;

    public State(State parent) {
        this.parent = parent;
    }

    public int getF(){
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

    public void setH(int h) {
        this.h = h;
    }

    public State getParent() {
        return parent;
    }
    public void setParent(State parent) {
        this.parent = parent;
    }

    public abstract List<State> completeSolution();
}
