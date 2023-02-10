package com.application.witsandgiggles.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "appUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;
    private String bio;

    @OneToMany
    @JoinColumn(name = "constructor_id")
    private List<Puzzle> creations;

    @OneToMany
    @JoinColumn(name = "solver_id")
    private List<Solve> solves;

    public User() {

    }

    public User(String username, String bio) {
        this.username = username;
        this.bio = bio;
        this.creations = new ArrayList<>(); // this could be a Set too but I like Lists
        this.solves = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Puzzle> getCreations() {
        return creations;
    }

    public void setCreations(List<Puzzle> creations) {
        this.creations = creations;
    }

    public void addCreation(Puzzle creation) {
        this.creations.add(creation);
    }

    public List<Solve> getSolves() {
        return solves;
    }

    public void setSolves(List<Solve> solves) {
        this.solves = solves;
    }

    public void addSolve(Solve solve) {
        this.solves.add(solve);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", creations=" + creations +
                ", solves=" + solves +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
