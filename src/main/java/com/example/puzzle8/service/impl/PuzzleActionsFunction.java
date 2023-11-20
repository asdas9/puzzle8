package com.example.puzzle8.service.impl;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ActionsFunction;
import com.example.puzzle8.dto.PuzzleAction;
import com.example.puzzle8.dto.PuzzleState;

import java.util.LinkedHashSet;
import java.util.Set;

public class PuzzleActionsFunction implements ActionsFunction {
    @Override
    public Set<Action> actions(Object state) {
        PuzzleState puzzleState = (PuzzleState) state;
        Set<Action> actions = new LinkedHashSet<>();

        int emptyRow = puzzleState.getEmptyRow();
        int emptyCol = puzzleState.getEmptyCol();

        if (emptyRow > 0) {
            actions.add(new PuzzleAction("up"));
        }

        if (emptyRow < PuzzleState.SIZE - 1) {
            actions.add(new PuzzleAction("down"));
        }

        if (emptyCol > 0) {
            actions.add(new PuzzleAction("left"));
        }

        if (emptyCol < PuzzleState.SIZE - 1) {
            actions.add(new PuzzleAction("right"));
        }

        return actions;
    }
}
