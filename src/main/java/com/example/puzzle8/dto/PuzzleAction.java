package com.example.puzzle8.dto;

import aima.core.agent.Action;

public class PuzzleAction implements Action {

    private String move;

    public PuzzleAction(String move) {
        this.move = move;
    }

    public String getMove() {
        return move;
    }

    @Override
    public boolean isNoOp() {
        return false;
    }
}
