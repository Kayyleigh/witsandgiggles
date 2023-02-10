package com.application.witsandgiggles.domain;

import com.application.witsandgiggles.converters.GridConverter;
import com.application.witsandgiggles.enums.PuzzleType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Arrays;

@Entity
@Table(name = "SUDOKU_PUZZLE")
public class Sudoku extends Puzzle {
    private int gridSize;

    @Convert(converter = GridConverter.class)
    private int[][] grid;

    public Sudoku() {
    }

    public Sudoku(String title, String description, User constructor, boolean isPublished, int gridSize) {
        super(PuzzleType.Sudoku, title, description, constructor, isPublished);
        this.gridSize = gridSize;
        this.grid = new int[gridSize][gridSize];
    }

    public void addRuleDescription(String description) {
        this.setDescription(this.getDescription() + description + "/n");
    }

    @Override
    public String toString() {
        return super.toString() +
                "gridSize=" + gridSize +
                ", grid=" + Arrays.toString(grid)
                ;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setCell(int i, int j, int digit) {
        this.grid[i][j] = digit;
    }
}
