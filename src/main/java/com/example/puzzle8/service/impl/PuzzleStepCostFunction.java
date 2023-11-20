package com.example.puzzle8.service.impl;

import aima.core.agent.Action;
import aima.core.search.framework.problem.StepCostFunction;

public class PuzzleStepCostFunction implements StepCostFunction {
    @Override
    public double c(Object s, Action a, Object sPrime) {
        return 1.0;
    }
}
