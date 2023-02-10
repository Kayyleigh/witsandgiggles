package com.application.witsandgiggles.models;

import com.application.witsandgiggles.domain.User;

public class UserModel {

    private String username;
    private String bio;

    public UserModel() {

    }

    public UserModel(String username, String bio) {
        this.username = username;
        this.bio = bio;
    }

    public User getUser() {
        return new User(
                this.username,
                this.bio
        );
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

}
