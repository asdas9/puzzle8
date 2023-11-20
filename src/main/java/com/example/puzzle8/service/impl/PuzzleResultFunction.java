package com.example.puzzle8.service.impl;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ResultFunction;
import com.example.puzzle8.dto.PuzzleAction;
import com.example.puzzle8.dto.PuzzleState;


public class PuzzleResultFunction implements ResultFunction {
    @Override
    public Object result(Object o, Action action) {
        PuzzleState state = (PuzzleState) o;
        PuzzleAction puzzleAction = (PuzzleAction) action;

        int emptyRow = state.getEmptyRow();
        int emptyCol = state.getEmptyCol();
        int[][] newBoard = deepCopyBoard(state.getBoard());

        switch (puzzleAction.getMove()) {
            case "up":
                emptyRow--;
                break;
            case "down":
                emptyRow++;
                break;
            case "left":
                emptyCol--;
                break;
            case "right":
                emptyCol++;
                break;
            default:
                return state;
        }

        if (isValidPosition(emptyRow, emptyCol, newBoard)) {
            newBoard[state.getEmptyRow()][state.getEmptyCol()] = newBoard[emptyRow][emptyCol];
            newBoard[emptyRow][emptyCol] = 0;
            return new PuzzleState(newBoard, emptyRow, emptyCol);
        } else {
            return state;
        }
    }

    private int[][] deepCopyBoard(int[][] original) {
        if (original == null) {
            return null;
        }
        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].clone();
        }
        return result;
    }

    private boolean isValidPosition(int row, int col, int[][] board) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}
