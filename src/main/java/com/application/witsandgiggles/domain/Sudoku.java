package com.application.witsandgiggles.domain;

import com.application.witsandgiggles.converters.GridConverter;
import com.application.witsandgiggles.enums.PuzzleType;
import com.application.witsandgiggles.enums.SudokuBoxType;
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

    @Convert(converter = GridConverter.class)
    private int[][] boxPerCell;

    private SudokuBoxType boxType;

    public Sudoku() {
    }

    public Sudoku(String title, String description, User constructor, boolean isPublished,
                  int gridSize, SudokuBoxType boxType) {
        super(PuzzleType.SUDOKU, title, description, constructor, isPublished);
        this.gridSize = gridSize;
        this.grid = new int[gridSize][gridSize];
        this.boxPerCell = new int[gridSize][gridSize];
        this.boxType = boxType;

        StringBuilder baseRule = new StringBuilder();
        baseRule.append("Every row, column and ");
        if (this.boxType == SudokuBoxType.CLASSIC) {
            initializeBoxesClassic();
            baseRule.append((int) Math.sqrt(gridSize)).append("x").append((int) Math.sqrt(gridSize));
        } else {
            initializeBoxesJigsaw();
            baseRule.append(gridSize).append("-cell");
        }
        baseRule.append(" box contains the numbers 1 to ").append(gridSize).append(" exactly once each");
        addRuleDescription(baseRule.toString());
    }

    public void addRuleDescription(String description) {
        this.setDescription(this.getDescription() + description + "/n");
    }

    //TODO make it work for 6x6
    private void initializeBoxesClassic() {
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                this.setBoxForCell(i, j,
                        ((i / (int) Math.sqrt(this.gridSize)) * this.gridSize)
                                + (j / (int) Math.sqrt(this.gridSize))
                );
            }
        }
    }

    /**
     * Initialize every cell to have a different negative box number to allow changes by user
     */
    private void initializeBoxesJigsaw() {
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                this.setBoxForCell(i, j, (-i * this.gridSize) -j);
            }
        }
    }

    public boolean canPlace(int row, int column, int digit) {
        for (int i = 0; i < this.gridSize; i++) {
            if (row != i && this.grid[i][column] == digit) {
                return false;
            }
        }
        for (int j = 0; j < this.gridSize; j++) {
            if (column != j && this.grid[row][j] == digit) {
                return false;
            }
        }
        return canPlaceInBox(row, column, digit);
    }

    //TODO this feels like that Kruskal thing where you merge/join sets etc...
    // would be better, that lowers time complexity of this method

    // for now I will do the ugly thing
    public boolean canPlaceInBox(int row, int column, int digit) {
        int boxNumber = this.boxPerCell[row][column];
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                if (!(i == row && j == column)
                        && this.boxPerCell[i][j] == boxNumber
                        && this.grid[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
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

    public int[][] getBoxPerCell() {
        return boxPerCell;
    }

    public void setBoxPerCell(int[][] boxPerCell) {
        this.boxPerCell = boxPerCell;
    }

    public void setBoxForCell(int row, int column, int boxNumber) {
        this.boxPerCell[row][column] = boxNumber;
    }

    public SudokuBoxType getBoxType() {
        return boxType;
    }

    public void setBoxType(SudokuBoxType boxType) {
        this.boxType = boxType;
    }
}
