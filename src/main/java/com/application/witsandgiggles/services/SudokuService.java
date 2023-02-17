package com.application.witsandgiggles.services;

import com.application.witsandgiggles.domain.Puzzle;
import com.application.witsandgiggles.domain.Sudoku;
import com.application.witsandgiggles.models.SudokuModel;
import com.application.witsandgiggles.repository.SudokuRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SudokuService {


    private final transient SudokuRepository sudokuRepository;

    @Autowired
    public SudokuService(SudokuRepository sudokuRepository) {
        this.sudokuRepository = sudokuRepository;
    }

    public Sudoku insert(SudokuModel sudokuModel) throws IllegalArgumentException {
        Sudoku sudoku = sudokuModel.getSudoku();
        return sudokuRepository.save(sudoku);
    }

    public Iterable<Puzzle> getAllSudokus() {
        return sudokuRepository.findAll();
    }

    public Optional<Puzzle> getSudoku(Long id) {
        if (!sudokuRepository.existsById(id)) {
            return Optional.empty();
        } else {
            return sudokuRepository.findById(id);
        }
    }
}
