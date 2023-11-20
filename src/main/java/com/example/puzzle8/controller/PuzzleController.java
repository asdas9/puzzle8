package com.example.puzzle8.controller;

import com.example.puzzle8.service.PuzzleService;
import com.example.puzzle8.service.impl.PuzzleBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PuzzleController {

    @Autowired
    private PuzzleService puzzleService;

    @Autowired
    private PuzzleBoard puzzleBoard;

    @GetMapping("/puzzle8")
    public String puzzle(Model model) {
        PuzzleBoard puzzleBoard = puzzleService.getBoard();
        model.addAttribute("board", puzzleBoard.getBoard());
        return "puzzle8";
    }

    @PostMapping("/puzzle/shuffle")
    public String shuffleBoard() {
        puzzleService.shuffleBoard();
        return "redirect:/puzzle8";
    }

    @PostMapping("/puzzle/solveDFS")
    public String solveDFS(Model model) {
        puzzleBoard.solveDFS();
        model.addAttribute("board", puzzleBoard.getBoardForDisplay());
        return "puzzle8";
    }
}
