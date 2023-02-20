package com.application.witsandgiggles.services;

import com.application.witsandgiggles.domain.Puzzle;
import com.application.witsandgiggles.domain.Solve;
import com.application.witsandgiggles.domain.User;
import com.application.witsandgiggles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final transient UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User insert(User user) throws IllegalArgumentException {
        return userRepo.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Iterable<String> getAllUsernames() {
        return userRepo.findAll().stream().map(User::getUsername).collect(Collectors.toList());
    }


    public Optional<User> getUser(Long id) {
        if (!userRepo.existsById(id)) {
            return Optional.empty();
        } else {
            return userRepo.findById(id);
        }
    }

    public Optional<User> getUser(String username) {
        return userRepo.findByUsername(username);
    }

    public User updateBio(User user, String bio) {
        user.setBio(bio);
        userRepo.save(user);
        return user;
    }

    public User updateEmail(User user, String email) {
        user.setEmail(email);
        userRepo.save(user);
        return user;
    }

    public User addCreation(User user, Puzzle puzzle) {
        user.addCreation(puzzle);
        userRepo.save(user);
        return user;
    }

    public User addSolve(User user, Solve solve) {
        user.addSolve(solve);
        userRepo.save(user);
        return user;
    }
}
