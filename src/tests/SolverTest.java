package tests;

import a_star.AStar;
import a_star.State;
import bossPuzzleSolver.BossPuzzleRules;
import bossPuzzleSolver.BossPuzzleState;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SolverTest {
    private int[] win = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};

    BossPuzzleRules rules = new BossPuzzleRules(4, win);
    BossPuzzleState startState = new BossPuzzleState(null);
    AStar<BossPuzzleState, BossPuzzleRules> alg = new AStar<>(rules);

    @org.junit.jupiter.api.Test
    void startField() {
        int[] field1 = new int[]{1, 4, 3, 2, 5, 6, 12, 8, 9, 10, 11, 7, 13, 14, 15, 0};
        int[] field2 = new int[]{15, 12, 5, 8, 1, 13, 14, 10, 4, 9, 7, 11, 3, 2, 6, 0};
        int[] field3 = new int[]{1, 7, 2, 3, 5, 6, 4, 8, 9, 10, 11, 12, 13, 15, 14, 0};
        int[] field4 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 14, 0};
        int[] field5 = new int[]{4, 7, 3, 8, 10, 2, 12, 14, 13, 11, 9, 1, 5, 6, 15, 0};
        int[] field6 = new int[]{13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3, 12, 8, 4, 0};
        int[] field7 = new int[]{1, 4, 3, 2, 5, 6, 12, 8, 9, 10, 11, 7, 13, 14, 15, 0};
        int[] field8 = new int[]{6, 7, 12, 3, 9, 2, 14, 13, 8, 11, 10, 15, 1, 5, 4, 0};
        int[] field9 = new int[]{7, 4, 13, 8, 5, 12, 11, 1, 15, 9, 14, 6, 2, 10, 3, 0};
        int[] field10 = new int[]{4, 11, 1, 3, 8, 13, 5, 12, 7, 14, 10, 6, 2, 9, 15, 0};
        assertTrue(rules.isSolvable(field1));
        assertTrue(rules.isSolvable(field2));
        assertTrue(rules.isSolvable(field3));
        assertFalse(rules.isSolvable(field4));
        assertFalse(rules.isSolvable(field5));
        assertFalse(rules.isSolvable(field6));
        assertTrue(rules.isSolvable(field7));
        assertTrue(rules.isSolvable(field8));
        assertTrue(rules.isSolvable(field9));
        assertTrue(rules.isSolvable(field10));
    }

    @org.junit.jupiter.api.Test
    void countHeuristic() {
        int[] field1 = new int[]{1, 7, 2, 3, 5, 6, 4, 8, 9, 10, 11, 12, 13, 15, 14, 0};
        int[] field2 = new int[]{1, 4, 3, 2, 5, 6, 12, 8, 9, 10, 11, 7, 13, 14, 15, 0};
        int[] field3 = new int[]{6, 7, 12, 3, 9, 2, 14, 13, 8, 11, 10, 15, 1, 5, 4, 0};
        int[] field4 = new int[]{7, 4, 13, 8, 5, 12, 11, 1, 15, 9, 14, 6, 2, 10, 3, 0};
        int[] field5 = new int[]{4, 11, 1, 3, 8, 13, 5, 12, 7, 14, 10, 6, 2, 9, 15, 0};
        BossPuzzleState state = new BossPuzzleState(null);
        state.setField(field1);
        assertEquals(10, rules.getH(state));
        state.setField(field2);
        assertEquals(14, rules.getH(state));
        state.setField(field3);
        assertEquals(40, rules.getH(state));
        state.setField(field4);
        assertEquals(38, rules.getH(state));
        state.setField(field5);
        assertEquals(38, rules.getH(state));
    }

    @org.junit.jupiter.api.Test
    void getNeighbors() {
        List<BossPuzzleState> neighbors = new ArrayList<>();
        BossPuzzleState stateStartField = new BossPuzzleState(null);
        BossPuzzleState stateNeighbor1 = new BossPuzzleState(null);
        BossPuzzleState stateNeighbor2 = new BossPuzzleState(null);
        BossPuzzleState stateNeighbor3 = new BossPuzzleState(null);
        BossPuzzleState stateNeighbor4 = new BossPuzzleState(null);
        int[] startField = new int[] {4, 11, 1, 3, 8, 13, 5, 12, 7, 14, 10, 6, 2, 9, 15, 0};
        int[] neighbor1 = new int[] {4, 11, 1, 3, 8, 13, 5, 12, 7, 14, 10, 0, 2, 9, 15, 6};
        int[] neighbor2 = new int[] {4, 11, 1, 3, 8, 13, 5, 12, 7, 14, 10, 6, 2, 9, 0, 15};
        stateStartField.setField(startField);
        stateNeighbor1.setField(neighbor1);
        stateNeighbor2.setField(neighbor2);
        neighbors.add(stateNeighbor1);
        neighbors.add(stateNeighbor2);
        assertEquals(neighbors, rules.getNeighbors(stateStartField));
        neighbors.clear();

        int[] startField1 = new int[] {14, 15, 2, 7, 12, 0, 8, 11, 9, 1, 4, 3, 10, 13, 6, 5};
        int[] neighbor11 = new int[] {14, 0, 2, 7, 12, 15, 8, 11, 9, 1, 4, 3, 10, 13, 6, 5};
        int[] neighbor22 = new int[] {14, 15, 2, 7, 12, 1, 8, 11, 9, 0, 4, 3, 10, 13, 6, 5};
        int[] neighbor33 = new int[] {14, 15, 2, 7, 0, 12, 8, 11, 9, 1, 4, 3, 10, 13, 6, 5};
        int[] neighbor44 = new int[] {14, 15, 2, 7, 12, 8, 0, 11, 9, 1, 4, 3, 10, 13, 6, 5};
        stateStartField.setField(startField1);
        stateNeighbor1.setField(neighbor11);
        stateNeighbor2.setField(neighbor22);
        stateNeighbor3.setField(neighbor33);
        stateNeighbor4.setField(neighbor44);
        neighbors.add(stateNeighbor1);
        neighbors.add(stateNeighbor2);
        neighbors.add(stateNeighbor3);
        neighbors.add(stateNeighbor4);
        assertEquals(neighbors, rules.getNeighbors(stateStartField));
    }

    @org.junit.jupiter.api.Test
    void solveState() {
        AStar<BossPuzzleState, BossPuzzleRules> alg = new AStar<>(rules);
        BossPuzzleState startState = new BossPuzzleState(null);
        BossPuzzleState terminate = new BossPuzzleState(null);
        terminate.setField(win);

        int[] start1 = new int[]{1, 4, 3, 2, 5, 6, 12, 8, 9, 10, 11, 7, 13, 14, 15, 0};
        startState.setField(start1);
        List<State> res = alg.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        int[] start2 = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,0,13,14,15};
        startState.setField(start2);
        res = alg.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        int[] start3 = new int[]{15, 12, 5, 8, 1, 13, 14, 10, 4, 9, 7, 11, 3, 2, 6, 0}; //problems
        startState.setField(start3);
        res = alg.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));
    }
}
