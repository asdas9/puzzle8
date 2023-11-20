package com.example.puzzle8.service.impl;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.uninformed.BreadthFirstSearch;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Component
public class PuzzleBoard {

    private static final int SIZE = 3;
    private int[][] board;
    private int emptyRow;
    private int emptyCol;


    public PuzzleBoard() {
        board = new int[SIZE][SIZE];
        int value = 1;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = value++;
            }
        }

        emptyRow = SIZE - 1;
        emptyCol = SIZE - 1;
        board[emptyRow][emptyCol] = 0;
    }
    public int[][] getBoard() {
        return board;
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int newRow = random.nextInt(SIZE);
                int newCol = random.nextInt(SIZE);
                int temp = board[i][j];
                board[i][j] = board[newRow][newCol];
                board[newRow][newCol] = temp;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    return;
                }
            }
        }
    }
    public void solveDFS2() {
        EightPuzzleBoard initialState = convertToEightPuzzleBoard(this.board);
        Problem problem = new Problem(initialState,
                EightPuzzleFunctionFactory.getActionsFunction(),
                EightPuzzleFunctionFactory.getResultFunction(),
                new EightPuzzleGoalTest());
        SearchForActions search = new BreadthFirstSearch();

        try {
            SearchAgent agent = new SearchAgent(problem, search);
            List<Action> actions = agent.getActions();
            if (actions != null && !actions.isEmpty()) {
                for (Action action : actions) {
                    applyAction(action, initialState);
                }
            }
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void solveDFS() {
            EightPuzzleBoard initialState = convertToEightPuzzleBoard(this.board);
            Problem problem = new Problem(initialState,
                    EightPuzzleFunctionFactory.getActionsFunction(),
                    EightPuzzleFunctionFactory.getResultFunction(),
                    new EightPuzzleGoalTest());
            SearchForActions search = new BreadthFirstSearch();

            try {
                SearchAgent agent = new SearchAgent(problem, search);
                List<Action> actions = agent.getActions();
                EightPuzzleBoard currentState = initialState;
                for (Action action : actions) {
                    currentState = (EightPuzzleBoard) problem.getResultFunction().result(currentState, action);
                }

                this.board = convertToBidimensionalBoard(currentState);
                printActions(agent.getActions());
                printInstrumentation(agent.getInstrumentation());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    private static void printActions(List<Action> actions) {
        for (Action value : actions) {
            String action = value.toString();
            System.out.println(action);
        }
    }
    private static void printInstrumentation(Properties properties) {
        Iterator<Object> keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }
    }

    public EightPuzzleBoard convertToEightPuzzleBoard(int[][] board) {
        int[] flatBoard = new int[board.length * board[0].length];
         int index = 0;
         for (int i = 0; i < board.length; i++) {
             for (int j = 0; j < board[i].length; j++) {
                 flatBoard[index++] = board[i][j];
              }
         }
         return new EightPuzzleBoard(flatBoard);
    }




    private void applyAction(Action action, EightPuzzleBoard board) {
        String move = action.toString();
        move = move.replaceAll("Action\\[name==|\\]", "");
        switch (move) {
            case "Up":
                board.moveGapUp();
                break;
            case "Down":
                board.moveGapDown();
                break;
            case "Left":
                board.moveGapLeft();
                break;
            case "Right":
                board.moveGapRight();
                break;
            default:
                throw new IllegalArgumentException("Movimiento no válido: " + move);
        }
    }

    private int[][] convertToBidimensionalBoard(EightPuzzleBoard eightPuzzleBoard) {
        int[] flatBoard = eightPuzzleBoard.getState();
        int[][] newBoard = new int[SIZE][SIZE];
        int flatIndex = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newBoard[j][i] = flatBoard[flatIndex++];
            }
        }
        updateEmptyLocation(newBoard);
        return newBoard;
    }

    private void updateEmptyLocation(int[][] newBoard) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (newBoard[row][col] == 0) {
                    this.emptyRow = row;
                    this.emptyCol = col;
                    return;
                }
            }
        }
    }

    public List<List<Integer>> getBoardForDisplay() {
        List<List<Integer>> displayBoard = new ArrayList<>();
        for (int[] row : this.board) {
            List<Integer> displayRow = new ArrayList<>();
            for (int cell : row) {
                displayRow.add(cell);
            }
            displayBoard.add(displayRow);
        }
        return displayBoard;
    }



}
