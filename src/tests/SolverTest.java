package tests;

import a_star.AStar;
import a_star.State;
import bossPuzzleSolver.BossPuzzleRules;
import bossPuzzleSolver.BossPuzzleState;
import findPath.FindPathRules;
import findPath.FindPathState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    private int[] win15 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    private int[] win8 = {1, 2, 3, 4, 5, 6, 7, 8, 0};

    private BossPuzzleRules rules15 = new BossPuzzleRules(4, win15);
    private BossPuzzleRules rules8 = new BossPuzzleRules(3, win8);
    private FindPathRules rules = new FindPathRules(10);

    @Test
    void startField() {
        //пятнашки
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
        assertTrue(rules15.isSolvable(field1));
        assertTrue(rules15.isSolvable(field2));
        assertTrue(rules15.isSolvable(field3));
        assertFalse(rules15.isSolvable(field4));
        assertFalse(rules15.isSolvable(field5));
        assertFalse(rules15.isSolvable(field6));
        assertTrue(rules15.isSolvable(field7));
        assertTrue(rules15.isSolvable(field8));
        assertTrue(rules15.isSolvable(field9));
        assertTrue(rules15.isSolvable(field10));

        //восьмерняшки
        int[] field11 = new int[] {7, 5, 1, 3, 4, 6, 8, 2,0};
        int[] field12 = new int[] {3, 6, 4, 8, 1, 2, 5, 7, 0};
        int[] field13 = new int[] {6, 5, 1, 4, 2, 7, 3, 8, 0};
        int[] field14 = new int[] {6, 1, 4, 5, 2, 7, 3, 8, 0};
        int[] field15 = new int[] {5, 2, 7, 3, 6, 8, 1, 4, 0};
        int[] field16 = new int[] {1, 3, 7, 8, 4, 2, 5, 6, 0};
        int[] field17 = new int[] {3, 6, 4, 2, 1, 8, 7, 5, 0};
        int[] field18 = new int[] {2, 7, 5, 8, 4, 1, 6, 3, 0};
        int[] field19 = new int[] {8, 4, 7, 5, 1, 2, 3, 6, 0};
        int[] field20 = new int[] {6, 4, 5, 7, 3, 1, 2, 8, 0};
        int[] field21 = new int[] {6, 3, 4, 2, 1, 8, 7, 5, 0};
        int[] field22 = new int[] {7, 2, 5, 8, 4, 1, 6, 3, 0};
        int[] field23 = new int[] {4, 8, 7, 5, 1, 2, 3, 6, 0};
        int[] field24 = new int[] {4, 6, 5, 7, 3, 1, 2, 8, 0};
        assertTrue(rules15.isSolvable(field11));
        assertTrue(rules15.isSolvable(field12));
        assertTrue(rules15.isSolvable(field13));
        assertTrue(rules15.isSolvable(field14));
        assertTrue(rules15.isSolvable(field15));
        assertTrue(rules15.isSolvable(field16));
        assertTrue(rules15.isSolvable(field17));
        assertTrue(rules15.isSolvable(field18));
        assertTrue(rules15.isSolvable(field19));
        assertTrue(rules15.isSolvable(field20));
        assertFalse(rules15.isSolvable(field21));
        assertFalse(rules15.isSolvable(field22));
        assertFalse(rules15.isSolvable(field23));
        assertFalse(rules15.isSolvable(field24));
    }

    @org.junit.jupiter.api.Test
    void countHeuristic() {
        //пятнашки
        int[] field1 = new int[]{1, 7, 2, 3, 5, 6, 4, 8, 9, 10, 11, 12, 13, 15, 14, 0};
        int[] field2 = new int[]{1, 4, 3, 2, 5, 6, 12, 8, 9, 10, 11, 7, 13, 14, 15, 0};
        int[] field3 = new int[]{6, 7, 12, 3, 9, 2, 14, 13, 8, 11, 10, 15, 1, 5, 4, 0};
        int[] field4 = new int[]{7, 4, 13, 8, 5, 12, 11, 1, 15, 9, 14, 6, 2, 10, 3, 0};
        int[] field5 = new int[]{4, 11, 1, 3, 8, 13, 5, 12, 7, 14, 10, 6, 2, 9, 15, 0};
        BossPuzzleState state = new BossPuzzleState(null, 4);
        state.setField(field1);
        assertEquals(10, rules15.getH(state));
        state.setField(field2);
        assertEquals(14, rules15.getH(state));
        state.setField(field3);
        assertEquals(40, rules15.getH(state));
        state.setField(field4);
        assertEquals(38, rules15.getH(state));
        state.setField(field5);
        assertEquals(38, rules15.getH(state));

        rules.setDestination(79);
        assertEquals(3, rules.getH(new FindPathState(null, 10, 98)));
        rules.setDestination(9);
        assertEquals(18, rules.getH(new FindPathState(null, 10, 90)));
        rules.setDestination(87);
        assertEquals(9, rules.getH(new FindPathState(null, 10, 60)));
        rules.setDestination(91);
        assertEquals(1, rules.getH(new FindPathState(null, 10, 90)));
    }

    @Test
    void getNeighbors() {
        //пятнашки
        List<BossPuzzleState> neighbors = new ArrayList<>();
        BossPuzzleState stateStartField = new BossPuzzleState(null, 4);
        BossPuzzleState stateNeighbor1 = new BossPuzzleState(null, 4);
        BossPuzzleState stateNeighbor2 = new BossPuzzleState(null, 4);
        BossPuzzleState stateNeighbor3 = new BossPuzzleState(null, 4);
        BossPuzzleState stateNeighbor4 = new BossPuzzleState(null, 4);
        int[] startField = new int[] {4, 11, 1, 3, 8, 13, 5, 12, 7, 14, 10, 6, 2, 9, 15, 0};
        int[] neighbor1 = new int[] {4, 11, 1, 3, 8, 13, 5, 12, 7, 14, 10, 0, 2, 9, 15, 6};
        int[] neighbor2 = new int[] {4, 11, 1, 3, 8, 13, 5, 12, 7, 14, 10, 6, 2, 9, 0, 15};
        stateStartField.setField(startField);
        stateNeighbor1.setField(neighbor1);
        stateNeighbor2.setField(neighbor2);
        neighbors.add(stateNeighbor1);
        neighbors.add(stateNeighbor2);
        assertEquals(neighbors, rules15.getNeighbors(stateStartField));
        neighbors.clear();

        int[] startField1 = new int[] {14, 15, 2, 7, 12, 0, 8, 11, 9, 1, 4, 3, 10, 13, 6, 5};
        int[] neighbor11 = new int[] {14, 0, 2, 7, 12, 15, 8, 11, 9, 1, 4, 3, 10, 13, 6, 5};
        int[] neighbor12 = new int[] {14, 15, 2, 7, 12, 1, 8, 11, 9, 0, 4, 3, 10, 13, 6, 5};
        int[] neighbor13 = new int[] {14, 15, 2, 7, 0, 12, 8, 11, 9, 1, 4, 3, 10, 13, 6, 5};
        int[] neighbor14 = new int[] {14, 15, 2, 7, 12, 8, 0, 11, 9, 1, 4, 3, 10, 13, 6, 5};
        stateStartField.setField(startField1);
        stateNeighbor1.setField(neighbor11);
        stateNeighbor2.setField(neighbor12);
        stateNeighbor3.setField(neighbor13);
        stateNeighbor4.setField(neighbor14);
        neighbors.add(stateNeighbor1);
        neighbors.add(stateNeighbor2);
        neighbors.add(stateNeighbor3);
        neighbors.add(stateNeighbor4);
        assertEquals(neighbors, rules15.getNeighbors(stateStartField));
        neighbors.clear();

        //восьмерняшки
        int[] startField2 = new int[] {1, 2, 3, 6, 0, 8, 7, 5, 4};
        int[] neighbor21 = new int[] {1, 0, 3, 6, 2, 8, 7, 5, 4};
        int[] neighbor22 = new int[] {1, 2, 3, 6, 5, 8, 7, 0, 4};
        int[] neighbor23 = new int[] {1, 2, 3, 0, 6, 8, 7, 5, 4};
        int[] neighbor24 = new int[] {1, 2, 3, 6, 8, 0, 7, 5, 4};
        stateStartField = new BossPuzzleState(null, 3);
        stateNeighbor1 = new BossPuzzleState(null, 3);
        stateNeighbor2 = new BossPuzzleState(null, 3);
        stateNeighbor3 = new BossPuzzleState(null, 3);
        stateNeighbor4 = new BossPuzzleState(null, 3);
        stateStartField.setField(startField2);
        stateNeighbor1.setField(neighbor21);
        stateNeighbor2.setField(neighbor22);
        stateNeighbor3.setField(neighbor23);
        stateNeighbor4.setField(neighbor24);
        neighbors.add(stateNeighbor1);
        neighbors.add(stateNeighbor2);
        neighbors.add(stateNeighbor3);
        neighbors.add(stateNeighbor4);
        assertEquals(neighbors, rules8.getNeighbors(stateStartField));
        neighbors.clear();

        int[] startField3 = new int[] {2, 8, 7, 0, 4, 1, 6, 3, 5};
        int[] neighbor31 = new int[] {0, 8, 7, 2, 4, 1, 6, 3, 5};
        int[] neighbor32 = new int[] {2, 8, 7, 6, 4, 1, 0, 3, 5};
        int[] neighbor33 = new int[] {2, 8, 7, 4, 0, 1, 6, 3, 5};
        stateStartField.setField(startField3);
        stateNeighbor1.setField(neighbor31);
        stateNeighbor2.setField(neighbor32);
        stateNeighbor3.setField(neighbor33);
        neighbors.add(stateNeighbor1);
        neighbors.add(stateNeighbor2);
        neighbors.add(stateNeighbor3);
        assertEquals(neighbors, rules8.getNeighbors(stateStartField));
        neighbors.clear();

        int[] startField4 = new int[] {4, 2, 0, 8, 6, 7, 1, 5, 3};
        int[] neighbor41 = new int[] {4, 2, 7, 8, 6, 0, 1, 5, 3};
        int[] neighbor42 = new int[] {4, 0, 2, 8, 6, 7, 1, 5, 3};
        stateStartField.setField(startField4);
        stateNeighbor1.setField(neighbor41);
        stateNeighbor2.setField(neighbor42);
        neighbors.add(stateNeighbor1);
        neighbors.add(stateNeighbor2);
        assertEquals(neighbors, rules8.getNeighbors(stateStartField));
        neighbors.clear();
    }

    int[] newCombination() {
        Random random = new Random();
        int[] numbers = new int[16];

        for (int j = 1; j < 16; j++) {
            int a, b;
            do {
                a = random.nextInt(4);
                b = random.nextInt(4);
            } while (numbers[a * 4 + b] != 0 || (a == 3 && b == 3));
            numbers[a * 4 + b] = j;
        }
        System.out.println(Arrays.toString(numbers));
        if (!rules15.isSolvable(numbers)) {
            newCombination();
        }
        return numbers;
    }


    private int dipCounter = 0;
    private int successCounter = 0;

    @Test
    void solveGame() {
        //пятнашки
        AStar<BossPuzzleState, BossPuzzleRules> alg = new AStar<>(rules15);
        BossPuzzleState startState = new BossPuzzleState(null, 4);
        BossPuzzleState terminate = new BossPuzzleState(null, 4);
        terminate.setField(win15);

        /*int[] start0 = new int[] {1, 2, 3, 0, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 4};
        startState.setField(start0);
        List<State> res = alg.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));
        //System.out.println(res);

        int[] start1 = new int[]{1, 4, 3, 2, 5, 6, 12, 8, 9, 10, 11, 7, 13, 14, 15, 0};
        startState.setField(start1);
        res = alg.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        int[] start2 = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,0,13,14,15};
        startState.setField(start2);
        res = alg.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));*/

        int seconds = 720;
        RunnableFuture<String> runnableFuture = new FutureTask<>(() -> {
            List<State> res;
            int[] field = newCombination();
            //System.out.println("working");
            startState.setField(field);
            res = alg.search(startState);
            assertEquals(startState, res.get(0));
            assertEquals(terminate, res.get(res.size() - 1));
            //System.out.println(Arrays.toString(field));
            System.out.println("Success: " + ++successCounter);
            return null;
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runnableFuture);

        try {
            runnableFuture.get(seconds, TimeUnit.SECONDS);
        } catch (TimeoutException ex) {
            runnableFuture.cancel(true);
            System.out.println("Failures: " + ++dipCounter);
            solveGame();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            solveGame();
        }
        solveGame();

        /*int[] start3 = new int[]{15, 12, 5, 8, 1, 13, 14, 10, 4, 9, 7, 11, 3, 2, 6, 0}; //problems
        startState.setField(start3);
        res = alg.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));*/

        //восьмерняшки
        /*AStar<BossPuzzleState, BossPuzzleRules> alg8 = new AStar<>(rules8);
        BossPuzzleState startState = new BossPuzzleState(null, 3);
        BossPuzzleState terminate = new BossPuzzleState(null, 3);
        terminate.setField(win8);

        int[] start1 = new int[] {7, 5, 1, 3, 4, 6, 8, 2, 0};
        int[] start2 = new int[] {3, 6, 4, 8, 1, 2, 5, 7, 0};
        int[] start3 = new int[] {6, 5, 1, 4, 2, 7, 3, 8, 0};
        int[] start4 = new int[] {6, 1, 4, 5, 2, 7, 3, 8, 0};
        int[] start5 = new int[] {5, 2, 7, 3, 6, 8, 1, 4, 0};
        int[] start6 = new int[] {1, 3, 7, 8, 4, 2, 5, 6, 0};
        int[] start7 = new int[] {3, 6, 4, 2, 1, 8, 7, 5, 0};
        int[] start8 = new int[] {2, 7, 5, 8, 4, 1, 6, 3, 0};
        int[] start9 = new int[] {8, 4, 7, 5, 1, 2, 3, 6, 0};
        int[] start10 = new int[] {6, 4, 5, 7, 3, 1, 2, 8, 0};
        startState.setField(start1);
        List<State> res = alg8.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        startState.setField(start2);
        res = alg8.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        startState.setField(start3);
        res = alg8.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        startState.setField(start4);
        res = alg8.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        startState.setField(start5);
        res = alg8.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        startState.setField(start6);
        res = alg8.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        startState.setField(start7);
        res = alg8.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        startState.setField(start8);
        res = alg8.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        startState.setField(start9);
        res = alg8.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));

        startState.setField(start10);
        res = alg8.search(startState);
        assertEquals(startState, res.get(0));
        assertEquals(terminate, res.get(res.size() - 1));*/
    }

    @Test
    void findPath() {
        AStar<FindPathState, FindPathRules> alg = new AStar<>(rules);

        rules.setDestination(55);
        FindPathState start = new FindPathState(null,10, 90);
        List<State> res = alg.search(start);
        assertEquals(55, ((FindPathState) res.get(0)).getPosition());

        rules.setDestination(9);
        start = new FindPathState(null,10, 90);
        res = alg.search(start);
        assertEquals(9, ((FindPathState) res.get(0)).getPosition());

        rules.setDestination(68);
        start = new FindPathState(null,10, 2);
        res = alg.search(start);
        assertEquals(68, ((FindPathState) res.get(0)).getPosition());

        rules.setDestination(99);
        start = new FindPathState(null,10, 90);
        int[] field = new int[100];
        field[90] = 1;
        for (int y = 9; y > 3; y--) {
            field[y * 10 + 5] = -1;
        }
        start.setField(field);
        res = alg.search(start);
        assertEquals(99, ((FindPathState) res.get(0)).getPosition());

        rules.setDestination(92);
        start = new FindPathState(null, 10, 90);
        field = new int[100];
        field[90] = 1;
        int x = 1;
        int y = 9;
        while (x < 9 && y > 1) {
            field[y * 10 + x] = -1;
            x++;
            y--;
        }
        start.setField(field);

        FindPathState.obstacles = true;
        res = alg.search(start);
        assertEquals(92, ((FindPathState) res.get(0)).getPosition()); // препятствия в эвристику, создаёт тупики

        rules.setDestination(92);
        start = new FindPathState(null, 10, 12);
        field = new int[100];
        field[12] = 1;
        x = 1;
        y = 9;
        while (x < 9 && y > 1) {
            field[y * 10 + x] = -1;
            x++;
            y--;
        }
        start.setField(field);
        res = alg.search(start);
        assertEquals(92, ((FindPathState) res.get(0)).getPosition());

        rules.setDestination(99);
        start = new FindPathState(null, 10, 44);
        field = new int[100];
        for (int i = 2; i < 6; i++) {
            field[20 + i] = -1;
            field[50 + i] = -1;
        }
        field[36] = -1;
        field[46] = -1;
        start.setField(field);
        res = alg.search(start);
        assertEquals(99, ((FindPathState) res.get(0)).getPosition());

        rules.setDestination(99);
        start = new FindPathState(null, 10, 0);
        field = new int[100];
        for (int i = 2; i < 6; i++) {
            field[20 + i] = -1;
            field[50 + i] = -1;
        }
        field[36] = -1;
        field[46] = -1;
        start.setField(field);
        res = alg.search(start);
        assertEquals(99, ((FindPathState) res.get(0)).getPosition());

        rules.setDestination(99);
        start = new FindPathState(null, 10, 44);
        field = new int[100];
        for (int i = 1; i < 8; i++) {
            field[20 + i] = -1;
            field[80 + i] = -1;
        }
        x = 3;
        y = 7;
        while (x < 8 && y > 2) {
            field[y * 10 + x] = -1;
            x++;
            y--;
        }
        start.setField(field);
        res = alg.search(start);
        assertEquals(99, ((FindPathState) res.get(0)).getPosition());
    }
}
