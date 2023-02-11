package com.application.witsandgiggles.models;

import com.application.witsandgiggles.domain.Sudoku;
import com.application.witsandgiggles.domain.User;
import com.application.witsandgiggles.enums.PuzzleType;
import com.application.witsandgiggles.enums.SudokuBoxType;

public class SudokuModel extends PuzzleModel {

    private int gridSize;
    private SudokuBoxType boxType;

    public SudokuModel() {

    }

    public SudokuModel(PuzzleType puzzleType, User user, int gridSize, SudokuBoxType boxType) {
        super(puzzleType, user);
        this.gridSize = gridSize;
        this.boxType = boxType;
    }

    public Sudoku getSudoku() {
        return new Sudoku (
                this.getConstructor().getUsername() + "'s " + this.boxType + " Sudoku",
                "",
                this.getConstructor(),
                false,
                this.gridSize,
                this.boxType
        );
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public SudokuBoxType getBoxType() {
        return boxType;
    }

    public void setBoxType(SudokuBoxType boxType) {
        this.boxType = boxType;
    }
}
