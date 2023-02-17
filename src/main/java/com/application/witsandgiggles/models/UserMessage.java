package com.application.witsandgiggles.models;

import com.application.witsandgiggles.domain.Puzzle;
import com.application.witsandgiggles.domain.Solve;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessage {

    private String username;
    private String email;
    private String bio;

    private List<Puzzle> creations;
    private List<Solve> solves;


}
