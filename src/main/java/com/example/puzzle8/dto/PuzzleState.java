package com.example.puzzle8.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
public class PuzzleState {
    public static final int SIZE = 3;
    private final int[][] board;
    private final int emptyRow;
    private final int emptyCol;

    public PuzzleState(int[][] board, int emptyRow, int emptyCol) {
        this.board = deepCopyBoard(board);
        this.emptyRow = emptyRow;
        this.emptyCol = emptyCol;
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

    public int getEmptyRow() {
        return emptyRow;
    }

    public int getEmptyCol() {
        return emptyCol;
    }
    public int[][] getBoard() {
        return deepCopyBoard(board);
    }


}
