package com.example.puzzle8.service.impl;

import aima.core.search.framework.problem.GoalTest;
import com.example.puzzle8.dto.PuzzleState;


public class PuzzleGoalTest implements GoalTest {
    @Override
    public boolean isGoalState(Object state) {
        PuzzleState puzzleState = (PuzzleState) state;
        int[][] board = puzzleState.getBoard();

        int value = 1;
        for (int row = 0; row < PuzzleState.SIZE; row++) {
            for (int col = 0; col < PuzzleState.SIZE; col++) {
                if (row == PuzzleState.SIZE - 1 && col == PuzzleState.SIZE - 1) {
                    if (board[row][col] != 0) {
                        return false;
                    }
                } else {
                    if (board[row][col] != value) {
                        return false;
                    }
                    value++;
                }
            }
        }
        return true;
    }
}