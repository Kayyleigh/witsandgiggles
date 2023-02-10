package com.application.witsandgiggles.domain;

import jakarta.persistence.*;

@Entity
public class Solve { //TODO: Create a repo and stuff

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int duration; // in seconds

    @ManyToOne
    private User solver;

    @OneToOne
    private Puzzle puzzle;

}
