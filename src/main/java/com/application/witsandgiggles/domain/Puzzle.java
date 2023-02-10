package com.application.witsandgiggles.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Puzzle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private boolean isPublished;

    @ManyToOne
    private User constructor;

    public Puzzle() {

    }

    public Puzzle(String title, String description, User constructor, boolean isPublished) {
        this.title = title;
        this.description = description;
        this.constructor = constructor;
        this.isPublished = isPublished;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getConstructor() {
        return constructor;
    }

    public void setConstructor(User setter) {
        this.constructor = setter;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", constructor=" + constructor +
                ", isPublished=" + isPublished +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle puzzle = (Puzzle) o;
        return Objects.equals(id, puzzle.id);
    }

    //TODO: If a user creates a new puzzle, check for fraud aka is everything except id the same.
    //      This gets more complicated for variant sudoku if the order of creation differs.
    //      At least the solution matrix can be compared!

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
