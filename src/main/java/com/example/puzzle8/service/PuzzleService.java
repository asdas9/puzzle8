package com.example.puzzle8.service;

import com.example.puzzle8.service.impl.PuzzleBoard;

public interface PuzzleService {

    void shuffleBoard();

    PuzzleBoard getBoard();

}