package com.example.puzzle8.service.impl;

import com.example.puzzle8.service.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuzzleServiceImpl implements PuzzleService {

    @Autowired
    private PuzzleBoard puzzleBoard;

    @Override
    public void shuffleBoard() {
        puzzleBoard.shuffle();
    }

    @Override
    public PuzzleBoard getBoard() {
        return puzzleBoard;
    }

}
