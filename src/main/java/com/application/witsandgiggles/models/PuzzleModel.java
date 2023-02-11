package com.application.witsandgiggles.models;

import com.application.witsandgiggles.domain.User;
import com.application.witsandgiggles.enums.PuzzleType;

public class PuzzleModel {

    private PuzzleType puzzleType;
    private User constructor;

    public PuzzleModel() {

    }

    public PuzzleModel(PuzzleType puzzleType, User user) {
        this.puzzleType = puzzleType;
        this.constructor = user;
    }

    public PuzzleType getPuzzleType() {
        return puzzleType;
    }

    public void setPuzzleType(PuzzleType puzzleType) {
        this.puzzleType = puzzleType;
    }

    public User getConstructor() {
        return constructor;
    }

    public void setConstructor(User constructor) {
        this.constructor = constructor;
    }
}
